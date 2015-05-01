package tvtropes_data_mining;

import java.util.ArrayList;
import java.util.Collections;

import useful.file.DirectoryFileLister;
import useful.file.StringToFile;

/*
 * La clase más importante del programa.
 * Contiene todas las estructuras de datos necesarias, 
 * y la función más importante, que resulta ser el constructor.
 */
public class ColeccionSeries {
	
	// Vector con las series, cada una con sus tropes.
	ArrayList<Serie> series = new ArrayList<Serie>();
	
	// Vector con los géneros, cada uno con sus tropes.
	ArrayList<Genero> generos = new ArrayList<Genero>();
	
	// Hash con cada trope asignado al género al que pertenece.
	// Le das el nombre de un trope y te devuelve su género.
	ListaMaestraTropes listaMaestra = new ListaMaestraTropes();
	
	/**
	 * Esta es la función más importante. Es como el verdadero main del programa.
	 * 
	 * @param dirCarpetaTropes	Path absoluto de la carpeta con los HTML de las series.
	 * @param dirCarpetaGeneros	Path absoluto de la carpeta con los HTML de los generos.
	 * @param dirArchivoWeka	Path absoluto de la carpeta donde se creará el archivo Weka.
	 */
	ColeccionSeries(String dirCarpetaTropes, String dirCarpetaGeneros, String dirArchivoWeka){
		// 1er paso: Rellenar el array de series.
		rellenarArraySeries(dirCarpetaTropes);
		
		// 2do paso: Agregar los tropes a la lista maestra.
		listaMaestra.agregarTropes(series);
		
		// 3er paso: Rellenar el array de generos.
		rellenarArrayGeneros(dirCarpetaGeneros);
		
		// 4to paso: Agregar los generos a la lista maestra.
		listaMaestra.agregarGeneros(generos);
		
		// 5to paso: Calcular los porcentajes de generos por cada serie.
		calcularConteoGeneros();
		
		// 6to paso: Generar archivo Weka
		generarArchivoWeka(dirArchivoWeka);
	}
	
	/**
	 * 1er paso: Rellenar el array de series
	 */
	void rellenarArraySeries(String dirCarpetaTropes){
		
		// Obtenemos la lista de archivos en el directorio:
		ArrayList<String> nombresArchivos = DirectoryFileLister.listFilesForFolderPath(dirCarpetaTropes, ".html");
		
		// Ordenamos los archivos alfabeticamente.
		Collections.sort(nombresArchivos);
		
		// Por cada archivo, crear una serie y agregarla:
		for (String nombreArchivo : nombresArchivos){
			
			//Convertimos el filepath relativo de cada serie en un filepath absoluto.
			String direccionSerie = dirCarpetaTropes + nombreArchivo;
			
			// Agregamos al vector de series una nueva serie.
			// El parametro del constructor es el filepath absoluto.
			series.add(new Serie(direccionSerie));
		}
	}
	
	// String que representa al género desconocido.
	// Esto es para contar también los tropes cuyo género no conocemos.
	final String GENERO_DESCONOCIDO = "Desconocido";
	
	/**
	 * 3er paso: Rellenar el array de generos
	 * 
	 * Este método es simétrico al del 1er paso.
	 */
	void rellenarArrayGeneros(String dirCarpetaGeneros){
		
		// Obtenemos la lista de archivos en el directorio:
		ArrayList<String> nombresArchivos = DirectoryFileLister.listFilesForFolderPath(dirCarpetaGeneros, ".html");
		
		// Ordenamos los archivos alfabéticamente.
		Collections.sort(nombresArchivos);
		
		// Por cada archivo, crear un género y agregarlo:
		for (String nombreArchivo : nombresArchivos){
			
			// Convertimos el filepath relativo de cada género en un filepath absoluto.
			String direccionSerie = dirCarpetaGeneros + nombreArchivo;
			
			// Agregamos al vector de géneros un nuevo género.
			// El parametro del constructor es el filepath absoluto.
			generos.add(new Genero(direccionSerie));
		}
	}
	
	/**
	 * 5to paso: calcular el numero de tropes de cada genero por serie
	 */
	void calcularConteoGeneros(){
		
		// Por cada serie...
		for (Serie s : series){
			
			// Primero, inicializamos a 0 para cada género:
			// Por cada género...
			for (Genero g : generos){
				
				// Colocamos el nombre de cada género, asociado a 0 (el conteo).
				s.conteoGeneros.put(g.name, 0);
			}
			
			// Inicializamos a 0 también el contador de tropes con género "desconocido"
			s.conteoGeneros.put(GENERO_DESCONOCIDO, 0);
			
			// Por cada uno de los tropes de la serie...
			for (String t : s.tropes){
				
				// Obtener el género al que pertenece el trope.
				String generoDelTrope = listaMaestra.hashMaestro.get(t);
				
				// Si el trope no tiene género, contarlo como "desconocido"
				if ( generoDelTrope == "" || generoDelTrope == null){
					generoDelTrope = GENERO_DESCONOCIDO;
				}
				
				// Pero si sí tiene, añadir uno al conteo del género correspondiente.
				else {
					int conteo = s.conteoGeneros.get(generoDelTrope);
					s.conteoGeneros.put(generoDelTrope, conteo + 1);
				}
			}
		}
	}
	
	/**
	 * 6to paso: generar archivo Weka
	 */
	void generarArchivoWeka(String dirArchivoWeka){
		
		// String que será el contenido del archivo.
		String wekaContent = "";
		
		// Colocamos lo que viene a ser el "título" del archivo Weka.
		wekaContent = wekaContent.concat("@RELATION series");
		
		// Pasamos a escribir los atributos que tendrá cada caso:
		
		// El nombre de cada serie será un atributo.
		wekaContent = wekaContent.concat("\n@ATTRIBUTE nombreSerie");
		
		// El número de tropes de cada serie será un atributo.
		wekaContent = wekaContent.concat("\n@ATTRIBUTE numeroTropes");
		
		// El género desconocido (su porcentaje en la serie) será un atributo.
		wekaContent = wekaContent.concat("\n@ATTRIBUTE " + GENERO_DESCONOCIDO);
		
		// Cada género (su porcentaje en la serie) será un atributo.
		for (Genero g : generos){
			wekaContent = wekaContent.concat("\n@ATTRIBUTE " + g.name);
		}
		
		// Declaramos que empezaremos a introducir la población de datos.
		wekaContent = wekaContent.concat("\n@DATA");
		
		// Por cada serie...
		for (Serie s : series){
			// Abrimos una nueva línea.
			wekaContent = wekaContent.concat("\n");
			// Apuntamos el nombre de la serie.
			wekaContent = wekaContent.concat(s.name + ",");
			// Apuntamos su número de tropes.
			wekaContent = wekaContent.concat(s.tropes.size() + ",");
			
			// Apuntamos el conteo del género desconocido.
			wekaContent = wekaContent.concat(s.conteoGeneros.get(GENERO_DESCONOCIDO) + ",");
			
			// Por cada género, apuntamos su conteo:
			for (Genero g : generos){

				// Apuntamos el conteo del género.
				wekaContent = wekaContent.concat(s.conteoGeneros.get(g.name) + ",");
			}
			
			// Quitamos la ultima coma de la línea, que sobra.
			wekaContent = wekaContent.substring(0, wekaContent.length()-1);
		}

		StringToFile.stringToFile(dirArchivoWeka, wekaContent);
	}
}
