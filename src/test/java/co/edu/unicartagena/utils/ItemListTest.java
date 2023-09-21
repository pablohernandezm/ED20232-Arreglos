package co.edu.unicartagena.utils;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;

public class ItemListTest {

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
    Assertions.assertDoesNotThrow(lista::cargarItems, "No se pudo cargar el archivo");
  }
}