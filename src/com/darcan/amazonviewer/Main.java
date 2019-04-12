package com.darcan.amazonviewer;

public class Main {
    public static void main(String[] args) {
        showMenu();
    }

    public static void showMenu() {
        int exit = 0;
        do {
            System.out.println(".::WELCOME TO AMAZONVIEWER::.");
            System.out.println("------------------------------");
            System.out.println("----.::Choice an option::.----");
            System.out.println("------------------------------");
            System.out.println("\t1-VIEW MOVIES");
            System.out.println("\t2-VIEW SERIES");
            System.out.println("\t3-READ BOOKS");
            System.out.println("\t4-Report");
            System.out.println("\t5-Report today");
            System.out.println("\t6-EXIT");

        } while (exit != 0);
    }
}