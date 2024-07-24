import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Servicio weatherService = new Servicio();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingrese el nombre de la ciudad o país: ");
        String location = scanner.nextLine();

        weatherService.printWeather(location);
    }
}
