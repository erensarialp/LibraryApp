package com.turkcell.LibraryApp.controller;

import com.turkcell.LibraryApp.entity.Book;
import com.turkcell.LibraryApp.entity.Student;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    List<Book> bookList = new ArrayList<>();

    public BookController() {
        bookList.add(new Book(1, "Java 101", "Smith", "ADMIN"));
        bookList.add(new Book(2, "Spring Boot Basics", "Jones", "STUDENT"));
        bookList.add(new Book(3, "Java Advanced", "Smith", "STUDENT"));
        bookList.add(new Book(4, "Hibernate Fundamentals", "Brown", "ADMIN"));
        bookList.add(new Book(5, "REST API Design", "Taylor", "STUDENT"));
    }


    @GetMapping
    public List<Book> getAllBooks(@RequestParam(required = false) String title,
                                  @RequestParam(required = false) String author) {

        // Eğer title ve author null veya boş/blank ise → tüm kitapları döndür
        if ((title == null || title.trim().isEmpty()) &&
                (author == null || author.trim().isEmpty())) {
            return bookList;
        }

        List<Book> filteredBooks = new ArrayList<>();
        for (Book b : bookList) {
            boolean matchesTitle = (title == null || title.trim().isEmpty())
                    || b.getTitle().toLowerCase().contains(title.trim().toLowerCase());
            boolean matchesAuthor = (author == null || author.trim().isEmpty())
                    || b.getAuthor().toLowerCase().contains(author.trim().toLowerCase());

            if (matchesTitle && matchesAuthor) {
                filteredBooks.add(b);
            }
        }
        return filteredBooks;
    }


    @GetMapping("{id}")
    public Book getBookById(@PathVariable int id){

        return getBookByIdForIndex(id);

    }

    @PostMapping
    public String addBook(@RequestBody Book book) {
        if (!"ADMIN".equalsIgnoreCase(book.getRole())) {
            return "Hata: Sadece Rolü Admin Olanlar Ekleyebilir.";
        }

        bookList.add(book);
        return "Kitap başarıyla eklendi: " + book.getTitle();
    }

    @PatchMapping("{id}")
    public Book updateBookById(@PathVariable int id,
                               @RequestBody Book book)
    {
        return null;
    }

    @DeleteMapping("{id}")
    public void deleteBookById(@PathVariable int id)
    {

    }

    public Book getBookByIdForIndex(int id){
        return bookList.get(id - 1); //IndexOutOfBounds hatasi almamak icin.
    }

    public void deleteStudent(int id){
        bookList.remove(id -1); //IndexOutOfBounds hatasi almamak icin.

    }

}
