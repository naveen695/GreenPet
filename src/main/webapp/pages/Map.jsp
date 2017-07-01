<p id="demo"></p>
<script type="text/javascript">
var center = new google.maps.LatLng(59.76522, 18.35002);

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
    var longitude = position.coords.longitude;
    var latlong = new google.maps.LatLng(lat, longitude);
    var myObj=${petDetails};
   var count= myObj.perdetails.length;
   var count1=1;
   var size=count+count1;
    var locations = new Array(size)
	locations [0] = new Array(4)

    locations[0][0]= "my"
	locations[0][1]= lat
	locations[0][2]= longitude
	locations[0][3]= 0



    	//[ ['my ',lat,longitude,6]];
    var  i, x = "";
    	for(var i=1;i<locations.length;i++){
    		locations [i] = new Array(4)
			var k=i-count1;
    		locations[i][0]= myObj.perdetails[k].petName
    		locations[i][1]= 	myObj.perdetails[k].latitude
    		locations[i][2]= 	myObj.perdetails[k].longittude
    		locations[i][3]= i
        
    }
     var myOptions = {
        center: latlong,
        zoom: 16,
        mapTypeControl: true,
        navigationControlOptions: {style:google.maps.NavigationControlStyle.SMALL}
    }
    
    var map = new google.maps.Map(document.getElementById('map-canvas'), myOptions);
    
    var searchBox = new google.maps.places.SearchBox(document.getElementById('pac-input'));
    var marker = new google.maps.Marker({position:google.maps.ControlPosition.TOP_CENTER, map:map, title:"You are here!"});
    
    
    var contentString = '<div id="content">'+
    '<div id="siteNotice">'+
    '</div>'+
    '<h1 id="firstHeading" class="firstHeading">Uluru</h1>'+
    '<div id="bodyContent">'+
    '<p><b>Uluru</b>, also referred to as <b>Ayers Rock</b>, is a large ' +
    'sandstone rock formation in the southern part of the '+
    'Northern Territory, central Australia. It lies 335&#160;km (208&#160;mi) '+
    'south west of the nearest large town, Alice Springs; 450&#160;km '+
    '(280&#160;mi) by road. Kata Tjuta and Uluru are the two major '+
    'features of the Uluru - Kata Tjuta National Park. Uluru is '+
    'sacred to the Pitjantjatjara and Yankunytjatjara, the '+
    'Aboriginal people of the area. It has many springs, waterholes, '+
    'rock caves and ancient paintings. Uluru is listed as a World '+
    'Heritage Site.</p>'+
    '<p>Attribution: Uluru, <a href="https://en.wikipedia.org/w/index.php?title=Uluru&oldid=297882194">'+
    'https://en.wikipedia.org/w/index.php?title=Uluru</a> '+
    '(last visited June 22, 2009).</p>'+
    '</div>'+
    '</div>';

var infowindow = new google.maps.InfoWindow({
  content: contentString
});
    
    for (i = 0; i < locations.length; i++) {  
      marker = new google.maps.Marker({
        position: new google.maps.LatLng(locations[i][1], locations[i][2]),
        map: map,
        title:'hi'
      });
      
    /*   google.maps.event.addListener(marker, 'click', (function(marker, i) {
        return function() {
          infowindow.setContent(locations[i][0]);
          infowindow.open(map, marker);
        }
      })(marker, i));
    } */

    marker.addListener('click', function() {
        infowindow.open(map, marker);
      });
	}
    google.maps.event.addListener(searchBox, 'places_changed', function() {
      searchBox.set('map', null);
      var places = searchBox.getPlaces();
            var marker, i;

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