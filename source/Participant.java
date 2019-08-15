// Alex Rosell
// alro8042
// Grupp 076
package Inlupp2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

public class Participant {
	
	private String firstName;
	private String lastName;
	private String teamName;
	private int number = 99;
	private Team team;
	private ArrayList<Result> results = new ArrayList <Result>();
	private Result result;
	public Participant(String newFirstName,String newLastName,String newTeam, int newNumber){
		this.firstName=newFirstName;
		this.lastName=newLastName;
		this.teamName=newTeam;
		this.number=newNumber;
	}
	
	public void setObjects(Result r, Team t){
		result = r;
		team = t;
	}
	public void printResultsForParticipant(Scanner scanner){
		System.out.println("Number: ");
		int number = scanner.nextInt();
		boolean participantExists = false;
		boolean participantHasResults = false;
		if(team.checkIfParticipantWithNumberExists(number)){
			participantExists = true;
		}
		
		if(participantExists){
			for(Result r: results){
				if(r.getNumber()==number){
					participantHasResults = true;
				}
			}
			
			if(participantHasResults){
				Collections.sort(results, new EventNameComparator());
				for (Result rt: results){
					if (rt.getNumber()==(number)){
						
						System.out.println(rt.getStringForResults());	
					}
					//scanner.nextLine();
				}
			}
			else{
				System.out.println("This participant does not have any results...");
				//scanner.nextLine();
			}
	
		}
		
		else{
			System.out.println("No participant with number " + number);
			//scanner.nextLine();

		}
		scanner.nextLine();
	}
	
	public void addOneToNumber(){
		this.number = number + 1;
	}
	public int getParticipantNumber(){
		return number;
	}
	
	public Result result(){
		return result;
	}
	public void addResult(Scanner scanner) {
		int attempts = 0;
		boolean teamDoesNotExist = false;
		boolean participantExists = false;
		boolean foundParticipant = false;
		boolean foundEvent = false;
		System.out.println("Number: ");
		int numberOfParticipant = scanner.nextInt();

		if (team.checkIfParticipantWithNumberExists(numberOfParticipant)) {
			foundParticipant = true;
		}
		if (foundParticipant) {
			participantExists = true;
		} else {
			scanner.nextLine();
			System.out.println("No such participant with number " + numberOfParticipant);
			participantExists = false;
		}
		if (participantExists) {
			scanner.nextLine();
			System.out.println("Event: ");
			String nameOfEvent = scanner.nextLine();
			if (result.checkIfEventExists(nameOfEvent)) {
				foundEvent = true;
			}
			if (!foundEvent) {
				nameOfEvent = result.formatString(nameOfEvent);
				if(result.checkIfEventExists(nameOfEvent)){
					foundEvent = true;
				}
				else{
					System.out.println("No event called: " + nameOfEvent + " found!");
				}
				
			}
			if (foundEvent) {
				if (team.checkIfParticipantWithNumberExists(numberOfParticipant)) {
					Participant p = team.getFoundParticipant();
					Event event = result.getFoundEvent();
					for (Result r : results) {
						if (r.getNumber() == (numberOfParticipant) && r.getEvent().equals(nameOfEvent)) {
							attempts++;
						}
					}
					if (event.getAttempts() > attempts) {
						String team = p.getTeam();
						
						System.out.println("Results for " + p.getFirstName() + " " + p.getLastName() + " from " + p.getTeam() + " in " + nameOfEvent + ":");
						String name = p.getFirstName() + " " + p.getLastName();
						double resultForParticipant = scanner.nextDouble();
						while (resultForParticipant < 0) {
							System.out.println("Must be greater than or equal to zero!");
							System.out.println("Results for " + p.toString() + " in entered event:");
							resultForParticipant = scanner.nextDouble();
						}
						scanner.nextLine();
						Result newResult = new Result(resultForParticipant, nameOfEvent, numberOfParticipant, name,
								team);
						results.add(newResult);
						Team newTeam = new Team(p.getTeam(), 0, 0, 0);
						if (result.event().checkIfTeamExists(team)) {
							teamDoesNotExist = false;
						} else {
							teamDoesNotExist = true;
						}

						if (teamDoesNotExist) {
							result.event().addTeam(newTeam);
						}

						boolean exists = false;
						boolean addResult = false;
						if (result.event().checkIfParticipantResultExists(numberOfParticipant, nameOfEvent)) {
							exists = true;
						}

						if (exists) {
							if (result.event().checkIfNewResultIsHigher(numberOfParticipant, resultForParticipant,
									nameOfEvent)) {
								result.event().deleteResult(result.event().getFoundResult());
								addResult = true;
							} else if (!result.event().checkIfNewResultIsHigher(numberOfParticipant,
									resultForParticipant, nameOfEvent)) {
								addResult = false;
							}
						}

						else {
							addResult = true;
						}

						if (addResult) {
							result.event().storeResult(newResult);

						}

					}

					else {
						System.out.println(p.toString() + " has already made his " + event.getAttempts()
								+ " attempts in " + event.getEvent());
					}
				}
			}
		}

	}
	
	public void removeParticipantResult(int number, String teamName){
		boolean removeTeam = false;
		
		result.event().removeAllResultsForParticipant(number);
		LinkedList<Result> removeResult = new LinkedList<Result>();

		int i = 0;
		for(Result result: results){
			if(result.getNumber() == number){
				removeResult.add(result);
			}
			
		}
		results.removeAll(removeResult);

		if(result.event().checkIfResultWithTeamNameExists(teamName)){
			i++;
		}

		if(i == 0){
			removeTeam = true;
		}

		if(removeTeam){
			result.event().removeTeam(teamName);
		}

	}
	
	
	public String getFirstName(){
		return firstName;
	}
	
	public String getLastName(){
		return lastName;
		
	}
	public String getTeam(){
		return teamName;
	}
	public int getNumber(){
		return number;
	}
	
	public String toString(){
		return firstName + " " + lastName + " " + teamName + " " + number;
	}
	
}