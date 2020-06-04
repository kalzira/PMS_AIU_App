package com.example.pms_aiu.Models;

import com.google.firebase.database.Exclude;

public class User {
    private String FirstName, LastName, Phone,
            StudentId, Department, Email;
    private String mKey;

    public User() {
    }


    public User(String firstName, String lastName, String studentId, String email, String phone, String department) {
        FirstName = firstName;
        LastName = lastName;
        Phone = phone;
        StudentId = studentId;
        Department = department;
        Email = email;
    }


    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
    }


    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    @Exclude
    public String getmKey() {
        return mKey;
    }

    @Exclude
    public void setmKey(String mKey) {
        this.mKey = mKey;
    }
}
