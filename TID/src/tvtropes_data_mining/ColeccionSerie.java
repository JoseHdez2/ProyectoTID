package tvtropes_data_mining;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

import useful.array.StringArrayHelper;
import useful.file.DirectoryFileLister;

public class ColeccionSerie {
	
	ArrayList<Serie> series = new ArrayList<Serie>();			//se rellena en el paso 1
	ArrayList<String> masterList = new ArrayList<String>();		//se crea en el paso 2
	ArrayList<Serie> generos = new ArrayList<Serie>();			//se rellena en el paso 3
	ArrayList<String> masterList2 = new ArrayList<String>();	//se crea en el paso 4
	
	ColeccionSerie(String dirCarpetaTropes, String dirCarpetaGeneros, String dirArchivoWeka){
		ArrayList<Serie> series = new ArrayList<Serie>();
		
		//1er paso: Rellenar el array de series
		rellenarArraySeries(dirCarpetaTropes);
		
		//2do paso: hacer la lista maestra
		hacerListaMaestra();
		
		//3er paso: rellenar el array de generos
		rellenarArrayGeneros(dirCarpetaGeneros);
		
		//4to paso: hacer la lista maestra 2 (de generos)
		hacerListaMaestra2();
		
		//5to paso: generar archivo Weka
		generarArchivoWeka(dirArchivoWeka);
	}
	
	/**
	 * 1er paso: Rellenar el array de series
	 */
	void rellenarArraySeries(String dirCarpetaTropes){
		//Obtenemos la lista de archivos en el directorio:
		ArrayList<String> dirsArchivos = DirectoryFileLister.listFilesForFolderPath(dirCarpetaTropes, "html");
		
		for(int i = 0; i < dirsArchivos.size() ; i++){
			series.add(new Serie(dirsArchivos.get(i)));	//Por cada archivo, crear una serie y agregarla.
		}
	}
	
	/**
	 * 2do paso: Hacer la lista maestra
	 */
	void hacerListaMaestra(){
		
		//Creamos un HashSet donde agregar todos los tropes.
		//Elegi esta clase porque los HashSet no admiten elementos repetidos.
		HashSet<String> masterHash = new HashSet<String>();
		
		for (int i = 0; i < series.size(); i++){
			for (int j = 0; j < series.get(i).tropes.size(); j++){	//Iteramos por cada trope de cada serie.
				String currentTrope = series.get(i).tropes.get(j);	//currentTrope = Trope "j" de la serie "i".
				masterHash.add(currentTrope);						//Se agregara si no esta ya en el HashSet.
			}
		}

		//Ahora toca pasar los tropes del masterHash al masterList.
		
		Iterator<String> i = masterHash.iterator();	//Creamos iterador para iterar por el masterHash.
		
		while (i.hasNext()){			//Iteramos por el masterHash.
			masterList.add(i.next());	//Pasamos los valores del masterHash a la masterList.
		}
		
		Collections.sort(masterList);	//Ordenamos la lista maestra.
		
	}
	
	/**
	 * 3er paso: Rellenar el array de generos
	 */
	void rellenarArrayGeneros(String dirCarpetaGeneros){
		
		//Obtenemos la lista de archivos en el directorio:
		ArrayList<String> dirsArchivos = DirectoryFileLister.listFilesForFolderPath(dirCarpetaGeneros, "html");
		
		for(int i = 0; i < dirsArchivos.size() ; i++){
			generos.add(new Serie(dirsArchivos.get(i)));	//Por cada archivo, crear un genero y agregarlo.
		}
	}
	
	/**
	 * 4to paso: hacer la lista maestra 2 (de generos)
	 */
	void hacerListaMaestra2(){
		
		for (int i = 0; i < masterList.size(); i++)
			masterList2.add("");
		
		for (int i = 0; i < generos.size(); i++){
			for (int j = 0; j < generos.get(i).tropes.size(); j++){	//Iteramos por cada trope de cada genero.
				String currentTrope = series.get(i).tropes.get(j);	//currentTrope = Trope "j" del genero "i".
				int elemPos = StringArrayHelper.getElementPosition(masterList, currentTrope);
				if (elemPos >= 0)	//Si la posicion es valida (esta la trope en el genero actual)
					
			}
		}

		//Ahora toca pasar los tropes del masterHash al masterList.
		
		Iterator<String> i = masterHash.iterator();	//Creamos iterador para iterar por el masterHash.
		
		while (i.hasNext()){			//Iteramos por el masterHash.
			masterList2.add(i.next());	//Pasamos los valores del masterHash a la masterList.
		}
		
		Collections.sort(masterList2);	//Ordenamos la lista maestra.
		
	}
	
	/**
	 * 5to paso: generar archivo Weka
	 */
	void generarArchivoWeka(String dirCarpetaWeka){
		
	}
}
