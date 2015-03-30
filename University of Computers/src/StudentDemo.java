// You must use this demo.
import java.io.*;
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
			int response = OutPut.menu(new String[]{"Save student to file",
													"Calculate data from file",
													"Change working file",
													"Exit"});
			switch(response){
				case 1://Save Student
					Boolean moreStudents = false;
					do{
						Student person = new Student();
						person.readInput();
						person.calculateData(); // This method must call private methods to do the
												// calculations.
						person.writeOutput();
						person.save(fileName);
					}while(moreStudents);
					break;
				case 2://Calculate university data from file
					break;
				case 3://Change fileName
					break;
				case 4:
				default:
					System.out.println("Thank you, come again!");
					finished = true;
					break;
			}
		}while(!finished);
		
		University clerk = new University();
		Student person = new Student();// one student
		int numberOfStudents, i, count;
		System.out.println("Enter number of students:");
		numberOfStudents = scan .nextInt();
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
}