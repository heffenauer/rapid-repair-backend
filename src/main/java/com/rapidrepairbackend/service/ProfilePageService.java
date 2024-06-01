//package com.rapidrepairbackend.service;
//
//
//import com.rapidrepairbackend.entity.Admin;
//import com.rapidrepairbackend.entity.User;
//import org.springframework.stereotype.Service;
//
//@Service
//public class ProfilePageService {
//
//    private long id = 2;
//
//    User user = new User();
//   Admin admin = new Admin(1, "", "", "");
//
//
//    public User currentUser() {
//        return user;
//
//    }
//
//    public Admin currentAdmin() {
//        return admin;
//    }
//
//    public User updateCurrentUserInformation(User newUser) {
//        User currentUser = this.currentUser();
//        if (newUser.getFirstName() == "" || newUser.getLastName() == "" || newUser.getEmail() == "" || newUser.getPassword() == "") {
//            System.out.println("Please enter all details");
//        } else {
//
//
//            currentUser.setFirstName(newUser.getFirstName());
//            currentUser.setLastName(newUser.getLastName());
//            currentUser.setEmail(newUser.getEmail());
//            currentUser.setPassword(newUser.getPassword());
//            currentUser.setId(this.id++);
//
//        }
//        return currentUser;
//    }
//
//    public void deleteCurrentUserInformation() {
//        user.setLastName("");
//        user.setEmail("");
//        user.setPassword("");
//        user.setFirstName("");
//        user.setId(0);
//    }
//
//    public String checkIfAllInformationExists() {
//        if (this.user.getId() == 0 || this.user.getFirstName() == null || this.user.getLastName() == null || this.user.getEmail() == null || this.user.getPassword() == null) {
//            return "Please enter missing user details.";
//        } else return "All information exists.";
//    }
//
//    public Admin loginAdmin(Admin newAdmin) {
//        if ((newAdmin.getUsername().equals("Admin") || newAdmin.getEmail().equals("admin@admin.com")) && newAdmin.getPassword().equals("admin123")) {
//            deleteCurrentUserInformation();
//            admin = newAdmin;
//            return this.currentAdmin();
//
//        } else {
//            System.out.println("Wrong username/email or password.");
//        }
//        return null;
//    }
//}
