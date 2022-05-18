package com.huedge.movie.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movie")
@Data
@NoArgsConstructor
public class Movie {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "imdb_title_id")
	private String imdbTitleId;
	private String title;
	@Column(name = "original_title")
	private String originalTitle;
	private int year;
	private String date_published;
	private String genre;
	private int duration;
	private String country;
	private String language;
	private String director;
	private String writer;
	@Column(name = "production_company")
	private String productionCompany;
	@Column(length=600)
	private String actors;
	private String description;
	private String avg_vote;
	private int votes;
	private String budget;
	private String usa_gross_income;
	private String worlwide_gross_income;
	private int metascore;
	private int reviews_from_users;
	private int reviews_from_critics;
	public Movie(String imdbTitleId, String title,
			String originalTitle, int year, String date_published, String genre,
			int duration, String country, String language, String director,
			String writer, String productionCompany, String actors,
			String description, String avg_vote, int votes, String budget,
			String usa_gross_income, String worlwide_gross_income,
			int metascore, int reviews_from_users, int reviews_from_critics) {
		this.imdbTitleId = imdbTitleId;
		this.title = title;
		this.originalTitle = originalTitle;
		this.year = year;
		this.date_published = date_published;
		this.genre = genre;
		this.duration = duration;
		this.country = country;
		this.language = language;
		this.director = director;
		this.writer = writer;
		this.productionCompany = productionCompany;
		this.actors = actors;
		this.description = description;
		this.avg_vote = avg_vote;
		this.votes = votes;
		this.budget = budget;
		this.usa_gross_income = usa_gross_income;
		this.worlwide_gross_income = worlwide_gross_income;
		this.metascore = metascore;
		this.reviews_from_users = reviews_from_users;
		this.reviews_from_critics = reviews_from_critics;
	}
	public Movie() {
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setImdbTitleId(String imdbTitleId) {
		this.imdbTitleId = imdbTitleId;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setOriginalTitle(String originalTitle) {
		this.originalTitle = originalTitle;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public void setDate_published(String date_published) {
		this.date_published = date_published;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public void setProductionCompany(String productionCompany) {
		this.productionCompany = productionCompany;
	}
	public void setActors(String actors) {
		this.actors = actors;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setAvg_vote(String avg_vote) {
		this.avg_vote = avg_vote;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
	public void setBudget(String budget) {
		this.budget = budget;
	}
	public void setUsa_gross_income(String usa_gross_income) {
		this.usa_gross_income = usa_gross_income;
	}
	public void setWorlwide_gross_income(String worlwide_gross_income) {
		this.worlwide_gross_income = worlwide_gross_income;
	}
	public void setMetascore(int metascore) {
		this.metascore = metascore;
	}
	public void setReviews_from_users(int reviews_from_users) {
		this.reviews_from_users = reviews_from_users;
	}
	public void setReviews_from_critics(int reviews_from_critics) {
		this.reviews_from_critics = reviews_from_critics;
	}
	public String getTitle() {
		return title;
	}
	
}

