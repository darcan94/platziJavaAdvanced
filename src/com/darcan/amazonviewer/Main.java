package com.darcan.amazonviewer;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import com.darcan.amazonviewer.models.Book;
import com.darcan.amazonviewer.models.Chapter;
import com.darcan.amazonviewer.models.Magazine;
import com.darcan.amazonviewer.models.Movie;
import com.darcan.amazonviewer.models.Serie;

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

            var sc = new Scanner(System.in);
            int op = sc.nextInt();
            sc.close();
            switch (op) {
            case 1:
                showMovies();
                break;
            case 2:
                showSeries();
                break;

            case 3:
                showBooks();
                break;
            case 4:
                showMagazines();
                break;
            case 5:
                MakeReport();
                break;
            case 6:
                MakeReport(new Date());
                break;
            case 0:
                System.exit(0);
            default:
                System.out.println();
                System.out.println("....Selecciona una opcion!!....");
                System.out.println();
                exit = 1;
                break;
            }
        } while (exit != 0);

    }

    static ArrayList<Movie> movies = Movie.makeMoviesList();

    public static void showMovies() {

        int exit = 1;
        do {
            System.out.println();
            System.out.println("..:: MOVIES ::..");
            System.out.println();
            movies.stream().forEach((x) -> System.out.println(". " + x.getTitle() + " visto" + x.isViewed()));
            System.out.println("0. Regresar al menu");
            System.out.println();
            var sc = new Scanner(System.in);
            var response = sc.nextInt();
            sc.close();

            if (response == 0) {
                exit = 0;
                showMenu();
            } else {
                movies.get(response - 1).toSee();
            }
        } while (exit != 0);
    }

    static ArrayList<Serie> series = Serie.makeSeriesList();

    public static void showSeries() {
        int exit = 1;
        do {
            System.out.println();
            System.out.println("..:: SERIES ::..");
            System.out.println();
            series.stream().forEach(x -> System.out.println(". " + x.getTitle() + " visto: " + x.isViewed()));
            var sc = new Scanner(System.in);
            var response = sc.nextInt();
            sc.close();
            if (response == 0) {
                exit = 0;
                showMenu();
            } else {
                showChapters(series.get(response).getChapters());
            }
        } while (exit != 0);
    }

    public static void showChapters(ArrayList<Chapter> chaptersSelected) {
        int exit = 1;
        do {
            System.out.println();
            System.out.println("..:: CHAPTERS ::..");
            System.out.println();
            chaptersSelected.stream().forEach(x -> System.out.println(". " + x.getTitle() + " visto: " + x.isViewed()));
            var sc = new Scanner(System.in);
            var response = sc.nextInt();
            sc.close();
            if (response == 0) {
                exit = 0;
                showSeries();
            } else {
                chaptersSelected.get(response).toSee();
            }
        } while (exit != 0);
    }

    static ArrayList<Book> books = Book.makeBookList();

    public static void showBooks() {
        int exit = 1;
        do {
            System.out.println();
            System.out.println("..:: BOOKS ::..");
            System.out.println();
            books.stream().forEach(x -> System.out.println(". " + x.getTitle() + " visto: " + x.isReaded()));
            var sc = new Scanner(System.in);
            var response = sc.nextInt();
            sc.close();
            if (response == 0) {
                exit = 0;
                showMenu();
            } else {
                books.get(response).toSee();
            }
        } while (exit != 0);
    }

    static ArrayList<Magazine> magazines = Magazine.makeMagazineList();

    public static void showMagazines() {
        int exit = 1;
        do {
            System.out.println();
            System.out.println("..:: MAGAZINES ::..");
            System.out.println();
            magazines.stream().forEach(x -> System.out.println(". " + x.getTitle()));
            var sc = new Scanner(System.in);
            var response = sc.nextInt();
            sc.close();
            if (response == 0) {
                exit = 0;
                showMenu();
            }
        } while (exit != 0);
    }

    public static void MakeReport() {
    }

    public static void MakeReport(Date date) {
    }
}