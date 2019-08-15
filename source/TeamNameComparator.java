// Alex Rosell
// alro8042
// Grupp 076
package Inlupp2;

import java.util.Comparator;

public class TeamNameComparator implements Comparator<Team> {
	@Override
	public int compare(Team t1, Team t2) {
		// TODO Auto-generated method stub
		String name1 = t1.getName();
		String name2 = t2.getName();
		return name2.compareTo(name1);

	
	}
};