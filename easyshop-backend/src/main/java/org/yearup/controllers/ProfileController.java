package org.yearup.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yearup.data.ProfileDao;
import org.yearup.data.UserDao;
import org.yearup.models.Profile;

import java.security.Principal;

@RestController
@RequestMapping("/profile")
public class ProfileController {

    ProfileDao profileDb;
    UserDao userDB;

    @Autowired
    public ProfileController(ProfileDao profileDb, UserDao userDB) {
        this.profileDb = profileDb;
        this.userDB = userDB;
    }

    @GetMapping
    public Profile getUserProfile(Principal principal){
        String username = principal.getName();
        int userId = userDB.getIdByUsername(username);

        return profileDb.getUserById(userId);
    }

}
