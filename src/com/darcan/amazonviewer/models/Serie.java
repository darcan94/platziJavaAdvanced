package com.darcan.amazonviewer.models;

import java.util.ArrayList;
import java.util.Date;

public class Serie extends Film {
    private int id;
    private int sessionQuantity;
    private ArrayList<Chapter> chapters;

    public Serie(String title, String genre, String creator, int duration, int sessionQuantity) {
        super(title, genre, creator, duration);
        this.sessionQuantity = sessionQuantity;
    }

    public int getId() {
        return this.id;
    }

    public int getSessionQuantity() {
        return this.sessionQuantity;
    }

    public void setSessionQuantity(int sessionQuantity) {
        this.sessionQuantity = sessionQuantity;
    }

    public ArrayList<Chapter> getChapters() {
        return this.chapters;
    }

    public void setChapters(ArrayList<Chapter> chapters) {
        this.chapters = chapters;
    }

    @Override
    public String toString() {
        return "\n.::SERIE::." + "\nTitulo: " + getTitle() + "\nGenero: " + getGenre() + "\nAño: " + getYear()
                + "\nCreador: " + getCreator() + "\nDuracion: " + getDuration();
    }

    public static ArrayList<Serie> makeSeriesList() {
        ArrayList<Serie> series = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Serie serie = new Serie("Serie " + i, "Genero" + i, "Creador" + i, 1200, 5);
            serie.setChapters(Chapter.makeChapterList(serie));
            series.add(serie);
        }
        return series;
    }

    @Override
    public void toSee() {
        setViewed(true);
    }
}