package finalProject;

import java.util.*;


import java.io.File;
import java.io.FileNotFoundException;

public class US_elections {
	public static void main(String[] args) {
		try {
			String path = args[0];
			File myFile = new File(path);
			Scanner sc = new Scanner(myFile);
			int num_states = sc.nextInt();
			int[] delegates = new int[num_states];
			int[] votes_Biden = new int[num_states];
			int[] votes_Trump = new int[num_states];
			int[] votes_Undecided = new int[num_states];
			for (int state = 0; state < num_states; state++) {
				delegates[state] = sc.nextInt();
				votes_Biden[state] = sc.nextInt();
				votes_Trump[state] = sc.nextInt();
				votes_Undecided[state] = sc.nextInt();
			}
			sc.close();
			int answer = solution(num_states, delegates, votes_Biden, votes_Trump, votes_Undecided);
			System.out.println(answer);
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
	public static int sumAll(int [] arr) {
		int res = 0;
		for(int i = 0; i < arr.length; i++) {
			res = res + arr[i];
		}
		return res;
	}

	public static int computeConvince(int[] arr1, int [] arr2, int[] arr3, int i) {
		int sum = arr1[i] + arr2[i] + arr3[i];
		int div = (sum / 2) + 1;
		return Math.abs(arr1[i] - div);
	}

	public static int[] convertToArray(List<Integer> list) {
		int arr [] = new int[list.size()];
		for(int i =0; i < list.size(); ++i) {
			arr[i] = list.get(i);
		}
		return arr;
	}

	public static int hasVictorBeenDecided(int biden, int trump) {
		if(biden <= 0) {
			return 0;
		} 
		else if(trump <= 0){
			return -1;
		}
		return 1;
	}

	public static int result(int maxInt, int biden, int maxW, int[] arr) {
		int res = maxInt;
		int i = biden;
		while(i <= maxW) {
			res = Math.min(res, arr[i]);
			i++;
		}
		return res;
	}

	public static int convinced(int [] delegates, List<Integer> uL, List<Integer> v, int B){
		Collections.sort(v);
		int min = 0;
		for(int i = 0; i < v.size(); ++i){
			int j = uL.get(i);
			int votes = delegates[j];
			B -= v.get(i);
			min += v.get(i);
			if(B <= 0){
				break;
			}
		}
		return min;
	}

	public static int decision(int m, int res, int biden, int [] w, int[] v ) {
		return res;
	}

	public static int[] fillIn(int x, int m) {
		int [] arr = new int[m + 1];
		for(int i = 1; i <= m; i++) {
			arr[i] = x;
		}
		return arr;
	}

	public static int solution(int num_states, int[] delegates, int[] votes_Biden, int[] votes_Trump, int[] votes_Undecided) {
		int sum = 0;
		int maxW = 0;
		List<Integer> uL = new LinkedList<>();
		List<Integer> w = new LinkedList<>();
		List<Integer> v = new LinkedList<>();
		for(int i = 0; i < num_states; i++) {
			sum += delegates[i];
		}
		int biden = (sum / 2) + 1;
		int trump = 0;
		if(sum % 2 != 0) {
			trump = biden;
		}else {
			trump = sum / 2;
		}

		for(int i = 0; i < num_states; i++){
			boolean bidenFlag  = false;
			boolean trumpFlag = false;

			if(votes_Trump[i] >= votes_Biden[i] + votes_Undecided[i]){
				trump = trump - delegates[i];
				trumpFlag = true;
			}
			else if(votes_Biden[i] > votes_Trump[i] + votes_Undecided[i]){
				biden = biden - delegates[i];
				bidenFlag = true;;
			}else if (bidenFlag == false && trumpFlag == false){
				uL.add(i);
				int c = computeConvince(votes_Biden, votes_Trump, votes_Undecided, i);
				v.add(c);
			}

		}	
		if(hasVictorBeenDecided(biden, trump) == 0) {			//biden won
			return 0;	
		}else if(hasVictorBeenDecided(biden, trump) == -1){		//trump won
			return -1;
		}else {
			for(int i = 0; i < uL.size(); i++) {
				w.add(delegates[uL.get(i)]);
			}
			int [] val = convertToArray(v);
			int [] wt = convertToArray(w);
			maxW = sumAll(wt);
			int[] arr = fillIn(Integer.MAX_VALUE, maxW);
			int i = 0;
			while(i < val.length) {			
				for(int j = maxW; j >= wt[i]; j--) {
					if(arr[j - wt[i]] != Integer.MAX_VALUE) {
						arr[j] = Math.min(arr[j], arr[j - wt[i]] + val[i]);
					}
				}
				i++;
			}
			return result(Integer.MAX_VALUE, biden, maxW, arr);
		}
	}

}
