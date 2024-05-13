import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import http.Get;

import java.io.FileWriter;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.InputMismatchException;
import java.util.Scanner;

public class QueryMovie {
    Movie searchMovie(int movieNumber) throws IOException, InterruptedException {
        Scanner keyboard = new Scanner(System.in);
        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        System.out.println("Select a movie bewteen 1 and 6:");

        try {
            movieNumber = keyboard.nextInt();
        }catch (InputMismatchException e){
            System.out.println("Oh, just introduce a number :)");
            System.exit(0);
        }

        if (movieNumber>6 || movieNumber<1){
            System.out.println("Just select between 1 and 6           >:(");
            System.exit(0);
        }
        String apiUrl = "https://swapi.dev/api/films/"+movieNumber;

        Get httpGet = new Get(apiUrl);

        try {
            HttpResponse response = httpGet.doRequest();

            String json = response.body().toString();
            System.out.println();
            System.out.println("Raw json: " + json);

            return new Gson().fromJson(json, Movie.class);

        }catch (Exception e){
            throw new RuntimeException("We can't find that movie...");
        }
    }
}
