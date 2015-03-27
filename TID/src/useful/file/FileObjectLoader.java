package useful.file;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileObjectLoader {
	
	/**
	 * Loads a Java object from a file.
	 * @param filename	Name of the file where the object is stored.
	 * @return			Object read from file.
	 */
	static public Object loadObject(String filename){
		try{
			FileInputStream file = new FileInputStream(filename);
			ObjectInputStream stream = new ObjectInputStream(file); 
			Object object = stream.readObject();
			file.close();
			return object;
		}
		catch (IOException e){
			System.err.println("Failed to load file (IOException).");
		}
		catch (ClassNotFoundException e){
			System.err.println("Failed to load file (ClassNotFoundException).");
		}
		return null;
	}
	
	//TODO see if this is valid and/or works.
	/**
	 * Store a Java object in a file.
	 * @param obj		Object to be stored.
	 * @param filename	Path of the file where the object will be stored.
	 */
	static public void storeAsObject(Object obj, String filename){
		try{
			FileOutputStream file = new FileOutputStream(filename);
			ObjectOutputStream stream = new ObjectOutputStream(file); 
			stream.writeObject(obj);
			file.close();
			return;
		}
		catch (IOException e){
			System.err.println("Failed to load file (IOException).");
		}
		return;
	}

}
