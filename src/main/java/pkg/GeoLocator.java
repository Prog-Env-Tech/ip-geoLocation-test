package pkg;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.URL;

/*import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;*/

import org.apache.commons.io.IOUtils;

public class GeoLocator {

	public static final String GEOLOCATOR_SERVICE_URI = "http://freegeoip.net/json/";

	public GeoLocator() {} //Constructor

	public GeoLocation getGeoLocation() throws IOException {
		URL url = new URL(GEOLOCATOR_SERVICE_URI);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
			throw new IOException(conn.getResponseMessage());
		}
                
		String s = IOUtils.toString(conn.getInputStream(), "UTF-8");
               
                
                ObjectMapper mapper = new ObjectMapper(); //creating an JSON to Java Obj. Mapper
                
                
                GeoLocation geo_loc = mapper.readValue(s.replace(',', '\n'), GeoLocation.class);
                
                
                return geo_loc;  
		//Gson gson = new GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES).create();
		//return gson.fromJson(s, GeoLocation.class); 
                
	}

	public static void main(String[] args) throws IOException {
		try {
			System.out.println(new GeoLocator().getGeoLocation());
		} catch(IOException e) {
			System.err.println(e.getMessage());
		}
	}

}
