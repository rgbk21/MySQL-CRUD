package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.dao.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents(){
        return studentRepository.findAll();
    }

    public Student getStudentById(Integer id){
        Optional<Student> student = studentRepository.findById(id);
        return student.orElse(null);
    }

    public Student addNewStudent(Student student){
        return studentRepository.save(student);
    }

    public Student replaceStudent(Student newStudent, Integer id) {

        Optional<Student> optionalStudent = studentRepository.findById(id);

        if (!optionalStudent.isPresent())
            return null;
//            return ResponseEntity.notFound().build();

        Student prevStudent = optionalStudent.get();
        prevStudent.setFirstName(newStudent.getFirstName());
        prevStudent.setLastName(newStudent.getLastName());
        prevStudent.setEmailId(newStudent.getEmailId());

        return studentRepository.save(prevStudent);

    }

    public boolean deleteStudentById(Integer id) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            studentRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }

}