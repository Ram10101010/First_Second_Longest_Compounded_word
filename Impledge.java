package Impledge_technologies;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


class Node{
	// Create a Node and each node have a 26 different char storing capability 
	Node links[]= new Node[26]; 
	boolean flag;				// flag is says that is this last char or not 
	public char children;		// children store the char 
	
	public Node() {
		
	}
	boolean ContainsKey(char ch) {
		return (links[ch - 'a'] != null); 	
	}
	/*Store the char at the position. which means we have a 26 char space so if we store directly 
	 ch or the alphabet and if the alphabet in smallercase the the ascii value is 97 which is 
	 Bigger than 26 so it's showing an Exception ArrayOutOfBound ,,,,, using this approach we can 
	 find this solution [ch-'a'], 'a' = 97 so if we subtract form 'ch' ascii character by 
	 'a' the we easily store ch .And that time No Exception is occurs. */
	Node get(char ch) {			
		return links[ch-'a'];	
	}
	void put(char ch,Node node) {
		links[ch-'a'] = node;
	}
	void setEnd() {
		flag = true;			// If the ch is last character then mark as true 
	}
	boolean isEnd() {
		return this.flag;
	}
	
}
class Trie{
	//Now create a Trie data Structure to insert and search the word 
	public static Node root;
	
	public Trie(){
		root = new Node();
	}
	// insert a word into the trie
	public void insert(String word) {
		Node node = root;
		for(int i =0 ;i<word.length();i++) {  // if the word length is < i than the loop will be 
											  // Terminated 
			
			/*in this section we check that if the ch is exist in Tire already or not if not exist then 
			 * create a New Node and insert the ch and at the same time we create reference of New Node*/
			if(!node.ContainsKey(word.charAt(i))) {  // 
				node.put(word.charAt(i), new Node());
			}
			node = node.get(word.charAt(i));
		}
		node.setEnd();  // at the end we the assign the flag = True
	}
	static boolean check_if_compounded(String word) { // find the mistake and solve it quickly 
		Node node = root;
		//boolean flag = true;
		
		for(int i=0;i<word.length()  ;i++) {
			
			if(node.ContainsKey(word.charAt(i))) {
				
				node = node.get(word.charAt(i));       
				
				if(node.isEnd()) {
					check_if_compounded(word.substring(i,word.length())); // Again call the method as argument substring
				}
			}
			else {
				return false;
			}
		}
		return true;
	}
}


public class Impledge {
	public static void main(String rpk[]) {
		long start = System.nanoTime();
		compounded();    /* Call the method . hear the we not create a any obj of the calss
						   because the method is a static method so don't require to create and 
						   object of the class*/
		long end = System.nanoTime();
		long extime = end - start;
		System.out.println("Execution time : "+extime*0.000000001+" seconds");
	}
	public static void compounded() {
		
		BufferedReader bufReader = null;
		try {
			bufReader = new BufferedReader(new FileReader("E:\\Input_02.txt"));  
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} 
    	 
    	 ArrayList<String> dictionary = new ArrayList<String>();  // Create a dictionary to Store all the words present in the file
         String line = null;
		try {
			line = bufReader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	 while (line != null) { 
    		 dictionary.add(line); 
    		 try {
				line = bufReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			} 
    	}
		
		
		Trie obj = new Trie();
		for(int i = 0;i < dictionary.size();i++) {
			String word = dictionary.get(i);     
			obj.insert(word);         // Hear we Assign the word to the Trie using insert method 
		}
	
		// Now it's time to find out the First And second Longest Compounded word in a dictionary 
		String longest = "";
		String Second_longest = "";
		for(int i = 0;i < dictionary.size();i++) {
			String word = dictionary.get(i);       // store the word in to 'a' form the Dictionary 
			if(Trie.check_if_compounded(word)) {	// then call the method and check it it compounded word or not 
				if(word.length() > longest.length()) {
					Second_longest = longest;
					longest = word;
				}
				else if(word.length() == longest.length() && word.length() < 0) {
					longest = word;
				}
				else if(word.length() > Second_longest.length() && word.length() != longest.length()) {
					Second_longest = word;
				}
			}
		}
		
		System.out.println("Second longest compounded word : "+Second_longest);
		System.out.println("longest compounded word : "+longest);
		
	}
}
