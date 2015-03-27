package useful.regex;

public abstract class RegexFilenameHelper {

	//TODO getFolderHierarchyAsStringArrayList
	
	/**
	 * @param path	Path of a file.
	 * @return		Filename of said file.
	 */
	public static String getFilename(String path){
		RegexFinder rf = new RegexFinder(path, RegexCollection.getFilenameFromPath);
		return rf.getMatchesList().get(0);
	}
	
	/**
	 * @param path	Path of a file.
	 * @return		Extension of said file.
	 */
	public static String getExtension(String path){
		RegexFinder rf = new RegexFinder(path, RegexCollection.getExtensionFromPath);
		return rf.getMatchesList().get(0);
	}
}
