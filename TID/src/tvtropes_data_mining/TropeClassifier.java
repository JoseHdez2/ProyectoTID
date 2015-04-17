/*
package tvtropes_data_mining;

import java.util.ArrayList;

import useful.file.DirectoryFileLister;

public abstract class TropeClassifier {
	
	public static void classifyTropes(String masterList, String classPages, String classEnd){
		ArrayList<String> filesArray = DirectoryFileLister.listFilesForFolderPath(classPages, ".html");
		
		TropesParser tp = new TropesParser();
		
		ArrayList<TropeWork> classes = new ArrayList<TropeWork>();
		for (int i = 0; i < filesArray.size(); i++){
			TropeWork dummyClass = tp.parsePage(filesArray.get(i));
			dummyClass
		}

	}
}
*/