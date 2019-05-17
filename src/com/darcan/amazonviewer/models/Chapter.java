package com.darcan.amazonviewer.models;

import java.util.ArrayList;
import java.util.Date;

import com.darcan.amazonviewer.dao.ChapterDao;
/**
 * <h1>Chapter</h1>
 * Hereda de la clase {@link Movie}
 * @see Film
 */
public class Chapter extends Movie implements ChapterDao
{
    private int id;
    private short sessionNumber;
    private Serie serie;

    public Chapter(){}

    public Chapter(String title, String genre, String creator, int duration, short year, short sessionNumber, Serie serie)
    {
        super(title, genre, creator, duration, year);
        this.sessionNumber = sessionNumber;
        this.serie = serie;
    }

    public int getId()
    {
        return this.id;
    }

    public int getSessionNumber() 
    {
        return this.sessionNumber;
    }

    public void setSessionNumber(short sessionNumber) 
    {
        this.sessionNumber = sessionNumber;
    }

    public Serie getSerie() 
    {
        return this.serie;
    }

    public void setSerie(Serie serie) 
    {
        this.serie = serie;
    }

    @Override
    public String toString() 
    {
        return "\n.::CHAPTER::." + "\nTitulo: " + getTitle() + "\nyear: " + getYear() + "\nCreador: " + getCreator()
                + "\nDuration: " + getDuration() + "\n.::SERIE::." + "\nSerie: " + getSerie().getTitle();
    }

    public static ArrayList<Chapter> makeChapterList(Serie serie) 
    {
      return new Chapter().readChapter(serie);
    }

    @Override
    public void toSee() 
    {   
        setViewed(true);
        Date dateI = startToSee(new Date());
        for (int i = 0; i < 100; i++) {
            System.out.println(".........");
        }
       // super.toSee();
        ArrayList<Chapter> chapters = getSerie().getChapters();
        int chapterViewedConter = 0;
        for (Chapter chapter : chapters) 
        {
            if (chapter.isViewed() == "Si")
                chapterViewedConter++;
                chapter.setChapterViewed(chapter);
        }
        if (chapterViewedConter == chapters.size())
            getSerie().setSerieViewed(serie);
        
            
            
            stopToSee(dateI, new Date());
            System.out.println();
            System.out.println("Viste: " + toString());
            System.out.println("Por: " + getTimeViewed() + " milisegundos");
    }
}