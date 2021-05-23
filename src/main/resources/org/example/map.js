let map;

function initMap() {

    var options = {
         zoom: 12,
         center: { lat:  47.17, lng: 27.57 }
    }
    map = new google.maps.Map(document.getElementById("map"),options);

    //Listen for click on map
    google.maps.event.addListener( map , 'click', 
    function(event){
    // addMarker({});
    });

    var infoWindow = new google.maps.InfoWindow({
        content:'<h1> Wakanda is here </h1>'
    });

    //Array of markers 

    var markers = [
        {   
            coords: { lat:  47.17, lng: 27.57 } 
        }
    ];

    //Loop through markers
    for ( var i = 0; i < markers.length; i++ ){
        addMarker( markers[i] );
    }


    function addMarker(props){
        var marker = new google.maps.Marker({
        position: props.coords,
        map:map
        });

        marker.addListener ('click', function(){
            infoWindow.open(map,marker);
        });


    }

    //Java script experiments
}

