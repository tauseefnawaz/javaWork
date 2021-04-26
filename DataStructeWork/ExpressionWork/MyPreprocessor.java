import java.io.*;
import java.util.*;
public class MyPreprocessor {
	static List<String> expressions = new ArrayList<String>();
	public static void readFile(String path) throws FileNotFoundException, IOException {
		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
		    for(String line; (line = br.readLine()) != null; ) {
		        // process the line.
		    	expressions.add(line);
		    }
		    // line is not visible here.
		}
	}
	
	public static void main(String[] args) {
		Preprocessor A = new Preprocessor();
		try {
			readFile("//home//tauseefnawaz//eclipse-workspace//Project1//src//test.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(int i =0;i<expressions.size();i++) {
			System.out.print(expressions.get(i)+"---  Valid :");
			System.out.print(A.validation(expressions.get(i)));
			System.out.println();
		}
	}	
}
