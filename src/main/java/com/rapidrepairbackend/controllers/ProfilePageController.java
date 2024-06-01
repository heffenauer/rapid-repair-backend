//package com.rapidrepairbackend.controllers;
//
//
//import com.rapidrepairbackend.service.ProfilePageService;
//import com.rapidrepairbackend.entity.User;
//import com.rapidrepairbackend.entity.Admin;
//import org.springframework.web.bind.annotation.*;
//
//@RequestMapping("/userprofile")
//@RestController
//public class ProfilePageController {
//
//    private final ProfilePageService profileService;
//
//    public ProfilePageController(ProfilePageService profileService) {
//        this.profileService = profileService;
//    }
//
//    @GetMapping("/showUser")
//    public User currentUser() {
//        return this.profileService.currentUser();
//    }
//
//    @PutMapping("/updateInfo")
//    public User changeUser(@RequestBody User user) {
//        return this.profileService.updateCurrentUserInformation(user);
//    }
//
//    @DeleteMapping("/deleteCurrentInfo")
//    public void deleteUser() {
//        this.profileService.deleteCurrentUserInformation();
//    }
//
//    @GetMapping("/checkInfo")
//    public String checkInfo() {
//        return this.profileService.checkIfAllInformationExists();
//    }
//
//    @PutMapping("/admin")
//    public Admin logAdmin(@RequestBody Admin admin) {
//        return this.profileService.loginAdmin(admin);
//    }
//
//    @GetMapping("/currentAdmin")
//    public Admin currentAdmin() {
//        return this.profileService.currentAdmin();
//    }
//
//
//}
