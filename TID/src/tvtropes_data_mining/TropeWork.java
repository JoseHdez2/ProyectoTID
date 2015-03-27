package tvtropes_data_mining;

import java.util.ArrayList;

/*
 * Class that represents a work of fiction.
 */
public class TropeWork {
	public String name = "";										//Name of the work.
	public ArrayList<String> tropesRaw = new ArrayList<String>();	//Tropes that the work has, as Strings.
	public ArrayList<Integer> tropes = new ArrayList<Integer>();	//Tropes that the work has, as enums.
	
	TropeWork(){
		
	}
	
	TropeWork(String name){
		setName(name);
	}
	
	TropeWork(String name, ArrayList<String> tropesRaw){
		setName(name);
		setTropesRaw(tropesRaw);
	}
	
	@Override
	public String toString() {
		return "Trope [Name: " + getName() + ", Tropes: " + getNumberOfTropes() + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getTropesRaw() {
		return tropesRaw;
	}

	public void setTropesRaw(ArrayList<String> tropesRaw) {
		this.tropesRaw = tropesRaw;
	}

	/**
	 * @return	Number of tropes the work contains. Enum tropes take priority.
	 */
	public int getNumberOfTropes(){
		if(!tropes.isEmpty()){
			return tropes.size();
		}
		else{
			return tropesRaw.size();
		}
	}

	public void saveAsRaw(String filePath){
		filePath += "\\" + getName();
		System.out.print("\nsaving into " + filePath);
		//TODO create saveArraytoFile(filenamePath)
		//TODO call saveArraytoFile(filePath, getTropesRaw());
	}
}
