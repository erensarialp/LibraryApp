package com.turkcell.LibraryApp.controller;

import com.turkcell.LibraryApp.entity.Borrow;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private BorrowController borrowController;
    private BookController bookController;
    private StudentController studentController;

    public AdminController(BorrowController borrowController, BookController bookController, StudentController studentController) {
        this.borrowController = borrowController;
        this.bookController = bookController;
        this.studentController = studentController;
    }

    @GetMapping("/borrows")
    public List<Borrow> getBorrowsList(){
        return borrowController.borrowList;

    }

    @GetMapping("/stats")
    public String status(){

        return "Toplam Kitap Sayısı: " +bookController.bookList.size() + "\nAktif Öğrenci Sayısı: " +
                                    studentController.getAllStudents().size() +
                                    "\nÖdünçteki Kitap Sayısı: " + borrowController.borrowList.size();
    }
}
