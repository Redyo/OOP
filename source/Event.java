// Alex Rosell
// alro8042
// Grupp 076

package Inlupp2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Event {
	private String eventName;
	private int attempts;
	private ArrayList<Team> teams = new ArrayList<Team>();
//	private Team team;
	private Result tempResult;
	private HashMap<String, ArrayList<Result>> eventResults = new HashMap<>();
	public Event(String newEvent, int newAttempts){
		this.eventName=newEvent;
		this.attempts=newAttempts;
		
	}
	
	private void sortByHighestResult(ArrayList<Result> myList) {
		for (int i = 0; i < myList.size() - 1; i++) {
			for (int j = i + 1; j < myList.size(); j++) {
				if (myList.get(i).getResult() < myList.get(j).getResult()) {
					Result res = myList.get(j);
					myList.set(j, myList.get(i));
					myList.set(i, res);
				}
			}
		}
	}
	
	public Result getFoundResult(){
		return tempResult;
	}

	private void printTeamMedals(){
		Comparator<Team> byGold = (t1, t2) -> Integer.compare(
	            t1.getGold(), t2.getGold());
		Comparator<Team> bySilver = (t1, t2) -> Integer.compare(
	            t1.getSilver(), t2.getSilver());
		
		Comparator<Team> byBronze = (t1, t2) -> Integer.compare(
	            t1.getBronze(), t2.getBronze());
				
		Stream<Team> listStream = teams.stream().sorted(byGold.thenComparing(bySilver).thenComparing(byBronze));
		List<Team> sortedResults = listStream.collect(Collectors.toList());	    
		List<Team> completeList = sortedResults.subList(0, sortedResults.size());	    
		Collections.reverse(completeList);	
		System.out.println("1st    2nd     3rd    Team name \n*********************************");	
		for(Team t: completeList){
			System.out.println(t);
		}
		
	}
	
	public boolean checkIfTeamExists(String name){
		for(Team teamResult: teams){
			if(teamResult.getName().equals(name)){
				return true;
			}
		}
		return false;
	}
	
	public void addTeam(Team t){
		teams.add(t);
	}
	
	public void removeTeam(String r){
		LinkedList<Team> removeTeam = new LinkedList<Team>();
		for(Team t: teams){
			if(t.getName().equals(r)){
				
				removeTeam.add(t);
			}
		}
		
		teams.removeAll(removeTeam);
	}
	

	private void addMedal(String r, int placement){
		for(Team teamR: teams){
			if(teamR.getName().equals(r)){
				if(placement == 1){
					teamR.addGold();
				}
				
				else if(placement == 2){
					teamR.addSilver();
				}
				
				else if(placement == 3){
					teamR.addBronze();
				}
				
			}
		}
	}
	
	
	public void setMedalsForTeams(){
		Collections.sort(teams, new TeamNameComparator());
		if(teams.isEmpty()){
    		System.out.println("No teams available");
    	}

    	LinkedList<Team> removeTeam = new LinkedList<Team>();
    	for(Team team: teams){
    		team.clearTeamMedals();
    	}
    	
    	teams.removeAll(removeTeam);
    	for (Entry<String, ArrayList<Result>> entry : eventResults.entrySet()) {    		
    	    if(!eventResults.isEmpty()){
    	    	
    	    	boolean sameAsLastResult = false;
    	    	boolean notSameAsLastResult = false;
    	    	double lastResult = 0;
    	    	int currentPlacement = 0;
    	    	int currentResults = 0;
    	    	
    	    	for(int i = 0; entry.getValue().size() > i; i++){
    	    		notSameAsLastResult = false;
    	    		sameAsLastResult = false;
    	    		currentResults++; 	    		
    	    		if(lastResult == entry.getValue().get(i).getResult()){
    	    			sameAsLastResult = true;
    	    		}
    	    		
    	    		else{
    	    			notSameAsLastResult = true;
    	    		}
    	    	
    	    		if(sameAsLastResult){
    	    			
    	    		}
    	    		
    	    		else {
    	    			if(notSameAsLastResult){
    	    				currentPlacement = currentResults;
    	    			}
    	    			else{
    	    				currentPlacement++;
    	    			}
    	    			
    	    			
    	    		}
    	    		
    	    		if(currentPlacement == 1){
    	    			String team = entry.getValue().get(i).getTeam();
    	    			addMedal(team, 1);
    	    		}
    	    		
    	    		else if(currentPlacement == 2){
    	    			String team = entry.getValue().get(i).getTeam();
    	    			addMedal(team, 2);
    	    			
    	    		}
    	    		
    	    		else if(currentPlacement == 3){
    	    			String team = entry.getValue().get(i).getTeam();
    	    			addMedal(team, 3);
    	    		}
    	    		
    	    		lastResult = entry.getValue().get(i).getResult();
    	    	}

    	    }
    	}
    	printTeamMedals();
	}
	


	
	public boolean checkIfResultWithTeamNameExists(String teamName){
		for (Entry<String, ArrayList<Result>> entry : eventResults.entrySet()) {			
			for (int i = 0; entry.getValue().size() > i; i++) {
				if(entry.getValue().get(i).getTeam().equals(teamName)){	
					return true;
				}
			}
			
		}
		return false;
	}
	
	
	public boolean checkIfParticipantResultExists(int number, String eventName){
		for (Entry<String, ArrayList<Result>> entry : eventResults.entrySet()) {
			if(entry.getKey().equals(eventName)){
				for(int i = 0; entry.getValue().size() > i; i++){
					if(entry.getValue().get(i).getNumber() == number){
						
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public boolean checkIfNewResultIsHigher(int number, double result, String eventName){
		boolean exists = false;
		for (Entry<String, ArrayList<Result>> entry : eventResults.entrySet()) {
			if(entry.getKey().equals(eventName)){
				for(int i = 0; entry.getValue().size() > i; i++){
					if(entry.getValue().get(i).getNumber() == number){
						exists = true;
					}
					if(exists){
						if(entry.getValue().get(i).getResult() < result && entry.getValue().get(i).getNumber()==number){
							tempResult = entry.getValue().get(i);
							return true;						
						}
					}

				}
			}
		}
		return false;
		
	}
	

	
	public void printEventResults(String eventName){
		boolean sameResultAsLastResult = false;
		double lastResult = 0;
		int currentPlacement = 0;
		int var = 0;
		boolean notSameAsLastResult = false;
		
		for (Entry<String, ArrayList<Result>> entry : eventResults.entrySet()) {
			if(entry.getKey().equals(eventName)){
				
				for(int i = 0; entry.getValue().size() > i; i++){
					sameResultAsLastResult = false;
					
					notSameAsLastResult = false;
					var++;
					
				
					if(lastResult == entry.getValue().get(i).getResult()){
						sameResultAsLastResult = true;
					
					}
					else {
						notSameAsLastResult = true;
					}
			
					if(sameResultAsLastResult){
					
					}
				
					else {
						
						if(notSameAsLastResult){
							currentPlacement = var;
						}
						else{
							currentPlacement++;
						}
					}
				
					lastResult = entry.getValue().get(i).getResult();
					
					System.out.println(currentPlacement + ". "+ entry.getValue().get(i));
					
		

				}
			}
			
		}

	}
	
	
	
	public void storeResult(Result r){
		String theEvent = r.getEvent();
		if (eventResults.get(theEvent) != null){
			ArrayList<Result> list = new ArrayList<>();
			list.addAll(eventResults.get(theEvent));
			list.add(r);
			eventResults.put(theEvent,list);
			sortByHighestResult(list);
	    	
			
		}else{
			ArrayList<Result> list = new ArrayList<>();
			list.add(r);
			eventResults.put(theEvent,list);
		}
	}
	//
	
	public void removeAllResultsForParticipant(int number){
		for (Entry<String, ArrayList<Result>> entry : eventResults.entrySet()) {
    	    for(int i = 0; entry.getValue().size() > i; i++){
    	    	if(entry.getValue().get(i).getNumber() == number){   	    		
    	    		deleteResult(entry.getValue().get(i));

    	    	}
    	    }
		}
	}
	
	public void deleteResult(Result r){
		String n = r.getEvent();
		boolean found = false;
		if (eventResults.get(n) != null){
			ArrayList<Result> list = new ArrayList<>();
			list.addAll(eventResults.get(n));
			for (Result loc: list){
				if (r == loc) {
					found = true;
				}
			}
			if (found){
				list.remove(r);
			}
			eventResults.put(n,list);
		}
	}
	
	
	public String getEvent(){
		return eventName;
	}
	public int getAttempts(){
		return attempts;
	}
	
	public String toString(){
		return eventName + " ";
	}


}
