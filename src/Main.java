import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Servicio weatherService = new Servicio();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese la latitud: ");
        double latitude = scanner.nextDouble();

        System.out.print("Ingrese la longitud: ");
        double longitude = scanner.nextDouble();

        weatherService.printWeather(latitude, longitude);
    }
}
