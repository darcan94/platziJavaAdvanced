package com.darcan.amazonviewer.models;

import java.util.Date;
import java.util.ArrayList;

public class Book extends Publication implements IVisualizable {
    private int id;
    private String isbn;
    private boolean readed;
    private int timeReaded;

    public Book(String title, Date editionDate, String editorial, String[] authors) {
        super(title, editionDate, editorial);
    }

    public int getId() {
        return this.id;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isReaded() {
        return this.readed;
    }

    public void setReaded(boolean readed) {
        this.readed = readed;
    }

    public int getTimeReaded() {
        return this.timeReaded;
    }

    public void setTimeReaded(int timeReaded) {
        this.timeReaded = timeReaded;
    }

    @Override
    public String toString() {

        String detailBook = "\n :: BOOK ::" + "\n Title: " + getTitle() + "\n Editorial: " + getEditorial()
                + "\n Edition Date: " + getEditionDate() + "\n Authors: ";
        for (int i = 0; i < getAuthors().length; i++) {
            detailBook += "\t" + getAuthors()[i] + " ";
        }
        return detailBook;
    }

    @Override
    public void stopToSee(Date dateI, Date dateF) {
        if (dateF.getTime() > dateI.getTime()) {
            setTimeReaded((int) (dateF.getTime() - dateI.getTime()));
        } else {
            setTimeReaded(0);
        }
    }

    @Override
    public Date startToSee(Date dateI) {
        return dateI;
    }

    public void toSee() {

        setReaded(true);
        Date dateI = startToSee(new Date());

        for (int i = 0; i < 100000; i++) {
            System.out.println("..........");
        }

        // Termine de verla
        stopToSee(dateI, new Date());
        System.out.println();
        System.out.println("Leíste: " + toString());
        System.out.println("Por: " + getTimeReaded() + " milisegundos");

    }

    public static ArrayList<Book> makeBookList() {
        ArrayList<Book> books = new ArrayList<>();
        String[] authors = new String[3];
        for (int i = 0; i < 3; i++) {
            authors[i] = "author " + i;
        }
        for (int i = 1; i <= 5; i++) {
            books.add(new Book("Book " + i, new Date(), "editorial " + i, authors));
        }

        return books;
    }

}
