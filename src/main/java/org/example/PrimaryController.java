package org.example;

import javafx.concurrent.Worker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;
import data.*;

import java.net.URL;
import java.util.ResourceBundle;

public class PrimaryController implements Initializable {

    private MapInfo mapInfo;
    private ParcareInfo parcareInfo;
    private UserInfo userInfo;
    private SQLite database;

    @FXML
    private WebView webView;

    @FXML
    private WebEngine engine;

    @FXML
    private TextField numeField, masinaField, telefonField;

    @FXML
    private Button saveButton,vreauButton,CLoseMenuParcareButton;

    @FXML
    private AnchorPane menuParcare;

    @FXML
    private Label numeParcareLabel, numarLocuriLabel, mesajLabel;

    @FXML
    private ListView listView;


    private ContextMenu menuLista;

    private JavaConnector javaConnector = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        parcareInfo = new ParcareInfo();
        userInfo = new UserInfo();
        database = new SQLite();

        engine  = webView.getEngine();
        engine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (Worker.State.SUCCEEDED == newValue) {
                JSObject javascriptConnector = (JSObject) engine.executeScript("getJsConnector()");
                mapInfo = new MapInfo(database.getListaParcari().readAllData());
                javaConnector = new JavaConnector(javascriptConnector, mapInfo);
                for(Parcare p : mapInfo.getLista())
                {
                    javaConnector.addMarker(p.getLat(),p.getLng(),p.getID());
                }
                JSObject window = (JSObject) engine.executeScript("window");
                window.setMember("javaConnector", javaConnector);

            }
        });

        menuLista = new ContextMenu();
        MenuItem deleteRezervation = new MenuItem("Free Rezervation");
        deleteRezervation.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                int index = listView.getSelectionModel().getSelectedIndex();
                userInfo.getListaRezervari().remove(index);
                listView.getItems().remove(listView.getSelectionModel().getSelectedItem());
                updateNumarLocuri(parcareInfo.getNumarLocuri() + 1);
            }
        });

        menuLista.getItems().add(deleteRezervation);

        listView.setOnContextMenuRequested(new EventHandler<ContextMenuEvent>() {

            @Override
            public void handle(ContextMenuEvent event) {
                menuLista.show(listView,event.getScreenX(), event.getScreenY());
            }
        });

        loadMap(this.getClass().getResource( "index.html" ).toString());
        changeProfile(false);
        displayMenuParcare(false);
    }

    public void updateNumarLocuri(int nr) {
        parcareInfo.setNumarLocuri(nr);
        numarLocuriLabel.setText("Numar de locuri libere: " + parcareInfo.getNumarLocuri());
    }

    public void loadMap(String url) {
        engine.load(url);
    }

    public void refresh() {
        engine.reload();
    }

    public void center() {
        webView.setZoom(1);
    }

    public void displayMenuParcare(boolean dis) {
        CLoseMenuParcareButton.setDisable(!dis);
        menuParcare.setDisable(!dis);
        menuParcare.setVisible(dis);
        mesajLabel.setVisible(!dis);
        if(dis) {
            if(parcareInfo.getNumarLocuri() <= 0)
                vreauButton.setDisable(true);
            Parcare parcare = mapInfo.getCurrentPin();
            updateParcare(parcare);
        }
    }

    public void updateParcare(Parcare parcare) {
        numeParcareLabel.setText("Nume: " + parcare.getNume());
        numarLocuriLabel.setText("Numar locuri libere: " + parcare.getLocuriLibere());
    }

    public void pinClick() {
        displayMenuParcare(true);
    }

    public void changeProfile(boolean edit) {
        saveButton.setDisable(!edit);
        numeField.setEditable(edit);
        masinaField.setEditable(edit);
        telefonField.setEditable(edit);
    }

    public void makeRezervation() throws RezervationException {
        if(userInfo.invalid())
            throw new RezervationException();
        updateNumarLocuri(parcareInfo.getNumarLocuri() - 1);
        if(parcareInfo.getNumarLocuri() <= 0)
            vreauButton.setDisable(true);

        RezervationClass rezervare = new RezervationClass(numeParcareLabel.getText(), userInfo.getCar());
        userInfo.getListaRezervari().add(rezervare);


        listView.getItems().add(numeParcareLabel.getText() + " | " + userInfo.getCar());

        mesajLabel.setVisible(true);
        mesajLabel.setText("Succes!");
    }

    public void vreauLoc() {
        mesajLabel.setVisible(false);
        try{
            makeRezervation();
        } catch (RezervationException e) {
            Alert emptyProfile = new Alert(Alert.AlertType.ERROR,"Please first complete your profile!");
            emptyProfile.show();
            displayMenuParcare(false);
        }
    }

    public void editProfile() {
        changeProfile(true);
    }

    public void saveProfile() {
        userInfo.setCar(masinaField.getText());
        userInfo.setTel(telefonField.getText());
        userInfo.setName(numeField.getText());
        changeProfile(false);
    }

    public void deleteList() {
        updateNumarLocuri(parcareInfo.getNumarLocuri() + listView.getItems().size());
        listView.getItems().clear();
    }

    @FXML
    public void changeTab() {
        displayMenuParcare(false);
    }

    @FXML
    private void printeazaChestie()
    {
        System.out.println("sall");
    }

}


