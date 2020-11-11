package com.example.demo.service;

import com.example.demo.model.Student;
import com.example.demo.dao.StudentRepository;
import com.example.demo.web.controller.RestPreConditions.MyExceptions.MyResourceNotFoundException;
import com.example.demo.web.controller.RestPreConditions.RestPreConditions;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public List<Student> findPaginated(int page, int size) {

        final Page<Student> resultPage = this.findAllPaginatedRaw(page, size);

        if (page > resultPage.getTotalPages()) {
            throw new MyResourceNotFoundException();
        }

        return Lists.newArrayList(resultPage.getContent());
    }

    @Transactional(readOnly = true)
    public Page<Student> findAllPaginatedRaw(int page, int size) {
        return studentRepository.findAll(PageRequest.of(page, size));
    }

    public Student replaceStudent(Student newStudent, Integer id) {

        RestPreConditions.checkRequestElementNotNull(newStudent);
        RestPreConditions.checkRequestElementNotNull(newStudent.getId());
        RestPreConditions.checkIfBadRequest(newStudent.getId().equals(id),
                newStudent.getClass().getSimpleName() + " ID does not match URI ID");

        Optional<Student> optionalStudent = studentRepository.findById(id);

        if (!optionalStudent.isPresent()) {
            RestPreConditions.checkNotNull(optionalStudent.get());
        }

        Student prevStudent = optionalStudent.get();
        prevStudent.setFirstName(newStudent.getFirstName());
        prevStudent.setLastName(newStudent.getLastName());
        prevStudent.setEmailId(newStudent.getEmailId());
        prevStudent.setAge(newStudent.getAge());

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
