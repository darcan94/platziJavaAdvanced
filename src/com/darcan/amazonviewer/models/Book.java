package com.darcan.amazonviewer.models;

import java.util.Date;

import com.darcan.util.ChoiceHelper;

import java.util.ArrayList;
/**
<h1>Movie</h1>
 * Hereda de la clase {@link Publication} 
 * Implementa metodos de la interface {@link IVisualizable} 
*/
 public class Book extends Publication implements IVisualizable {
    private int id;
    private String isbn;
    private boolean readed;
    private int timeReaded;
    ArrayList<Page> pages;

    public Book(String title, Date editionDate, String editorial, String[] authors, ArrayList<Page> pages) {
        super(title, editionDate, editorial);
        setAuthors(authors);
        this.pages = pages;
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


    public ArrayList<Page> getPages() {
        return this.pages;
    }

    public void setPages(ArrayList<Page> pages) {
        this.pages = pages;
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

        int i = 0;
        do {
            System.out.println("................");
            System.out.println("Page " + getPages().get(i).getNumber());
            System.out.println(getPages().get(i).getContent());
            System.out.println("................");
            if (i != 0) 
                System.out.println("1. Reghresar pagina");
                System.out.println("2. Siguiente pagina");
                System.out.println("0. Cerrar libro");
                System.out.println();

            var response = ChoiceHelper.validateUserResponse(0, 2);
            if (response == 2) {
                i++;
            }else if(response == 1){
                i--;
            }else if(response == 0){
                break;
            }
        } while (i < getPages().size());

        // Termine de verla
        stopToSee(dateI, new Date());
        System.out.println();
        System.out.println("Leiste: " + toString());
        System.out.println("Por: " + getTimeReaded() + " milisegundos");

    }

    public static ArrayList<Book> makeBookList() {
        ArrayList<Book> books = new ArrayList<>();
        String[] authors = new String[3];
        for (int i = 0; i < 3; i++) {
            authors[i] = "author " + i;
        }
        int pagina = 0;
        ArrayList<Page> pages = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            pagina = i + 1;
            pages.add(new Book.Page(pagina, "El contenido de la pagina " + pagina));
        }    

        for (int i = 1; i <= 5; i++) {
            books.add(new Book("Book " + i, new Date(), "editorial " + i, authors, pages));
        }

        return books;
    }
    /**
     * <H1 Aqui en la clase anidadade {@link Book} llamada {@link Page}
     * empieza el curso de avanzado de java en platzi
     */
    public static class Page{
        private int id;
        private int number;
        private String content;

        public Page(int number, String content){
            this.number = number;
            this.content = content;
        }

        public int getId(){
            return id;
        }
        public void setId(int id){
            this.id = id;
        }
        public int getNumber(){
            return number;
        }
        public void setNumber(int number){
            this.number = number;
        }
        public String getContent(){
            return content;
        }
        public void setContent(String content){
            this.content = content;
        }
    }

}
