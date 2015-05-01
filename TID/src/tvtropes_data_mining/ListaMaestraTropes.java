package tvtropes_data_mining;

import java.util.ArrayList;
import java.util.HashMap;

public class ListaMaestraTropes {
	
	//Un hash que contendrá todos los tropes, y el género de cada uno.
	HashMap<String, String> hashMaestro = new HashMap<String, String>();
	
	public void agregarTropes(ArrayList<Serie> series){
		
		//Por cada serie...
		for (Serie s : series){
			
			//Por cada uno de sus tropes...
			for (String t: s.tropes){
				
				//Incluimos el trope "t" en el hash maestro
				hashMaestro.put(t, "");
				//El segundo parámetro es vacío-- por ahora desconocemos el género.
			}
		}
	}
	
	public void agregarGeneros(ArrayList<Genero> generos){
		
		// Por cada género...
		for (Genero g : generos){
			
			// Por cada uno de sus tropes...
			for (String t : g.tropes){
				
				// Avisar en caso de que no existiese dicho trope en el hash maestro.
				if (!hashMaestro.containsKey(t)) {
					System.err.println("No existe el trope " + t + " en el hashMaestro!");
				}
				
				// Avisar en caso de que ya existiese un género asignado al trope.
				if (hashMaestro.get(t) != "") {
					System.err.format("El trope %s ya tenia como genero '%s',", t, hashMaestro.get(t));
					System.err.format(" y se le quiso asignar '%s'%n", g.name);
				}
				
				// Incluimos el trope con su género.
				hashMaestro.put(t, g.name);
			}
		}
	}
}
