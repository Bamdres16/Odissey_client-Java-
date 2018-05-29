/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odysseyLogic;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ReproductorMp3{
    
    private static ReproductorMp3 instancia;
    private FileInputStream inputStream;
    private BufferedInputStream bufferedInputStream;
    private Player player;
    private long pausedAt, endsAt;
    private String songPath;
    private Thread current_song;
   
    private ReproductorMp3(){
        
    }
    
    public static ReproductorMp3 getSingletonInstance() {
        if (instancia == null){
            instancia = new ReproductorMp3();
        }
        else{
            System.out.println("No se puede crear el objeto");
            
        }
        return instancia;
        
    }
    
    
    public void Stop(){
        System.out.println("Stopped song...");
        stop_music();
        endsAt = pausedAt = 0;
    }

    public void Play(String ruta){
        play_music(ruta);
        System.out.println("Playing song...");
    }

    public  void Pause(){
        System.out.println("Paused song...");
        pause_music();
    }

    public void Resume(){
        if (player != null){
        resume_music();
        }
    }

    public void slider_drag(){

    }

    private void stop_music(){
        if(player != null){
            player.close();
        }
    }

    private void play_music(String path){
        
        if(player == null){
            try {
                inputStream = new FileInputStream(path);
                bufferedInputStream = new BufferedInputStream(inputStream);

                player = new Player(bufferedInputStream);
                endsAt = inputStream.available();
                songPath = path + "";

            } catch (FileNotFoundException e) {
                System.out.println("Cannot reproduce file");
            } catch (JavaLayerException e) {
                System.out.println("Cannot start player");
            } catch (IOException e) {

            }
            current_song = new Thread(){
                @Override
                public void run() {
                    try {
                        player.play();
                    } catch (JavaLayerException e) {
                        System.out.println("Cannot play :(");
                    }
                }
            };
            current_song.setDaemon(true);
            current_song.start();
            
        }else{
  
            try {
                
                this.player =null;
                this.inputStream = new FileInputStream(path);
                this.bufferedInputStream = new BufferedInputStream(inputStream);

                this.player = new Player(bufferedInputStream);
                endsAt = inputStream.available();
                songPath = path + "";

            } catch (FileNotFoundException e) {
                System.out.println("Cannot reproduce file");
            } catch (JavaLayerException e) {
                System.out.println("Cannot start player");
            } catch (IOException e) {

            }
            current_song.stop();
            current_song = new Thread(){
                @Override
                public void run() {
                    try {
                        player.play();
                    } catch (JavaLayerException e) {
                        System.out.println("Cannot play :(");
                    }
                }
            };
            current_song.setDaemon(true);
            current_song.start();
            
        }
    }

    private void pause_music(){
        if(player != null){
            try {
                pausedAt = inputStream.available();
            } catch (IOException e) {

            }
            player.close();
        }
    }

    private void resume_music(){
        try {
            inputStream = new FileInputStream(songPath);
            bufferedInputStream = new BufferedInputStream(inputStream);

            player = new Player(bufferedInputStream);
            inputStream.skip(endsAt - pausedAt);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot reproduce file");
        } catch (JavaLayerException e) {
            System.out.println("Cannot start player");
        } catch (IOException e) {

        }
        current_song.stop();
        
        current_song = new Thread(){
            @Override
            public void run() {
                try {
                    player.play();
                } catch (JavaLayerException e) {
                    System.out.println("Cannot play :(");
                }
            }
        };
        current_song.setDaemon(true);
        current_song.start();
        
    }

    private void setSliderPosition(){
        
    }
    
}
    
  