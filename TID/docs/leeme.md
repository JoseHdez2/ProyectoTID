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
2. Poblar la **lista maestra** con los tropes.
3. Parsear y agregar los **géneros** (con sus tropes) a un vector.
4. Asignar a cada trope de la **lista maestra** un género.
5. Realizar el conteo de los géneros de los tropes de cada serie.
6. Crear el archivo Weka.

####Implementación

Para conocer toda la implementación, sólo se require consultar:
* `ColeccionSeries.java` *(~200 líneas)*
* `Serie.java` *(~40 líneas)*
* `Genero.java` *(~40 líneas) (es igual a Serie.java)*
* `ListaMaestraTropes.java` *(~50 líneas)*

Estos son los archivos de código importantes, y contienen todo el código *completamente comentado*.
