# Taller 3
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
