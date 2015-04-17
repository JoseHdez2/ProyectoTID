package useful.array;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public abstract class ArrayToUnique {
	//TODO generalize for all arrayLists
	public static ArrayList<String> makeUnique(ArrayList<String> list){
		HashSet<String> set = new HashSet<String>();
		for(int i = 0; i < list.size(); i++){
			set.add(list.get(i));
		}
		list.clear();

		Iterator<String> i = set.iterator();
		
		while (i.hasNext()){
			list.add(i.next());
		}
		
		return list;
	}
	
	public static ArrayList<Integer> makeUniqueInt(ArrayList<Integer> list){
		HashSet<Integer> set = new HashSet<Integer>();
		for(int i = 0; i < list.size(); i++){
			set.add(list.get(i));
		}
		list.clear();

		Iterator<Integer> i = set.iterator();
		
		while (i.hasNext()){
			list.add(i.next());
		}
		
		return list;
	}
}
