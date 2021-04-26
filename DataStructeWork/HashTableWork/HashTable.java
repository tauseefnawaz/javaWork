import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

class HashNode<K, V> {
    K key;
    V value;
    HashNode<K, V> next;
    public HashNode(K key, V value)
    {
        this.key = key;
        this.value = value;
    }
}
public class HashTable<K, V> {
    private ArrayList<HashNode<K, V> > bucketArray;
    private int numBuckets;
    private int size;
    public HashTable()
    {
        bucketArray = new ArrayList<>();
        numBuckets = 100;
        size = 0;
        for (int i = 0; i < numBuckets; i++)
            bucketArray.add(null);
    }
 
    public int size() { return size; }
    public boolean isEmpty() { return size() == 0; }
    private int getBucketIndex(K key)
    {
        int hashCode = key.hashCode();
        int index = hashCode % numBuckets;
        index = index < 0 ? index * -1 : index;
        return index;
    }
    public V get(K key)
    {
        int bucketIndex = getBucketIndex(key);
        HashNode<K, V> head = bucketArray.get(bucketIndex);
        while (head != null) {
            if (head.key.equals(key))
                return head.value;
            head = head.next;
        }
        return null;
    }
    public void printHashTable()
    {
    	for(HashNode<K, V> head : bucketArray) {
            while(head != null) {
                System.out.println("{Key : "+head.key+",  Value : "+head.value+"}");
                head = head.next;
            }
    	}
    }
    public void add(K key, V value)
    {
        int bucketIndex = getBucketIndex(key);
        HashNode<K, V> head = bucketArray.get(bucketIndex);
        while (head != null) {
            if (head.key.equals(key)) {
                head.value = value;
                return;
            }
            head = head.next;
        }
 
        // Insert key in chain
        size++;
        head = bucketArray.get(bucketIndex);
        HashNode<K, V> newNode = new HashNode<K, V>(key, value);
        newNode.next = head;
        bucketArray.set(bucketIndex, newNode);

        if ((1.0 * size) / numBuckets >= 0.7) {
            ArrayList<HashNode<K, V> > temp = bucketArray;
            bucketArray = new ArrayList<>();
            numBuckets = 2 * numBuckets;
            size = 0;
            for (int i = 0; i < numBuckets; i++)
                bucketArray.add(null);
 
            for (HashNode<K, V> headNode : temp) {
                while (headNode != null) {
                    add(headNode.key, headNode.value);
                    headNode = headNode.next;
                }
            }
        }
    }
	public static int HashFunction(String word) {
		int hashvalue = 0;
		for(int i=0;i<word.length();i++) {
			hashvalue += (int)word.charAt(i);
		}
		return (hashvalue%26);
	}
	public static void main(String[] args) {
		HashTable<String, Integer> hashtable = new HashTable<>();

		String filepath = "//home//tauseefnawaz//eclipse-workspace//Ass5//src//dictionary.txt";
		String key = null;
		try{  
			//the file to be opened for reading  
			FileInputStream fis=new FileInputStream(filepath);       
			Scanner sc=new Scanner(fis);    //file to be scanned  
			//returns true if there is another line to read  
			String word;
			int hashValue;
			int i=0;
			while(sc.hasNextLine()){
				word = sc.nextLine();      //returns the line that was skipped
				word = word.toLowerCase();
				if(i==0) {key=word;i+=1;}
				hashValue = HashFunction(word);
				hashtable.add(word,hashValue);
			}
			sc.close();     //closes the scanner  
		}  
		catch(IOException e){  
			e.printStackTrace();  
		}  
		System.out.println("Dictionary Hash Table:");
		hashtable.printHashTable();
	}

}
