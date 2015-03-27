package useful.regex;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
 * Class for finding all matches of a regex inside of a string.
 * It's functionality from different standard Java classes packed into a single container, for streamlined use.
 * Lazy matches.
 */
public class RegexFinder {
	
	String text = "";
	String regex = "";
	
	ArrayList<String> matchesList = new ArrayList<String>();	//List of matches found.
	//TODO maybe also store the start and end points of each match...
	int matchesCount = 0;			//number of matches in last search.
	
	public RegexFinder(String text, String regex){
		this.text = text;
		this.regex = regex;
		recalculate();
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
		recalculate();
	}

	public String getRegex() {
		return regex;
	}

	public void setRegex(String regex) {
		this.regex = regex;
		recalculate();
	}

	public ArrayList<String> getMatchesList() {
		return matchesList;
	}

	public void setMatchesList(ArrayList<String> matchesList) {
		this.matchesList = matchesList;
	}

	public int getMatchesCount() {
		return matchesCount;
	}

	public void setMatchesCount(int matchesCount) {
		this.matchesCount = matchesCount;
	}
	
	private void recalculate(){
		setMatchesList(new ArrayList<String>());	//vaciamos matches
		setMatchesCount(0);
		
		//rebuild matcher
		Matcher matcher = buildMatcher();
			
		//find matches
		while (matcher.find()){
			for(int i=0; i < matcher.groupCount(); i++){
				matchesList.add(matcher.group(i).toString());	//Add each group into matchesList
				setMatchesCount(getMatchesCount() + 1);
			}
		}
	}
	
	/**
	 * @param text	Text which we want to search in.
	 * @param regex	Regular expression to search for.
	 * @return	Matcher that will have the matches.
	 */
	private Matcher buildMatcher() {
		Pattern pattern = Pattern.compile("");
		
		try{	//Try to compile the provided string as the pattern to be used.
			pattern = Pattern.compile(regex);
		} catch (Exception e) {
			System.err.println("RegexFinder: Couldn't compile provided string as pattern.");
		}
		
        Matcher matcher = pattern.matcher(text);

		return matcher;
	}


}
