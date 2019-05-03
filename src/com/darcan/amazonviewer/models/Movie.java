package com.darcan.amazonviewer.models;

import java.util.Date;
import java.util.ArrayList;
/**
 * <h1>Movie</h1>
 * Hereda de la clase {@link Film} 
 * Implementa metodos de la interface {@link IVisualizable}  
 */
public class Movie extends Film implements IVisualizable {
	private int id;
	private int timeViewed;

	public Movie(String title, String genre, String creator, int duration, short year) {
		super(title, genre, creator, duration);
		setYear(year);
	}

	public int getId() {
		return this.id;
	}

	public int getTimeViewed() {
		return this.timeViewed;
	}

	public void setTimeViewed(int timeViewed) {
		this.timeViewed = timeViewed;
	}

	@Override
	public String toString() {
		return "\n.::MOVIE::." + "\n Titulo: " + getTitle() + "\n Genero: " + getGenre() + "\n year: " + getYear()
				+ "\n Creador: " + getCreator() + "\n Duracion: " + getDuration();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Date startToSee(Date dateI) {
		return dateI;
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void stopToSee(Date dateI, Date dateF) {
		if (dateF.getTime() > dateI.getTime())
			setTimeViewed((int) (dateF.getTime() - dateI.getTime()));
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void toSee() {
		setViewed(true);
		Date dateI = startToSee(new Date());
		for (int i = 0; i < 10000; i++) {
			System.out.println(".........");
		}
		stopToSee(dateI, new Date());
		System.out.println();
		System.out.println("Viste: " + toString());
		System.out.println("Por: " + getTimeViewed() + " milisegundos");
	}

	public static ArrayList<Movie> makeMoviesList() {
		ArrayList<Movie> movies = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			movies.add(new Movie("Movie" + i, "Genero" + i, "Creador" + i, 120 + i, (short) (2016 + i)));
		}
		return movies;
	}

}