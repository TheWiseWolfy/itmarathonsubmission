package org.example;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Label numeParcareLabel, numarLocuriLabel;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mapInfo = new MapInfo();
        webView.setZoom(mapInfo.getZoom());
        engine  = webView.getEngine();
        loadMap("http://www.google.com");
        changeProfile(false);

    }

    public void loadMap(String url) {
        engine.load(url);
    }

    public void refresh() {
        engine.reload();
    }

    public void zoomIn() {
        mapInfo.setZoom(mapInfo.getZoom() + 0.25f);
        webView.setZoom(mapInfo.getZoom());
    }

    public void zoomOut() {
        mapInfo.setZoom(mapInfo.getZoom() - 0.25f);
        webView.setZoom(mapInfo.getZoom());
    }

    public void center() {
        mapInfo.setZoom(1);
        webView.setZoom(mapInfo.getZoom());
    }

    public void pinClick(int idParcare) {

    }

    public void changeProfile(boolean edit) {
        saveButton.setDisable(!edit);
        numeField.setEditable(edit);
        masinaField.setEditable(edit);
        telefonField.setEditable(edit);
    }

    public void editProfile() {
        changeProfile(true);
    }

    public void saveProfile() {
        changeProfile(false);
    }

}
