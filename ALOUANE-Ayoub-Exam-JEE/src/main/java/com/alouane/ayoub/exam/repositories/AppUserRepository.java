package com.alouane.ayoub.exam.repositories;

import com.alouane.ayoub.exam.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findByUsername(String username);
}
