package org.example;

import netscape.javascript.JSObject;

public class JavaConnector {
    /**
     * called when the JS side wants a String to be converted.
     *
     * @param value
     *         the String to convert
     */

    private JSObject javascriptConnector = null;

    public JavaConnector(JSObject js) {
        javascriptConnector = js;
    }

    public void toLowerCase(String value) {
        if (null != value) {
            javascriptConnector.call("showResult", value.toLowerCase());
        }
    }

    public void receiveID(int id) {
        System.out.println(id);
    }
}

