package useful.regex;

public abstract class RegexFilenameHelper {

	//TODO getFolderHierarchyAsStringArrayList
	//TODO change dependencies from RegexFinder to TempRegexMatcher
	
	/**
	 * @param path	Path of a file.
	 * @return		Filename of said file.
	 */
	public static String getFilename(String path){
		MatchData md = TempRegexMatcher.getMatchData(RegexCollection.getFilenameFromPath, path);
		return md.getMatches().get(0);
	}
	
	/**
	 * @param path	Path of a file.
	 * @return		Extension of said file.
	 */
	public static String getExtension(String path){
		MatchData md = TempRegexMatcher.getMatchData(RegexCollection.getExtensionFromPath, path);
		return md.getMatches().get(0);
	}
}
