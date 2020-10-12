package com.example.demo.model;

import com.sun.istack.NotNull;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.StringJoiner;

// This tells Hibernate to make a table out of this class
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer id;
    @NotBlank(message = "First Name cannot be empty")
    private String firstName;
    @Column(unique = false, nullable = false)
    private String lastName;
    private String emailId;

    public Integer getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailId() {
        return emailId;
    }

    /*
    Primary key values never change, so you shouldn't allow modification of the identifier property column

    public void setId(Integer id) {
        this.id = id;
    }

    */

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Student.class.getSimpleName() + "[", "]")
                .add("id=" + id)
                .add("firstName='" + firstName + "'")
                .add("lastName='" + lastName + "'")
                .add("emailId='" + emailId + "'")
                .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return com.google.common.base.Objects.equal(id, student.id) &&
                com.google.common.base.Objects.equal(firstName, student.firstName) &&
                com.google.common.base.Objects.equal(lastName, student.lastName) &&
                com.google.common.base.Objects.equal(emailId, student.emailId);
    }

    @Override
    public int hashCode() {
        return com.google.common.base.Objects.hashCode(id, firstName, lastName, emailId);
    }
}
