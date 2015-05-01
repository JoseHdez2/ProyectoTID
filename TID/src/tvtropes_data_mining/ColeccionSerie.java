package tvtropes_data_mining;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;

import useful.array.StringArrayHelper;
import useful.file.DirectoryFileLister;
import useful.file.StringToFile;

public class ColeccionSerie {
	
	ArrayList<Serie> series = new ArrayList<Serie>();			//se rellena en el paso 1
	ArrayList<String> masterList = new ArrayList<String>();		//se crea en el paso 2
	ArrayList<Serie> generos = new ArrayList<Serie>();			//se rellena en el paso 3
	ArrayList<String> masterList2 = new ArrayList<String>();	//se crea en el paso 4
	
	ColeccionSerie(String dirCarpetaTropes, String dirCarpetaGeneros, String dirArchivoWeka){
//		askMode();
		
		//1er paso: Rellenar el array de series
		rellenarArraySeries(dirCarpetaTropes);
		
		//2do paso: hacer la lista maestra
		hacerListaMaestra();
		
		//3er paso: rellenar el array de generos
		rellenarArrayGeneros(dirCarpetaGeneros);
		
		//4to paso: hacer la lista maestra 2 (de generos)
		hacerListaMaestra2();
		
		//5to paso: 5to paso: calcular el numero de tropes de cada genero por serie
		calcularConteoGeneros();
		
		//6to paso: generar archivo Weka
		generarArchivoWeka(dirArchivoWeka);
	}
	
	/**
	 * 1er paso: Rellenar el array de series
	 */
	void rellenarArraySeries(String dirCarpetaTropes){
		//Obtenemos la lista de archivos en el directorio:
		ArrayList<String> dirsArchivos = DirectoryFileLister.listFilesForFolderPath(dirCarpetaTropes, ".html");
		Collections.sort(dirsArchivos);
		
		for(int i = 0; i < dirsArchivos.size() ; i++){
			String direccionSerie = dirCarpetaTropes + dirsArchivos.get(i);
			series.add(new Serie(direccionSerie));	//Por cada archivo, crear una serie y agregarla.
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
		ArrayList<String> dirsArchivos = DirectoryFileLister.listFilesForFolderPath(dirCarpetaGeneros, ".html");
		
		for(int i = 0; i < dirsArchivos.size() ; i++){
			String direccionSerie = dirCarpetaGeneros + dirsArchivos.get(i);
			generos.add(new Serie(direccionSerie));	//Por cada archivo, crear una genero y agregarlo.
		}
	}
	
	final int SIN_GENERO = -1;
	
	/**
	 * 4to paso: hacer la lista maestra 2 (de generos)
	 */
	void hacerListaMaestra2(){
		
		for (int i = 0; i < masterList.size(); i++)	//Hacemos que la masterList2 tenga el mismo numero de elementos que masterList 
			masterList2.add("");
		/*
		for (Serie s : generos){
			for (String t : s.tropes){
				String currentTrope = 
				int elemPos;
				if (elemPos >= 0)
					masterList2.set();
			}
		}*/
		
		for (int i = 0; i < generos.size(); i++){
			for (int j = 0; j < generos.get(i).tropes.size(); j++){	//Iteramos por cada trope de cada genero.
				String currentTrope = series.get(i).tropes.get(j);	//currentTrope = Trope "j" del genero "i".
				int elemPos = StringArrayHelper.getElementPosition(masterList, currentTrope);
				if (elemPos >= 0){	//Si la posicion es valida (esta la trope en el genero actual)
					//Avisar cada vez que se produzca una colision
					if(masterList2.get(elemPos) != "")
						System.out.format("Trope %s tenia genero %s, cambia a %s.%n",
								masterList.get(elemPos), masterList2.get(elemPos), generos.get(i).name);
					//Colocar el nombre del genero actual.
					masterList2.set(elemPos, generos.get(i).name);
				}
			}
		}
	}
	
	/**
	 * 5to paso: calcular el numero de tropes de cada genero por serie
	 */
	void calcularConteoGeneros(){
		for (int i = 0; i < series.size(); i++){
			for (int j = 0; j < generos.size(); j++){
				String nombreGenero = generos.get(j).name;
				int conteoGenero = 0;
				for (int k = 0; k < series.get(i).tropes.size(); k++){
					String nombreTrope = series.get(i).tropes.get(j);
					int indiceTrope = masterList.indexOf(nombreTrope);
					if (masterList2.get(indiceTrope) == nombreGenero)
						conteoGenero++;
				}
				series.get(i).conteoGeneros.add(conteoGenero);
			}
		}
	}
	
	/**
	 * 6to paso: generar archivo Weka
	 */
	void generarArchivoWeka(String dirCarpetaWeka){
		String wekaContent = "";
		wekaContent = wekaContent.concat("@RELATION series");
		
		//atributos
		wekaContent = wekaContent.concat("\n@ATTRIBUTE serieName");
		wekaContent = wekaContent.concat("\n@ATTRIBUTE tropeNumber");
		for (int i = 0; i < generos.size(); i++){	//Ponemos cada genero como un atributo
			wekaContent = wekaContent.concat("\n@ATTRIBUTE " + generos.get(i));
		}
		
		//datos (series)
		wekaContent = wekaContent.concat("\n@DATA");
		for (int i = 0; i < series.size(); i++){	//Ponemos cada genero como un atributo
			wekaContent = wekaContent.concat("\n");
			wekaContent = wekaContent.concat(series.get(i).name + ",");
			wekaContent = wekaContent.concat(series.get(i).tropes.size() + ",");
			for (int j = 0; j < generos.size(); j++){
				wekaContent = wekaContent.concat(series.get(i).conteoGeneros.toString());
				if (j < generos.size()-1)
					wekaContent = wekaContent.concat(",");
			}
		}
		StringToFile.stringToFile(dirCarpetaWeka, wekaContent);
	}
}
