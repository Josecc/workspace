import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;


public class Student {
	private String firstN;
	private String middleN;
	private String lastN;
	private int age;
	private String sex;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String phone; //Stored as string because it does not need to be operated on
	private String university;
	private int credits;
	private boolean inState;
	private boolean healthPlan;
	private String foodPlan;
	private boolean lateFees;
	private StudentData data;
	PrintWriter fileOutputStream = null;
	private Scanner keyboard = new Scanner(System.in);
	private Scanner readVars;
	
	public Student(){
	}
	
	public Student(String inputVars){
		readVars = new Scanner(inputVars);
		readVars.useDelimiter(",|\n|\r");
		
		firstN = readVars.next();
		middleN = readVars.next();
		lastN = readVars.next();
		age = readVars.nextInt();
		sex = readVars.next();
		street = readVars.next();
		city = readVars.next();
		state = readVars.next();
		zip = readVars.next();
		phone = readVars.next();
		university = readVars.next();
		credits = readVars.nextInt();
		inState = readVars.nextBoolean();
		healthPlan = readVars.nextBoolean();
		foodPlan = readVars.next();
		lateFees = readVars.nextBoolean();
	}
	
	public String toString(){
		String result;
		//result = data.getUniversity();
		result = OutPut.printStringLeft(25, "NAME")+OutPut.printStringLeft(40, firstN +" "+ middleN + " " +lastN) + "CREDITS    " + credits + "\n";
		result += OutPut.printStringLeft(25, "ADDRESS") + street + ", " + city + ", " + state + " " + zip + "\n";
		result += OutPut.printStringLeft(25, "PHONE") + phone + "\n";//format phone output
		//result += data;
		return result;
	}
	
	/**
	 * <b>Purpose:</b> Collects data using the keyboard.<br><br>
	 * <b>Precondition:</b> Heavily relies on the OutPut class to collect data in an error-free manner.<br>
	 * <b>Algorithm:</b> collect all the string data first, then use the OutPut class to collect the correct boolean values, and the menu option of the class to collect the correct meal plan
	 */
	public void collectData(){
		System.out.println("Eneter student's first name:");
		firstN = keyboard.next();
		System.out.println("Enter the student's middle name:");
		middleN = keyboard.next();
		System.out.println("Enter the student's last name:");
		lastN = keyboard.next();
		System.out.println("Enter " + firstN + "'s age:");
		age = OutPut.intCheck();
		System.out.println("Is " + firstN + " a man or a woman?");
		int response = OutPut.menu(new String[] {"Man", "Woman"});
		if(response == 1)
			sex = "M";
		else
			sex = "F";
		keyboard.nextLine();
		System.out.println("Enter " + firstN + "'s street address:");
		street = keyboard.nextLine();
		System.out.println("Enter " + firstN + "'s city:");
		city = keyboard.next();
		System.out.println("Enter " + firstN + "'s state:");
		state = keyboard.next();
		System.out.println("Enter " + firstN + "'s zip code:");
		zip = keyboard.next();
		keyboard.nextLine();
		System.out.println("Enter " + firstN + "'s phone number:");
		phone = keyboard.nextLine();
		System.out.println("Enter " + firstN + "'s university");
		university = keyboard.nextLine();
		System.out.println("How many credits is " + firstN + " taking?");
		credits = OutPut.intCheck();
		
		System.out.println("Is " + firstN + " an in-state student?");
		response = OutPut.menu(new String[] {"Yes", "No, he is out-of state"});
		if(response == 1)
			inState = true;
		else
			inState = false;
		
		System.out.println("Does " + firstN + " have a health plan with his school?");
		response = OutPut.menu(new String[] {"Yes", "No"});
		if(response == 1)
			healthPlan = true;
		else
			healthPlan = false;
		
		System.out.println("What is " +firstN + "'s meal plan?");
		response = OutPut.menu(new String[] {"Meal A", "Meal B", "Meal C", "No meal plan :'("});
		switch(response){
			case 1:
				foodPlan = "A";
				break;
			case 2:
				foodPlan = "B";
				break;
			case 3:
				foodPlan = "C";
				break;
			case 4:
				foodPlan = "D";
				break;
		}
		
		System.out.println("Will late fees be applied for " + firstN + "?");
		response = OutPut.menu(new String[] {"Yes", "No"});
		if (response == 1)
			lateFees = true;
		else
			lateFees = false;
	}
	
	
	
	/**
	 * <b>Purpose:</b> To save the student into a file, and append the student.<br>
	 * <b>Precondition:</b> A valid file name should be passed.<br>
	 * <b>Postcondition:</b> The student will be saved onto the file in a format that the constructor can read.<br>
	 */
	public Boolean save(String fileName){
		Boolean success = false;//Save file
		try{
			fileOutputStream = new PrintWriter(new BufferedWriter(new FileWriter(fileName + ".txt", true)));
			success = true;
		}
		catch(IOException e1){
			System.out.println("Error writing to file: " + fileName + ".txt, Please make sure"
								+ " the file is not already  open and still present.");
			System.err.println("File not modified.");
			System.exit(0);
		}
		
		fileOutputStream.print(firstN + ",");
		fileOutputStream.print(middleN + ",");
		fileOutputStream.print(lastN + ",");
		fileOutputStream.print(age + ",");
		fileOutputStream.print(sex + ",");
		fileOutputStream.print(street + ",");
		fileOutputStream.print(city + ",");
		fileOutputStream.print(state + ",");
		fileOutputStream.print(zip + ",");
		fileOutputStream.print(phone + ",");
		fileOutputStream.print(university + ",");
		fileOutputStream.print(credits + ",");
		fileOutputStream.print(inState + ",");
		fileOutputStream.print(healthPlan + ",");
		fileOutputStream.print(foodPlan + ",");
		fileOutputStream.println(lateFees);
		
		fileOutputStream.close();
		return success;
	}
	
	public String getLast(){
		return lastN;
	}

	public boolean isInState() {
		return inState;
	}

	public boolean isHealthPlan() {
		return healthPlan;
	}

	public String getFoodPlan() {
		return foodPlan;
	}

	public boolean isLateFees() {
		return lateFees;
	}

	public String getUniversity() {
		return university;
	}
	
	public int getCredits(){
		return credits;
	}

	public StudentData getData() {
		return data;
	}

	public void setData(StudentData data) {
		this.data = data;
	}
}
