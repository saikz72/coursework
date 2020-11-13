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
		if (a1.deadline == a2.deadline) {
			if (a1.weight > a2.weight)
				return -1;
			else if (a1.weight < a2.weight)
				return 1;
			else
				return 0;
		} else if (a1.deadline > a2.deadline)
			return 1;
		else
			return -1;
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
		int j = 0; // tracks the index of assignments in the list
		for (int i = 0; i < schedule.length; i++) {
			schedule[i] = -1; // initialize all slots with -1
		}
		ArrayList<Assignment> l = get(Assignments);
		schedule[0] = l.get(0).number; // greedy choice = the howework with highest weight
		for (int i = 0; i < schedule.length; ++i) {
			// check if assignments don't overlap
			if (i < l.size()) {
				schedule[i] = l.get(i).number;
			}
		}
		return schedule;
	}

	public ArrayList<Assignment> get(List<Assignment> l) {
		HashSet<Assignment> seen = new HashSet<>();
		ArrayList<Assignment> ll = new ArrayList<>();
		ll.add(l.get(0));
		seen.add(l.get(0));
		for (int i = 1; i < l.size(); i++) {
			if (l.get(i - 1).deadline != l.get(i).deadline) {
				if (!seen.contains(l.get(i))) {
					ll.add(l.get(i));
					seen.add(l.get(i));
				}
			}
		}
		return ll;
	}
}
