package cj2.hash_prog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class HashObjectCollection {
	HashMap<String, HashObjectVarType> varTypes;
	
	ArrayList<HashObject> objs;
	@SuppressWarnings("rawtypes")
	HashMap<String, HashSet> allVals;
	
	void getAllVals(String atr){
		HashSet<String> newSet = new HashSet<String>;
		for (HashObject ho : objs){
			newSet.addAll(ho.getAsSet());
		}
		
	}
	
	HashObjectCollection(){
		varTypes.put("name", HashObjectVarType.STRING);
		varTypes.put("tropes", HashObjectVarType.STRING_SET);
	}
}
