import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class ConsumoAPI {
    private static final String API_KEY = "8f9460625amsh5f5ec91ac33ce53p1a8502jsndb873f34be90";
    private static final String API_HOST = "weatherapi-com.p.rapidapi.com";
    private static final String API_URL = "https://weatherapi-com.p.rapidapi.com/current.json?q=%s";

    public JsonObject getWeather(String location) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String encodedLocation = URLEncoder.encode(location, StandardCharsets.UTF_8);
        String url = String.format(API_URL, encodedLocation);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("x-rapidapi-key", API_KEY)
                .header("x-rapidapi-host", API_HOST)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return new Gson().fromJson(response.body(), JsonObject.class);
        } else {
            throw new IOException("Error en la solicitud: " + response.statusCode());
        }
    }
}
