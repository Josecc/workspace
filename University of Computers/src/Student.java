import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * 
 * @author Jose Canahui
 *	Goal: Create a students class, well documented, to use for keeping a record of the students. 
 *	Validation will be used using try-catch statements, abstracted into a method for reusability
 *	
 *	Focus: error catching, documentation and reusability.
 */
public class Student {
	
	//***********state variables************
	
	private String name;
	private String address;
	private String phone;
	private int creditsEnrolled;
	private boolean instateRate;
	private boolean lateFeeAssessed;
	private boolean campusFood;
	private boolean healthCare;
	private double tuition = 0;
	private double lateFee;
	private double incidentalFee;
	private double healthCarePrice;
	private double mealPlan;
	private double total;
	private Scanner keyboard = new Scanner(System.in);
	
	//********default constructor***********
	public Student(){
	}
	
	public Student(String inputVars){//Needs a string of comma-deliniated variables
		Scanner readVars = new Scanner(inputVars);
		readVars.useDelimiter(", | \n");
		
		name = readVars.next();
		address = readVars.next();
		phone = readVars.next();
		creditsEnrolled = readVars.nextInt();
		instateRate = readVars.nextBoolean();
		lateFeeAssessed = readVars.nextBoolean();
		campusFood = readVars.nextBoolean();
		healthCare = readVars.nextBoolean();
		mealPlan = readVars.nextDouble();
		
		calculateData();
	}
	
	//**************accessor****************
	public boolean getInstate(){
		return instateRate;
	}
	
	//NOTE: to determine the right meal plan, find the amount returned. ex. 4999 - mealStuff; 3499 - mealNasty; 2599 - mealAnorexic
	public double getMealPlan(){
		return mealPlan;
	}
	
	public double getTuition(){
		return tuition;
	}
	
	public double getLateFee(){
		return lateFee;
	}
	
	public double getIncidental(){
		return incidentalFee;
	}
	
	public double getHealthCare(){
		return healthCarePrice;
	}
	
	public double getTotal(){
		return total;
	}

	//**********public methods**************
	
	public void readInput() {
		//Name (first & last)
		System.out.println("First Name");
		String first = keyboard.next();
		System.out.println("Last Name");
		String last = keyboard.next();
		name = first + " " + last;
		keyboard.nextLine();//clear buffer
		
		//Address, phone number, and credits enrolled
		System.out.println("Address");
		address = keyboard.nextLine();
		System.out.println("Phone number");
		phone = keyboard.nextLine();
		System.out.println("Credits Enrolled");
		creditsEnrolled = OutPut.intCheck(30);//Makes sure it is positive, an int, and gets rid of data that doesnt make sense ex. 5,000 credits
		//System.err.println(creditsEnrolled);
		
		//Instate rate
		System.out.println("Qualifies for instate rate");
		int inState = OutPut.menu(new String[]{"yes", "no"});
		if(inState==1)
			instateRate = true;
		else
			instateRate = false;
		
		//Late fee
		System.out.println("Late fee assessed");
		int late = OutPut.menu(new String[]{"yes", "no"});
		if(late == 1)
			lateFeeAssessed = true;
		else 
			lateFeeAssessed = false;
		
		//Campus food
		System.out.println("On campus food");
		int food = OutPut.menu(new String[]{"yes", "no"});
		if(food == 1)
			campusFood = true;
		else
			campusFood = false;
		
		//Health care option
		System.out.println("Health care option");
		int health = OutPut.menu(new String[]{"yes", "no"});
		if(health == 1)
			healthCare = true;
		else
			healthCare = false;
		
		//Meal plan
		if(campusFood){
			String[] mealPlans = {OutPut.printStringLeft(25, "Stuff-your-face") + "$4,999.00",
								  OutPut.printStringLeft(25, "I-can't-stand-this-food") + "$3,499.00",
								  OutPut.printStringLeft(25, "I'm-on-a-diet") + "$2,599.00"};
			System.out.println("Please select meal plan");
			int meal = OutPut.menu(mealPlans);
			if(meal == 1)
				mealPlan = 4999;
			else if(meal == 2)
				mealPlan = 3499;
			else
				mealPlan = 2599;
		}
		
		//Confirm addition
		//System.out.println("Successfully added: " + name);
	}
	
	public void calculateData() {
		//tuition rate
		if (creditsEnrolled < 12)
			calcTuition(102.5, 351, creditsEnrolled);
		else
			calcTuition(102.5, 351, 11);
		if (creditsEnrolled <= 18 && creditsEnrolled > 11)
			calcTuition(75.45, 255, (creditsEnrolled - 11));
		else if(creditsEnrolled > 11)
			calcTuition(75.45, 255, 7);
		if (creditsEnrolled > 18)
			calcTuition(93, 304, (creditsEnrolled - 18));
		
		if (lateFeeAssessed)
			lateFee = tuition * .1;
		
		if (creditsEnrolled < 13)
			incidentalFee = creditsEnrolled * 20;
		else
			incidentalFee = 250;
		
		if(healthCare){
			if(creditsEnrolled > 10)
				healthCarePrice = 25 * 10;
			else
				healthCarePrice = 25 * creditsEnrolled;
			if(creditsEnrolled <= 15 && creditsEnrolled > 10)
				healthCarePrice = healthCarePrice + 20 * (creditsEnrolled - 10);
			else if(creditsEnrolled > 10)
				healthCarePrice = healthCarePrice + 20 * 5;
			
			if (creditsEnrolled > 15)
				healthCarePrice = healthCarePrice + 15 * (creditsEnrolled - 15);
		}
		total = tuition + lateFee + incidentalFee + healthCarePrice + mealPlan;
	}
	
	//This along with the university class seems sloppy, for future programs I will consider extending the OutPut class.
	public void writeOutput() {
		DecimalFormat dollarFormat = new DecimalFormat("$#,###,###.00");
		System.out.println(OutPut.printStringLeft(25, "NAME")+OutPut.printStringLeft(40, name) + "CREDITS    " + creditsEnrolled);
		System.out.println(OutPut.printStringLeft(25, "ADDRESS") + address);
		System.out.println(OutPut.printStringLeft(25, "PHONE") + phone);
		System.out.println(OutPut.printStringLeft(25, "TUITION") + dollarFormat.format(tuition));
		System.out.println(OutPut.printStringLeft(25, "LATE FEE") + dollarFormat.format(lateFee));
		System.out.println(OutPut.printStringLeft(25, "INCIDENTAL") + dollarFormat.format(incidentalFee));
		System.out.println(OutPut.printStringLeft(25, "HEALTH CARE") + dollarFormat.format(healthCarePrice));
		System.out.println(OutPut.printStringLeft(25, "MEAL PLAN") + dollarFormat.format(mealPlan));
		System.out.println(OutPut.printStringLeft(65, "")+"TOTAL      "+dollarFormat.format(total));
	}
	
	public Boolean save(String fileName){
		Boolean success = true;//Save file
		return success;
	}
	
	//***********helper methods*************
	
	/**
	 * <b>Purpose:</b> Makes the code shorter for the tuition calculation.<br><br>
	 * <b>Precondition:</b> parameters are passed, credits are within range.<br>
	 * @param inRate
	 * @param outRate
	 * @param credits
	 */
	private void calcTuition(double inRate, double outRate, int credits){
		if (instateRate)
			tuition = tuition + inRate * credits;
		else
			tuition = tuition + outRate * credits;
	}
}
