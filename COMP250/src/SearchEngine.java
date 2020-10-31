import java.util.HashMap;
import java.util.ArrayList;

public class SearchEngine {
	public HashMap<String, ArrayList<String> > wordIndex;   // this will contain a set of pairs (String, LinkedList of Strings)	
	public MyWebGraph internet;
	public XmlParser parser;

	public SearchEngine(String filename) throws Exception{
		this.wordIndex = new HashMap<String, ArrayList<String>>();
		this.internet = new MyWebGraph();
		this.parser = new XmlParser(filename);
	}

	/* 
	 * This does a graph traversal of the web, starting at the given url.
	 * For each new page seen, it updates the wordIndex, the web graph,
	 * and the set of visited vertices.
	 * 
	 * 	This method will fit in about 30-50 lines (or less)
	 */
	public void crawlAndIndex(String url) throws Exception {

		if (this.internet.addVertex(url)) {
			this.internet.setVisited(url, true); 
		}

		ArrayList<String> listOfLinks = this.parser.getLinks(url);
		for(int i=0; i < listOfLinks.size(); i++) {

			if(!this.internet.getVisited(listOfLinks.get(i))) {
				crawlAndIndex(listOfLinks.get(i));
			}
			this.internet.addEdge(url, listOfLinks.get(i));
		}

		ArrayList<String> listOfWords = this.parser.getContent(url);
		
		for(int i = 0; i < this.parser.getContent(url).size(); i++) {
			listOfWords.add(i, listOfWords.remove(i).toLowerCase());
		}
		for(int i=0; i < listOfWords.size(); i++) {

			ArrayList<String> newKey = new ArrayList<String>();
			if(!(this.wordIndex.containsKey(listOfWords.get(i)))) {
				newKey.add(url);
				this.wordIndex.put(listOfWords.get(i), newKey);
			}else {
				if(!this.wordIndex.get(listOfWords.get(i)).contains(url)) {
					this.wordIndex.get(listOfWords.get(i)).add(url);
				}
			}

		}
		this.internet.setPageRank(url, 1);        

	}

	/* 
	 * This computes the pageRanks for every vertex in the web graph.
	 * It will only be called after the graph has been constructed using
	 * crawlAndIndex(). 
	 * To implement this method, refer to the algorithm described in the 
	 * assignment pdf. 
	 * 
	 * This method will probably fit in about 30 lines.
	 */
	public void assignPageRanks(double epsilon) {
		ArrayList<Double> firstPageRank =new  ArrayList<Double>();
		ArrayList<Double> nextPageRank =new  ArrayList<Double>();
		ArrayList<String> vertices =new  ArrayList<String>();

		boolean test =true;
		vertices = this.internet.getVertices();
		firstPageRank = this.computeRanks(vertices);
		nextPageRank = this.computeRanks(vertices);

		if(nextPageRank.isEmpty()) {		//no ranks found
			return;
		}
		for(int i=0; i< nextPageRank.size(); i++) {
			double sum =firstPageRank.get(i)-nextPageRank.get(i);
			if(Math.abs(sum) >= epsilon) {
				test =false;
				break;
			}				
		}
		while(!test) {
			firstPageRank = nextPageRank;
			nextPageRank = this.computeRanks(vertices);
			for(int i=0; i< nextPageRank.size(); i++) {
				double rankToUpdate =firstPageRank.get(i)-nextPageRank.get(i);
				if(Math.abs(rankToUpdate) >= epsilon) {
					test =false;
					break;
				}
				if(Math.abs(rankToUpdate) < epsilon) {
					test =true;
					break;
				}
			}
		}		
	}

	/*
	 * The method takes as input an ArrayList<String> representing the urls in the web graph 
	 * and returns an ArrayList<double> representing the newly computed ranks for those urls. 
	 * Note that the double in the output list is matched to the url in the input list using 
	 * their position in the list.
	 */

	public ArrayList<Double> computeRanks(ArrayList<String> vertices) {
		ArrayList<Double> pageRanksList = new ArrayList<Double>();
		double rank;

		for(int j = 0; j < vertices.size(); j++) {
			double rankToUpdate = 0.0;
			ArrayList<String> edgesInto = internet.getEdgesInto(vertices.get(j));
			if(!edgesInto.isEmpty()) {
				for(int i = 0; i < edgesInto.size(); i++) {
					double pageRank = internet.getPageRank(edgesInto.get(i));
					int outDegree = internet.getOutDegree(edgesInto.get(i));
					rankToUpdate +=  (pageRank / outDegree);
				}
			}
			rank = 0.5 *(rankToUpdate) + 0.5;
			pageRanksList.add(rank);
			internet.setPageRank(vertices.get(j), rank);
		}
		return pageRanksList;
	}


	/* Returns a list of urls containing the query, ordered by rank
	 * Returns an empty list if no web site contains the query.
	 * 
	 * This method should take about 25 lines of code.
	 */
	public ArrayList<String> getResults(String query) {

		ArrayList<String> list = wordIndex.get(query.toLowerCase());
		HashMap<String, Double> myMap = new HashMap<>();

		if(list == null) {			
			return new ArrayList<String>();
		}else if(list.size() == 1) {	
			return list;
		}else {
			for(int i=0; i<list.size(); i++) {
				myMap.put(list.get(i), internet.getPageRank(list.get(i)));
			}
			return Sorting.slowSort(myMap);
		}
	}
}
