// You must use this demo.
import java.io.*;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class StudentDemo
{
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args)
	{
		//File Name?:
		//Menu?:
			//Enter info to file
			//Calculate data for university from file
			//Exit
		String fileName = OutPut.validateFileName();
		Boolean finished = false;
		
		do{
			System.out.println("\n***************MAIN MENU***************");
			int response = OutPut.menu(new String[]{"Save student to file",
													"Calculate data from file",
													"Change working file",
													"Exit"});
			switch(response){
				case 1://Save Student
					Boolean moreStudents = true;
					do{
						Student person = new Student();
						person.readInput();
						person.calculateData(); // This method must call private methods to do the
												// calculations.
						person.writeOutput();
						person.save(fileName);
						
						System.out.println("Would you like to save another student?");
						int anotherStudent = OutPut.menu(new String[] {"Yes", "No"});
						if(anotherStudent == 2)
							moreStudents = false;
					}while(moreStudents);
					break;
				case 2://Calculate university data from file
					University clerk = new University();
					Scanner file = OutPut.getFile(fileName);
					try{
						do{
							
							Student person = new Student(file.nextLine());
							clerk.colectDataForReport(person);
						}while(file.hasNext());
						
						clerk.printDataForSchoolReport();
					}catch(NoSuchElementException e){
						System.out.println("You have to save a student before calculating the data.");
						System.out.println("Select \"1\" from the menu!");
					}
					file.close();
					break;
				case 3://Change fileName
					fileName = OutPut.validateFileName();
					break;
				case 4:
				default:
					System.out.println("Thank you, come again!");
					finished = true;
					break;
			}
		}while(!finished);
		
//		University clerk = new University();
//		Student person = new Student();// one student
//		int numberOfStudents, i, count;
//		System.out.println("Enter number of students:");
//		numberOfStudents = scan .nextInt();
//		for(i = 0; i < numberOfStudents; i++)
//		{
//			person.readInput();
//			person.calculateData(); // This method must call private methods to do the
//									// calculations.
//			person.writeOutput();
//			clerk.colectDataForReport(person);
//		}
//		clerk.printDataForSchoolReport();
	}
}