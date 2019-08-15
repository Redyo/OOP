// Alex Rosell
// alro8042
// Grupp 076
package Inlupp2;

import java.util.Comparator;

public class EventNameComparator implements Comparator<Result> {
	@Override
	public int compare(Result n1, Result n2) {
		// TODO Auto-generated method stub
		String name1 = n1.getEvent();
		String name2 = n2.getEvent();
		return name1.compareTo(name2);

	
	}
};