package org.example;

import data.Parcare;
import javafx.concurrent.Worker;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

public class JavaScriptCommunication {


    private JavaConnector javaConnector = null;


    JavaScriptCommunication(WebView webView, MapInfo mapInfo){

        WebEngine engine  = webView.getEngine();

        engine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (Worker.State.SUCCEEDED == newValue) {
                JSObject javascriptConnector = (JSObject) engine.executeScript("getJsConnector()");

                javaConnector = new JavaConnector(javascriptConnector, mapInfo);

                JSObject window = (JSObject) engine.executeScript("window");
                window.setMember("javaConnector", javaConnector);
            }
        });

    }

    public JavaConnector getJavaConnector() {
        return javaConnector;
    }
}
