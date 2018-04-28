package app.models;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class WeatherStation {
    private String weather, temp, humidity, windStatus, windSpeed, time, feelsLike, location;
    private String windDirection;

    //Constructor
    public WeatherStation(ArrayList<String> inputs){
        //establish credentials
        Credentials credentials = new Credentials(inputs);
        String url = credentials.getUrl();
        //connection status
        String connection = callAPI(url);
        Document response = Jsoup.parse(connection, url, Parser.xmlParser());
        //Set Current Values
        updateCurrentWeather(response);
    }

    private void updateCurrentWeather(Document response){
        //Set Weather Variables to current values
        weather = response.select("weather").text();
        temp = response.select("temperature_string").text();
        String logo = response.select("url").text();
        humidity = response.select("relative_humidity").text();
        windStatus = response.select("wind_string").text();
        windDirection = response.select("wind_dir").text();
        windSpeed = response.select("wind_mph").text();
        time = response.select("observation_time").text();
        Elements display_location = response.select("display_location");
        location = (display_location.select("city").text() + ", " + display_location.select("state").text());
        feelsLike = response.select("feelslike_string").text();
        System.out.println(logo);
        generateReport();
    }

    public String generateReport() {
        String locationString;
        locationString = ("Weather Report For: " + location);
        return (locationString + "   -   " + time + "\n" +
                weather + "\n" +
                "Currently: " + temp + "\nFeels Like: " + feelsLike + "\n" +
                humidity + " Humidity" + "\n" +
                "Wind Conditions: " + windStatus);
    }

    private static String callAPI(String urlString){
        StringBuilder data = new StringBuilder();
        try{
            URL url = new URL(urlString);
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );
            String s;
            while((s = input.readLine()) != null){
                data.append(s);
            }
            input.close();
        } catch (MalformedURLException e){
            System.err.println("Unable to connect to url: " + urlString);
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(2);
        }
        return data.toString();
    }
}
