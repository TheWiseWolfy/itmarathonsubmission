package org.example;

import javafx.concurrent.Worker;
import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public class JavaScriptCommunication {

    private JavaConnector javaConnector = null;

    public void initializeListeners(WebEngine engine){

        engine.getLoadWorker().stateProperty().addListener((observable, oldValue, newValue) -> {
            if (Worker.State.SUCCEEDED == newValue) {

                // set an interface object named 'javaConnector' in the web engine's page
                JSObject javascriptConnector = (JSObject) engine.executeScript("getJsConnector()");

               // javaConnector = new JavaConnector(javascriptConnector);
                JSObject window = (JSObject) engine.executeScript("window");
                window.setMember("javaConnector", javaConnector);
            }
        });
    }
}
