package com.gulamhasan.onboarding_app.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "preferences")
public class Preferences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String theme; // e.g. "light" or "dark"
    private String dashboardLayout; // e.g. "default", "compact"

    @OneToOne
    @JoinColumn(name = "user_id", unique = true)
    private User user;
}
