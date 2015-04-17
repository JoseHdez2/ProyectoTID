package useful.array;

import java.util.ArrayList;

public abstract class StringArrayToConsole {
	public static void showInConsole(ArrayList<String> list){
		String str = ArrayToString.arrayToLSV(list);
		System.out.println(str);
	}
}
