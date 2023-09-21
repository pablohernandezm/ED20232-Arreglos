# Descripción del problema
Amazon.com, la compañía de comercio electrónico más conocida de la red, lo ha contratado a usted para que desarrolle un nuevo producto. Dicho producto debe administrar un número limitado de ítems en su departamento de equipamiento para la oficina. Amazon quiere un prototipo inicial que permita gestionar los siguientes datos:
- Item ID (consta de 8 caracteres alfanuméricos)
- Nombre (consta de 50 caracteres alfabéticos)
- Proveedor (consta de 50 caracteres alfabéticos)
- Tipo (Artículos de Oficina y Escuela, Artículos Electrónica de Oficina, Muebles y Lámparas de Oficina) (cada tipo puede ser identificado con un número entero positivo mayor que 0; por ejemplo, 1, 2, o 3).
- Precio (valor numérico con una parte entera y otra decimal. La parte
  decimal, hasta dos posiciones).

---
1. Su responsabilidad en dicho producto se limita a la implementación de una estructura de datos (basado en arreglos estáticos) que permita la gestión la información anteriormente mencionada.
2. Adicionalmente, Amazon.com ha listado un conjunto de requerimientos para ser tenidos en cuenta el producto debe permitir:
    - Adicionar nuevos ítems (dos ítems no pueden tener el mismo ID)
    - Borrar ítems
    - Actualizar ítems
    - Buscar ítems (Por ID, Tipo)
    - Listar ítems.
    - Ordenar ítems por precio, o por nombre.
    - Listar los ítems cuyo precio sea mayor a x y menor a y
    - Almacenar la información en un archivo denominado items_Amazon.txt
    - Cargar información almacenada en archivos denominados items_Amazon.txt (los datos de cada registro se separan por un “punto y coma” y cada registro descrito en cada línea del archivo .txt)

3. Amazon.com ha estimado que en las pruebas del producto solo se tengan
   en cuenta 50 ítems.

4. Según su especificación, en el peor de los casos cuanta memoria, en
   tiempo de ejecución, se requiere para gestionar la información.

# Equipo de trabajo
- [Pablo José Hernández meléndez](https://github.com/pablohernandezm)
- [Jhoy Luis Castro Casanova](https://www.linkedin.com/in/jhoy-luis-castro-casanova-061142249/)
- [Gabriel David Lara Montiel](https://www.linkedin.com/in/gabriel-david-lara-montiel-367933288/)

# Memoria consumida en el peor de los casos por el arreglo de elementos
***
Los datos almacenados en el arreglo son los siguientes:
- **ItemID**: Identificador único alfanumérico de 8 caracteres.
- **Nombre**: Nombre del item de hasta 50 caracteres.
- **Proveedor**: Nombre del proveedor del item de hasta 50 caracteres.
- **Tipo**: Identificador numérico del tipo de item (1-3).
- **Precio**: Precio del item.


Según las características de los datos podemos agrupar los elementos de acuerdo a su tipo, de la siguietne manera:
- **strings**: ItemID, Nombre, Proveedor.
- **byte** tipo.
- **double** precio.

El tipo de dato _String_ consta de 2 bytes por carácter, por lo que el tamaño de tipo String de los campos serían los siguientes:
- **ItemID**: 8 caracteres * 2 bytes = 16 bytes.
- **Nombre**: 50 caracteres * 2 bytes = 100 bytes.
- **Proveedor**: 50 caracteres * 2 bytes = 100 bytes.

El tipo de dato _byte_ consta de 1 byte y abarca el rango de datos más cercano disponible en java para cubrir el rango de 1-3 requerido por las especificaciones.
- **Tipo**: 1 byte.

El tipo de dato _double_ consta de 8 bytes.
- **Precio**: 8 bytes.

Por lo tanto, podemos calcular el tamaño total del arreglo de elementos de la siguiente manera:
`(16+100+100+1+8) * 50 = 11250B = 11.25kB`

El máximo que almacenará el arreglo en tiempo de ejecución es de 50 elementos, por lo que el tamaño máximo del arreglo será de:
`11.25kB`.
