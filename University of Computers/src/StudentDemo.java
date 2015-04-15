// You must use this demo.
import java.io.*;
import java.util.Scanner;

public class StudentDemo
{
	private static Scanner keyboard = new Scanner(System.in);
	/*
	 * Loop:    Get some sort of input
				print it out
				ask to save
				add to university data
	   print university data
	 */
	public static void main(String[] args)
	{
		System.out.println("How would you like to start?");
		
		University clerk = new University();
		Student person = new Student();// one student
		int numberOfStudents, i, count;
		System.out.println("Enter number of students:");
		numberOfStudents = keyboard .nextInt();
		for(i = 0; i < numberOfStudents; i++)
		{
			person.readInput();
			person.calculateData(); // This method must call private methods to do the
									// calculations.
			person.writeOutput();
			clerk.colectDataForReport(person);
		}
		clerk.printDataForSchoolReport();
	}
	public static void getInput(){
		
	}
	public static void printToFile(){
		
	}
	public static void readFromFile(){
		
	}
	
	/**
	 * <b>Purpose:</b> Allows for a reusable way of inquiring several options.<br><br>
	 * <b>Precondition:</b> An array of options must be supplied. The method will take<br>
	 * 						care of numbering, and collecting the response.<br><br>
	 * <b>Postcondition:</b> The selected answer will be returned as an integer.<br><br>
	 * <b>Note:</b> For ease-of-use, initialize required sting array in method call, <br>
	 * 				like so: <b>int response = menu(new String[]{"option one", "option two", "etc.."});</b><br>
	 * @param options
	 * @return
	 */
	private static int menu(String[] options){
		int selection;
		String space = "     ";
		
		int index = 1;
		for(String option: options){
			System.out.println(space + index + ": " + option);
			index++;
		}
		
		selection = intCheck(options.length);
		return selection;
	}
	
	/**
	 * <b>Purpose:</b> Checks to make sure the input is an integer.<br><br>
	 * <b>Precondition:</b> There must be a scanner available in class state variables.<br>
	 * @return valid input
	 */
	private static int intCheck(){
		boolean valid = false;
		int number = 0;
		
		do{
			try{
				number = keyboard.nextInt();
				valid = true;
			}
			catch(Exception e){
				System.err.println("Please input an integer.");
				keyboard.next();
			}
		}while(!valid);
		
		return number;
	}
	
	/**
	 * <b>Purpose:</b> Checks to make sure the input is and integer and falls<br>
	 * in the range of 1 to <i>lenght</i>.<br><br>
	 * <b>Precondition:</b> A length must be given, depends on intCheck() method.<br>
	 * @param length
	 * @return valid input
	 */
	private static int intCheck (int length){
		int toCheck = 0;
		boolean valid = false;
		
		do{
			try{
				toCheck = intCheck();
				if (toCheck > length || toCheck < 1)
					throw new Exception();
				else
					valid = true;
			}
			catch(Exception e){
				System.err.println("Please enter a number that ranges 1 - " + length);
			}
		}while(!valid);
		
		return toCheck;
	}
}