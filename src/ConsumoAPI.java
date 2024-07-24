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
    private static final String WEATHER_API_KEY = "5d80201ca892401084a1a19b3ba68fb5";
    private static final String GEO_API_KEY = "5d80201ca892401084a1a19b3ba68fb5";  // Asegúrate de reemplazar esto con tu clave API real
    private static final String GEO_API_URL = "https://api.opencagedata.com/geocode/v1/json?q=%s&key=" + GEO_API_KEY;
    private static final String WEATHER_API_URL = "https://api.weatherbit.io/v2.0/current?lat=%s&lon=%s&key=" + WEATHER_API_KEY;

    public JsonObject getWeather(double latitude, double longitude) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        String url = String.format(WEATHER_API_URL, latitude, longitude);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() == 200) {
            return new Gson().fromJson(response.body(), JsonObject.class);
        } else {
            throw new IOException("Error en la solicitud del clima: " + response.statusCode());
        }
    }
}
