package modelo;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class JsonSimpleReadExample {

    public static void main(String[] args) {

        JSONParser parser = new JSONParser();

        try {

            Object obj = parser.parse(new FileReader("/home/sergio/placas.json"));

            JSONObject jsonObject = (JSONObject) obj;
            System.out.println(jsonObject);

            //String plate = (String) jsonObject.get("results");
            //System.out.println(plate);

            //long age = (Long) jsonObject.get("age");
            //System.out.println(age);

            // loop array
            JSONArray results = (JSONArray) jsonObject.get("results");
            //iterator = results.iterator();
            
            Iterator<Object> iterator = results.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
                JSONObject jsonObjectnovo = (JSONObject) results.iterator().next();
                String plate = (String) jsonObjectnovo.get("plate");
                System.out.println(plate);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}