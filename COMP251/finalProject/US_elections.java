import java.util.*;


import java.io.File;
import java.io.FileNotFoundException;

public class US_elections {

	public static int solution(int num_states, int[] delegates, int[] votes_Biden, int[] votes_Trump, int[] votes_Undecided) {
		int total = getTotalVotes(delegates);
		int B = getVotesNeededForBiden(total);
		int T = getVotesNeededForTrump(total);
		ArrayList<Integer> uVoters = __init__();
		ArrayList<Integer> w =  __init__();
		ArrayList<Integer> v = __init__();
		for(int i = 0; i < num_states; ++i){
			if(votes_Biden[i] > votes_Trump[i] + votes_Undecided[i]){
				B -= delegates[i];
				continue;
			}
			else if(votes_Trump[i] >= votes_Biden[i] + votes_Undecided[i]){
				T -= delegates[i];
				continue;
			}
			uVoters.add(i);
			int pos = votes_Biden[i] + votes_Trump[i] + votes_Undecided[i]; 
			int pre = (pos / 2) + 1;
			int cur = Math.abs(pre - votes_Biden[i]);
			v.add(cur);

		}
		for(int i = 0; i < uVoters.size(); ++i) {
			w.add(delegates[uVoters.get(i)]);
		}	
		if(T <= 0){
			return -1;
		}
		else if(B <= 0){
			return 0;
		}
		
		int [] val = new int[v.size()];
		for(int i = 0; i < val.length; ++i) {
			val[i] = v.get(i);
		}
		
		int [] wt = new int[w.size()];
		for(int i = 0; i < wt.length; ++i) {
			wt[i] = w.get(i);
		}
		
		int sum = 0;
		for(int i = 0; i < wt.length; ++i) {
			sum += wt[i];
		}
		int [] arr = new int[sum + 1];
		for(int i = 1; i <= sum; ++i) {
			arr [i] = Integer.MAX_VALUE;
		}
		return bottomUpDP(val, wt, B, sum, arr, Integer.MAX_VALUE);
	}

	public static ArrayList<Integer> __init__() {
		return new ArrayList<Integer>();
	}

	public static int getTotalVotes(int [] arr){
		int sum = 0;
		for(int i : arr){
			sum += i;
		}
		return sum;
	}

	public static int getVotesNeededForBiden(int total){
		return (total / 2) + 1;
	}

	public static int getVotesNeededForTrump(int total){
		if(total % 2 != 0) {
			return (total / 2) + 1;
		}
		return total / 2;
	}

	public static int bottomUpDP(int[] v, int[] w, int B, int maxWeight, int[] minValue, int bigNum) {
		for(int i = 0; i < v.length; ++i) {
			for(int j = maxWeight; j >= w[i]; --j) {
				if(minValue[j - w[i]] != bigNum) {
					minValue[j] = Math.min(minValue[j], minValue[j - w[i]] + v[i]);
				}
			}
		}
		int answer = bigNum;
		int j = B;
		while(j <= maxWeight) {
			answer = Math.min(answer, minValue[j]);
			j++;
		}
		return answer;
	}

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


}
