import java.util.Scanner;

public class Dictionary {
	public void addWord(WordList W) {		
		String word=null;
		String meaning=null;
		while(true) {
			word = W.getWord();
			if(word.compareToIgnoreCase("No")==0)break;
			else W.AddWord(word); 
			
			while(true) {
				meaning = W.getMeaning(word);
				if(meaning.compareToIgnoreCase("No")==0) break; 
				else W.AddMeaning(word,meaning);
			}
		
		}
	}
	public void removeWord(WordList W) {		
		String word=null;
		word = W.getWord();
		W.removeWord(word);
	}

	public static void main(String[] args){
		Scanner input = new Scanner(System.in);
		WordList W = new WordList();
		Dictionary dic = new Dictionary();
		int choice = 0;
		while(true) {
			System.out.print("Enter \"1\" for adding wording or meaning in a dictionary\n"
					+ "Or Enter \"2\" for Removing wording from a dictionary : ");
			System.out.println();
			choice = input.nextInt();
			if(choice==1) dic.addWord(W);
			if(choice==2) dic.removeWord(W);
			W.printingOutput();
			input.reset();
		}
	}
}
