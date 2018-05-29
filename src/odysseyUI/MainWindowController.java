/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odysseyUI;

import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;

import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import odysseyLogic.Cancion;
import odysseyLogic.DocumentoXML;
import odysseyLogic.MP3;
import odysseyLogic.ReproductorMp3;
import odysseyLogic.Song_item;
import odysseyLogic.XML_Parser;
import odysseyLogic.clientetcp;
import org.xml.sax.SAXException;

/**
 * FXML Controller class
 *
 * @author cris
 */
public class MainWindowController implements Initializable {
    @FXML
    private TextField userTextField;

    @FXML
    private Button ingresarBTN;

    @FXML
    private Button registrarseBTN;

    @FXML
    private PasswordField passwordTextField;

    @FXML
    private ImageView imageLogo;
    
    @FXML
    private Slider sliderCancion;
       
  
    @FXML
    private TableView <Song_item> songs;
    @FXML
    private TableColumn <Song_item, String> album;
    @FXML
    private TableColumn <Song_item, String> name;
    @FXML
    private TableColumn <Song_item, String> artist;
    @FXML
    private TableColumn <Song_item, Integer> year;
    @FXML
    private TableColumn <Song_item, Double> duration;
    @FXML
    private TableColumn <Song_item, String> genre;
    
    
            
    private ReproductorMp3 repro = ReproductorMp3.getSingletonInstance();
    
    public String ruta;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // 
        album.setCellValueFactory(new PropertyValueFactory<Song_item, String> ("album"));
        name.setCellValueFactory(new PropertyValueFactory<Song_item, String> ("song_name"));
        artist.setCellValueFactory(new PropertyValueFactory<Song_item, String> ("artist"));
        genre.setCellValueFactory(new PropertyValueFactory<Song_item, String> ("genre"));
        year.setCellValueFactory(new PropertyValueFactory<Song_item, Integer> ("year"));
        duration.setCellValueFactory(new PropertyValueFactory<Song_item, Double> ("duration"));
        songs.setItems(getSongs());
        
    }

    
    
    @FXML
    void EliminarCancion(ActionEvent event) {
        
    }
    
    @FXML
    void SincronizarDatos(ActionEvent event) {

    }        
   
    @FXML
    void seleccionDeCancion(ActionEvent event) {

    FileChooser fileChooser = new FileChooser();
    File selectedFile = fileChooser.showOpenDialog(null);
    fileChooser.setTitle("Open Resource File");
    this.ruta = selectedFile.getPath();
    Play(event);
        
    }
        
    void streaming() throws ParserConfigurationException, IOException, TransformerException, UnsupportedEncodingException, SAXException{   
        DocumentoXML stream = new DocumentoXML("comunicacion");
        stream.crearHijos("codigo", "0");
        stream.crearHijos("chunk", "1");
        clientetcp client = new clientetcp();
        java.net.Socket socket = client.crear();
        System.out.println(stream);
        client.enviar(socket, stream.ConvertirXML_String());
      
        
        XML_Parser parser = new XML_Parser();        
        parser.parsearString(client.getMensajeActual());
        
        //String limite = parser.by_tagName("limite").item(0).getTextContent();
        //int x = Integer.parseInt(limite);
        
        /*
        for(int y = 0; y <= x; y++){
            
        }*/
        
    }

    @FXML
    void Pause(ActionEvent event) {
        repro.Pause();
    }

    @FXML
    void Play(ActionEvent event)  {
        
        repro.Play(ruta);
    }

    @FXML
    void Resume(ActionEvent event) {
        repro.Resume();
    }

    @FXML
    void Stop(ActionEvent event) {
        repro.Stop();
    }
    public ObservableList <Song_item> getSongs (){
        ObservableList <Song_item> songs = FXCollections.observableArrayList();
        songs.add(new Song_item("Ludcrais","Baby",2.30,"Pop","Justin Bieber",2010));
        return songs;
    }
    
    
}
