package com.darcan.amazonviewer.models;

import java.util.Date;

public class Book extends Publication {
    public Book(String title, Date editionDate, String editorial) {
        super(title, editionDate, editorial);
    }
}