package useful.file;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class StringToFile {

	static public void stringToFile(String fileNamepath, String strToSave){
		stringToFile(fileNamepath, strToSave, Charset.defaultCharset());
	}

	static public void stringToFile(String fileNamepath, String strToSave, Charset encoding){
		try{
			byte[] b_arr = strToSave.getBytes(encoding);
			Files.write(Paths.get(fileNamepath), b_arr, StandardOpenOption.CREATE);
		} catch (Exception e){
			System.err.println("String could not be saved. " + e.getMessage());
		}
	}
}
