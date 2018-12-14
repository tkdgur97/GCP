package test;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JPanel;

import com.teamdev.jxbrowser.chromium.Browser;
import com.teamdev.jxbrowser.chromium.events.FinishLoadingEvent;
import com.teamdev.jxbrowser.chromium.events.LoadAdapter;
import com.teamdev.jxbrowser.chromium.swing.BrowserView;

import tcpserver.MemberDTO;
import tcpserver.TCPClient1;

public class Test6 extends JFrame {

	private Browser browser = new Browser();
	private BrowserView browserView = new BrowserView(browser);
	int[] count;
	JPanel jp;

	public Test6() throws Exception {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setSize(800, 600);
		jp = new JPanel();
		jp.setLayout(null);
		jp.setBounds(0, 0, 800, 600);
		browser.addLoadListener(new LoadAdapter() {
			@Override
			public void onFinishLoadingFrame(FinishLoadingEvent event) {
			}
		});
		browser.loadHTML(getChart());
		browserView.setBounds(0, 0, 447, 258);
		browserView.setBackground(Color.LIGHT_GRAY);
		jp.add(browserView);
		jp.setBackground(Color.LIGHT_GRAY);
		add(jp, BorderLayout.CENTER);
	}

	public JPanel jpanel() {
		return jp;
	}

	public String getChart() throws Exception {

		String htmlString = "<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"  <head>\r\n" + 
				"    <title>Place ID Finder</title>\r\n" + 
				"    <meta name=\"viewport\" content=\"initial-scale=1.0, user-scalable=no\">\r\n" + 
				"    <meta charset=\"utf-8\">\r\n" + 
				"    <style>\r\n" + 
				"      /* Always set the map height explicitly to define the size of the div\r\n" + 
				"       * element that contains the map. */\r\n" + 
				"      #map {\r\n" + 
				"        height: 100%;\r\n" + 
				"      }\r\n" + 
				"      /* Optional: Makes the sample page fill the window. */\r\n" + 
				"      html, body {\r\n" + 
				"        height: 100%;\r\n" + 
				"        margin: 0;\r\n" + 
				"        padding: 0;\r\n" + 
				"      }\r\n" + 
				"      .controls {\r\n" + 
				"        background-color: #fff;\r\n" + 
				"        border-radius: 2px;\r\n" + 
				"        border: 1px solid transparent;\r\n" + 
				"        box-shadow: 0 2px 6px rgba(0, 0, 0, 0.3);\r\n" + 
				"        box-sizing: border-box;\r\n" + 
				"        font-family: Roboto;\r\n" + 
				"        font-size: 15px;\r\n" + 
				"        font-weight: 300;\r\n" + 
				"        height: 29px;\r\n" + 
				"        margin-left: 17px;\r\n" + 
				"        margin-top: 10px;\r\n" + 
				"        outline: none;\r\n" + 
				"        padding: 0 11px 0 13px;\r\n" + 
				"        text-overflow: ellipsis;\r\n" + 
				"        width: 400px;\r\n" + 
				"      }\r\n" + 
				"\r\n" + 
				"      .controls:focus {\r\n" + 
				"        border-color: #4d90fe;\r\n" + 
				"      }\r\n" + 
				"      .title {\r\n" + 
				"        font-weight: bold;\r\n" + 
				"      }\r\n" + 
				"      #infowindow-content {\r\n" + 
				"        display: none;\r\n" + 
				"      }\r\n" + 
				"      #map #infowindow-content {\r\n" + 
				"        display: inline;\r\n" + 
				"      }\r\n" + 
				"\r\n" + 
				"    </style>\r\n" + 
				"  </head>\r\n" + 
				"  <body>\r\n" + 
				"    <input id=\"pac-input\" class=\"controls\" type=\"text\"\r\n" + 
				"        placeholder=\"Enter a location\">\r\n" + 
				"    <div id=\"map\"></div>\r\n" + 
				"    <div id=\"infowindow-content\">\r\n" + 
				"      <span id=\"place-name\"  class=\"title\"></span><br>\r\n" + 
				"      Place ID <span id=\"place-id\"></span><br>\r\n" + 
				"      <span id=\"place-address\"></span>\r\n" + 
				"    </div>\r\n" + 
				"\r\n" + 
				"    <script>\r\n" + 
				"      // This sample uses the Place Autocomplete widget to allow the user to search\r\n" + 
				"      // for and select a place. The sample then displays an info window containing\r\n" + 
				"      // the place ID and other information about the place that the user has\r\n" + 
				"      // selected.\r\n" + 
				"\r\n" + 
				"      // This example requires the Places library. Include the libraries=places\r\n" + 
				"      // parameter when you first load the API. For example:\r\n" + 
				"      // <script src=\"https://maps.googleapis.com/maps/api/js?key=YOUR_API_KEY&libraries=places\">\r\n" + 
				"\r\n" + 
				"      function initMap() {\r\n" + 
				"        var map = new google.maps.Map(document.getElementById('map'), {\r\n" + 
				"          center: {lat: -33.8688, lng: 151.2195},\r\n" + 
				"          zoom: 13\r\n" + 
				"        });\r\n" + 
				"\r\n" + 
				"        var input = document.getElementById('pac-input');\r\n" + 
				"\r\n" + 
				"        var autocomplete = new google.maps.places.Autocomplete(input);\r\n" + 
				"        autocomplete.bindTo('bounds', map);\r\n" + 
				"\r\n" + 
				"        map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);\r\n" + 
				"\r\n" + 
				"        var infowindow = new google.maps.InfoWindow();\r\n" + 
				"        var infowindowContent = document.getElementById('infowindow-content');\r\n" + 
				"        infowindow.setContent(infowindowContent);\r\n" + 
				"        var marker = new google.maps.Marker({\r\n" + 
				"          map: map\r\n" + 
				"        });\r\n" + 
				"        marker.addListener('click', function() {\r\n" + 
				"          infowindow.open(map, marker);\r\n" + 
				"        });\r\n" + 
				"\r\n" + 
				"        autocomplete.addListener('place_changed', function() {\r\n" + 
				"          infowindow.close();\r\n" + 
				"          var place = autocomplete.getPlace();\r\n" + 
				"          if (!place.geometry) {\r\n" + 
				"            return;\r\n" + 
				"          }\r\n" + 
				"\r\n" + 
				"          if (place.geometry.viewport) {\r\n" + 
				"            map.fitBounds(place.geometry.viewport);\r\n" + 
				"          } else {\r\n" + 
				"            map.setCenter(place.geometry.location);\r\n" + 
				"            map.setZoom(17);\r\n" + 
				"          }\r\n" + 
				"\r\n" + 
				"          // Set the position of the marker using the place ID and location.\r\n" + 
				"          marker.setPlace({\r\n" + 
				"            placeId: place.place_id,\r\n" + 
				"            location: place.geometry.location\r\n" + 
				"          });\r\n" + 
				"          marker.setVisible(true);\r\n" + 
				"\r\n" + 
				"          infowindowContent.children['place-name'].textContent = place.name;\r\n" + 
				"          infowindowContent.children['place-id'].textContent = place.place_id;\r\n" + 
				"          infowindowContent.children['place-address'].textContent =\r\n" + 
				"              place.formatted_address;\r\n" + 
				"          infowindow.open(map, marker);\r\n" + 
				"        });\r\n" + 
				"      }\r\n" + 
				"    </script>\r\n" + 
				"    <script src=\"https://maps.googleapis.com/maps/api/js?key=AIzaSyAjuW4h0YOQjyv5wian15w4ELHZFFu7lTc&libraries=places&callback=initMap\"\r\n" + 
				"        async defer></script>\r\n" + 
				"  </body>\r\n" + 
				"</html>";
		return htmlString;
	}
	//AIzaSyAjuW4h0YOQjyv5wian15w4ELHZFFu7lTc
	public static void main(String[] args) throws Exception {
		new Test6();
	}

}