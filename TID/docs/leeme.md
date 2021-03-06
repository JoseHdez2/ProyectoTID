###Programa de preprocesamiento

La base del programa se fundamenta en la clase **ColeccionSeries**, y su constructor.

####Estructuras

Existen tres estructuras de datos:
* **Series**, que serán los individuos de la población estadística.
* **Géneros**, que sirven para asignar el género a cada trope, y por tanto podrían perfectamente descartarse después de haber realizado el **paso 4**.
* **Lista maestra**, que contendrá todos los tropes, y la relación de los mismos con el género al que pertenecen.

####Pasos

Los pasos a seguir son:

1. Parsear y agregar las **series** (con sus tropes) a un vector.
2. Parsear y agregar los **géneros** (con sus tropes) a un vector.
3. Asignar a cada trope de la **lista maestra** un género.
4. Realizar el conteo de los géneros de los tropes de cada serie.
5. Crear el archivo Weka.

####Implementación

La parte de la implementación que concierne al proyecto está contenida en el paquete `tvtropes_data_mining`.

Para conocer toda la implementación, se require consultar las siguientes clases:


Clase | Líneas | Descripción
-|-|-
`Main.java` | ~15 | Sólo llama al constructor de `ColeccionSeries.java`
`ColeccionSeries.java` | ~200 | Clase principal
`Serie.java` | ~40 | Pequeña estructura de datos
`Genero.java` | ~40 | Clase similar a `Serie.java`
`ListaMaestraTropes.java` | ~50 | Estructura simple basada en un hash


Estos son los archivos de código importantes, y contienen todo el código *completamente comentado*.

Opcionalmente, se puede también mirar:

* `TropesParser.java` *(~30 líneas)*: Se usa en los constructores de Serie y Genero.
