package com.alouane.ayoub.exam.services;

import com.alouane.ayoub.exam.entities.AppRole;
import com.alouane.ayoub.exam.entities.AppUser;

public interface AccountService {
    AppUser addNewUser(AppUser appUser);
    AppRole addNewRole(AppRole appRole);
    void addRoleToUser(String username, String roleName);
    AppUser loadUserByUsername(String username);
}
