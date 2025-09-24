package com.kkettch.secure_api.repository;

import com.kkettch.secure_api.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
    Optional<Profile> findByUserUsername(String username);
}
