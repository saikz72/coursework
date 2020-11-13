import java.util.*;

class Assignment implements Comparator<Assignment> {
	int number;
	int weight;
	int deadline;

	protected Assignment() {
	}

	protected Assignment(int number, int weight, int deadline) {
		this.number = number;
		this.weight = weight;
		this.deadline = deadline;
	}

	/**
	 * This method is used to sort to compare assignment objects for sorting. Return
	 * -1 if a1 > a2 Return 1 if a1 < a2 Return 0 if a1 = a2
	 */
	@Override
	public int compare(Assignment a1, Assignment a2) {
		// should I order based on deadline or weights?????? //weight maximization
		if (a1.weight > a2.weight) {
			return -1;
		} else if (a2.weight > a1.weight) {
			return 1;
		} else {
			return a1.deadline < a2.deadline ? 1 : a1.deadline > a2.deadline ? -1 : 0;
		}
	}
}

public class HW_Sched {
	ArrayList<Assignment> Assignments = new ArrayList<Assignment>();
	int m; // number of assignments todo
	int lastDeadline = 0;

	protected HW_Sched(int[] weights, int[] deadlines, int size) {
		for (int i = 0; i < size; i++) {
			Assignment homework = new Assignment(i, weights[i], deadlines[i]);
			this.Assignments.add(homework);
			if (homework.deadline > lastDeadline) {
				lastDeadline = homework.deadline;
			}
		}
		m = size;
	}

	/**
	 * 
	 * @return Array where output[i] corresponds to the assignment that will be done
	 *         at time i.
	 */
	public int[] SelectAssignments() {
		// Sort assignments
		// Order will depend on how compare function is implemented
		Collections.sort(Assignments, new Assignment());
		Assignments.forEach((n) -> System.out
				.println("number = " + n.number + " deadline = " + n.deadline + " weight = " + n.weight));
		// If schedule[i] has a value -1, it indicates that the
		// i'th timeslot in the schedule is empty
		int[] schedule = new int[lastDeadline];
		for (int i = 0; i < schedule.length; i++) {
			schedule[i] = -1; // initialize all slots with -1
		}

		HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
		for(int i = 0; i < Assignments.size(); ++i) {
			int deadline = Assignments.get(i).deadline;
			ArrayList<Integer> list = new ArrayList<>();
			if(!map.containsKey(deadline)) {
				list.add(deadline);
				list.add(1);
				map.put(deadline, list);
			}else {
				int count = 1 + map.get(deadline).get(1);
				map.get(deadline).set(1, count);
				map.put(deadline, map.get(deadline));
			}
		}

		int j = 0;
		for (int i = 0; i < schedule.length; ++i) {
			if(j < Assignments.size()) {
				int deadline = Assignments.get(j).deadline;
				int number  = Assignments.get(j).number;
				int val = map.get(deadline).get(1);			// frequency
				if(map.get(deadline).get(0) != 0) {		// deadline has to be > frequency
					schedule[i] = number;
					int count = map.get(deadline).get(0) - 1;
					map.get(deadline).set(0, count);
					j++;
				}else{
					for(int k = j; k < Assignments.size(); k++){
						int deadline2 = Assignments.get(k).deadline;
						int number2  = Assignments.get(k).number;
						if(deadline != deadline2){
							schedule[i] = number2;
							j = k+1;
							break;
						}
					}
				}
				
			}
		}
		return schedule;
	}
}








