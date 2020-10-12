package com.example.demo.web.controller;

public class StudentNotFoundException extends RuntimeException {

    StudentNotFoundException(Integer id) {
        super("Could not find student " + id);
    }

}
