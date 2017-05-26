<script type="text/javascript">
var center = new google.maps.LatLng(59.76522, 18.35002);

/* function initialize() {

   var mapOptions = {
       zoom: 7,
       mapTypeId: google.maps.MapTypeId.ROADMAP,
       center: center
   };

   map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);

   var marker = new google.maps.Marker({
       map: map,
       position: center
   });
} */



function showPosition(){
    if(navigator.geolocation){
        navigator.geolocation.getCurrentPosition(showMap, showError, {maximumAge:60000, timeout:5000, enableHighAccuracy:true});
    } else{
        alert("Sorry, your browser does not support HTML5 geolocation.");
    }
}
function showMap(position){
    // Get location data
     var lat = position.coords.latitude;
    var long = position.coords.longitude;
    var latlong = new google.maps.LatLng(lat, long);
    
    var myOptions = {
        center: latlong,
        zoom: 16,
        mapTypeControl: true,
        navigationControlOptions: {style:google.maps.NavigationControlStyle.SMALL}
    }
    
    var map = new google.maps.Map(document.getElementById('map-canvas'), myOptions);
    
    var searchBox = new google.maps.places.SearchBox(document.getElementById('pac-input'));
    var marker = new google.maps.Marker({position:google.maps.ControlPosition.TOP_CENTER, map:map, title:"You are here!"});

    google.maps.event.addListener(searchBox, 'places_changed', function() {
      searchBox.set('map', null);
      var places = searchBox.getPlaces();

      var bounds = new google.maps.LatLngBounds();
      var i, place;
      for (i = 0; place = places[i]; i++) {
        (function(place) {
          var marker = new google.maps.Marker({

            position: place.geometry.location
          });
          marker.bindTo('map', searchBox, 'map');
          google.maps.event.addListener(marker, 'map_changed', function() {
            if (!this.getMap()) {
              this.unbindAll();
            }
          });
          bounds.extend(place.geometry.location);


        }(place));

      }
      map.fitBounds(bounds);
      searchBox.set('map', map);
      map.setZoom(Math.min(map.getZoom(),12));

    });


}
//Define callback function for failed attempt
function showError(error){
 if(error.code == 1){
     result.innerHTML = "You've decided not to share your position, but it's OK. We won't ask you again.";
 } else if(error.code == 2){
     result.innerHTML = "The network is down or the positioning service can't be reached.";
 } else if(error.code == 3){
     result.innerHTML = "The attempt timed out before it could get the location data.";
 } else{
     result.innerHTML = "Geolocation failed due to unknown error.";
 }
}
$(document).ready( function () {

	showPosition();   
   $('#modal').modal({
       backdrop: 'static',
       keyboard: false
   }).on('shown.bs.modal', function () {
       google.maps.event.trigger(map, 'resize');
       map.setCenter(center);
   });
});

</script>
<style>
		body, .modal-open .page-container, .modal-open .page-container .navbar-fixed-top, .modal-open .modal-container {
    overflow-y: scroll;
}
@media (max-width: 979px) {
    .modal-open .page-container .navbar-fixed-top {
        overflow-y: visible;
    }
}
#map-canvas {
    height: 300px;
}
.jumbotron {
    padding-top: 15px;
    padding-bottom:15px;
}
.container .jumbotron, .container-fluid .jumbotron{
    padding-right: 0px;
    padding-left: 0px;
}
.element.style {
    height: 25px !important;
    width: 200px !important;
    position: absolute;
    top: 0px;
    left: 500px;
    }
</style>
<div class="jumbotron text-center">

	<input type="text"  id="pac-input"  placeholder="Search Box">
   <div id="map-canvas"></div>
</div>