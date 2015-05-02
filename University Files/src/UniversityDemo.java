/**
 * Author: Jose Canahui
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class UniversityDemo {
	private static String studentFileName = "studentData";
	private static String universityFileName = "universityData";
	private static String outputFileName = "outputData";
	static PrintWriter fileOutputStream = null;
	private static Scanner keyboard = new Scanner(System.in);
	
	public static void main(String[] args){
		//menu
		//read files into memory
		//loop though each line, send line to constructor... parse?
		//create a new object, add it to the object list
		//generate output file
		boolean done = false;
		System.out.print("Welcome, ");
		do{
			System.out.println("What would you like to do next?");
			int response = OutPut.menu(new String[] {"Add a new Student", "Add a new university", "Print data file","Change working file names", "Exit"});
			switch(response){
				case 1:
					addNewStudent();
					break;
				case 2:
					addNewUniversity();
					break;
				case 3:
					printDataFile();
					break;
				case 4:
					changeFileNames();
					break;
				case 5:
					System.out.println("Thank you for using the UNIVERSITY PROGRAM! - Jose Canahui");
					done = true;
					break;
			}
		}while(!done);
	}
	
	/**
	 * <b>Algorithm:</b>
	 * <ul>
	 * <li>Create a new Student object</li>
	 * <li>Collect student data</li>
	 * <li>Save data to student file</li>
	 * </ul>
	 */
	public static void addNewStudent(){
		Student myStudent = new Student();
		myStudent.collectData();
		//System.out.println(myStudent);
		myStudent.save(studentFileName);
		System.out.println("Student saved!\n");
	}
	
	/**
	 * <b>Algorithm:</b>
	 * <ul>
	 * <li>Create new University object</li>
	 * <li>Collect university data</li>
	 * <li>Print university data, ask for correctness</li>
	 * <li>save University data to file</li>
	 * </ul>
	 */
	public static void addNewUniversity(){
		University myUniversity = new University();
		myUniversity.collectData();
		//System.out.println(myUniversity);
		myUniversity.save(universityFileName);
		System.out.println("University saved!\n");
	}
	
	/**
	 * <b>Algorithm:</b>
	 * <ul>
	 * <li>Order the lists of students and universities in alphabetical order</li>
	 * <li>Loop through students, and find their university</li>
	 * <li>compute the studentData, pass it to university and student</li>
	 * <li>Print the data to the file, separating students and universities.</li>
	 * </ul>
	 */
	public static void printDataFile(){
		LinkedList<Student> students= new LinkedList<Student>();
		LinkedList<University> universities= new LinkedList<University>();
		
		//Input students from file
		Scanner file = OutPut.getFile(studentFileName);
		try{
			do{
				Student person = new Student(file.nextLine());
				students.add(person);
			}while(file.hasNext());
			//System.out.println(students.peek());
		}catch(NoSuchElementException e){
			System.out.println("You have to save a student before calculating the data.");
			System.out.println("Select \"1\" from the menu!");
		}
		file.close();
		
		//input universities from file
		file = OutPut.getFile(universityFileName);
		try{
			do{
				University myInstitution = new University(file.nextLine());
				universities.add(myInstitution);
			}while(file.hasNext());
			//System.out.println(universities.peek());
		}catch(NoSuchElementException e){
			System.out.println("You have to save a student before calculating the data.");
			System.out.println("Select \"1\" from the menu!");
		}
		file.close();
		
		//Sorting linked lists
		Collections.sort(students, new Comparator<Student>() {
			public int compare(Student o1, Student o2) {
				return o1.getLast().compareTo(o2.getLast());
			}
		});
		Collections.sort(universities, new Comparator<University>() {
			public int compare(University o1, University o2) {
				return o1.getName().compareTo(o2.getName());
			}
		});
		//Deleting previous info in file
		try{
			fileOutputStream = new PrintWriter(new BufferedWriter(new FileWriter(outputFileName + ".txt", false)));
		}
		catch(IOException e1){
			System.out.println("Error writing to file: " + outputFileName + ".txt, Please make sure"
								+ " the file is not already  open and still present.");
			System.err.println("File not modified.");
			System.exit(0);
		}
		fileOutputStream.print("");//Writing output to file
		fileOutputStream.close();
		
		//Looping through student objects and selecting their university
		for (Iterator<Student> i = students.iterator(); i.hasNext();) {
			Student myStudent =  i.next();
			for(Iterator<University> u = universities.iterator(); u.hasNext();){
				University myUniversity = u.next();
				if(myUniversity.getName().equals(myStudent.getUniversity())){
					StudentData data = new StudentData(myStudent, myUniversity);
					myStudent.setData(data);
					myUniversity.addStudent(data);
					try{
						fileOutputStream = new PrintWriter(new BufferedWriter(new FileWriter(outputFileName + ".txt", true)));
					}
					catch(IOException e1){
						System.out.println("Error writing to file: " + outputFileName + ".txt, Please make sure"
											+ " the file is not already  open and still present.");
						System.err.println("File not modified.");
						System.exit(0);
					}
					fileOutputStream.print(myUniversity.toString() + myStudent.toString() +data);//Writing output to file
					fileOutputStream.close();
				}
			}
		}
		
		for(Iterator<University> u = universities.iterator(); u.hasNext();){
			University myUniversity = u.next();
			try{
				fileOutputStream = new PrintWriter(new BufferedWriter(new FileWriter(outputFileName + ".txt", true)));
			}
			catch(IOException e1){
				System.out.println("Error writing to file: " + outputFileName + ".txt, Please make sure"
									+ " the file is not already  open and still present.");
				System.err.println("File not modified.");
				System.exit(0);
			}
			fileOutputStream.print(myUniversity.printData());//Writing output to file
			fileOutputStream.close();
		}
		
		System.out.println("NOTE: File " + outputFileName + ".txt has beed created with your output!\n");
	}
	
	/**
	 * Purpose: to change the file name state variables that will be used int this program.
	 */
	public static void changeFileNames(){
		System.out.println("What file would you like to change?");
		int response = OutPut.menu(new String[] {"Students File", "Universities File", "Output File"});
		if(response == 1){
			System.out.println("Please enter the new file name. \n\".txt\" will be automaticlally appended to the end. (default: studentData)");
			studentFileName = keyboard.next();
		}
		else if(response == 2){
			System.out.println("Please enter the new file name. \n\".txt\" will be automaticlally appended to the end. (default: universityData)");
			universityFileName = keyboard.next();
		}
		else{
			System.out.println("Please enter the new file name. \n\".txt\" will be automaticlally appended to the end. (default: outputData)");
			outputFileName = keyboard.next();
		}
	}
}
