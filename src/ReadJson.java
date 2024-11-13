import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


// video to load jar
//https://www.youtube.com/watch?v=QAJ09o3Xl_0

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

// Program for print data in JSON format.
public class ReadJson {
    public static void main(String args[]) throws ParseException {
        // In java JSONObject is used to create JSON object
        // which is a subclass of java.util.HashMap.

        JSONObject file = new JSONObject();
        file.put("Full Name", "Ritu Sharma");
        file.put("Roll No.", 1704310046);
        file.put("Tuition Fees", 65400);

        // To print in JSON format.
        System.out.print(file.get("Tuition Fees"));
        ReadJson readingIsWhat = new ReadJson();

    }

    public ReadJson(){
        try {
            pull();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    public  void pull() throws ParseException {
        String output = "abc";
        String totlaJson="";
        try {

            URL url = new URL("https://swapi.dev/api/people/4/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {

                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
                totlaJson+=output;
            }

            conn.disconnect();

        } catch (MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }

        JSONParser parser = new JSONParser();
        //System.out.println(str);
        org.json.simple.JSONObject jsonObject = (org.json.simple.JSONObject) parser.parse(totlaJson);
        System.out.println(jsonObject);

        try {

            String name = (String)jsonObject.get("name");
            String mass = (String)jsonObject.get("mass");
            String eyecolor = (String)jsonObject.get("eye_color");
            String birthyear = (String)jsonObject.get("birth_year");

            org.json.simple.JSONArray msg = (org.json.simple.JSONArray) jsonObject.get("films");
            int n =   msg.size(); //(msg).length();
            for (int i = 0; i < n; ++i) {
                String test =(String) msg.get(i);
                System.out.println(test);
                // System.out.println(person.getInt("key"));
            }

            System.out.println(name);
            System.out.println(mass);
            System.out.println(eyecolor);
            System.out.println(birthyear);

            org.json.simple.JSONArray starships = (org.json.simple.JSONArray) jsonObject.get("starships");
            int b =   starships.size(); //(msg).length();
            for (int i = 0; i < b; ++i) {
                String test =(String) starships.get(i);
                System.out.println(test);
                // System.out.println(person.getInt("key"));
            }

        }

        catch (Exception e) {
            e.printStackTrace();
        }

    }
}


