package useful.regex;

import org.junit.Test;

public class TempRegexMatcherTest {

	@Test
	public void test() {
		System.out.println("getMatchData()");
		MatchData md = TempRegexMatcher.getMatchData("(a)m", "amimama");
		md.showInConsole();
		//TODO fix getMatchData2()
		System.out.println("getMatchData2()");
		md = TempRegexMatcher.getMatchData2("(a)(m)", "amimama");
		md.showInConsole();
	}

}
