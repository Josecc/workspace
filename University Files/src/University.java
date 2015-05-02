/**
 * Author: Jose Canahui
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;


public class University {
	private String name;
	private String street;
	private String city;
	private String state;
	private String zip;
	private String phone;
	private double inState1to11;
	private double inState12to18;
	private double inStateOver18;
	private double outState1to11;
	private double outState12to18;
	private double outStateOver18;
	private double mealA;
	private double mealB;
	private double mealC; // D = no college meal plan
	private double health1to10;
	private double health11to15;
	private double healthOver15;
	private double lateFee;
	private double incidentalFee;
	private double incidentalFeeMax;
	private LinkedList<StudentData> students = new LinkedList<StudentData>();
	PrintWriter fileOutputStream = null;
	private Scanner keyboard = new Scanner(System.in);
	private Scanner readVars;
	
	public University(){
	}
	
	public University(String inputVars){
		readVars = new Scanner(inputVars);
		readVars.useDelimiter(",|\n|\r");
		
		name = readVars.next();
		street = readVars.next();
		city = readVars.next();
		state = readVars.next();
		zip = readVars.next();
		phone = readVars.next();
		inState1to11 = readVars.nextDouble();
		inState12to18 = readVars.nextDouble();
		inStateOver18 = readVars.nextDouble();
		outState1to11 = readVars.nextDouble();
		outState12to18 = readVars.nextDouble();
		outStateOver18 = readVars.nextDouble();
		mealA = readVars.nextDouble();
		mealB = readVars.nextDouble();
		mealC = readVars.nextDouble(); // D = no college meal plan
		health1to10 = readVars.nextDouble();
		health11to15 = readVars.nextDouble();
		healthOver15 = readVars.nextDouble();
		lateFee = readVars.nextDouble();
		incidentalFee = readVars.nextDouble();
		incidentalFeeMax = readVars.nextDouble();
	}
	/**
	 * Purpose: Prints the data of the university to file. 
	 * Algorithm: loops through each student data file from the linked list, computes the totals, prints them to the file.
	 */
	public String printData(){
		String result;
		
		int numOfStudents = 0;
		int instate = 0;
		int outOfState = 0;
		double mealStuff = 0;
		double mealNasty = 0;
		double mealAnorexic = 0;
		double tuition = 0;
		double lateFees = 0;
		double incidental = 0;
		double healthCare = 0;
		double total = 0;
		
		for (Iterator<StudentData> i = students.iterator(); i.hasNext();) {
			StudentData student = i.next();
		    numOfStudents++;
		    if(student.isInState())
		    	instate++;
		    else
		    	outOfState++;
		    
		    if(student.foodPlan().equals("A"))
		    	mealStuff += mealA;
		    else if(student.foodPlan().equals("B"))
		    	mealNasty += mealB;
		    else if(student.foodPlan().equals("C"))
		    	mealAnorexic += mealC;
		    
		    tuition += student.getTuition();
		    lateFees += student.getLate();
		    incidental += student.getincidental();
		    healthCare += student.getHealthCare();
		    total += student.getTotal();
		}
		
		DecimalFormat dollarFormat = new DecimalFormat("$#,###,###.00");
		result = OutPut.printStringRight(40, name + "\n");
		result += OutPut.printStringLeft(30, "NUMBER OF STUDENTS") + numOfStudents + "\n";
		result += OutPut.printStringLeft(30, "INSTATE") + instate + "\n";
		result += OutPut.printStringLeft(30, "OUT OF STATE") + outOfState + "\n";
		result += OutPut.printStringLeft(15, "MEAL PLAN") + OutPut.printStringLeft(35, "STUFF-YOUR-FACE") + OutPut.printStringLeft(5, ""+(int)(mealStuff / mealA)) + dollarFormat.format(mealStuff) + "\n";
		result += OutPut.printStringLeft(15, "") + OutPut.printStringLeft(35, "I-CAN'T-STAND-THIS-FOOD") + OutPut.printStringLeft(5, ""+(int)(mealNasty / mealB)) + dollarFormat.format(mealNasty) + "\n";
		result += OutPut.printStringLeft(15, "") + OutPut.printStringLeft(35, "I'M-ON-A-DIET") + OutPut.printStringLeft(5, ""+(int)(mealAnorexic / mealC)) + dollarFormat.format(mealAnorexic) + "\n";
		result += OutPut.printStringRight(50, "FOOD SUBTOTAL") + OutPut.printStringRight(14, ""+dollarFormat.format((mealStuff+mealAnorexic+mealNasty))) + "\n";
		result += OutPut.printStringLeft(30, "TUITION") + dollarFormat.format(tuition) + "\n";
		result += OutPut.printStringLeft(30, "LATE FEE") + dollarFormat.format(lateFees) + "\n";
		result += OutPut.printStringLeft(30, "INCEDINTAL") + dollarFormat.format(incidental) + "\n";
		result += OutPut.printStringLeft(30, "HEALH CARE") + OutPut.printStringLeft(15, dollarFormat.format(healthCare)) + "TOTAL " + dollarFormat.format(total) + "\n";
		return result;
	}
	
	/**
	 * <b>Purpose:</b> To save the student into a file, and append the university.<br>
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
		
		fileOutputStream.print(name + ",");
		fileOutputStream.print(street + ",");
		fileOutputStream.print(city + ",");
		fileOutputStream.print(state + ",");
		fileOutputStream.print(zip + ",");
		fileOutputStream.print(phone + ",");
		fileOutputStream.print(inState1to11 + ",");
		fileOutputStream.print(inState12to18 + ",");
		fileOutputStream.print(inStateOver18 + ",");
		fileOutputStream.print(outState1to11 + ",");
		fileOutputStream.print(outState12to18 + ",");
		fileOutputStream.print(outStateOver18 + ",");
		fileOutputStream.print(mealA + ",");
		fileOutputStream.print(mealB + ",");
		fileOutputStream.print(mealC + ",");
		fileOutputStream.print(health1to10 + ",");
		fileOutputStream.print(health11to15 + ",");
		fileOutputStream.print(healthOver15 + ",");
		fileOutputStream.print(lateFee + ",");
		fileOutputStream.print(incidentalFee + ",");
		fileOutputStream.println(incidentalFeeMax);
		
		fileOutputStream.close();
		return success;
	}
	
	public void collectData(){
		System.out.println("University Name:");
		name = keyboard.nextLine();
		System.out.println("Street address:");
		street = keyboard.nextLine();
		System.out.println("City:");
		city = keyboard.next();
		System.out.println("State:");
		state = keyboard.next();
		System.out.println("Zip code:");
		zip = keyboard.next();
		keyboard.nextLine();
		System.out.println("Phone number");
		phone = keyboard.nextLine();
		System.out.println("Instate tuition rate for the first 11 credits:");
		inState1to11 = OutPut.posDoubleCheck();
		System.out.println("Instate tuition rate for credits 12 - 18:");
		inState12to18 = OutPut.posDoubleCheck();
		System.out.println("Instate tuition rate for credits over 18:");
		inStateOver18 = OutPut.posDoubleCheck();
		System.out.println("Out of satate tuition rate for first 11 credits:");
		outState1to11 = OutPut.posDoubleCheck();
		System.out.println("Out of state tuition rate for credits 12 - 18:");
		outState12to18 = OutPut.posDoubleCheck();
		System.out.println("Out of state tuition rate for credits over 18:");
		outStateOver18 = OutPut.posDoubleCheck();
		System.out.println("Price of meal plan A:");
		mealA = OutPut.posDoubleCheck();
		System.out.println("Price of meal plan B:");
		mealB = OutPut.posDoubleCheck();
		System.out.println("Price of meal plan C:");
		mealC = OutPut.posDoubleCheck(); // D = no college meal plan
		System.out.println("Cost of health care for credits 1 - 10:");
		health1to10 = OutPut.posDoubleCheck();
		System.out.println("Cost of health care for credits 11 - 15:");
		health11to15 = OutPut.posDoubleCheck();
		System.out.println("Cost of health care for credits over 15:");
		healthOver15 = OutPut.posDoubleCheck();
		System.out.println("Cost of late fee:");
		lateFee = OutPut.posDoubleCheck();
		System.out.println("Rate of incidental fee:");
		incidentalFee = OutPut.posDoubleCheck();
		System.out.println("Maximum cost of incidental fee:");
		incidentalFeeMax = OutPut.posDoubleCheck();
		System.out.println(name + "'s data has been gathered!");
	}
	
	public String toString(){
		String result;
		String spaces = "                    ";
		result = spaces + name + "\n";
		result += spaces + street + "\n";
		result += spaces + city + ", " + state + " " + zip + "\n";
		result += spaces + phone + "\n";//format phone output
		return result;
	}
	
	public String getName(){
		return name;
	}

	public double getInState1to11() {
		return inState1to11;
	}

	public void setInState1to11(double inState1to11) {
		this.inState1to11 = inState1to11;
	}

	public double getInState12to18() {
		return inState12to18;
	}

	public void setInState12to18(double inState12to18) {
		this.inState12to18 = inState12to18;
	}

	public double getInStateOver18() {
		return inStateOver18;
	}

	public void setInStateOver18(double inStateOver18) {
		this.inStateOver18 = inStateOver18;
	}

	public double getOutState1to11() {
		return outState1to11;
	}

	public void setOutState1to11(double outState1to11) {
		this.outState1to11 = outState1to11;
	}

	public double getOutState12to18() {
		return outState12to18;
	}

	public void setOutState12to18(double outState12to18) {
		this.outState12to18 = outState12to18;
	}

	public double getOutStateOver18() {
		return outStateOver18;
	}

	public void setOutStateOver18(double outStateOver18) {
		this.outStateOver18 = outStateOver18;
	}

	public double getMealA() {
		return mealA;
	}

	public void setMealA(double mealA) {
		this.mealA = mealA;
	}

	public double getMealB() {
		return mealB;
	}

	public void setMealB(double mealB) {
		this.mealB = mealB;
	}

	public double getMealC() {
		return mealC;
	}

	public void setMealC(double mealC) {
		this.mealC = mealC;
	}

	public double getHealth1to10() {
		return health1to10;
	}

	public void setHealth1to10(double health1to10) {
		this.health1to10 = health1to10;
	}

	public double getHealth11to15() {
		return health11to15;
	}

	public void setHealth11to15(double health11to15) {
		this.health11to15 = health11to15;
	}

	public double getHealthOver15() {
		return healthOver15;
	}

	public void setHealthOver15(double healthOver15) {
		this.healthOver15 = healthOver15;
	}

	public double getLateFee() {
		return lateFee;
	}

	public void setLateFee(double lateFee) {
		this.lateFee = lateFee;
	}

	public double getIncidentalFee() {
		return incidentalFee;
	}

	public void setIncidentalFee(double incidentalFee) {
		this.incidentalFee = incidentalFee;
	}

	public double getIncidentalFeeMax() {
		return incidentalFeeMax;
	}

	public void setIncidentalFeeMax(double incidentalFeeMax) {
		this.incidentalFeeMax = incidentalFeeMax;
	}
	
	public void addStudent(StudentData data){
		students.add(data);
	}
}
