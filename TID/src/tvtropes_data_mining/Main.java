package tvtropes_data_mining;

import java.util.ArrayList;

import useful.array.ArrayStringConverter;

//TODO create TropesLoader class, for loading from, and dealing with, post-parsing files.

public class Main {
	
	public static void main(String[] args) {

		TropesParser tp = new TropesParser();
//		ArrayList<TropeWork> works = tp.parseDirectory("C:\\Series\\");
		
//		System.out.println(ArrayStringConverter.arrayToLSV(works));

		TropeWork lost = tp.parsePage("C:\\Series\\Lost.html");
		
		System.out.println(lost);
		System.out.println("Tropes:");
		lost.showTropesInConsole();
		
		/*
		TreeSet<String> tropeSet = new TreeSet<String>();
		
		for (int i = 0; i < works.size(); i++){
			for (int j = 0; j < works.get(i).tropesRaw.size(); j++){
				tropeSet.add(works.get(i).tropesRaw.get(j));
			}
		}
		
		Iterator i = tropeSet.iterator();
		
		while(i.hasNext())
			System.out.println(i.next());*/
	}
}
