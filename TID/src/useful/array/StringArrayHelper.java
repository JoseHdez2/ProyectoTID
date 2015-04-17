package useful.array;

import java.util.ArrayList;

public abstract class StringArrayHelper {
	
	/**
	 * @param list
	 * @param elem
	 * @return	Index of the (first) element in the list that is equal to elem.
	 */
	public static int getElementPosition(ArrayList<String> list, String elem){
		
		for (int i = 0; i < list.size(); i++){
			if (list.get(i) == elem)
				return i;
		}
		
		return -1;	//Return -1, meaning the element does not exist in the list
	}
}
