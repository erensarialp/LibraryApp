package com.turkcell.LibraryApp.controller;

import com.turkcell.LibraryApp.entity.Student;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private List<Student> studentList = new ArrayList<>();

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public String register(@RequestBody Student student){
        studentList.add(student);
        return ("Kayıt Başarıyla Oluşturuldu " + student.getName());
    }

    @PostMapping("/login/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public String login(@PathVariable int id){

        Student student = getStudentById(id);
        String token = generateDummyToken(student.getName());
        student.setToken(token);

        return ("Giriş Başarıyla Yapıldı " + student.getEmail() + "\nToken:" + student.getToken());
    }

    private String generateDummyToken(String username) {
        return "token-" + username + "-" + UUID.randomUUID().toString();
    }

   public List<Student> getStudentList(){
     return studentList;
    }

    public Student getStudentById(int id){
        return studentList.get(id - 1); //IndexOutOfBounds hatasi almamak icin.
    }

    public void deleteStudent(int id){
        studentList.remove(id -1); //IndexOutOfBounds hatasi almamak icin.

    }

}
