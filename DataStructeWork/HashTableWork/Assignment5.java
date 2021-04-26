import java.io.*;
import java.util.*;

public class Assignment5 {
	public static int binarySearch(int arr[], int first, int last){
		int counter=0;
		int mid = (first + last)/2;  
		while( first < last ){  
			if ( arr[mid] == 1 ){
				last = mid - 1;  
				counter += (arr.length-mid);     
			}else if (arr[mid] == 0 ){  
				first = mid + 1;  
			}  
			mid = (first + last)/2;  
		}  
		return counter;
	}  
	///////////////////////////////////////////////////////////////////////////////////////////////
	static Hashtable<String, Integer> hsahatable = new Hashtable<String, Integer>();
	
	public static int HashFunction(String word) {
		int hashvalue = 0;
		for(int i=0;i<word.length();i++) {
			hashvalue += (int)word.charAt(i);
		}
		return (hashvalue%26);
	}
	public static void readFile(String filepath) {
		try{  
			//the file to be opened for reading  
			FileInputStream fis=new FileInputStream(filepath);       
			Scanner sc=new Scanner(fis);    //file to be scanned  
			//returns true if there is another line to read  
			String word;
			int hashValue;
			while(sc.hasNextLine()){
				word = sc.nextLine();      //returns the line that was skipped
				word = word.toLowerCase();
				hashValue = HashFunction(word);
				hsahatable.put(word,hashValue);
			}
			sc.close();     //closes the scanner  
		}  
		catch(IOException e){  
			e.printStackTrace();  
		}  
		System.out.println("Dictionary Hash Table:"+hsahatable);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*
		 
		Test Task One
		int []Array = {0, 0, 0, 0, 0, 1, 1};	
		System.out.println(binarySearch(Array,0,Array.length));
		int []Array1 = {0, 0, 0, 1, 1, 1, 1};	
		System.out.println(binarySearch(Array1,0,Array.length));
		int []Array2 = {0, 0, 0, 0, 0, 0, 0};	
		System.out.println(binarySearch(Array2,0,Array.length));
		 */
		readFile("//home//tauseefnawaz//eclipse-workspace//Ass5//src//dictionary.txt");
	}

}
