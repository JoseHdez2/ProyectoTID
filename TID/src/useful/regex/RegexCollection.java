package useful.regex;

/*
 * Static collection of regular expressions in String format.
 */
public abstract class RegexCollection {
	
	//regexp for getting filenames from paths (Windows)
	public static final String getFilenameFromPath = "(?<=.*\\\\)(\\w+)(?=\\..*)";
	public static final String getExtensionFromPath = "(?<=.*)(\\..*)";
}
