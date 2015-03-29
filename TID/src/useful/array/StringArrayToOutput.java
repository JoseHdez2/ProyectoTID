package useful.array;

import java.util.ArrayList;

import useful.file.StringToFile;

public abstract class StringArrayToOutput {
	public static void showInConsole(ArrayList<String> list){
		String str = ArrayToString.arrayToLSV(list);
		System.out.println(str);
	}
	
	public static void saveIntoFile(ArrayList<String> list, String filePath){
		String str = ArrayToString.arrayToLSV(list);
//		System.out.println(str);
		StringToFile.stringToFile(filePath, str);
	}
}
