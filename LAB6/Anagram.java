package lab6;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Iterator;

/**
 * Class that checks Anagrams
 * @author Nidhi
 */
 
public class Anagram {

		/**
		 * Data Fields
		 */
		final Integer[] primes = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83,
				89, 97, 101 };
		Map<Character, Integer> letterTable;
		Map<Long, ArrayList<String>> anagramTable;
	
		/**
		 * Constructors
		 */
		public Anagram() {
			this.buildLetterTable();
			anagramTable = new HashMap<Long, ArrayList<String>>();
		}
	
		/**
		 * Builds a Letter Table
		 */
		private void buildLetterTable() {
			Character abc[] = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q',
					'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z' };
			letterTable = new HashMap<Character, Integer>();
			for (int j = 0; j < abc.length; j++) {
				letterTable.put(abc[j], primes[j]);
			}
		}
	
		/**
		 * functions that generates unique keys 
		 * @param s
		 * @return
		 */
		public Long myHashCode(String s) {
			if (s == "" || s == null) {
				throw new NullPointerException();
			} else {
				int j = 0;
				long key = (long) 1;
				while (j < s.length()) {
					Character index = s.charAt(j);
					key = key * letterTable.get(index);
					j++;
				}
				return key;
			}
		}
	
		/**
		 * functions for storing anagrams 
		 * @param s
		 */
		public void addWord(String s) {
			Long hold = myHashCode(s);
			ArrayList<String> record = anagramTable.get(hold);
			if (!anagramTable.containsKey(hold)) {
				ArrayList<String> temp = new ArrayList<String>();
				temp.add(s);
				anagramTable.put(hold, temp);
			} else {
				record.add(s);
				anagramTable.put(hold, record);
				
			}
		}
	
		/**
		 * @param s
		 * @throws IOException
		 */
		private void processFile(String s) throws IOException {
			FileInputStream fStream = new FileInputStream(s);
			BufferedReader br = new BufferedReader(new InputStreamReader(fStream));
			String strLine;
			while ((strLine = br.readLine()) != null) {
				this.addWord(strLine);
			}
			br.close();
		}
	
		/**
		 * Gets the key with the most anagrams
		 * @return
		 */
		
		private ArrayList<Map.Entry<Long,ArrayList<String>>> getMaxEntries()
		{
			ArrayList<Map.Entry<Long,ArrayList<String>>> hold = new ArrayList<Map.Entry<Long,ArrayList<String>>>(); 
			Iterator<Entry<Long, ArrayList<String>>> i1 = anagramTable.entrySet().iterator();
			int max = 0;
			while(i1.hasNext())
			{
				Entry<Long, ArrayList<String>> temp = i1.next();
				int size = temp.getValue().size();
				if(size == max)
				{
					hold.add(temp);
				}
				else {
						if (size > max){
						hold.clear();
						hold.add(temp);
						max = size;
						}
					}
			}
			return hold;
		}
	
		/**
		 * 
		 * @param args
		 */
		 
		public static void main(String[] args) {
			Anagram a = new Anagram();
			final long startTime = System.nanoTime();
			try {
				a.processFile("words_alpha.txt");		
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			ArrayList<Map.Entry<Long, ArrayList<String>>> maxEntries = a.getMaxEntries();
			final long estimatedTime = System.nanoTime() - startTime;
			final double seconds = ((double) estimatedTime / 1000000000);
			int length = maxEntries.get(0).getValue().size();
			long key = maxEntries.get(0).getKey();
			System.out.println("Time : " + seconds + "\n");
			System.out.println("Key of max anagrams: " + key + "\n");
			System.out.println("List of max anagrams : " + maxEntries.get(0).getValue() + "\n");
			System.out.println("Length of list of max anagrams: "+ length);
		}

}