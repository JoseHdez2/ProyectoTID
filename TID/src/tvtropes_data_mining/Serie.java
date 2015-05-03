package tvtropes_data_mining;

import java.util.ArrayList;
import java.util.HashMap;

import useful.file.StringFromFile;
import useful.regex.RegexFilenameHelper;

/*
 * La clase Serie y la clase Genero son lo mismo;
 * son clases distintas para que el código sea más legible.
 */

public class Serie {

	// Nombre de la serie.
	String name;

	// Vector con cada uno de los tropes de la serie.
	ArrayList<String> tropes;

	// Hash con el conteo de los tropes de cada serie.
	HashMap<String, Integer> conteoGeneros = new HashMap<String, Integer>();

	Serie(String dirArchivoHTML){

		// Le asignamos el nombre del archivo como nombre.
		name = File.basename(dirArchivoHTML, File.extname(dirArchivoHTML));
		
		// Cargamos el contenido del HTML en la variable "htmlContent"
		String htmlContent = StringFromFile.fromFile(dirArchivoHTML);

		// Obtenemos el ArrayList de tropes al parsear el HTML
		tropes = ParseadorTropes.parseFile(htmlContent);

	}
}
