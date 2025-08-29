package com.turkcell.LibraryApp.entity;

public class Borrow {
    private int id;
    private int bookId;
    private int studentId;

    public Borrow(int id, int bookId, int studentId) {
        this.id = id;
        this.bookId = bookId;
        this.studentId = studentId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookd(int bookid) {
        this.bookId = bookid;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
