package com.gulamhasan.onboarding_app.service;

import com.gulamhasan.onboarding_app.model.Preferences;
import com.gulamhasan.onboarding_app.model.User;
import com.gulamhasan.onboarding_app.repository.PreferencesRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PreferencesService {

    private final PreferencesRepository preferencesRepository;

    public PreferencesService(PreferencesRepository preferencesRepository) {
        this.preferencesRepository = preferencesRepository;
    }

    public Preferences saveOrUpdatePreferences(User user, Preferences newPrefs) {
        Optional<Preferences> existingPrefsOpt = preferencesRepository.findByUser(user);

        if (existingPrefsOpt.isPresent()) {
            Preferences existingPrefs = existingPrefsOpt.get();
            existingPrefs.setTheme(newPrefs.getTheme());
            existingPrefs.setDashboardLayout(newPrefs.getDashboardLayout());
            return preferencesRepository.save(existingPrefs);
        } else {
            newPrefs.setUser(user);
            return preferencesRepository.save(newPrefs);
        }
    }

    public Optional<Preferences> getPreferencesByUser(User user) {
        return preferencesRepository.findByUser(user);
    }
}
