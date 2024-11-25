package com.example.emazon_aux.domain.model;

import java.time.LocalDate;

public class UserModel {

    private Long id;
    private String name;
    private String lastName;
    private String password;
    private String email;
    private Long idRole;
    private String document;
    private String phone;
    private LocalDate birthday;

    public UserModel() {
    }

    public UserModel(Long id, String name, String lastName, String password,
                     String email, Long idRole, String document, String phone, LocalDate birthday) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.idRole = idRole;
        this.document = document;
        this.phone = phone;
        this.birthday = birthday;
        //this.roleModel = roleModel;
    }

    public UserModel(long l, String s, String s1, String s2, String s3, LocalDate of, String s4, String s5, RoleModel roleModel) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    /*public RoleModel getRoleModel() {
        return roleModel;
    }

    public void setRoleModel(RoleModel roleModel) {
        this.roleModel = roleModel;
    }*/
}
