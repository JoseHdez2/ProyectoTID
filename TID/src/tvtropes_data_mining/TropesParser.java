package tvtropes_data_mining;

import java.util.ArrayList;

import useful.file.DirectoryFileLister;
import useful.file.StringFromFile;
import useful.regex.MatchData;
import useful.regex.RegexCreator;
import useful.regex.RegexFilenameHelper;
import useful.regex.TempRegexMatcher;

public class TropesParser{
	
	String tropeRegexp;
	
	TropesParser(){
//		tropeRegexp = "d";
		tropeRegexp = "[^']+";	//anything except ', which is the delimiter character.
		tropeRegexp = RegexCreator.addLookbehind("title='http://tvtropes.org/pmwiki/pmwiki.php/Main/", tropeRegexp);
		tropeRegexp = RegexCreator.addLookahead("'>[^<]+</a>:", tropeRegexp);
	}
	
//	final String tropeRegexp = "(?<=)[^']+(?=)";	//finds tropes in HTML
	
	public TropeWork parsePage(String htmlFilePath){
		String htmlContent = "";
		try{
			htmlContent = StringFromFile.fromFile(htmlFilePath);	//load file content
		} catch (Exception e){
			System.err.println("In parsePage(" + htmlFilePath + "), couldn't find file.");
			return new TropeWork();
		}
			
		TropeWork work = new TropeWork(RegexFilenameHelper.getFilename(htmlFilePath));
	
		MatchData md = TempRegexMatcher.getMatchData(tropeRegexp, htmlContent);
		work.setTropesRaw(md.getMatches());
		
		return work;
	}

	/**
	 * Parses the tropes of TvTropes HTMLs and saves them into plain text files.
	 * @param originDirPath	The directory where the HTML files are.
	 * @param targetDirPath	The directory where the plain text files will be saved.
	 */
	public ArrayList<TropeWork> parseDirectory(String originDirPath){
		ArrayList<String> filesArray = DirectoryFileLister.listFilesForFolderPath(originDirPath, ".html");
		
		ArrayList<TropeWork> works = new ArrayList<TropeWork>();
		for (int i = 0; i < filesArray.size(); i++){
			if(i % 50 == 0){	//show progress
				System.out.format("Parsing %4d of %4d ", i, filesArray.size());
				System.out.format("(%3d%%) ...%n", (int)((float)i/(float)filesArray.size()*100));
			}
			filesArray.set(i, originDirPath + filesArray.get(i));
			works.add(i, parsePage(filesArray.get(i)));
		}
		
		return works;
	}
}
