package com.darcan.amazonviewer.models;

import java.util.ArrayList;

import com.darcan.amazonviewer.dao.SerieDao;

public class Serie extends Film implements SerieDao{
    private int id;
    private int sessionQuantity;
    private ArrayList<Chapter> chapters;

    public Serie(){}

    public Serie(String title, String genre, String creator, int duration, int sessionQuantity) {
        super(title, genre, creator, duration);
        this.sessionQuantity = sessionQuantity;
    }


    public int getId() {
        return this.id;
    }


    public void setId(int id) {
        this.id = id;
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
        return "\n.::SERIE::." + "\nTitulo: " + getTitle() + "\nGenero: " + getGenre() + "\nyear: " + getYear()
                + "\nCreador: " + getCreator() + "\nDuracion: " + getDuration();
    }

    public static ArrayList<Serie> makeSeriesList() 
    {
            return new Serie().read();    
    }

    @Override
    public void toSee() {
        setViewed(true);
    }
}