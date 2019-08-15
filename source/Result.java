// Alex Rosell
// alro8042
// Grupp 076
package Inlupp2;


import java.util.ArrayList;
import java.util.Scanner;


public class Result  {
	private double result;
	private int number;
	private String eventName;
	private String teamName;
	private String participantName;
	private Event event;
	private Event tempEvent;
	private ArrayList<Event> events = new ArrayList<Event>();
	
	public Result(double result,String event, int number, String name, String team){
		this.result = result;
		this.eventName = event;
		this.number = number;
		this.participantName = name;
		this.teamName = team;
	}
	public void addEvent(Event e){
		events.add(e);
	}	
	
	
	public boolean checkIfEventExists(String name){
		for(Event event: events){
			if(event.getEvent().equals(name)){
				tempEvent = event;
				return true;
			}
		}
		return false;
	}
	
	public Event getFoundEvent(){
		return tempEvent;
	}
	
	public String formatString(String eventName){
		boolean twoWords = false;
		eventName = eventName.trim();
		String[] parts = eventName.split(" ");
		String completeEventName = "";
		String firstWord = parts[0];
		String secondWord = "";
		String firstWordToLowerCase = firstWord.toLowerCase();
		firstWord = firstWordToLowerCase.substring(0,1).toUpperCase() + firstWordToLowerCase.substring(1);
		
		if(parts.length == 2){
			twoWords = true;
			secondWord = parts[1];
			
		}
		if(twoWords){
			completeEventName = firstWord + " " + secondWord.toLowerCase();
			return completeEventName;
		}
		else{
			completeEventName = firstWord;
			return completeEventName;
		}
	}
	
	
	public void addEvent(Scanner scanner){
		boolean found = false;
		String completeEventName = "";
		System.out.println("Event name: ");
		String tempEvent = scanner.nextLine();
		tempEvent = tempEvent.trim();

		while(tempEvent.isEmpty()){
			System.out.println("Event name can't be empty!");
			System.out.println("Event name: ");
			tempEvent = scanner.nextLine().toLowerCase();
			tempEvent = tempEvent.trim();
		}
		
		completeEventName = formatString(tempEvent);
		
		for(Event e: events){
			if(e.getEvent().equals(completeEventName)){
				found = true;
			}
		}
		if(found){
			System.out.println("That event already exists");
		}		
		else{
			int minAttempts = 1;
			System.out.println("Attempts: ");
			int tempEventAttempts = scanner.nextInt();
			while(tempEventAttempts < minAttempts){
				System.out.println("Error: must be bigger than 0!");
				System.out.println("Attempts: ");
				
				tempEventAttempts = scanner.nextInt();
				
			}		
			
			Event newEvent = new Event(completeEventName, tempEventAttempts);
			events.add(newEvent);
			System.out.println(completeEventName + " added");
			scanner.nextLine();
		}
	}
	
	public void setEventObject(Event e){
		event = e;
	}
	
	public Event event(){
		return event;
	}
	
	public String getStringForResults(){ 
		return "Results for "+ participantName + " in " + eventName + ": " + result; 
	}
	
	public int getNumber(){
		return number; 
	}
	
	public String getEvent(){ 
		return eventName; 
	}
	
	public String getTeam(){
		return teamName;
	}
	
	public double getResult(){
		return result;
	}

	public String toString(){
		return participantName + " "  + teamName + " " + result;
	}
}
