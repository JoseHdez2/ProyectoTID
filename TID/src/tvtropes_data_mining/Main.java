package tvtropes_data_mining;

import java.util.ArrayList;

import useful.array.IntArrayToOutput;
import useful.array.StringArrayToOutput;

//TODO create TropesLoader class, for loading from, and dealing with, post-parsing files.

public class Main {
	
	public static void main(String[] args) {

		TropesParser tp = new TropesParser();
		
		ArrayList<TropeWork> worksB = tp.parseDirectory("C:\\Series\\PagesSeries\\");
		
		ArrayList<TropeWork> works = new ArrayList<TropeWork>();
		for (int i = 0; i < 100; i++){
			works.add(worksB.get(i));
		}
		
		for (int i = 0; i < works.size(); i++){
			works.get(i).saveRawIntoFolder("C:\\Series\\TropesRaw\\");
		}
		System.out.println("Raw tropes saved.");
		
		ArrayList<String> masterList = tp.createMasterList(works);
		System.out.println("Master list created.");
		StringArrayToOutput.saveIntoFile(masterList, "C:\\Series\\MasterList\\masterList.txt");
		System.out.println("Master list saved.");
		
		tp.setEnumTropes(works, masterList);
		
		for (int i = 0; i < works.size(); i++){
			works.get(i).saveEnumIntoFolder("C:\\Series\\TropesEnum\\");
		}
		System.out.println("Enum tropes saved.");
		
		IntArrayToOutput.showInConsole(works.get(3).getTropes());
		
		tp.createWekaFile(works, masterList.size(), "C:\\Series\\Weka\\tvtropes.arff");
		System.out.println("Weka file created and saved.");
	}
}
