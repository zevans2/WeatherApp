package app.models;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class WeatherStation {
    public boolean status;
    protected String url,logo, weather, temp, humidity, windStatus, windDirection, windSpeed, time, connection, feelsLike;
    protected String output;
    protected Credentials credentials;

    //Constructor
    public WeatherStation(ArrayList<String> inputs){
        //establish credentials
        this.credentials = new Credentials(inputs);
        url = credentials.getUrl();
        //connection status
        status = credentials.success;
        connection = callAPI(url);
        Document response = Jsoup.parse(connection, url, Parser.xmlParser());
        //Set Current Values
        updateCurrentWeather(response);
    }

    private void updateCurrentWeather(Document response){
        //Set Weather Variables to current values
        weather = response.select("weather").text();
        temp = response.select("temperature_string").text();
        logo = response.select("url").text();
        humidity = response.select("relative_humidity").text();
        windStatus = response.select("wind_string").text();
        windDirection = response.select("wind_dir").text();
        windSpeed = response.select("wind_mph").text();
        time = response.select("observation_time").text();
        feelsLike = response.select("feelslike_string").text();
        printWeatherReport();
    }

    public String printWeatherReport() {
        String locationString;
        if(credentials.getCity().length() != 0)
            locationString = ("Weather Report For: " + credentials.getCity());
        else
            locationString = ("Weather Report For: " + credentials.zipcode);
        output = (locationString + "   -   " + time + "\n" +
        weather + "\n" +
        "Currently: " + temp + "\nFeels Like: " + feelsLike + "\n" +
        humidity + " Humidity" + "\n" +
        "Wind Conditions: " + windStatus + " with gusts up to " + windSpeed);
        return output;
    }

    private static String callAPI(String urlString){
        String data = "";
        try{
            URL url = new URL(urlString);
            BufferedReader input = new BufferedReader(
                    new InputStreamReader(url.openStream())
            );
            String s;
            while((s = input.readLine()) != null){
                data += s;
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
        return data;
    }
}
