package useful.file;

import java.io.IOException;

/*
 * Unnecessary class, to have the file's name, path and content in the same container.
 */
public class FileInMem {
	String filepath = "";
	String content = "";
	
	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	FileInMem(String filepath){
		setFilepath(filepath);
		try{
			String content = StringFromFile.fromFile(filepath);
			setContent(content);
		} catch (IOException e){
			System.err.println(e.getMessage());
		}
	}
	
}
