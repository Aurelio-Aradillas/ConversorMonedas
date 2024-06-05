import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        ConsultarMoneda consulta = new ConsultarMoneda();
        int opcion;
        System.out.println("************************************************************************");
        System.out.println("Bienvenido al Conversor de Moneda =]");
        System.out.println("************************************************************************");

        while (true) {
            System.out.println("1) Dolar => Peso argentino");
            System.out.println("2) Peso argentino => Dolar");
            System.out.println("3) Dolar => Real brasileño");
            System.out.println("4) Real brasileño => Dolar");
            System.out.println("5) Dolar => Peso colombiano");
            System.out.println("6) Peso colombiano => Dolar");
            System.out.println("7) Dolar => Peso Mexicano");
            System.out.println("8) Peso Mexicano => Dolar");
            System.out.println("9) SALIR");
            System.out.print("Elija una opción válida: ");

            if (lectura.hasNextInt()) {
                opcion = lectura.nextInt();
                switch (opcion) {
                    case 1, 2, 3, 4, 5, 6, 7, 8:
                        try {
                            String monedaOrigen = obtenerMonedaOrigen(opcion);
                            String monedaDestino = obtenerMonedaDestino(opcion);
                            Moneda moneda = consulta.buscaMoneda(monedaOrigen);
                            System.out.print("Ingrese el valor de " + monedaOrigen + " que deseas convertir a " + monedaDestino + ": ");
                            if (lectura.hasNextDouble()) {
                                double cantidad = lectura.nextDouble();
                                Double tasaConversion = moneda.obtenerTasaDeConversion(monedaDestino);
                                if (tasaConversion != null) {
                                    double resultado = tasaConversion * cantidad;
                                    System.out.println("CONVERSIÓN:");
                                    System.out.printf("El valor %.2f [%s] corresponde al valor final de %.2f [%s]%n",
                                            cantidad, monedaOrigen, resultado, monedaDestino);
                                } else {
                                    System.out.println("No se pudo encontrar la tasa de conversión para " + monedaDestino);
                                }
                            } else {
                                System.out.println("Por favor, ingrese un número válido.");
                                lectura.next(); // Limpia el buffer
                            }
                        } catch (RuntimeException e) {
                            System.out.println(e.getMessage());
                            System.out.println("Finalizando la aplicación.");
                            System.exit(0);
                        }
                        break;
                    case 9:
                        lectura.close();
                        System.exit(0);
                    default:
                        System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                }
            } else {
                System.out.println("Por favor, ingrese un número válido.");
                lectura.next(); // Limpia el buffer
            }
            System.out.println("************************************************************************");
        }
    }

    private static String obtenerMonedaOrigen(int opcion) {
        return switch (opcion) {
            case 1, 3, 5, 7 -> "USD";
            case 2 -> "ARS";
            case 4 -> "BRL";
            case 6 -> "COP";
            case 8 -> "MXN";
            default -> throw new IllegalArgumentException("Opción de conversión no válida.");
        };
    }

    private static String obtenerMonedaDestino(int opcion) {
        return switch (opcion) {
            case 2, 4, 6, 8 -> "USD";
            case 1 -> "ARS";
            case 3 -> "BRL";
            case 5 -> "COP";
            case 7 -> "MXN";
            default -> throw new IllegalArgumentException("Opción de conversión no válida.");
        };
    }
}
