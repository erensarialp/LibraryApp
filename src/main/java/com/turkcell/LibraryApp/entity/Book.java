package com.turkcell.LibraryApp.entity;

public class Book {
    private int id;
    private String title;
    private String author;
    private String role;
    private boolean isBorrow;


    public Book() {
    }

    public Book(int id, String title, String author, String role, boolean isBorrow) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.role = role;
        this.isBorrow = isBorrow;
    }

    // Getter ve Setterlar
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isBorrow() {
        return isBorrow;
    }

    public void setBorrow(boolean borrow) {
        isBorrow = borrow;
    }
}
