package models;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Credentials {
    private String url = "";
    private String apiKey;
    private String state;
    private String city;
    protected boolean success = false;
    private Scanner input = new Scanner(System.in);

    //Primary Method for Assembling data
    private boolean populate() {
        if (url.length() < 2){
            validateXML();
            requestState(input);
            requestCity(input);
            assembleURL();
        }
        return success;
    }

    private void assembleURL() {
        city = clean(city);
        url += (apiKey + "/conditions/q/" + state + "/" + city + ".xml");
    }

    private String clean(String string) {
        string = string.replaceAll("\\s", "_");
        return string;
    }

    private void requestCity(Scanner input) {
        System.out.println("Enter City: ");
        city = input.nextLine();
    }

    private void requestState(Scanner input) {
        System.out.println("Enter State Abbreviation");
        state = input.nextLine();
        while(state.length() != 2){
            System.out.println("Please Enter A Valid 2 Letter State Abbreviation:");
            state = input.nextLine();
        }
    }//end requestState


    private void validateXML(){
        File inputFile = new File("credentials.xml");
        StringBuilder xml = new StringBuilder();
        //read credentials
        String ret;
        try {
            Scanner input = new Scanner(inputFile);
            while (input.hasNextLine()) {
                xml.append(input.nextLine());
            }
        } catch (FileNotFoundException e) {
            System.err.println("Could not retrieve credentials.\n" + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        //Read in XML Data
        Document doc = Jsoup.parse(xml.toString(), "credentials.xml", Parser.xmlParser());
        //Extract URL
        url = doc.select("url").text();
        //Extract Key
        apiKey = doc.select("weatherUndergroundKey").text();
        System.out.println("Connection Successful:\n");
        this.success = true;
    }

    String getUrl() {
        populate();
        return url;
    }

    private String getApiKey() {
        //Make Sure Key is updated
        validateXML();
        return apiKey;
    }

    public String getCity() {
        return city.replaceAll("_"," ");
    }




}
