package tvtropes_data_mining;

import java.util.ArrayList;

import useful.array.StringArrayToOutput;

//TODO create TropesLoader class, for loading from, and dealing with, post-parsing files.

public class Main {
	
	public static void main(String[] args) {

		TropesParser tp = new TropesParser();
		
		ArrayList<TropeWork> works = tp.parseDirectory("C:\\Series\\");
		
		for (int i = 0; i < works.size(); i++){
			works.get(i).saveRawIntoFolder("C:\\Series\\RawTropes\\");
		}
		
		ArrayList<String> masterList = tp.createMasterList(works);
		System.out.println("Master list created.");
		StringArrayToOutput.saveIntoFile(masterList, "C:\\Series\\MasterList\\masterList.txt");
		System.out.println("Master list saved.");
		
		tp.setEnumTropes(works, masterList);
		
		for (int i = 0; i < works.size(); i++){
			works.get(i).saveEnumIntoFolder("C:\\Series\\EnumTropes\\");
		}
	}
}
