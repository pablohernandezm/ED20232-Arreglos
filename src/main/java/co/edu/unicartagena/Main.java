package co.edu.unicartagena;

/*
;==========================================
; NOMBRE:                   CÓDIGO
; PABLO HERNÁNDEZ MELÉNDEZ  0221910052
; JHOY CASTRO CASANOVA      0221910044
; GABRIEL LARA MONTIEL      0222110057
;==========================================
*/

import co.edu.unicartagena.utils.ItemList;
import co.edu.unicartagena.utils.Producto;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Aplicación para el manejo de un inventario de productos.
 * Cada producto tiene los siguientes atributos:
 * - ID
 * - Nombre
 * - Tipo
 * - Proveedor
 * - Precio
 *
 * <p>La aplicación permite:
 * - Adicionar productos
 * - Borrar productos
 * - Actualizar productos
 * - Buscar productos
 * - Listar productos
 * - Ordenar productos
 * - Listar productos por rango de precio
 * - Guardar productos en un archivo de texto
 * - Cargar productos desde un archivo de texto
 *
 * @author
 *         <ul>
 *         <li>Pablo Hernández</li>
 *         <li>Jhoy Castro</li>
 *         <li>Gabriel Lara</li>
 *         </ul>
 *
 */
public final class Main {
  private static final ItemList lista = new ItemList();
  private static final Scanner sc = new Scanner(System.in);

  /**
   * Método de ejecución de la aplicación.
   *
   * @param args argumentos de la línea de comandos
   */

  public static void main(String[] args) {
    menu();
    sc.close();
  }

  /**
   * Método para pedir una opción al usuario.
   *
   * @return opción elegida por el usuario
   * @throws IllegalArgumentException si la opción no está en el rango
   */
  @SuppressWarnings("SpellCheckingInspection")
  private static byte pedirOpcion() throws IllegalArgumentException {
    System.out.print("\nOpción a elegir: ");
    String optionString = sc.nextLine();
    byte opcion = Byte.parseByte(optionString);

    if (opcion < (byte) 0 || opcion > (byte) 9) {
      throw new IllegalArgumentException(
              String.format("Opción no válida. El rango de opciones está entre %d y %d",
                      (byte) 0,
                      (byte) 9));
    }

    return opcion;
  }

  /**
   * Método para limpiar la consola.
   */
  private static void clearConsole() {
    try {
      Thread.sleep(500);

      // Windows
      new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();

      // Sistemas basados en unix (Linux, macOS, etc.)
      System.out.print("\033[H\033[2J");
      System.out.flush();

    } catch (IOException e) {
      System.out.println("Error: " + e.getMessage());
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Método para esperar un número de segundos.
   */
  private static void freeze() {
    try {
      Thread.sleep(3 * 1000);
    } catch (InterruptedException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  /**
   * Método para mostrar el menú de la aplicación.
   * El menú se muestra hasta que el usuario elija la opción de salir.
   */
  @SuppressWarnings("SpellCheckingInspection")
  public static void menu() {
    do {
      clearConsole();

      System.out.println("Menu");
      System.out.println("1. Adicionar Items");
      System.out.println("2. Borrar Items");
      System.out.println("3. Actualizar Items");
      System.out.println("4. Buscar Items");
      System.out.println("5. Listar Items");
      System.out.println("6. Ordenar Items");
      System.out.println("7. Listar con precio Mayor que x o Menor a y");
      System.out.println("8. Almacenar información en un archivo txt");
      System.out.println("9. Cargar información desde un archivo txt");
      System.out.println("0. Salir");

      try {
        byte opcion = pedirOpcion();
        clearConsole();

        switch (opcion) {
          case 1:
            adicionarItems();
            break;
          case 2:
            borrarItems();
            break;
          case 3:
            actualizarItems();
            break;
          case 4:
            buscarItems();
            break;
          case 5:
            listarItems();
            break;
          case 6:
            ordenarItems();
            break;
          case 7:
            listarPorRango();
            break;
          case 8:
            guardarItems();
            break;
          case 9:
            cargarItems();
            break;
          case 0:
            System.exit(0);
            break;
          default:
            break;
        }
      } catch (IllegalArgumentException e) {
        System.out.println("Error: " + e.getMessage());
        freeze();
      } catch (Exception e) {
        System.out.println("Error: " + e.getMessage());
        sc.nextLine();
      }
    } while (true);

  }

  /**
   * Método para adicionar items a la lista.
   * El método se llama recursivamente hasta que el usuario no quiera adicionar
   * más items.
   */
  public static void adicionarItems() {
    System.out.println("ADICIONAR ITEMS");
    System.out.println("\nEscribe los datos del producto: ");

    // Imprimir los datos a llenar
    System.out.print("\nID: ");
    String id = sc.nextLine();

    System.out.print("\nNombre: ");
    String nombre = sc.nextLine();

    System.out.printf("\n\nTipos: \n(%d) %s. \n(%d) %s. \n(%d) %s.\n Elección(entero): ",
            Producto.Tipo.OFICINA_Y_ESCUELA.getValue(),
            Producto.Tipo.OFICINA_Y_ESCUELA,
            Producto.Tipo.ELECTRONICA_OFICINA.getValue(),
            Producto.Tipo.ELECTRONICA_OFICINA,
            Producto.Tipo.MUEBLES_Y_LAMPARA_OFICINA.getValue(),
            Producto.Tipo.MUEBLES_Y_LAMPARA_OFICINA);

    byte tipo = sc.nextByte();
    sc.nextLine();

    System.out.print("\nProveedor: ");
    String proveedor = sc.nextLine();

    System.out.print("\nPrecio: ");
    double precio = sc.nextDouble();
    sc.nextLine();

    try {
      lista.add(id, nombre, proveedor, tipo, precio);
    } catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    }

    // Salir del menú si el usuario lo desea
    System.out.println("\n¿Desea añadir otro producto? (s/n)");
    String respuesta;
    do {
      respuesta = sc.nextLine();
    } while (!respuesta.equalsIgnoreCase("s") && !respuesta.equalsIgnoreCase("n"));

    if (respuesta.equals("s")) {
      adicionarItems();
    }
  }

  /**
   * Método para borrar items de la lista.
   * El método pide el ID del producto a borrar y lo elimina de la lista.
   */
  public static void borrarItems() {
    System.out.println("BORRAR ITEMS");
    System.out.print("Ingrese la ID del producto a eliminar: ");
    String idToDelete = sc.nextLine();

    if (lista.borrarItem(idToDelete)) {
      System.out.println("\nProducto eliminado correctamente.\n");
    } else {
      System.out.println("\nProducto no encontrado.\n");
    }

    freeze();
  }

  /**
   * Método para actualizar items de la lista.
   * El método pide el ID del producto a actualizar y los nuevos datos del
   * producto.
   */
  public static void actualizarItems() {
    System.out.println("ACTUALIZAR ITEMS");
    System.out.print("Ingrese la ID del producto a actualizar: ");
    String idToUpdate = sc.nextLine();

    System.out.print("\nIngrese el nuevo nombre del producto: ");
    String nuevoNombre = sc.nextLine();

    System.out.print("\n Ingrese el nuevo proveedor: ");
    String nuevoProveedor = sc.nextLine();

    System.out.print("\n"
            + "Ingrese el nuevo tipo de producto"
            + "(1 - Oficina y Escuela, 2 - Electronica Oficina, 3 - Muebles y Lámpara Oficina): ");
    int nuevoTipo = sc.nextInt();
    sc.nextLine();

    System.out.println("\nIngrese el nuevo precio: ");
    double nuevoPrecio = sc.nextDouble();
    sc.nextLine();

    try {
      lista.actualizarItem(idToUpdate, nuevoNombre, nuevoProveedor, nuevoTipo, nuevoPrecio);
      System.out.println("\nProducto actualizado correctamente.");
      freeze();
      clearConsole();
    } catch (IllegalArgumentException e) {
      System.out.println("Error: " + e.getMessage());
    }

  }

  /**
   * Método para buscar items de la lista.
   * El método pide el ID del producto a buscar y lo muestra en pantalla.
   */
  public static void buscarItems() {
    System.out.println("BUSCAR ITEMS");
    System.out.print("Ingrese la ID del producto a buscar: ");
    String idBuscar = sc.nextLine();

    Producto productoEncontrado = lista.buscarItem(idBuscar);
    if (productoEncontrado != null) {
      System.out.println(ItemList.HEADER_FORMAT);
      System.out.println(productoEncontrado);
      sc.nextLine();
    } else {
      System.out.println("Error: Producto no encontrado.");
      freeze();
    }
  }

  /**
   * Método para listar items de la lista.
   * El método muestra todos los items de la lista en pantalla.
   */
  public static void listarItems() {
    clearConsole();
    System.out.println("LISTADO DE PRODUCTOS");
    lista.listarItems();
    sc.nextLine();
  }

  /**
   * Método para ordenar items de la lista.
   * El método pide la opción de ordenamiento y ordena la lista.
   */
  @SuppressWarnings("SpellCheckingInspection")
  public static void ordenarItems() {
    System.out.println("1. Ordenar por precio");
    System.out.println("2. Ordenar por nombre\n");

    System.out.print("Ingrese la opción de ordenamiento: ");
    int opcionOrdenamiento = sc.nextInt();
    sc.nextLine();

    if (opcionOrdenamiento == 1) {
      lista.ordenarItems(ItemList.CriterioOrden.precio);
    } else if (opcionOrdenamiento == 2) {
      lista.ordenarItems(ItemList.CriterioOrden.nombre);
    } else {
      System.out.println("Error: opción no valida.");
      freeze();
    }
  }

  /**
   * Método para listar items de la lista por rango de precio.
   * El método pide el precio mínimo y máximo y muestra los productos en ese
   * rango.
   */
  public static void listarPorRango() {
    System.out.print("Ingrese el precio mínimo: ");
    final double min = sc.nextDouble();
    sc.nextLine();

    System.out.print("\nIngrese el precio máximo: ");
    final double max = sc.nextDouble();
    sc.nextLine();

    lista.listarPorRango(min, max);
    sc.nextLine();
  }

  /**
   * Método para guardar items de la lista en un archivo de texto.
   * El método guarda los items en un archivo llamado items_Amazon.txt.
   */
  public static void guardarItems() {
    lista.guardarItems();
    System.out.printf("Productos guardados en %s.", ItemList.PATH);
    sc.nextLine();
  }

  /**
   * Método para cargar items de un archivo de texto a la lista.
   * El método carga los items desde un archivo llamado items_Amazon.txt.
   */
  public static void cargarItems() {
    try {
      lista.cargarItems();
    } catch (FileNotFoundException e) {
      System.out.println("Error: " + e.getMessage());
      sc.nextLine();
    }
    System.out.print("Productos cargados desde items_Amazon.txt.");
    freeze();
  }
}
