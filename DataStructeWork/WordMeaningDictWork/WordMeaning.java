import java.io.*;
import java.util.*;

public class WordMeaning {
	WordMeaningNode head; // head of list
	static String Word = null;
	static class WordMeaningNode {
		String data;
		WordMeaningNode next;
		// Constructor
		WordMeaningNode(String d)
		{
			data = d;
			next = null;
		}
	}
	WordMeaning(String word){
		Word = word;
	}
	public WordMeaning insert(WordMeaning list,String data)
	{
		WordMeaningNode new_node = new WordMeaningNode(data);
		new_node.next = null;
		if (list.head == null) {
			list.head = new_node;
		}
		else {
			WordMeaningNode last = list.head;
			while (last.next != null) {
				last = last.next;
			}
			last.next = new_node;
		}

		return list;
	}
	public void printList(WordMeaning list)
	{
		WordMeaningNode currNode = list.head;
		while (currNode != null) {
			System.out.print(currNode.data + " ");
			currNode = currNode.next;
		}
	}
	public ArrayList getList(WordMeaning list)
	{
		ArrayList<String> meanings = new ArrayList<>();

		WordMeaningNode currNode = list.head;
		while (currNode != null) {
			meanings.add(currNode.data);
			currNode = currNode.next;
		}
		return meanings;
	}
}
