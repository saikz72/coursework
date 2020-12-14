package finalProject;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class US_elections {

	public static int solution(int num_states, int[] delegates, int[] votes_Biden, int[] votes_Trump, int[] votes_Undecided) {
		int total = getTotalVotes(delegates);
		int B = getVotesNeededForBiden(total);
		int T = getVotesNeededForTrump(total);
		List<Integer> uL = new ArrayList<>();
		List<Integer> w = new ArrayList<>();
		List<Integer> v = new ArrayList<>();
		
		for(int i = 0; i < num_states; ++i){
			if(votes_Biden[i] > votes_Trump[i] + votes_Undecided[i]){
				B -= delegates[i];
				continue;
			}
			else if(votes_Trump[i] >= votes_Biden[i] + votes_Undecided[i]){
				T -= delegates[i];
				continue;
			}
			uL.add(i);
			int t = votes_Biden[i] + votes_Trump[i] + votes_Undecided[i]; 
			int x = (t / 2) + 1;
			int c = Math.abs(x - votes_Biden[i]);
			v.add(c);
			
		}
		if(T <= 0){
			return -1;
		}
		else if(B <= 0){
			return 0;
		}
		
		return convinced(delegates, uL, v, B);
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
//		int [] d = {7, 6, 2};
//		int [] b = {2401, 2401, 750};
//		int [] t = {3299, 2399, 750};
//		int [] u = {0,0,99};
//		System.out.println(solution(3, d, b, t, u));
	}

}
