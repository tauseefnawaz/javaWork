import java.util.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class WordList {
	ArrayList<WordMeaning> wordLists = new ArrayList<>();
	ArrayList<String> deltedwordLists = new ArrayList<>();
	WordList(){}
	public void AddWord(String word) {
		wordLists.add(new WordMeaning(word));
	}
	public void AddMeaning(String word,String meaning) {
		for(WordMeaning wordList :wordLists) {
			if(wordList.Word.compareToIgnoreCase(word)==0) {
				wordList.insert(wordList, meaning);
			}
		}
	}
	public String getWord() {
		JFrame f=new JFrame();  
		f.setSize(600, 400);
		String word=JOptionPane.showInputDialog(f,"Enter Word\n(Enter \"No\" for stop)");
		return word;
	}

	public String getMeaning(String word) {
		JFrame f=new JFrame();   
		f.setSize(600, 400);
		String mean=JOptionPane.showInputDialog(f,"Enter meaning of word:"+word+"\n(Enter \"No\" for stop)");
		return mean;
	}
	public void removeWord(String word) {
		for(int i= 0; i<wordLists.size();i++) {
			if(wordLists.get(i).Word.compareToIgnoreCase(word)==0) {
				deltedwordLists.add(wordLists.get(i).Word);
				wordLists.remove(i);
			}
		}
	}
	public void printingOutput() {
		System.out.println("The current list of words and their meanings");
		for(WordMeaning wordList :wordLists) {
			System.out.print(wordList.Word+" ");
			ArrayList list = wordList.getList(wordList);
			for(int i=0;i<list.size();i++) {
				System.out.println(" - "+list.get(i));
			}
		}
		System.out.println("list of the deleted words");
		for(String word :deltedwordLists) {
			System.out.println(word);
		}
	}

}
