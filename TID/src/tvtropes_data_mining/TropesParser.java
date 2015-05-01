package tvtropes_data_mining;

import java.util.ArrayList;
import java.util.Collections;

import useful.regex.MatchData;
import useful.regex.RegexCreator;
import useful.regex.TempRegexMatcher;

public abstract class TropesParser{
	
	static String lookBehind = "title='http://tvtropes.org/pmwiki/pmwiki.php/Main/";
	static String lookAhead = "'>[^<]+</a>:";
	
	public static ArrayList<String> parseFile(String htmlContent){
		String tropeRegexp = "[^']+";	//anything except ', which is the delimiter character.
		tropeRegexp = RegexCreator.addLookbehind(lookBehind, tropeRegexp);
		tropeRegexp = RegexCreator.addLookahead(lookAhead, tropeRegexp);
		
		//Guardamos los tropes en un objeto MatchData, y los ordenamos alfabeticamente.
		MatchData md = TempRegexMatcher.getMatchData(tropeRegexp, htmlContent);
		Collections.sort(md.getMatches());
		
		//Devolvemos la lista de los tropes.
		return md.getMatches();
	}
}
