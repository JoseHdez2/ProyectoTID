package useful.file;

import java.io.File;
import java.util.ArrayList;

import useful.regex.RegexFilenameHelper;


/**
 *	Class for listing the contents of a directory.
 */
public abstract class DirectoryFileLister {

//TODO reduce redundancy in similarly named functions-- a lot of code is repeated.
	
	static public ArrayList<String> listFilesForFolderPath(String folderPath){
		File folder = new File(folderPath);
		return listFilesForFolder(folder);
	}
	
	/**
	 * @param folder
	 * @return
	 */
	static public ArrayList<String> listFilesForFolder(final File folder){

		ArrayList<String> resultFiles = new ArrayList<String>();
		
		for (final File fileEntry : folder.listFiles()){
			if (fileEntry.isDirectory()){
				listFilesForFolder(fileEntry);	//recursive call
			} else {
				resultFiles.add(fileEntry.getName());
			}
		}
		
		return resultFiles;
	}
	
	static public ArrayList<String> listFilesForFolderPath(String folderPath, String extension){
		File folder = new File(folderPath);
		return listFilesForFolder(folder, extension);
	}
	
	static public ArrayList<String> listFilesForFolder(final File folder, String extension){

		ArrayList<String> resultFiles = new ArrayList<String>();
		
		for (final File fileEntry : folder.listFiles()){
			if (fileEntry.isDirectory()){
				listFilesForFolder(fileEntry);	//recursive call
			} else {
				String fileExtension = RegexFilenameHelper.getExtension(fileEntry.toString());
				if(fileExtension.equals(extension)){
					resultFiles.add(fileEntry.getName());
				}
				else{
					System.out.println("\nFile discarded! got " + fileExtension + ", expected " + extension);
				}
			}
		}
		
		return resultFiles;
	}

}
