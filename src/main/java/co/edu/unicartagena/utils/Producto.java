package co.edu.unicartagena.utils;

/*
;==========================================
; NOMBRE:                   CÓDIGO
; PABLO HERNÁNDEZ MELÉNDEZ  0221910052
; JHOY CASTRO CASANOVA      0221910044
; GABRIEL LARA MONTIEL      0222110057
;==========================================
 */

/**
 * Clase para manejar un producto.
 *
 * @author
 *         <ul>
 *         <li>Pablo Hernández</li>
 *         <li>Jhoy Castro</li>
 *         <li>Gabriel Lara</li>
 *         </ul>
 */
public class Producto {
  private final String id; // 8 caracteres
  private final String nombre; // 50 caracteres
  private final String proveedor; // 50 caracteres
  private final byte tipo; // 1, 2 o 3
  private final double precio; // 2 decimales

  /**
   * Enumeración para el tipo de producto.
   */
  public enum Tipo {
    ELECTRONICA_OFICINA((byte) 2),
    @SuppressWarnings("SpellCheckingInspection")
    MUEBLES_Y_LAMPARA_OFICINA((byte) 3),
    OFICINA_Y_ESCUELA((byte) 1);

    private final byte value;
    private final String string;

    public byte getValue() {
      return value;
    }

    public String toString() {
      return string;
    }

    Tipo(final byte value) {
      this.value = value;

      if (value == 1) {
        string = "Artículos de oficina y escuela";
      } else if (value == 2) {
        string = "Artículos electrónica de Oficina";
      } else {
        string = "Muebles y Lámparas de Oficina";
      }
    }
  }

  /**
   * Constructor de la clase Producto.
   *
   * @param id        ID del producto
   * @param nombre    nombre del producto
   * @param proveedor proveedor del producto
   * @param tipo      tipo de producto
   * @param precio    precio del producto
   */
  public Producto(String id, String nombre, String proveedor, Tipo tipo, double precio)
          throws IllegalArgumentException {
    validarAtributos(id, nombre, proveedor, precio);

    this.id = id;
    this.nombre = nombre;
    this.proveedor = proveedor;
    this.tipo = tipo.getValue();
    this.precio = Double.parseDouble(String.format("%.2f", precio).replace(",", "."));
  }

  /**
   * Validar los atributos del producto. El tipo de producto no se valida aquí, ya
   * que se valida al momento de crear el objeto.
   *
   * @param id        ID del producto
   * @param nombre    nombre del producto
   * @param proveedor proveedor del producto
   * @param precio    precio del producto
   */
  private void validarAtributos(String id, String nombre, String proveedor, double precio)
          throws IllegalArgumentException {
    if (id == null || id.length() != 8) {
      throw new IllegalArgumentException("EL ID debe tener exactamente 8 caracteres.");
    }

    if (nombre == null || nombre.isEmpty() || nombre.length() > 50) {
      throw new IllegalArgumentException("El nombre no puede tener más de 50 caracteres.");
    }

    if (proveedor == null || proveedor.isEmpty() || proveedor.length() > 50) {
      throw new IllegalArgumentException("El proveedor no puede tener más de 50 caracteres.");
    }

    if (precio < 0) {
      throw new IllegalArgumentException("El precio no puede ser menor que 0.");
    }
  }

  /**
   * Obtener el ID del producto.
   *
   * @return ID
   */
  public String getId() {
    return id;
  }

  /**
   * Obtener el nombre del producto.
   *
   * @return nombre
   */
  public String getNombre() {
    return nombre;
  }

  /**
   * Obtener el proveedor del producto.
   *
   * @return proveedor
   */
  public String getProveedor() {
    return proveedor;
  }

  /**
   * Obtener el tipo de producto.
   *
   * @return tipo
   */
  public int getTipo() {
    return tipo;
  }

  /**
   * Obtener el precio del producto.
   *
   * @return precio
   */
  public double getPrecio() {
    return precio;
  }

  /**
   * Obtener el tipo de producto como String.
   *
   * @return tipo de producto
   */
  @Override
  public String toString() {
    return String.format("%-10s%-52s%-52s%-6s%.2f", id, nombre, proveedor, tipo, precio);
  }
}
