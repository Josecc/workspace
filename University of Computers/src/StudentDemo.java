// You must use this demo.
import java.io.*;
import java.util.Scanner;

public class StudentDemo
{
	private static Scanner scan = new Scanner(System.in);

	public static void main(String[] args)
	{
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