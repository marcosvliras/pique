package com.pique.pique.domain.entities;

import com.pique.pique.domain.entities.enums.UserType;
import jakarta.persistence.*;

@Entity(name="users")
@Table(name="users")
public class UserEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(unique = true)
    private String document;

    @Column(unique = true)
    private String email;

    private String password;

    @Column(name = "user_type")
    private UserType userType;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    // pattern constructor for jpa
    public UserEntity() {
    }

    public UserEntity(
            String firstName,
            String lastName,
            String document,
            String email,
            String password,
            UserType userType
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.document = document;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    @Override
    public String toString() {
        return String.format("""
            Customer[
                id=%d, 
                firstName='%s', 
                lastName='%s', 
                document='%s', 
                email='%s', 
                password='%s', 
                userType='%s'
            ]""",
                id, firstName, lastName, document, email, password, userType);
    }

}
