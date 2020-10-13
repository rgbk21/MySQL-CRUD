package com.example.demo.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.StringJoiner;

// This tells Hibernate to make a table out of this class
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO, generator="native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer id;

    @NotBlank(message = "First Name cannot be empty")
    @Size(min = 2, max = 32)
    private String firstName;

    @Column(unique = false, nullable = false)
    private String lastName;

    @Min(0)
    @Max(100)
    @NotNull
    private Integer age;

    @Email
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return Objects.equal(id, student.id) &&
                Objects.equal(firstName, student.firstName) &&
                Objects.equal(lastName, student.lastName) &&
                Objects.equal(age, student.age) &&
                Objects.equal(emailId, student.emailId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, firstName, lastName, age, emailId);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("age", age)
                .add("emailId", emailId)
                .toString();
    }
}
