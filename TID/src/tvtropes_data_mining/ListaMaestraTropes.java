package tvtropes_data_mining;

import java.util.ArrayList;
import java.util.HashMap;

public class ListaMaestraTropes {
	
	//Un hash que contendrá todos los tropes, y el género de cada uno.
	HashMap<String, String> hashMaestro = new HashMap<String, String>();
	
	/**
	 * 2do paso: Agregar los tropes a la lista maestra.
	 */
	
	public void agregarTropes(ArrayList<Serie> series){
		
		//Por cada serie...
		for (Serie serie : series){
			
			//Por cada uno de sus tropes...
			for (String trope: serie.tropes){
				
				//Incluimos el trope "t" en el hash maestro
				hashMaestro.put(trope, "");
				//El segundo parámetro es vacío-- por ahora desconocemos el género.
			}
		}
	}
	
	/**
	 * 4to paso: Agregar los generos a la lista maestra.
	 */
	
	public void agregarGeneros(ArrayList<Genero> generos){
		
		// Por cada género...
		for (Genero genero : generos){
			
			// Por cada uno de sus tropes...
			for (String trope : genero.tropes){
				
				// Avisar en caso de que no existiese dicho trope en el hash maestro.
				if (!hashMaestro.containsKey(trope)) {
					System.err.println("No existe el trope " + trope + " en el hashMaestro!");
				}
				
				// Avisar en caso de que ya existiese un género asignado al trope.
				else if (hashMaestro.get(trope) != "") {
					System.err.format("El trope %s ya tenia como genero '%s',", trope, hashMaestro.get(trope));
					System.err.format(" y se le quiso asignar '%s'%n", genero.name);
				}
				
				// Si todo esta bien, incluimos el trope con su género.
				else
					hashMaestro.put(trope, genero.name);
			}
		}
	}
}
