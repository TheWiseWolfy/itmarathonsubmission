let map;

var markers = [
   // {  coords: { lat:  47.17, lng: 27.57 }, id: 57 }
];

function initMap() {

    var options = {
         zoom: 12,
         center: { lat:  47.17, lng: 27.57 }
    }

    map = new google.maps.Map(document.getElementById("map"),options);

    //Listen for click on map
    google.maps.event.addListener( map , 'click', 
         function(event){
           
    });

    var infoWindow = new google.maps.InfoWindow({
        content:'<h1> Wakanda is here </h1>'
    });

    //Array of markers 

    //Loop through markers

   //addAllMarkers(markers, map );
    //Java script experiments
}


function addMarker(props, map){
    var marker = new google.maps.Marker({
    position: props.coords,
    map:map
    });

    marker.addListener ('click', function(){
        sendIDToJava(props.id)
        infoWindow.open(map,marker);
    });
}


function addAllMarkers( markers, map ){

    for ( var i = 0; i < markers.length; i++ ){
        addMarker( markers[i], map );
    }

}

function displayMarkers(){
   addAllMarkers(markers, map);
}

//Sa primesc markerele

//Sa returnez in java markerul selectat

//TEST
function sendIDToJava (id) {
    javaConnector.receiveID(id);
};


//TEST
function sendToJava () {
    var s = document.getElementById('input').value;
    javaConnector.toLowerCase(s);
};


//This object is used in Java
var jsConnector = {
    showResult: function (result) {
        document.getElementById('result').innerHTML = result;
    },
    addMarkerToArray: function( _lat, _lng, _id){
        markers.push(
            {  
                coords: { lat:  _lat, lng: _lng },
                id: _id
            });
    },
    updateMarkers: function(){
        displayMarkers();
    }
};

function getJsConnector() {
    return jsConnector;
};