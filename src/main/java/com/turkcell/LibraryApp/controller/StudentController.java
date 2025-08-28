package com.turkcell.LibraryApp.controller;


import com.turkcell.LibraryApp.entity.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private AuthController authController;

    public StudentController(AuthController authController) {
        this.authController = authController;
    }

    @GetMapping()
    public List<Student> getAllStudents(){
        return authController.getStudentList();
    }


    @GetMapping("{id}")
    public Student getStudentById(@PathVariable int id){
        return authController.getStudentById(id);
    }

    @PatchMapping("{id}")
    public Student updateStudentById(@PathVariable int id,
                                     @RequestBody Student student)
    {
        Student updateStudent = authController.getStudentById(id);
        updateStudent.setEmail(student.getEmail());

        return updateStudent;
    }

    @DeleteMapping("{id}")
    public void deleteStudentById(@PathVariable int id)
    {
        authController.deleteStudent(id);
        System.out.println("Kaydınız Başarıyla Silindi.");
    }
}
