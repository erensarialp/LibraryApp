package com.turkcell.LibraryApp.controller;


import com.turkcell.LibraryApp.entity.Book;
import com.turkcell.LibraryApp.entity.Borrow;
import com.turkcell.LibraryApp.entity.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/borrow")
public class BorrowController {

    List<Borrow> borrowList = new ArrayList<>();

    private BookController bookController;

    public BorrowController(BookController bookController) {
        this.bookController = bookController;
    }

    @PostMapping("/{bookId}")
    public String borrowBook(@PathVariable int bookId, @RequestParam int studentId) {
        Book book = bookController.getBookById(bookId);

        if(book.isBorrow() == true ){
            return "Bu Kitap Zaten Ödünç Verilmiş.";
        }else {
            Borrow borrow = new Borrow(borrowList.size() + 1, bookId, studentId);
            borrowList.add(borrow);
            book.setBorrow(true);
            return "Bu Kitap " + bookId + " bu öğrenci tarafından ödünç alındı " + studentId;
        }
    }

    // Öğrencinin kendi ödünçleri
    @GetMapping("/me")
    public List<Borrow> getMyBorrows(@RequestParam int studentId) {
        List<Borrow> myBorrows = new ArrayList<>();
        for (Borrow b : borrowList) {
            if (b.getStudentId() == studentId) {
                myBorrows.add(b);
            }
        }
        return myBorrows;
    }

    @PostMapping("/{bookId}/return")
    public String returnBook(@PathVariable int bookId, @RequestParam int studentId) {
        Borrow toRemove = null;

        List<Book> bookList = bookController.bookList;

        // borrowList içinde studentId ve bookId eşleşen kaydı bul
        for (Borrow b : borrowList) {
            if (b.getBookId() == bookId && b.getStudentId() == studentId) {
                toRemove = b;
                break;
            }
        }

        if (toRemove != null) {
            borrowList.remove(toRemove);

            // Kitap listesinden bookId ile kitabı bul ve isBorrowed false yap
            for (Book book : bookList) { // bookList: BookController veya in-memory kitap listesi
                if (book.getId() == bookId) {
                    book.setBorrow(false);
                    break;
                }
            }

            return "Bu Kitap " + bookId + " öğrenci tarafından iade edildi " + studentId;
        } else {
            return "Bu Kitaba Ait Ödünç Durumu Bulunamadı " + bookId + " Öğrenci Id " + studentId;
        }
    }


}
