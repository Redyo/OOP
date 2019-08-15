// Alex Rosell
// alro8042
// Grupp 076
package Inlupp2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Team{
	private String teamName;
	private int gold = 0;
	private int silver = 0;
	private int bronze = 0;
	private ArrayList<Participant> participants = new ArrayList <Participant>();
	private Participant participant;
	private Participant tempParticipant;
	
	public Team(String name, int gold, int silver, int bronze){
		this.gold = gold;
		this.silver = silver;
		this.bronze = bronze;
		this.teamName = name;
		
	}
	
	public void addParticipant(Participant p){
	participants.add(p);
	}
	
	public void setParticipantObject(Participant p){
		participant = p;
	}

	public boolean checkIfParticipantWithNumberExists(int i){
		for(Participant participant: participants){
			if(participant.getNumber()==i){
				tempParticipant = participant;
				return true;
			}
		}
		return false;
	}
	


	private String checkScanner(String str, int currentStep, Scanner scanner){
		String changedString = "";
		String completeString = "";
		while(str.trim().isEmpty()){
			if(currentStep == 1){
				System.out.println("First name can't be empty!");
				System.out.println("First name: ");
			}
			else if(currentStep == 2){
				System.out.println("Last name can't be empty!");
				System.out.println("Last name: ");
			}
			else if(currentStep == 3){
				System.out.println("Team name can't be empty!");
				System.out.println("Team name: ");
			}
			str = scanner.nextLine().toLowerCase();

		}
		changedString = str.trim();
		changedString = str.substring(0, 1).toUpperCase().replaceAll("\\s", "") + str.substring(1).replaceAll("\\s", "");
		completeString = changedString.substring(0,1).toUpperCase() + changedString.substring(1);	
		completeString = completeString.trim();
		
		if(!str.trim().isEmpty()){
			String trim = str.trim();
			String lowerCase = trim.toLowerCase();
			changedString = lowerCase.substring(0, 1).toUpperCase().replaceAll("\\s", "") + lowerCase.substring(1).replaceAll("\\s", "");
			completeString = changedString.substring(0,1).toUpperCase() + changedString.substring(1);	
			completeString = completeString.trim();
		}
		return completeString;
	}
	
	
	public void addParticipant(Scanner sc){
		
		System.out.println("First name: ");
		String firstName = sc.nextLine();
		firstName = checkScanner(firstName, 1, sc);
		
		System.out.println("Last name: ");
		String lastName = sc.nextLine();
		lastName = checkScanner(lastName, 2, sc);
		
		System.out.println("Team name: ");
		String teamName = sc.nextLine();
		teamName = checkScanner(teamName, 3, sc);
		Participant newParticipant;
		if(participants.isEmpty()){
			newParticipant = new Participant(firstName, lastName, teamName, 99 + 1);
		}
		else{		
			newParticipant = new Participant(firstName, lastName, teamName, participant.getNumber());
			
		}
		participants.add(newParticipant);
		//System.out.println(newParticipant.toString());
		System.out.println(firstName + " " + lastName + " from "+ teamName + " " + "with number "+ (participant.getNumber()) + " added");
		participant.addOneToNumber();
		
		Team team = new Team(teamName, 0, 0, 0);
		if(!participant.result().event().checkIfTeamExists(teamName)){
			participant.result().event().addTeam(team);
		}	
		
	}
	
	public void removeParticipant(Scanner scanner){
		//Scanner scanner = new Scanner(System.in);
		boolean found = false;
		String part = "";
		System.out.println("Number: ");
		int number = scanner.nextInt();
		int participantNumber = 0;
		String participantTeam = "";
		LinkedList<Participant> removeParticipant = new LinkedList<Participant>();
		for(Participant p : participants){
			if(p.getNumber()==number){
				removeParticipant.add(p);
				found = true;
				participantNumber = p.getNumber();
				participantTeam = p.getTeam();
			    part = p.toString();
			}
 
		}
		if(found){
			System.out.println(part + " has been removed");
			participant.removeParticipantResult(participantNumber, participantTeam);
			scanner.nextLine();
		}
		
		else{
			System.out.println("Participant with number: " + number + " was not found");
			scanner.nextLine();
		}

		participants.removeAll(removeParticipant);
	}
	
	
	public void clearTeamMedals(){
		gold = 0;
		silver = 0;
		bronze = 0;
	}
	
	public void addGold(){
		gold++;
	}
	
	public void addSilver(){
		silver++;
	}
	
	public void addBronze(){
		bronze++;
	}
	
	public Participant getFoundParticipant(){
		return tempParticipant;
	}

	public int getGold(){
		return gold;
	}
	
	public int getSilver(){
		return silver;
	}
	
	public int getBronze(){
		return bronze;
	}
	
	
	public String getName(){
		return teamName;
	}
	public String toString(){
		 return gold + "      " + silver + "       " + bronze + "      " + teamName;
	}
	

}
