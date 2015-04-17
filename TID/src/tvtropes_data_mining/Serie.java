package tvtropes_data_mining;

import java.util.ArrayList;

import useful.file.StringFromFile;
import useful.regex.RegexFilenameHelper;

public class Serie {
	String name;
	ArrayList<String> tropes;
	
	Serie(String dirArchivoHTML){
		name = RegexFilenameHelper.getFilename(dirArchivoHTML);			//Nombre serie = nombre archivo.
		String htmlContent = StringFromFile.fromFile(dirArchivoHTML);	//Cargamos el contenido HTML en memoria.
		tropes = TropesParser.parseFile(htmlContent);					//Obtenemos el Array de tropes al parsear el HTML.
	}
}
