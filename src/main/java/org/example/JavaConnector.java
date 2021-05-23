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
    MapInfo mapInfo;

    public JavaConnector(JSObject js, MapInfo mapInfo) {
        javascriptConnector = js;
        this.mapInfo = mapInfo;
    }

    public void toLowerCase(String value) {
        if (null != value) {
            javascriptConnector.call("showResult", value.toLowerCase());
        }
    }

    public void receiveID(int id) {
        System.out.println(id);
        mapInfo.setCurrentId( id);
    }

    public  void addMarker(float lat, float lng, int id){
        javascriptConnector.call("addMarkerToArray", lat,lng, id);
    }

    public  void displayMarkers(){
        javascriptConnector.call("updateMarkers");
    }
}

