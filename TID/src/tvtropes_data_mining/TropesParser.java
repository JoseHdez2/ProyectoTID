package tvtropes_data_mining;

import java.util.ArrayList;

import useful.file.DirectoryFileLister;
import useful.file.StringFromFile;
import useful.regex.RegexFilenameHelper;
import useful.regex.RegexFinder;

public class TropesParser{
	
	//  vieja -> <.+?>((\\w|\\s)+?)</a>:
//	final String tropeRegexp = "<.+?>((\\w|\\s)+?)</a>:";	//finds tropes in HTML
	//	nueva -> (?<=\/)[^\]+?\/[^']*?(?='>[^<]+<\/a>:)
	final String tropeRegexp = "(?<=/)[^\\]+?/[^']*?(?='>[^<]+</a>:)";	//finds tropes in HTML
	
	public TropeWork parsePage(String htmlFilePath){
		String htmlContent = "";
		try{
			htmlContent = StringFromFile.fromFile(htmlFilePath);	//load file content
		} catch (Exception e){
			System.err.println("In parsePage(" + htmlFilePath + "), couldn't find file.");
			return new TropeWork();
		}
		RegexFinder rf;
		try{
			rf = new RegexFinder(htmlContent, tropeRegexp);			//look for tropes
		} catch (Exception e){
			System.err.println("In parsePage(" + htmlFilePath + "), couldn't RegexFinder.");
			return new TropeWork();
		}
			
		TropeWork work = new TropeWork(RegexFilenameHelper.getFilename(htmlFilePath));
	
		work.setTropesRaw(rf.getMatchesList());
		
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
