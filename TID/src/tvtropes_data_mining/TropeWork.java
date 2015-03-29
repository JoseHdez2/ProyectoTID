package tvtropes_data_mining;

import java.util.ArrayList;

import useful.array.ArrayToUnique;
import useful.array.StringArrayToOutput;

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

	public void showTropesInConsole(){
		StringArrayToOutput.showInConsole(getTropesRaw());
	}
	
	public void saveRawIntoFolder(String folderPath){
		StringArrayToOutput.saveIntoFile(getTropesRaw(), folderPath + getName() + ".txt");
	}
	
	public void saveEnumIntoFolder(String folderPath){
		ArrayList<String> array = new ArrayList<String>();
		for (int i = 0; i < getTropes().size(); i++){
			array.add(getTropes().get(i).toString());
		}
		StringArrayToOutput.saveIntoFile(array, folderPath + getName() + ".txt");
	}
	
	/**
	 * If no enum tropes exist, get the number of String tropes.
	 * @return	Number of tropes the work contains.
	 */
	public int getNumberOfTropes(){
		if(!tropes.isEmpty())
			return tropes.size();
		else
			return tropesRaw.size();
	}
	
	/**
	 * @param trope	Integer of the trope we search for.
	 * @return
	 */
	public boolean hasTrope(Integer trope){
		for (int i = 0; i < getTropes().size(); i++){
			if (trope == getTropes().get(i))
				return true;
		}
		return false;
	}
	
//TODO saveIntoFile()
	
	/*
	 * Getters and setters.
	 */
	
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
		this.tropesRaw = ArrayToUnique.makeUnique(tropesRaw);
	}

	public ArrayList<Integer> getTropes() {
		return tropes;
	}

	public void setTropes(ArrayList<Integer> tropes) {
		this.tropes = tropes;
	}
}
