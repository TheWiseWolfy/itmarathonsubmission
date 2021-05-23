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

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class PrimaryController implements Initializable {

    private MapInfo mapInfo;
    private ParcareInfo parcareInfo;
    private UserInfo userInfo;

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

    private ContextMenu menuLista;

    private JavaConnector javaConnector = null;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mapInfo = new MapInfo();
        parcareInfo = new ParcareInfo();
        userInfo = new UserInfo();

        webView.setZoom(mapInfo.getZoom());
        engine  = webView.getEngine();
        engine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (Worker.State.SUCCEEDED == newValue) {
                // set an interface object named 'javaConnector' in the web engine's page
                JSObject javascriptConnector = (JSObject) engine.executeScript("getJsConnector()");
                javaConnector = new JavaConnector(javascriptConnector);
                JSObject window = (JSObject) engine.executeScript("window");
                window.setMember("javaConnector", javaConnector);

                // get the Javascript connector object.
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

    public void displayMenuParcare(boolean dis) {
        menuParcare.setDisable(!dis);
        menuParcare.setVisible(dis);
        mesajLabel.setVisible(!dis);
        if(dis) {
            if(parcareInfo.getNumarLocuri() <= 0)
                vreauButton.setDisable(true);
        }
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
        parcareInfo.setNumarLocuri(parcareInfo.getNumarLocuri() - 1);
        numarLocuriLabel.setText("Numar de locuri libere: " + parcareInfo.getNumarLocuri());
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
        listView.getItems().clear();
    }

}


