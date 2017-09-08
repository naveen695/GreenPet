<p id="demo"></p>
<script type="text/javascript">

function loadimages(locations) {
	
	
	var dataRequestObject= {}; 
    dataRequestObject= {id:locations};

	 	 $.ajax({
	         type : "post",
	         url : "LoadMultipleImages",
	         data : dataRequestObject,
	         success : function(responseText) {
	        	 $('#msg1').html(responseText);
	         },
	         error : function(xhr, ajaxOptions, thrownError) {
	             $('#msg1').html(" <div class='jumbotron'> ERROR Loading images. </div>");
	         }
	    });
}

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
	locations [0] = new Array(7)
    locations[0][0]= 0
	locations[0][1]= lat
	locations[0][2]= longitude
    locations[0][3]= "my location"
    locations[0][3]= "Hi !"

    var  i, x = "";
    	for(var i=1;i<locations.length;i++){
    		locations [i] = new Array(4)
			var k=i-count1;
    		locations[i][0]= i
    		locations[i][1]= 	myObj.perdetails[k].latitude
    		locations[i][2]= 	myObj.perdetails[k].longittude
    		locations[i][3]= myObj.perdetails[k].petName
    		locations[i][4]= myObj.perdetails[k].petDesc
    		locations[i][5]= myObj.perdetails[k].id
    		locations[i][6]=myObj.perdetails[k].address1
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
    
var infowindow = new google.maps.InfoWindow();
    
    for (i = 0; i < locations.length; i++) {  
      marker = new google.maps.Marker({
        position: new google.maps.LatLng(locations[i][1], locations[i][2]),
        map: map,
        title:locations[i][3]
      });
      
		google.maps.event.addListener(marker, 'click', (function (marker, i) {
			return function () {
					$('#msg1').html(" <div class='jumbotron'> click on load images .... </div>");
					infowindow.setContent('<div class="panel panel-primary"><div class="panel-heading" style="padding-top: 3px; padding-bottom: 3px; padding-right: 3px; padding-left: 3px;"><h4 class="modal-title">	Pet Details</h4></div><div class="panel-body" style="padding-top: 5px; padding-left: 2px;padding-right: 2px;padding-bottom: 5px;"><div class="col-sm-12" style="padding-left: 2px;padding-right: 2px"><div class="col-sm-6" style="padding-left: 3px;padding-right: 3px"><a onclick=\"zoom(path);\"   data-toggle=\"modal\" data-target=\"#myModal\"><img id=\"path\" src="LoadAjaxImage/'+locations[i][5]+'" alt="please wait loding image .." class="media-object" height="120px" width="120px"></a></div><div class="col-sm-6"><table class="table"><tbody><tr class="info"><th>TreeName</th><td>\''+locations[i][3]+'\'</td></tr></tbody></table><div class="title h5">  <button id="loadimages1" type="submit"  onclick="loadimages(\''+locations[i][5]+'\')" > Click for more Images </button></div></div></div></div></div>');
					infowindow.open(map, marker);
			}
		})(marker, i));
    }/* 
    <tr class="info"><th>Place</th><td>\''+locations[i][6]+'\'</td></tr> */
    
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