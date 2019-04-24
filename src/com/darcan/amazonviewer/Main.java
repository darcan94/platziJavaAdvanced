package com.darcan.amazonviewer;

import java.util.Date;

public class Main {
    public static void main(String[] args) {
        showMenu();
    }

    public static void showMenu() {
        int exit = 0;
        do {
            System.out.println(".::WELCOME TO AMAZONVIEWER::.");
            System.out.println("------------------------------");
            System.out.println("----.::choose an option::.----");
            System.out.println("------------------------------");
            System.out.println("\t1. VIEW MOVIES");
            System.out.println("\t2. VIEW SERIES");
            System.out.println("\t3. READ BOOKS");
            System.out.println("\t3. VIEW MAGAZINES");
            System.out.println("\t5. REPORT");
            System.out.println("\t6. REPORT TODAY");
            System.out.println("\t0. EXIT");

        } while (exit != 0);
    }

    public static void showMovies() {
    }

    public static void showSeries() {
    }

    public static void showBooks() {
    }

    public static void showMagazines() {
    }

    public static void MakeReport() {
    }

    public static void MakeReport(Date date) {
    }
}