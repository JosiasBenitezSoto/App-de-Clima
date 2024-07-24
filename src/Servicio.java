import com.google.gson.JsonObject;

import java.io.IOException;

public class Servicio {
    private final ConsumoAPI apiClient;

    public Servicio() {
        this.apiClient = new ConsumoAPI();
    }

    public void printWeather(double latitude, double longitude) {
        try {
            JsonObject weatherResponse = apiClient.getWeather(latitude, longitude);
            JsonObject weatherData = weatherResponse.getAsJsonArray("data").get(0).getAsJsonObject();
            double tempCelsius = weatherData.get("temp").getAsDouble();
            String description = weatherData.getAsJsonObject("weather").get("description").getAsString();

            System.out.printf("La temperatura actual en las coordenadas (%.2f, %.2f) es de %.2f grados Celsius con %s.%n",
                    latitude, longitude, tempCelsius, description);
        } catch (IOException | InterruptedException e) {
            System.out.println("Ocurrió un error al obtener la información del clima: " + e.getMessage());
        }
    }
}
