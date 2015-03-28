package useful.regex;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class TempRegexMatcher {
	
	/**	Captures only first group of each match.
	 * @param regex
	 * @param text
	 */
	public static MatchData getMatchData(String regex, String text){
		Pattern pattern;
		pattern = RegexHelper.validatePattern(regex);
		Matcher matcher = pattern.matcher(text);
		MatchData md = new MatchData();
		while (matcher.find()){
			ArrayList<String> matches = md.getMatches();
			matches.add(matcher.group(0).toString());
			md.setMatches(matches);
			md.setCount(md.getCount() + 1);
		}
		return md;
	}
	
	/**	Captures all groups.
	 * @param regex	
	 * @param text	Text to be 
	 */
	public static MatchData getMatchData2(String regex, String text){
		Pattern pattern;
		pattern = RegexHelper.validatePattern(regex);
		Matcher matcher = pattern.matcher(text);
		MatchData md = new MatchData();
		while (matcher.find()){
			for(int i=0; i < matcher.groupCount(); i++){
				ArrayList<String> matches = md.getMatches();
				matches.add(matcher.group(0).toString());
				md.setMatches(matches);
				md.setCount(md.getCount() + 1);
			}
		}
		return md;
	}
}
