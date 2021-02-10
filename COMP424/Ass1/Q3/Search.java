import java.util.*;

public class Search {
	private static ArrayList<ArrayList<City>> permutations = new ArrayList<ArrayList<City>>();
	private static String path;
	private static double optimalCost;
	public static ArrayList<City> opt = new ArrayList<City>();
	public static ArrayList<City> allCities;
	public static ArrayList<Double> COST;


	public static double bruteForceSearch() {
		double minCost = 100000;
		permutations = permute(retrieveCities());
		for(int i = 0; i < permutations.size(); ++i) {
			double cost = getPathCost(permutations.get(i));
			String oldPath = getPath(permutations.get(i));
			ArrayList<City> a = permutations.get(i);
			if(cost < minCost) {
				minCost = cost;
				path = oldPath;	//update path
				opt = a;
			}
			minCost = Math.min(minCost, cost);			
		}
		optimalCost = minCost;
		return minCost;
	}

	//100 instances of the tsp using brute force search
	public static void bruteForceSearch100Instances() {
		ArrayList<Double> costs = new ArrayList<Double>();
		double cost;
		for(int i = 0; i < 100; ++i) {
			cost = bruteForceSearch();
			costs.add(cost);
		}
		COST = costs;
		System.out.println("3a) ");
		System.out.println("Mean ===> " + getMeanCost(costs));
		System.out.println("Max  ===> " + getMaxCost(costs));
		System.out.println("Min  ===> " + getMinCost(costs));
		System.out.println("Deviation ===> " + getStandardDeviation(costs));
	}

	public static int numberOfOptimalSol(ArrayList<Double> costs) {
		int count = 0;
		for(int i =0; i < costs.size(); ++i) {
			if(costs.get(i) <= bruteForceSearch()) {
				count++;
			}
		}
		return count;
	}


	//random tour
	public static ArrayList<ArrayList<City>> generateRandomTours(){
		ArrayList<ArrayList<City>> tours = new ArrayList<ArrayList<City>>();
		for(int i = 0; i < 100; ++i) {
			ArrayList<ArrayList<City>> random = permute(retrieveCities());
			tours.add(random.get(i));
		}
		return tours;			//tour at index 0 will be the random start state
	}

	public static void random100Instance() {
		ArrayList<ArrayList<City>> tours = generateRandomTours();
		ArrayList<Double> costs = new ArrayList<Double>();
		for(int i = 0; i < tours.size(); ++i) {
			costs.add(getPathCost(tours.get(i)));
		}
		System.out.println("3b) ");
		System.out.println("Mean ===> " + getMeanCost(costs));
		System.out.println("Max  ===> " + getMaxCost(costs));
		System.out.println("Min  ===> " + getMinCost(costs));
		System.out.println("Deviation ===> " + getStandardDeviation(costs));
		int count = numberOfOptimalSol(costs);
		System.out.println("Optimal Solution Found " + count);
	}

	//Hill climbing algorithm
	public static ArrayList<City> hillClimbingSearch(ArrayList<City> cities) {
		ArrayList<City> newTour ;
		double bestDist = getPathCost(cities);
		double newDist;
		int swaps = 1;

		while (swaps != 0) { 
			swaps = 0;
			for (int i = 1; i < cities.size() - 2; i++) {
				for (int j = i + 1; j < cities.size() - 1; j++) {
					if ((cities.get(i).getDistance(cities.get(i - 1)) + cities.get(j + 1).getDistance(cities.get(j))) >=
							(cities.get(i).getDistance(cities.get(j + 1)) + cities.get(i - 1).getDistance(cities.get(j)))) {
						newTour = swap(cities, i, j);
						newDist = getPathCost(newTour);
						if (newDist < bestDist) { 
							cities = newTour;
							bestDist = newDist;
							swaps++;
						}
					}
				}
			}
		}
		return cities;
	}
	public static void hillClimbingSearch100Instances() {
		ArrayList<Double> costs = new ArrayList<Double>();
		ArrayList<ArrayList<City>> tours = generateRandomTours();

		for(int i = 0; i < 100; ++i) {
			double cost = getPathCost(hillClimbingSearch(tours.get(0)));
			costs.add(cost);

		}
		System.out.println("3c) ");
		System.out.println("Mean ===> " + getMeanCost(COST));
		System.out.println("Max  ===> " + getMaxCost(COST));
		System.out.println("Min  ===> " + getMinCost(COST));
		System.out.println("Deviation ===> " + getStandardDeviation(COST));
		int count = numberOfOptimalSol(costs);
		System.out.println("Optimal Solution Found " + count);
	}

	private static ArrayList<City> swap(ArrayList<City> cities, int i, int j){
		ArrayList<City> newTour = new ArrayList<City>();
		int size = cities.size();
		for(int c = 0; c <= i - 1; ++c) {
			newTour.add(cities.get(c));
		}


		//invert order between i and j
		int d = 0; 
		for(int c = i; c <= j; ++c) {
			newTour.add(cities.get(j - d));
			d++;
		}

		//append array from j to end
		for(int c = j + 1; c < size; ++c) {
			newTour.add(cities.get(c));
		}
		return newTour;
	}

	public static ArrayList<City> retrieveCities() {
		ArrayList<City> cities = new ArrayList<City>();
		for(int i = 1; i <= 7; ++i) {
			double x = Math.random();
			double y = Math.random();
			City city = new City("City No " + i, x, y);
			cities.add(city);
		}
		allCities = cities;
		return cities;
	}

	public static double getMeanCost(ArrayList<Double> costs) {
		double sum = 0.0;
		for(int i = 0; i < costs.size(); ++i) {
			sum += costs.get(i);
		}
		return sum / costs.size();
	}

	public static double getMaxCost(ArrayList<Double> costs) {
		double max  = 0.0;
		for(int i = 0; i < costs.size(); ++i) {
			max = Math.max(max, costs.get(i));
		}
		return max;
	}
	public static double getMinCost(ArrayList<Double> costs) {
		double min  = 10000;
		for(int i = 0; i < costs.size(); ++i) {
			min = Math.min(min, costs.get(i));
		}
		return min;
	}

	public static double getStandardDeviation(ArrayList<Double> costs) {
		double deviation = 0.0;
		double mean = getMeanCost(costs);
		for(int i = 0; i < costs.size(); ++i) {
			deviation += (costs.get(i) - mean) * (costs.get(i) - mean);
		}
		return Math.sqrt(deviation / costs.size()); 
	}


	//gets the cost of a path
	public static double getPathCost(ArrayList<City> cities) {
		double cost = 0.0;
		for(int i = 0; i < cities.size() - 1; ++i) {
			double distance = cities.get(i).getDistance(cities.get(i + 1));
			cost += distance;
		}
		cost += cities.get(cities.size() - 1).getDistance(cities.get(0)); 	//salseman returns home
		return cost;
	}

	public static String getPath(ArrayList<City> cities) {
		String path = "";
		for(int i = 0; i < cities.size(); ++i) {
			path += cities.get(i).getName();
		}
		return path;
	}

	public static void permute(ArrayList<City> arr, int k){
		for(int i = k; i < arr.size(); i++){
			Collections.swap(arr, i, k);
			permute(arr, k+1);
			Collections.swap(arr, k, i);

		}
		if (k == arr.size() -1){
			System.out.println(Arrays.toString(arr.toArray()));
			permutations.add(arr);
		}
	}
	public static ArrayList<ArrayList<City>> permute(List<City> cities) {
		ArrayList<ArrayList<City>> result = new ArrayList<ArrayList<City>>();

		//start from an empty list
		result.add(new ArrayList<City>());

		for (int i = 0; i < cities.size(); i++) {
			//list of list in current iteration of the array num
			ArrayList<ArrayList<City>> current = new ArrayList<ArrayList<City>>();

			for (ArrayList<City> l : result) {
				// # of locations to insert is largest index + 1
				for (int j = 0; j < l.size()+1; j++) {
					// + add num[i] to different locations
					l.add(j, cities.get(i));

					ArrayList<City> temp = new ArrayList<City>(l);
					current.add(temp);

					//System.out.println(temp);

					// - remove num[i] add
					l.remove(j);
				}
			}

			result = new ArrayList<ArrayList<City>>(current);
		}

		return result;
	}
}
