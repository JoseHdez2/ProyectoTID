package useful.array;

import java.util.ArrayList;

public abstract class ArrayStringConverter {

	@SuppressWarnings("rawtypes")
	public static String arrayToCSV(ArrayList array){
		return arrayToCSV(array, ", ");
	}
	
	@SuppressWarnings("rawtypes")
	public static String arrayToLSV(ArrayList array){
		return arrayToCSV(array, "\n");
	}
	
	@SuppressWarnings("rawtypes")
	public static String arrayToCSV(ArrayList array, String separator){
		String output = "";
		for (int i = 0; i < array.size(); i++){
			System.out.println(array.get(i).toString());
			//TODO no funciona
//			output.concat(array.get(i).toString() + separator);
		}
		return output;
	}
}
