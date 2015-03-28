package useful.regex;

public abstract class RegexCreator {
	
	//TODO should I control so that the functions don't add lookahead or lookbehind if there already is one?
	
	//TODO should I explicitly say that these functions don't modify/destroy the input?
	
	//TODO should I leave intoLookbehind and intoLookahead private?
	/**
	 * Frees you from the hassle of remembering how to write a lookbehind syntax.
	 * Reduces the chances of messing up the syntax of a regular expression.
	 * @param regex	The regex that will be evaluated.
	 * @return	A lookbehind expression, to be used for a bigger regex.
	 */
	static String intoLookbehind(String regex){
		return "(?<=" + regex + ")";
	}

	/**
	 * Frees you from the hassle of remembering how to write a lookahead syntax.
	 * Reduces the chances of messing up the syntax of a regular expression.
	 * @param regex	The regex that will be evaluated.
	 * @return	A lookahead expression, to be used for a bigger regex.
	 */
	static String intoLookahead(String regex){
		return "(?=" + regex + ")";
	}
	
	//TODO should I leave the argument order of addLookbehind and addLookahead like that?
		
	/**
	 * @param prefixRegex	The regex to be used as a lookbehind
	 * @param targetRegex	The rest of the regex.
	 * @return	A compound expression: a regex with a lookbehind.
	 */
	static public String addLookbehind(String prefixRegex, String targetRegex){
		return intoLookbehind(prefixRegex) + targetRegex;
	}
	
	/**
	 * @param suffixRegex	The regex to be used as a lookahead.
	 * @param targetRegex	The rest of the regex.
	 * @return	A compound expression: a regex with a lookahead.
	 */
	static public String addLookahead(String suffixRegex, String targetRegex){
		return targetRegex + intoLookahead(suffixRegex);
	}
	
	/**
	 * @param prefix	Content of the lookbehind expression.
	 * @param target	Regex that will be captured.
	 * @param suffix	Content of the lookahead expression.
	 * @return			Compound regex with both a lookbehind and a lookahead expression.
	 */
	static public String createInfixRegex(String prefixRegex, String targetRegex, String suffixRegex){
		String newRegex = addLookbehind(prefixRegex, targetRegex);
		newRegex = addLookbehind(newRegex, suffixRegex);
		return newRegex;
	}
}
