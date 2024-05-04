package com.rapidrepairbackend;


import org.springframework.stereotype.Service;

@Service
public class ProfilePageService {

    private long id = 2;

    User user = new User(id++, "Adi", "Okeric", 22, "adi.okeric@stu.ssst.edu.ba", "adi123");
    Admin admin = new Admin(1, "", "", "");


    public User currentUser() {
        return user;

    }

    public Admin currentAdmin() {
        return admin;
    }

    public User updateCurrentUserInformation(User newUser) {
        User currentUser = this.currentUser();
        if (newUser.getName() == "" || newUser.getSurname() == "" || newUser.getAge() == 0 || newUser.getEmail() == "" || newUser.getPassword() == "") {
            System.out.println("Please enter all details");
        } else {


            currentUser.setName(newUser.getName());
            currentUser.setSurname(newUser.getSurname());
            currentUser.setAge(newUser.getAge());
            currentUser.setEmail(newUser.getEmail());
            currentUser.setPassword(newUser.getPassword());
            currentUser.setId(this.id++);

        }
        return currentUser;
    }

    public void deleteCurrentUserInformation() {
        user.setAge(0);
        user.setSurname("");
        user.setEmail("");
        user.setPassword("");
        user.setName("");
        user.setId(0);
    }

    public String checkIfAllInformationExists() {
        if (this.user.getId() == 0 || this.user.getName() == null || this.user.getSurname() == null || this.user.getAge() == 0 || this.user.getEmail() == null || this.user.getPassword() == null) {
            return "Please enter missing user details.";
        } else return "All information exists.";
    }

    public Admin loginAdmin(Admin newAdmin) {
        if ((newAdmin.getUsername().equals("Admin") || newAdmin.getEmail().equals("admin@admin.com")) && newAdmin.getPassword().equals("admin123")) {
            deleteCurrentUserInformation();
            admin = newAdmin;
            return this.currentAdmin();

        } else {
            System.out.println("Wrong username/email or password.");
        }
        return null;
    }
}
