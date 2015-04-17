package useful.file;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Paths;

import tvtropes_data_mining.TropeWork;

public class StringFromFile {
	/**
	 * @param path			Path of the file to read.
	 * @param encoding		Character encoding that the file uses.
	 * @return				Contents of the file as a String.
	 * @throws IOException	In case the file can't be read.
	 */
	static String fromEncodedFile(String path, Charset encoding){
		try{
			byte[] encodedString = Files.readAllBytes(Paths.get(path));
			return new String(encodedString, encoding);
		} catch (Exception e){
			System.err.println("Couldn't read content from " + path);
		}
		
		return "";
	}
	
	/**
	 * Similar to the other, but assumes default encoding.
	 * @param path			Path of the file to read.
	 * @return				Contents of the file as a String.
	 * @throws IOException	In case the file can't be read.
	 */
	static public String fromFile(String path){
		return fromEncodedFile(path, Charset.defaultCharset());
	}
	
}
