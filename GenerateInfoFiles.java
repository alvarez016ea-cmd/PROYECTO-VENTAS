import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Clase encargada de generar los archivos planos de prueba con informacion
 * pseudoaleatoria para el proyecto de ventas.
 */
public class GenerateInfoFiles {

    private static final String[] NOMBRES = {"Juan", "Maria", "Carlos", "Ana", "Luis", "Sofia"};
    private static final String[] APELLIDOS = {"Gomez", "Perez", "Rodriguez", "Lopez", "Martinez"};
    private static final String[] TIPOS_DOC = {"CC", "CE", "NIT"};

    public static void main(String[] args) {
        try {
            int vendedorCount = 5;
            int productosCount = 10;
            int maxVentasPorVendedor = 8;

            createSalesManInfoFile(vendedorCount);
            createProductsFile(productosCount);

            for (int i = 1; i <= vendedorCount; i++) {
                String nombreVendedor = NOMBRES[i % NOMBRES.length] + "_" + APELLIDOS[i % APELLIDOS.length];
                long idVendedor = 10000000L + i;
                createSalesMenFile(maxVentasPorVendedor, nombreVendedor, idVendedor);
            }

            System.out.println("Proceso finalizado con exito. Archivos creados correctamente.");

        } catch (Exception e) {
            System.err.println("Error durante la generacion de archivos: " + e.getMessage());
        }
    }

    public static void createSalesManInfoFile(int salesmanCount) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("vendedores.txt"))) {
            Random rand = new Random();
            for (int i = 1; i <= salesmanCount; i++) {
                String tipoDoc = TIPOS_DOC[rand.nextInt(TIPOS_DOC.length)];
                long numDoc = 10000000L + i;
                String nombre = NOMBRES[rand.nextInt(NOMBRES.length)];
                String apellido = APELLIDOS[rand.nextInt(APELLIDOS.length)];

                bw.write(tipoDoc + ";" + numDoc + ";" + nombre + ";" + apellido);
                bw.newLine();
            }
        }
    }

    public static void createProductsFile(int productsCount) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("productos.txt"))) {
            Random rand = new Random();
            for (int i = 1; i <= productsCount; i++) {
                int idProducto = i;
                String nombreProducto = "Producto_" + idProducto;
                double precioUnitario = 5000 + (rand.nextInt(20) * 1000);

                bw.write(idProducto + ";" + nombreProducto + ";" + precioUnitario);
                bw.newLine();
            }
        }
    }

    public static void createSalesMenFile(int randomSalesCount, String name, long id) throws IOException {
        String fileName = "Ventas_" + name + "_" + id + ".txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            Random rand = new Random();

            bw.write("CC;" + id);
            bw.newLine();

            int totalVentas = rand.nextInt(randomSalesCount) + 1;
            for (int i = 0; i < totalVentas; i++) {
                int idProducto = rand.nextInt(10) + 1;
                int cantidadVendida = rand.nextInt(5) + 1;

                bw.write(idProducto + ";" + cantidadVendida + ";");
                bw.newLine();
            }
        }
    }
}