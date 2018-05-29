/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odysseyLogic;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author bamdres16
 */
public class Song_item {
   private String album;
   private String song_name;
   private double duration;
   private String genre;
   private String artist;
   private Integer year;

    public Song_item(String album, String song_name, double duration, String genre, String artist, Integer year) {
        this.album = album;
        this.song_name = song_name;
        this.duration = duration;
        this.genre = genre;
        this.artist = artist;
        this.year = year;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getSong_name() {
        return song_name;
    }

    public void setSong_name(String song_name) {
        this.song_name = song_name;
    }

    public double getDuration() {
        return duration;
    }

    public void setDuration(double duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }
  
   
}
