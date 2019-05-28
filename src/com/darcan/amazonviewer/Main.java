package com.darcan.amazonviewer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

import com.anncode.makereport.Report;
import com.darcan.amazonviewer.models.Book;
import com.darcan.amazonviewer.models.Chapter;
import com.darcan.amazonviewer.models.Magazine;
import com.darcan.amazonviewer.models.Movie;
import com.darcan.amazonviewer.models.Serie;
import com.darcan.util.ChoiceHelper;

/**
 * <h1>AmazonViewer</h1>
 * Es un programa que permite visualizar peliculas, series,  libros y magazines. Permite generar reportes
 * <p>
 * 
 * @author Darcan
 * @version 1.0
 * @since 2018  
 * 
 */

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

            int op = ChoiceHelper.validateUserResponse(0, 6);
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
                makeReport();
                break;
            case 6:
                makeReport(new Date());
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

    static ArrayList<Movie> movies = new ArrayList<>();

    public static void showMovies() {
        if (movies.isEmpty()) 
            movies = Movie.makeMoviesList();
        
        int exit = 1;
        do {
            System.out.println();
            System.out.println("..:: MOVIES ::..");
            System.out.println();

            AtomicInteger atomicInteger = new AtomicInteger(1);
            movies.forEach(m -> System.out.println(atomicInteger.getAndIncrement()+". " + m.getTitle() + " - visto :" + m.isViewed()));
           
            System.out.println("0. Regresar al menu");
            System.out.println();
            var response = ChoiceHelper.validateUserResponse(0, movies.size());
           

            if (response == 0) {
                exit = 0;
                showMenu();
            } else {
                movies.get(response - 1).toSee();
            }
        } while (exit != 0);
    }

    static ArrayList<Serie> series = new ArrayList<>();

    public static void showSeries() {
        if(series.isEmpty())
            series = Serie.makeSeriesList();
      
            int exit = 1;
        do {
            System.out.println("\n..:: SERIES ::..\n");
            AtomicInteger atomicInteger = new AtomicInteger(1);
            series.forEach(x -> System.out.println(atomicInteger.getAndIncrement()+". " + x.getTitle() + " - visto: " + x.isViewed()));
            System.out.println("0. Regresar al menu\n");
           
            var response = ChoiceHelper.validateUserResponse(0, series.size());
            if (response == 0) {
                exit = 0;
                showMenu();
            } else {
                showChapters(series.get(response-1).getChapters());
            }
        } while (exit != 0);
    }

    public static void showChapters(ArrayList<Chapter> chaptersSelected) {
        int exit = 1;
        do {
            System.out.println();
            System.out.println("..:: CHAPTERS ::..");
            System.out.println();
            AtomicInteger atomicInteger = new AtomicInteger(1);
            chaptersSelected.stream().forEach(x -> System.out.println(atomicInteger.getAndIncrement() + ". " + x.getTitle() + " - visto: " + x.isViewed()));
            System.out.println("0. Regresar al menu");
            System.out.println();
            var response = ChoiceHelper.validateUserResponse(0, chaptersSelected.size());
           
            if (response == 0) {
                exit = 0;
                showSeries();
            } else {
                chaptersSelected.get(response-1).toSee();
            }
        } while (exit != 0);
    }

    static ArrayList<Book> books = Book.makeBookList();

    public static void showBooks() {
        int exit = 1;
        do {
            System.out.println("\n..:: BOOKS ::..\n");
            AtomicInteger atomicInteger = new AtomicInteger(1);
            books.stream().forEach(x -> System.out.println(atomicInteger.getAndIncrement()+". " + x.getTitle() + " visto: " + x.isReaded()));

            var response = ChoiceHelper.validateUserResponse(0, books.size());
            
            if (response == 0) {
                exit = 0;
                showMenu();
            } else {
                books.get(response-1).toSee();
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

            var response = ChoiceHelper.validateUserResponse(0, magazines.size());
           
            if (response == 0) {
                exit = 0;
                showMenu();
            }
        } while (exit != 0);
    }

    public static void makeReport() {
        
		Report report = new Report();
		report.setNameFile("reporte");
		report.setExtension("txt");
		report.setTitle(":: VISTOS ::");
		String contentReport = "";
		
		for (Movie movie : movies) {
			if (movie.isViewed()=="Si") {
				contentReport += movie.toString() + "\n";
				
			}
		}
		
		for (Serie serie : series) {
			if (serie.isViewed()=="Si") {
				contentReport += "\n\nViste esta serie completa: " + serie.toString() + "\n";
			}
			
			ArrayList<Chapter> chapters = serie.getChapters();
			for (Chapter chapter : chapters) {
				if (chapter.isViewed()=="Si") {
					contentReport += chapter.toString() + "\n";
					
				}
			}	
		}
		
		
		for (Book book : books) {
			if (book.isReaded()) {
				contentReport += book.toString() + "\n";
				
			}
		}

		report.setContent(contentReport);
		report.makeReport();
		System.out.println("Reporte Generado");
		System.out.println();
    }

    public static void makeReport(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd-h-m-s-S");
		String dateString = df.format(date);
		Report report = new Report();
		
		report.setNameFile("reporte" + dateString);
		report.setExtension("txt");
		report.setTitle(":: VISTOS ::");
		
		
		SimpleDateFormat dfNameDays = new SimpleDateFormat("E, W MMM Y");
		dateString = dfNameDays.format(date);
		String contentReport = "Date: " + dateString + "\n\n\n";
		
		for (Movie movie : movies) {
			if (movie.isViewed()=="Si") {
				contentReport += movie.toString() + "\n";
				
			}
		}
		
		
		for (Serie serie : series) {
			if (serie.isViewed()=="Si") {
				contentReport += "\n\nViste esta serie completa: " + serie.toString() + "\n";
			}
			
			ArrayList<Chapter> chapters = serie.getChapters();
			for (Chapter chapter : chapters) {
				if (chapter.isViewed()=="Si") {
					contentReport += chapter.toString() + "\n";
					
				}
			}
		}
		
		for (Book book : books) {
			if (book.isReaded()) {
				contentReport += book.toString() + "\n";
				
			}
		}
		report.setContent(contentReport);
		report.makeReport();
		
		System.out.println("Reporte Generado");
		System.out.println();
    }
}