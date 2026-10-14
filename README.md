package src;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Clase principal encargada de leer los archivos de datos generados,
 * procesar las ventas, calcular totales y generar los reportes CSV solicitados.
 * No requiere interacción con el usuario.
 */
public class main {

    // Rutas relativas basadas en la estructura del proyecto
    private static final String CARPETA_DATOS = "data/";
    private static final String ARCHIVO_PRODUCTOS = CARPETA_DATOS + "productos.txt";
    private static final String ARCHIVO_VENDEDORES = CARPETA_DATOS + "vendedores.txt";
    private static final String REPORTE_VENDEDORES = "reporte_vendedores.csv";
    private static final String REPORTE_PRODUCTOS = "reporte_productos.csv";

    public static void main(String[] args) {
        try {
            // 1. Cargar productos
            Map<String, Producto> productosMap = cargarProductos();
            
            // 2. Cargar vendedores
            Map<String, Vendedor> vendedoresMap = cargarVendedores();
            
            // 3. Procesar archivos de ventas de los vendedores
            procesarVentas(vendedoresMap, productosMap);
            
            // 4. Generar reporte de vendedores ordenado de mayor a menor recaudación
            generarReporteVendedores(vendedoresMap);
            
            // 5. Generar reporte de productos vendidos ordenado en forma descendente
            generarReporteProductos(productosMap);

            // Mensaje de finalización exitosa obligatorio
            System.out.println("¡Proceso finalizado con éxito! Los archivos de reporte han sido generados correctamente.");

        } catch (Exception e) {
            // Mensaje de error en caso de que algo salga mal
            System.err.println("Error durante la ejecución del programa: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Carga el archivo de productos y los almacena en un mapa.
