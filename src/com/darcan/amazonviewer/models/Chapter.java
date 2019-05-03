package com.darcan.amazonviewer.models;

import java.util.ArrayList;
/**
 * <h1>Chapter</h1>
 * Hereda de la clase {@link Movie}
 * @see Film
 */
public class Chapter extends Movie {
    private int id;
    private int sessionNumber;
    private Serie serie;

    public Chapter(String title, String genre, String creator, int duration, short year, int sessionNumber,
            Serie serie) {
        super(title, genre, creator, duration, year);
        this.sessionNumber = sessionNumber;
        this.serie = serie;
    }

    public int getId() {
        return this.id;
    }

    public int getSessionNumber() {
        return this.sessionNumber;
    }

    public void setSessionNumber(int sessionNumber) {
        this.sessionNumber = sessionNumber;
    }

    public Serie getSerie() {
        return this.serie;
    }

    public void setSerie(Serie serie) {
        this.serie = serie;
    }

    @Override
    public String toString() {
        return "\n.::CHAPTER::." + "\nTitulo: " + getTitle() + "\nyear: " + getYear() + "\nCreador: " + getCreator()
                + "\nDuration: " + getDuration() + "\n.::SERIE::." + "\nSerie: " + getSerie().getTitle();
    }

    public static ArrayList<Chapter> makeChapterList(Serie serie) {
        ArrayList<Chapter> chapters = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            chapters.add(new Chapter("Capitulo" + i, "Genero" + i, "Creador " + i, 45, (short) (2016 + i), i, serie));
        }
        return chapters;
    }

    @Override
    public void toSee() {
        super.toSee();
        ArrayList<Chapter> chapters = getSerie().getChapters();
        int chapterViewedConter = 0;
        for (Chapter chapter : chapters) {
            if (chapter.isViewed() == "Si")
                chapterViewedConter++;
        }
        if (chapterViewedConter == chapters.size())
            getSerie().setViewed(true);
    }
}