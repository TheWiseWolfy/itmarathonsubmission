package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;


public class PrimaryController implements Initializable {

    private MapInfo mapInfo;
    private ParcareInfo parcareInfo;

    @FXML
    private WebView webView;

    @FXML
    private WebEngine engine;

    @FXML
    private TextField numeField, masinaField, telefonField;

    @FXML
    private Button saveButton,vreauButton;

    @FXML
    private AnchorPane menuParcare;

    @FXML
    private Label numeParcareLabel, numarLocuriLabel, mesajLabel;

    @FXML
    private ListView listView;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mapInfo = new MapInfo();
        parcareInfo = new ParcareInfo();
        webView.setZoom(mapInfo.getZoom());
        engine  = webView.getEngine();
        loadMap(this.getClass().getResource( "index.html" ).toString());
        changeProfile(false);
        menuParcare.setDisable(true);
        menuParcare.setVisible(false);
        if(parcareInfo.getNumarLocuri() <= 0)
            vreauButton.setDisable(true);
    }

    public void loadMap(String url) {
        engine.load(url);
    }

    public void refresh() {
        engine.reload();
    }

    public void center() {
        mapInfo.setZoom(1);
        webView.setZoom(mapInfo.getZoom());
    }

    public void pinClick() {
        menuParcare.setDisable(false);
        menuParcare.setVisible(true);
        mesajLabel.setVisible(false);
    }

    public void changeProfile(boolean edit) {
        saveButton.setDisable(!edit);
        numeField.setEditable(edit);
        masinaField.setEditable(edit);
        telefonField.setEditable(edit);
    }

    public void vreauLoc() {
        mesajLabel.setVisible(false);
        parcareInfo.setNumarLocuri(parcareInfo.getNumarLocuri() - 1);
        numarLocuriLabel.setText("Numar de locuri libere: " + parcareInfo.getNumarLocuri());
        mesajLabel.setText("Succes!");
        mesajLabel.setVisible(true);
        if(parcareInfo.getNumarLocuri() <= 0)
            vreauButton.setDisable(true);
        listView.getItems().add(numeParcareLabel.getText());
    }

    public void editProfile() {
        changeProfile(true);
    }

    public void saveProfile() {
        changeProfile(false);
    }

    public void deleteList() {
        listView.getItems().clear();
    }

}
