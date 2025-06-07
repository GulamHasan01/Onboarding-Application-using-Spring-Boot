package com.gulamhasan.onboarding_app.repository;

import com.gulamhasan.onboarding_app.model.Preferences;
import com.gulamhasan.onboarding_app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PreferencesRepository extends JpaRepository<Preferences, Long> {
    Optional<Preferences> findByUser(User user);
}
