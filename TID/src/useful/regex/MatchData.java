package useful.regex;

import java.util.ArrayList;

import useful.array.StringArrayToOutput;
import useful.array.StringArrayToConsole;

public class MatchData {
	ArrayList<String> matches;	//String for each match.
	int count;					//Number of total matches.
	
	MatchData(){
		setMatches(new ArrayList<String>());
		setCount(0);
	}
	
	void showInConsole(){
		StringArrayToOutput.showInConsole(getMatches());
		StringArrayToConsole.showInConsole(getMatches());
		System.out.println("Matches: " + getCount());
	}
	
	public ArrayList<String> getMatches() {
		return matches;
	}
	public void setMatches(ArrayList<String> matches) {
		this.matches = matches;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
