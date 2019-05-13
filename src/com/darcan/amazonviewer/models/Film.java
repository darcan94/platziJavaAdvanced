package com.darcan.amazonviewer.models;
/**
 * <h1>Film</h1>
 * Es una superclase abstracta
 * <p>
 * Es la clase base de la familia films, movies y series. Contiene el metodo {@code view()}
 * que es obligatorio implementar a toda clase que herede de Films
 * 
 * @author Darcan
 * @version 1.0
 * @since 2019
 */
public abstract class Film {
    private String title;
    private String genre;
    private String creator;
    private int duration;
    private short year;
    private boolean viewed;

    public Film(){}
    public Film(String title, String genre, String creator, int duration) {
        super();
        this.title = title;
        this.genre = genre;
        this.creator = creator;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public short getYear() {
        return year;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public String isViewed() {
        String visto = "";
        if (viewed == true) {
            visto = "Si";
        } else {
            visto = "No";
        }

        return visto;
        //return visto = viewed ? "Si": "No";
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }
    /**
     * {@code view()} metodo abstracto, las clases hijas estan obligadas a implementarlo
     * a menos que estas sean abstractas tambien
     */
    public abstract void toSee();

}