package tvtropes_data_mining;

import java.util.ArrayList;

import useful.file.StringFromFile;
import useful.regex.RegexFilenameHelper;

/*
 * La clase Serie y la clase Genero son lo mismo; 
 * son clases distintas para que el código sea más legible.
 */

public class Genero {
	String name;
	ArrayList<String> tropes;
	
	Genero(String dirArchivoHTML){
		
		//Le asignamos el nombre del archivo como nombre.
		name = RegexFilenameHelper.getFilename(dirArchivoHTML);
		
		//Cargamos el contenido del HTML en la variable "htmlContent"
		String htmlContent = StringFromFile.fromFile(dirArchivoHTML);
		
		//Obtenemos el ArrayList de tropes al parsear el HTML
		tropes = TropesParser.parseFile(htmlContent);
	}
}
