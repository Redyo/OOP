// Alex Rosell
// alro8042
// Grupp 076
package Inlupp2;

import java.util.*;
public class Competition {
	private Team team;
	private Participant participant;
	private Event event;
	private Result result;
	private char[] messageArray = new char[57];
	public Competition(){
		team = new Team(null, 5, 5, 5);
		event = new Event(null, 0);
		participant = new Participant(null, null, null, 100);
		result = new Result(5, null, 5, null, null);
		participant.setObjects(result, team);
		result.setEventObject(event);
		team.setParticipantObject(participant);
	}
	
	private void run(){
		Scanner scanner = new Scanner (System.in);
		String choice = "";
		while(!choice.equals("exit")){
			choice=scanner.nextLine();
			boolean isCommand = false;
			
			if (choice.equals("teams")){
				event.setMedalsForTeams();
				isCommand = true;
				
			}
			else if(choice.equals("add participant")){
				team.addParticipant(scanner);
				isCommand = true;
			}
			else if (choice.equals("remove participant")){
				team.removeParticipant(scanner);
				isCommand = true;
				
			}
			else if(choice.equals("add result")){
				participant.addResult(scanner);
				isCommand = true;
			}
			else if (choice.equals("participant")){
				participant.printResultsForParticipant(scanner);
				isCommand = true;
			}

			else if (choice.equals("add event")){	
				isCommand = true;
				result.addEvent(scanner);
				isCommand = true;
			}
			else if(choice.contains("message")){
				 
				String[] parts = choice.split("message ");
				String second = parts[1];
				printMessage(second);
				isCommand = true;
			}

			else if (choice.equals("exit")){
				System.out.println("Exiting...");
			}
			
			else if(result.checkIfEventExists(result.formatString(choice))){
				event.printEventResults(result.formatString(choice));
			}
			else{
				print(choice, isCommand);
			}
		}
	}

	
	private void print(String choice, boolean isCommand){
		if(result.checkIfEventExists(choice) && !isCommand){
			event.printEventResults(choice);
		}
		else{
			System.out.println("Unknown command: " + choice);
		}
		
	}
		
	private void printMessage(String str){
		str = str.toUpperCase();
		boolean biggerThanFiftySixLetters = false;
		if(str.length() > 56){
			biggerThanFiftySixLetters = true;
		}
		
		if (biggerThanFiftySixLetters) {
			str = str.substring(0, 57);
			messageArray = new char[57];
			for (int c = 0; c < 56; c++) {
				messageArray[c] = ' ';
			}

			for (int c = 0; c < str.length(); c++) {
				messageArray[c] = str.charAt(c);
			}

			for (int c = 0; c < 60; c++) {
				System.out.print('*');
			}
			System.out.println();

			for (int c = 0; c < 60; c++) {
				if (c == 59 || c == 0) {
					System.out.print('*');
				} else {
					System.out.print(' ');
				}
			}

			System.out.println();

			for (int c = 0; c < 58; c++) {
				if (c == 57) {
					System.out.print(" *");
				} else if (c == 0) {
					System.out.print("* ");
				} else if (c < messageArray.length) {
					System.out.print(messageArray[c - 1]);
				} else {
					System.out.print(' ');
				}
			}

			System.out.println();
			for (int c = 0; c < 60; c++) {
				if (c == 0 || c == 59) {
					System.out.print("*");
				} else {
					System.out.print(' ');
				}
			}

			System.out.println();

			for (int c = 0; c < 60; c++) {
				System.out.print('*');
			}

			System.out.println();
		}
		
		
		else{
			str = str.substring(0, str.length());
			messageArray = new char[str.length()+1];
			for (int c = 0; c < str.length(); c++) {
				messageArray[c] = ' ';
			}

			for (int c = 0; c < str.length(); c++) {
				messageArray[c] = str.charAt(c);
			}

			for (int c = 0; c < 60; c++) {
				System.out.print('*');
			}
			System.out.println();

			for (int c = 0; c < 60; c++) {
				if (c == 59 || c == 0) {
					System.out.print('*');
				} else {
					System.out.print(' ');
				}
			}

			System.out.println();

			for (int c = 0; c < 58; c++) {
				if (c == 57) {
					System.out.print(" *");
				} else if (c == 0) {
					System.out.print("* ");
				} else if (c < messageArray.length) {
					System.out.print(messageArray[c - 1]);
				} else {
					System.out.print(' ');
				}
			}

			System.out.println();
			for (int c = 0; c < 60; c++) {
				if (c == 0 || c == 59) {
					System.out.print("*");
				} else {
					System.out.print(' ');
				}
			}

			System.out.println();

			for (int c = 0; c < 60; c++) {
				System.out.print('*');
			}

			System.out.println();
		}
			
	}


	public static void main(String[] args){
		Competition startProgram = new Competition();
		startProgram.run();
	}

}
