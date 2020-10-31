import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry; // You may need it to implement fastSort

public class Sorting {

	/*
	 * This method takes as input an HashMap with values that are Comparable. 
	 * It returns an ArrayList containing all the keys from the map, ordered 
	 * in descending order based on the values they mapped to. 
	 * 
	 * The time complexity for this method is O(n^2) as it uses bubble sort, where n is the number 
	 * of pairs in the map. 
	 */
	public static <K, V extends Comparable> ArrayList<K> slowSort (HashMap<K, V> results) {
		ArrayList<K> sortedUrls = new ArrayList<K>();
		sortedUrls.addAll(results.keySet());	//Start with unsorted list of urls

		int N = sortedUrls.size();
		for(int i=0; i<N-1; i++){
			for(int j=0; j<N-i-1; j++){
				if(results.get(sortedUrls.get(j)).compareTo(results.get(sortedUrls.get(j+1))) < 0){
					K temp = sortedUrls.get(j);
					sortedUrls.set(j, sortedUrls.get(j+1));
					sortedUrls.set(j+1, temp);					
				}
			}
		}
		return sortedUrls;                    
	}


	/*
	 * This method takes as input an HashMap with values that are Comparable. 
	 * It returns an ArrayList containing all the keys from the map, ordered 
	 * in descending order based on the values they mapped to. 
	 * 
	 * The time complexity for this method is O(n*log(n)), where n is the number 
	 * of pairs in the map. 
	 */
	public static <K, V extends Comparable> ArrayList<K> fastSort(HashMap<K, V> results) {

		ArrayList<K> sortedUrls = buildHeap(results);
//		int size = sortedUrls.size() - 1;

//		for(int i = 1; i <= size-1; i++){
//			K temp = sortedUrls.get(1);
//			sortedUrls.set(1, sortedUrls.get(size+1 - i));
//			sortedUrls.set(size+1 - i,temp);
//
//			downHeap(results,sortedUrls,size-i);
//		}
		swap(sortedUrls, results);
		
		sortedUrls.remove(0);
		return sortedUrls;
	}


	private static <K, V extends Comparable> ArrayList<K> buildHeap(HashMap<K, V> results){
		ArrayList<K> heapIt=new ArrayList<K>();
		heapIt.add(null);
		heapIt.addAll(results.keySet());

		for(int k=0;k<heapIt.size();k++){
			upHeap(results,heapIt,k);
		}
		return heapIt;
	}

	private static <K, V extends Comparable> ArrayList<K> upHeap(HashMap<K, V> results,ArrayList<K> sortedUrls, int k){

		while(k>1  &&  results.get(sortedUrls.get(k)).compareTo(results.get(sortedUrls.get(k/2))) < 0 ) {
			K temp=sortedUrls.get(k);
			sortedUrls.set(k, sortedUrls.get(k/2));
			sortedUrls.set(k/2, temp);
			k=k/2;
		}
		return sortedUrls;
	}

	private static <K, V extends Comparable> ArrayList<K> downHeap(HashMap<K, V> results,ArrayList<K> sortedUrls,int max){
		int index = 1;

		while(2*index <= max) {
			int child=2 * index;

			if(child<max) {
				if(results.get(sortedUrls.get(child+1)).compareTo(results.get(sortedUrls.get(child))) <0) {
					child=child+1;
				}
			}
			if(results.get(sortedUrls.get(child)).compareTo(results.get(sortedUrls.get(index))) <0) {
				K temp = sortedUrls.get(index);
				sortedUrls.set(index, sortedUrls.get(child));
				sortedUrls.set(child,temp);
				index = child;
			}else {
				break;
			}	
		}
		return sortedUrls;
	}
	public static <K, V extends Comparable> void swap(ArrayList<K> sortedUrls, HashMap<K, V> results) {

		int size = sortedUrls.size() - 1;
		for(int i = 1; i <= size ; i++){
			K temp = sortedUrls.get(1);
			sortedUrls.set(1, sortedUrls.get(size+1 - i));
			sortedUrls.set(size+1 - i,temp);
			
			downHeap(results,sortedUrls,size-i);
		}
	}
}

