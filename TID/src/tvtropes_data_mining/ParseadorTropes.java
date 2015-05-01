package tvtropes_data_mining;

import java.util.ArrayList;
import java.util.Collections;

import useful.regex.MatchData;
import useful.regex.RegexCreator;
import useful.regex.TempRegexMatcher;

public abstract class ParseadorTropes{
	
	// Defino el lookahead y lookbehind, que son 
	// el "contexto" en que estará lo que quiero pillar con la expresión regular.
	
	// lookBehind: Quiero pillar lo que tenga esto antes.
	static String lookBehind = "title='http://tvtropes.org/pmwiki/pmwiki.php/Main/";
	// lookAhead: Quiero pillar lo que tenga esto después. 
	static String lookAhead = "'>[^<]+</a>:";
	
	public static ArrayList<String> parseFile(String htmlContent){
		
		// Quiero pillar lo que sea, menos "'", que sería ya parte del lookAhead.
		String tropeRegexp = "[^']+";
		
		// Defino el contexto en el que debe estar lo que quiero pillar.
		tropeRegexp = RegexCreator.addLookbehind(lookBehind, tropeRegexp);
		tropeRegexp = RegexCreator.addLookahead(lookAhead, tropeRegexp);
		
		// Guardamos los tropes en un objeto MatchData.
		MatchData md = TempRegexMatcher.getMatchData(tropeRegexp, htmlContent);
		
		// Ordenamos los tropes alfabéticamente.
		Collections.sort(md.getMatches());
		
		// Devolvemos la lista de los tropes.
		return md.getMatches();
	}
}
