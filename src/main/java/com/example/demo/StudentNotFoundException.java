package com.example.demo;

public class StudentNotFoundException extends RuntimeException {

    StudentNotFoundException(Integer id) {
        super("Could not find student " + id);
    }

}
