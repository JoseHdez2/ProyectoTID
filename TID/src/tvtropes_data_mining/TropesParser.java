package tvtropes_data_mining;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import useful.file.DirectoryFileLister;
import useful.file.StringFromFile;
import useful.file.StringToFile;
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
		
		Collections.sort(work.getTropesRaw());
		
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
	
	//TODO remove repeats: TropeName == Tropename
	public ArrayList<String> createMasterList(ArrayList<TropeWork> worksArray){
		HashSet<String> masterHash = new HashSet<String>();
		
		
		for (int i = 0; i < worksArray.size(); i++){
			for (int j = 0; j < worksArray.get(i).getTropesRaw().size(); j++){
				String currentTrope = worksArray.get(i).getTropesRaw().get(j);
				masterHash.add(currentTrope);
			}
		}

		Iterator<String> i = masterHash.iterator();
		
		ArrayList<String> masterList = new ArrayList<String>();
		while (i.hasNext()){
			masterList.add(i.next());
		}
		
		Collections.sort(masterList);
		
		return masterList;
	}
	
	public void setEnumTropes(ArrayList<TropeWork> works, ArrayList<String> masterList){
		HashMap<String, Integer> masterMap = new HashMap<String, Integer>();
		for (int i = 0; i < masterList.size(); i++){
		   masterMap.put(masterList.get(i), i);
		}
		
		for (int i = 0; i < works.size(); i++){
			for (int j = 0; j < works.get(i).getTropesRaw().size(); j++){
				works.get(i).getTropes().add(masterMap.get(works.get(i).getTropesRaw().get(j)));
			}
			Collections.sort(works.get(i).getTropes());
		}
	}
	
	public void createWekaFile(ArrayList<TropeWork> works, int numberOfTropes, String filePath){
		String wekaFile = "";
		wekaFile = wekaFile.concat("@RELATION series");
		for(int i = 0; i < numberOfTropes; i++){
//			wekaFile = wekaFile.concat("\n@ATTRIBUTE trope" + i + " {0,1}");
			wekaFile = wekaFile.concat("\n@ATTRIBUTE trope" + i + " NUMERIC");
		}
		wekaFile = wekaFile.concat("\n@DATA");
		for(int i = 0; i < works.size(); i++){	//...for each TropeWork...
			if(i % 25 == 0){	//show progress
				System.out.format("Writing %4d of %4d ", i, works.size());
				System.out.format("(%3d%%) ...%n", (int)((float)i/(float)works.size()*100));
			}
			wekaFile = wekaFile.concat("\n{");
			for (int j = 0; j <	works.get(i).getNumberOfTropes(); j++){	//...for each Trope in the master list...
				wekaFile = wekaFile.concat(works.get(i).getTropes().get(j) + " 1");
				if( j < works.get(i).getNumberOfTropes()-1)
					wekaFile = wekaFile.concat(",");
			}
			wekaFile = wekaFile.concat("}");
		}
		StringToFile.stringToFile(filePath, wekaFile);
	}
}
