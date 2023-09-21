package co.edu.unicartagena.utils;

/*
;==========================================
; NOMBRE:                   CÓDIGO
; PABLO HERNÁNDEZ MELÉNDEZ  0221910052
; JHOY CASTRO CASANOVA      0221910044
; GABRIEL LARA MONTIEL      0222110057
;==========================================
 */

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;

/**
 * Clase para manejar una lista de productos.
 *
 * @author
 *         <ul>
 *         <li>Pablo Hernández</li>
 *         <li>Jhoy Castro</li>
 *         <li>Gabriel Lara</li>
 *         </ul>
 */
public class ItemList {
  private Producto[] list;
  /**
   * Tamaño máximo de la arreglo.
   */
  public static final byte MAX = 50;

  /**
   * Formato de la cabecera de la lista.
   */
  public static final String HEADER_FORMAT = String.format("%-10s%-52s%-52s%-6s%s",
          "ID", "Nombre", "Proveedor", "Tipo", "Precio");

  /**
   * Ruta del archivo de productos.
   */
  public final static Path PATH = Paths.get(".", "output", "items_Amazon.txt").normalize();

  /**
   * Tamaño de el arreglo.
   */
  private int size = 0;

  /**
   * Enumeración para el criterio de ordenamiento.
   */
  public enum CriterioOrden {
    precio, nombre
  }

  /**
   * Constructor de la clase ItemList.
   */
  public ItemList() {
    this.list = new Producto[MAX];
  }

  /**
   * Agrega un producto a la lista.
   *
   * @param id        id del producto
   * @param nombre    nombre del producto
   * @param proveedor proveedor del producto
   * @param tipo      tipo del producto
   * @param precio    precio del producto
   */
  public void add(String id, String nombre, String proveedor, Producto.Tipo tipo, double precio)
          throws IllegalArgumentException {
    if (buscarItem(id) != null) {
      throw new IllegalArgumentException("La ID del producto ya se encuentra registrada.");
    }

    if (precio < 0) {
      throw new IllegalArgumentException("El precio no puede ser negativo");
    }

    Producto nuevo = new Producto(id, nombre, proveedor, tipo, precio);

    list[size] = nuevo;
    size++;
  }

  /**
   * Agrega un producto a la lista (Alternativo).
   *
   * @param id        id del producto (8 caracteres)
   * @param nombre    nombre del producto (máximo 50 caracteres)
   * @param proveedor proveedor del producto (máximo 50 caracteres)
   * @param tipo      tipo del producto (1, 2 o 3)
   * @param precio    precio del producto (positivo)
   * @throws IllegalArgumentException si el tipo es inválido o el precio es negativo
   */
  public void add(String id, String nombre, String proveedor, byte tipo, double precio)
          throws IllegalArgumentException {

    for (Producto.Tipo t : Producto.Tipo.values()) {
      if (t.getValue() == tipo) {
        add(id, nombre, proveedor, t, precio);
        return;
      }
    }

    throw new IllegalArgumentException(
            "Tipo de producto inválido. Los tipos de producto son:\n"
                    + "(1) Artículos de oficina y escuela.\n"
                    + "(2) Artículos electrónica de Oficina.\n"
                    + "(3) Muebles y Lámparas de Oficina.");
  }

  /**
   * Borra un producto de la lista.
   *
   * @param id id del producto
   * @return true si se borró, false si no
   */
  public boolean borrarItem(String id) {
    boolean borrado = false;

    for (int i = 0; i < size; i++) {
      if (list[i].getId().equals(id)) {
        for (int j = i; j < size - 1; j++) {
          list[j] = list[j + 1];
        }
        borrado = true;
        --size;
        break;
      }
    }

    return borrado;
  }

  /**
   * Actualiza un producto de la lista.
   *
   * @param id        id del producto
   * @param nombre    nombre del producto
   * @param proveedor proveedor del producto
   * @param tipo      tipo del producto
   * @param precio    precio del producto
   * @throws IllegalArgumentException si los parámetros son inválidos
   */
  public void actualizarItem(
          String id,
          String nombre,
          String proveedor,
          Producto.Tipo tipo,
          double precio) throws IllegalArgumentException {

    int index = 0;
    for (Producto producto : list) {
      if (producto.getId().equals(id)) {
        list[index] = new Producto(id, nombre, proveedor, tipo, precio);
      }
      index++;
    }

    throw new IllegalArgumentException(
            "El ID de producto ingresado no coincide con los registros.");
  }

  /**
   * Actualiza un producto de la lista (Alternativo).
   *
   * @param id        id del producto
   * @param nombre    nombre del producto
   * @param proveedor proveedor del producto
   * @param tipo      tipo del producto
   * @param precio    precio del producto
   * @throws IllegalArgumentException si el tipo es inválido
   */
  public void actualizarItem(String id, String nombre, String proveedor, int tipo, double precio)
          throws IllegalArgumentException {
    for (Producto.Tipo t : Producto.Tipo.values()) {
      if (t.getValue() == tipo) {
        actualizarItem(id, nombre, proveedor, t, precio);
      }
    }

    throw new IllegalArgumentException("Tipo de producto inválido");
  }

  /**
   * Busca un producto en la lista.
   *
   * @param id id del producto
   * @return producto
   */
  public Producto buscarItem(String id) {
    for (int i = 0; i < size; i++) {
      if (list[i].getId().equals(id)) {
        return list[i];
      }
    }

    return null;
  }

  /**
   * Busca un producto en la lista.
   *
   * @param id id del producto
   * @return true si existe, false si no
   */
  public boolean existeItem(String id) {
    for (int i = 0; i < size; i++) {
      if (list[i].getId().equals(id)) {
        return true;
      }
    }

    return false;
  }

  /**
   * Lista todos los productos de la lista.
   */
  public void listarItems() {
    if (size > 0) {
      System.out.println(HEADER_FORMAT);
    }

    for (int i = 0; i < size; i++) {
      System.out.println(list[i].toString());
    }
  }

  /**
   * Ordena los productos de la lista de acuerdo al criterio.
   *
   * @param criterio criterio de ordenamiento
   */
  public void ordenarItems(CriterioOrden criterio) {
    // merge sort
    if (criterio == CriterioOrden.precio) {
      mergeSortPrecio(list, 0, size - 1);
    }

    if (criterio == CriterioOrden.nombre) {
      mergeSortNombre(list, 0, size - 1);
    }
  }

  /**
   * Lista los productos de la lista de acuerdo al rango de precios.
   *
   * @param min limite inferior
   * @param max limite superior
   */
  public void listarPorRango(double min, double max) {
    System.out.println(HEADER_FORMAT);
    for (Producto producto : list) {
      if (producto.getPrecio() >= min && producto.getPrecio() <= max) {
        System.out.println(producto);
      }
    }
  }

  /**
   * Guarda los productos de la lista en un archivo.
   */
  public void guardarItems() {
    try {
      Files.deleteIfExists(PATH);
      Files.createDirectories(PATH.getParent());


      for (int i = 0; i < size; i++) {
        String line = list[i].getId() + ";"
                + list[i].getNombre() + ";"
                + list[i].getProveedor() + ";"
                + list[i].getTipo() + ";"
                + list[i].getPrecio() + "\n";

        byte[] bytes = line.getBytes(StandardCharsets.UTF_8);

        Files.write(PATH, bytes,
                StandardOpenOption.CREATE,
                StandardOpenOption.APPEND);

      }
    } catch (IOException e) {
      System.out.println("Error: Fallo al guardar la lista en " + e.getMessage() + "\n" + e);
    }
  }

  /**
   * Devuelve el tamaño de la lista.
   * @return tamaño de la lista
   */
  public int size() {
    return size;
  }

  /**
   * Carga los productos de la lista desde un archivo.
   *
   * @throws FileNotFoundException si el archivo no existe
   */
  public void cargarItems() throws FileNotFoundException {
    if (Files.notExists(PATH)) {
      throw new FileNotFoundException(String.format(
              "El archivo %s no existe. Para cargar productos al programa por favor "
                      + "ingréselos manualmente o cree el archivo.",
              PATH.toAbsolutePath()));
    }
    try (Stream<String> lines = Files.lines(PATH)) {
      this.list = new Producto[MAX];
      this.size = 0;

      lines.forEach((line) -> {
        String[] partes = line.split(";");

        if (partes.length == 5) {
          String id = partes[0];

          if (!existeItem(id)) {
            String nombre = partes[1];
            String proveedor = partes[2];
            byte tipo = Byte.parseByte(partes[3]);
            double precio = Double.parseDouble(partes[4]);

            this.add(id, nombre, proveedor, tipo, precio);
          }
        }
      });
    } catch (IOException e) {
      System.out.println("Error: " + e.getMessage());
    }
  }

  /**
   * Ordena los productos de la lista por precio.
   *
   * @param list lista de productos
   * @param l    punto de inicio
   * @param r    punto final
   */
  private void mergeSortPrecio(Producto[] list, int l, int r) {
    if (l < r) {
      int m = (l + r) / 2;

      mergeSortPrecio(list, l, m);
      mergeSortPrecio(list, m + 1, r);

      merge(list, l, m, r, CriterioOrden.precio);
    }
  }

  /**
   * Condición de ordenamiento por precio.
   *
   * @param p1 producto 1
   * @param p2 producto 2
   * @return true si p1 es menor o igual a p2, false si no
   */
  private boolean mergeConditionPrecio(Producto p1, Producto p2) {
    return (p1.getPrecio() <= p2.getPrecio());
  }

  /**
   * Condición de ordenamiento por nombre.
   *
   * @param p1 producto 1
   * @param p2 producto 2
   * @return true si p1 es menor o igual a p2, false si no
   */
  private boolean mergeConditionNombre(Producto p1, Producto p2) {
    return p1.getNombre().compareTo(p2.getNombre()) <= 0;
  }

  /**
   * Paso merge para merge sort.
   * @param list lista de productos
   * @param l punto de inicio
   * @param m punto medio
   * @param r punto final
   * @param criterio criterio de ordenamiento
   */
  private void merge(Producto[] list, int l, int m, int r, CriterioOrden criterio) {
    int n1 = m - l + 1;
    int n2 = r - m;

    Producto[] left = new Producto[n1];
    Producto[] right = new Producto[n2];


    System.arraycopy(list, l, left, 0, n1);
    System.arraycopy(list, m + 1,  right, 0, n2);

    int i = 0;
    int j = 0;
    int k = l;

    while (i < n1 && j < n2) {
      if (criterio == CriterioOrden.precio && mergeConditionPrecio(left[i], right[j])
              || criterio == CriterioOrden.nombre && mergeConditionNombre(left[i], right[j])) {

        list[k] = left[i];
        i++;
      } else {
        list[k] = right[j];
        j++;
      }
      k++;
    }

    while (i < n1) {
      list[k] = left[i];
      i++;
      k++;
    }

    while (j < n2) {
      list[k] = right[j];
      j++;
      k++;
    }
  }

  /**
   * Ordena los productos de la lista por nombre.
   *
   * @param list lista de productos
   * @param l    punto de inicio
   * @param r    punto final
   */
  private void mergeSortNombre(Producto[] list, int l, int r) {
    if (l < r) {
      int m = (l + r) / 2;

      mergeSortNombre(list, l, m);
      mergeSortNombre(list, m + 1, r);

      merge(list, l, m, r, CriterioOrden.nombre);
    }
  }
}
