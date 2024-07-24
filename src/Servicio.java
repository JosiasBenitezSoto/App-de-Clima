import com.google.gson.JsonObject;

import java.io.IOException;

public class Servicio {
    private ConsumoAPI apiClient;

    public Servicio() {
        this.apiClient = new ConsumoAPI();
    }

    public void printWeather(String location) {
        try {
            JsonObject weatherData = apiClient.getWeather(location);
            if (weatherData != null && weatherData.has("current")) {
                JsonObject current = weatherData.getAsJsonObject("current");
                if (current != null && current.has("temp_c")) {
                    double tempCelsius = current.get("temp_c").getAsDouble();
                    System.out.printf("El clima en %s es de %.2f grados Celsius.%n", location, tempCelsius);
                } else {
                    System.out.println("No se encontró la temperatura en la respuesta de la API.");
                }
            } else {
                System.out.println("No se pudo obtener la información del clima. Respuesta de la API: " + weatherData);
            }
        } catch (IOException | InterruptedException e) {
            System.out.println("Ocurrió un error al obtener la información del clima: " + e.getMessage());
        }
    }
}
