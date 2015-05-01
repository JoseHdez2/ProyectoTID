package tvtropes_data_mining;


//TODO create TropesLoader class, for loading from, and dealing with, post-parsing files.

public class Main {
	
	public static void main(String[] args) {
		new ColeccionSeries(
				"/home/jose/Documents/tid/raw/series/",
				"/home/jose/Documents/tid/raw/generos/",
				"/home/jose/Documents/tid/weka/tropes.arff");
	}
}
