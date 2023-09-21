package co.edu.unicartagena.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.nio.file.Files;

public class ItemListTest {

  @Test
  public void crearLista(){
    ItemList lista = new ItemList();
    Assertions.assertNotNull(lista, "La lista no se creó");
  }

  @Test
  public void agregar(){
    ItemList lista = new ItemList();

    Assertions.assertDoesNotThrow(() -> lista.add(RandomStringUtils.randomAlphanumeric(8),
            "GeForce RTX 4090", "Nvidia",
            Producto.Tipo.ELECTRONICA_OFICINA,
            13999000), "No se pudo agregar el producto");

    Assertions.assertEquals(1, lista.size(), "El tamaño de la lista no es correcto");
  }

  @Test
  public void borrar(){
    ItemList lista = new ItemList();

    String id1 = RandomStringUtils.randomAlphanumeric(8);
    lista.add(id1, "GeForce RTX 4090", "Nvidia", Producto.Tipo.ELECTRONICA_OFICINA, 13999000);

    String id2 = RandomStringUtils.randomAlphanumeric(8);
    lista.add(id2, "GeForce RTX 3050", "Nvidia", Producto.Tipo.ELECTRONICA_OFICINA, 3299000);

    Assertions.assertDoesNotThrow(() -> lista.borrarItem(id1), "No se pudo borrar el producto");
    Assertions.assertEquals(1, lista.size(), "El tamaño de la lista no es correcto");

    Assertions.assertDoesNotThrow(() -> lista.borrarItem(id2), "No se pudo borrar el producto");
    Assertions.assertEquals(0, lista.size(), "El tamaño de la lista no es correcto");
  }

  @Test
  public void guardarItems(){
    ItemList lista = new ItemList();

    String[] productos = {"papa", "arroz", "frijol", "carne", "pollo", "pescado", "leche", "huevos", "queso", "yogurt"};
    String[] proveedores = {"Alpina", "Colanta", "Colombina", "Diana", "Zenú", "Pilgrim's", "Bavaria", "Coca-Cola", "Pepsi", "Postobón"};


    for (int i=0; i<30; i++){
      String producto = productos[(int) (Math.random() * productos.length)];
      String proveedor = proveedores[(int) (Math.random() * proveedores.length)];
      String id = RandomStringUtils.randomAlphanumeric(8);
      byte tipo = (byte) ((Math.random() * 3) + 1);
      double precio = (Math.random() * 100000) +500;

      lista.add(id, producto, proveedor, Producto.Tipo.values()[tipo-1], precio);
    }

    lista.guardarItems();
    Assertions.assertTrue(Files.exists(ItemList.PATH), "El archivo no existe");

  }

  @Test
  public void cargarItems(){
    ItemList lista = new ItemList();
    lista.guardarItems();
    Assertions.assertThrows(FileNotFoundException.class, lista::cargarItems, "No se pudo cargar el archivo");

    guardarItems();
    Assertions.assertDoesNotThrow(lista::cargarItems, "No se pudo cargar el archivo luego de ser cargado y guardado.");

    Assertions.assertNotEquals(lista.size(), 0, "La lista no se cargó correctamente.");
    Assertions.assertDoesNotThrow(lista::cargarItems, "No se pudo cargar el archivo luego de ser cargado anteriormente.");

  }
}
