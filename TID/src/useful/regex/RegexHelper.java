package useful.regex;

import java.util.regex.Pattern;

public abstract class RegexHelper {
	
	
	/**
	 * @param regex		String regex that may or may not be valid.
	 * @return	Pattern if it is valid, warning and empty Pattern if not.
	 */
	static Pattern validatePattern(String regex){
		Pattern pattern;
		try{
			pattern = Pattern.compile(regex);
		} catch (Exception e) {
			System.err.print("Pattern could not be created from provided String.");
			System.err.println(" Pattern will default to regex [^].");
			pattern = Pattern.compile("[^]");
		}
		return pattern;
	}
}
