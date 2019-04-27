package com.darcan.amazonviewer.models;

import java.util.Date;
import java.util.ArrayList;

public class Magazine extends Publication {
    private int id;

    public Magazine(String title, Date editionDate, String editorial) {
        super(title, editionDate, editorial);
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {

        String detailMagazine = "\n :: MAGAZINE ::" + "\n Title: " + getTitle() + "\n Editorial: " + getEditorial()
                + "\n Edition Date: " + getEditionDate() + "\n Authors: ";
        for (int i = 0; i < getAuthors().length; i++) {
            detailMagazine += "\t" + getAuthors()[i] + " ";
        }
        return detailMagazine;
    }

    public static ArrayList<Magazine> makeMagazineList() {
        ArrayList<Magazine> magazines = new ArrayList<>();
        String[] authors = new String[3];
        for (int i = 0; i < authors.length; i++) {
            authors[i] = "author" + i;
        }
        for (int i = 1; i <= 5; i++) {
            magazines.add(new Magazine("Magazine " + i, new Date(), "Editorial " + i));
        }
        return magazines;
    }
}