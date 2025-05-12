import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PeticionApiServicio peticion = new PeticionApiServicio();
        String textoDeMenu = """
                ====== CONVERSOR DE MONEDAS (base USD) ======
                1. Euros (EUR)
                2. Pesos Argentinos (ARS)
                3. Pesos Mexicanos (MXN)
                4. Libras Esterlinas (GBP)
                5. Dólares Canadienses (CAD)
                6. Dólares Australianos (AUD)
                7. Dólares Neozelandeses (NZD)
                8. Dólares de Singapur (SGD)
                9. Dólares de Hong Kong (HKD)
                =============================================
                """;

        try {
            PeticionApiRespuesta respuesta = peticion.fetchRates("USD");
            if (respuesta == null || respuesta.getConversiones() == null) {
                System.out.println("No se pudo obtener la respuesta de la API.");
                return;
            }

            do {
                System.out.print("Ingrese la cantidad en USD: ");
                double cantidad = scanner.nextDouble();

                System.out.println(textoDeMenu);
                System.out.print("Seleccione una opción: ");
                int opcion = scanner.nextInt();

                String monedaDestino = switch (opcion) {
                    case 1 -> "EUR";
                    case 2 -> "ARS";
                    case 3 -> "MXN";
                    case 4 -> "GBP";
                    case 5 -> "CAD";
                    case 6 -> "AUD";
                    case 7 -> "NZD";
                    case 8 -> "SGD";
                    case 9 -> "HKD";
                    default -> {
                        System.out.println("Opción no válida.");
                        yield null; // Salir del switch
                    }
                };

                Double tasa = respuesta.getConversiones().get(monedaDestino);
                if (tasa == null) {
                    System.out.println("Moneda no disponible.");
                    continue;
                }

                double resultado = cantidad * tasa;
                System.out.printf("Resultado: %.2f USD = %.2f %s%n", cantidad, resultado, monedaDestino);

                System.out.print("¿Desea hacer otra conversión? (s/n): ");
            } while (scanner.next().equalsIgnoreCase("s"));

            System.out.println("Gracias por usar el conversor.");
        } catch (IOException e) {
            System.out.println("Error de conexión con la API: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
