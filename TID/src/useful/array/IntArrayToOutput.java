package useful.array;

import java.util.ArrayList;

import useful.file.StringToFile;

public class IntArrayToOutput {
	public static void showInConsole(ArrayList<Integer> list){
		String str = ArrayToString.arrayToLSV(list);
		System.out.println(str);
	}
	public static void saveIntoFile(ArrayList<Integer> list, String filePath){
		String str = ArrayToString.arrayToLSV(list);
		StringToFile.stringToFile(filePath, str);
	}
}
