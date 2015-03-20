import java.util.Scanner;

/**
 * 
 * @author Jose Canahui
 *
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
	private double tuition;
	private double lateFee;
	private double incidentalFee;
	private double healthCarePrice;
	private double mealPlan;
	private Scanner keyboard = new Scanner(System.in);
	
	//********default constructor***********
	public Student(){
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
		creditsEnrolled = intCheck(30);//Makes sure it is positive, an int, and gets rid of data that doesnt make sense ex. 5,000 credits
		
		//Instate rate
		System.out.println("Qualifies for instate rate");
		int inState = menu(new String[]{"yes", "no"});
		if(inState==1)
			instateRate = true;
		else
			instateRate = false;
		
		//Late fee
		System.out.println("Late fee assessed");
		int late = menu(new String[]{"yes", "no"});
		if(late == 1)
			lateFeeAssessed = true;
		else 
			lateFeeAssessed = false;
		
		//Campus food
		System.out.println("On campus food");
		int food = menu(new String[]{"yes", "no"});
		if(food == 1)
			campusFood = true;
		else
			campusFood = false;
		
		//Health care option
		System.out.println("Health care option");
		int health = menu(new String[]{"yes", "no"});
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
			int meal = menu(mealPlans);
			if(meal == 1)
				mealPlan = 4999;
			else if(meal == 2)
				mealPlan = 3499;
			else
				mealPlan = 2599;
			System.out.println(mealPlan);
		}
		
		//Confirm addition
		//System.out.println("Successfully added: " + name);
	}
	
	public void calculateData() {
		//tuition rate
		if (creditsEnrolled < 12){
			if (instateRate)
				tuition = 102.50;
			else
				tuition = 351;
		}
		else if (creditsEnrolled <= 18){
			if (instateRate)
				tuition = 75.45;
			else 
				tuition = 255;
		}
		else{
			if (instateRate)
				tuition = 93;
			else
				tuition = 304;
		}
		
		if (lateFeeAssessed)
			lateFee = tuition * .1;
		
		if (creditsEnrolled < 13)
			incidentalFee = creditsEnrolled * 20;
		else
			incidentalFee = 250;
		
		if(healthCare){
			if(creditsEnrolled <= 10)
				healthCarePrice = 25;
			else if(creditsEnrolled <= 15)
				healthCarePrice = 20;
			else
				healthCarePrice = 15;
		}
			
	}

	public void writeOutput() {
		System.out.println(OutPut.printStringLeft(25, "NAME")+OutPut.printStringLeft(40, name) + "CREDITS    " + creditsEnrolled);
		System.out.println(OutPut.printStringLeft(25, "ADDRESS") + address);
		System.out.println(OutPut.printStringLeft(25, "PHONE") + phone);
		System.out.println(OutPut.printStringLeft(25, "TUITION") + tuition);
		System.out.println(OutPut.printStringLeft(25, "LATE FEE") + lateFee);
		System.out.println(OutPut.printStringLeft(25, "INCIDENTAL") + incidentalFee);
		System.out.println(OutPut.printStringLeft(25, "HEALTH CARE") + healthCarePrice);
		System.out.println(OutPut.printStringLeft(25, "MEAL PLAN") + mealPlan);
		
	}
	
	//***********helper methods*************
	
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
	private int menu(String[] options){
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
	private int intCheck(){
		boolean valid = false;
		int credits = 0;
		
		do{
			try{
				credits = keyboard.nextInt();
				valid = true;
			}
			catch(Exception e){
				System.err.println("Please input an integer.");
				keyboard.next();
			}
		}while(!valid);
		
		return credits;
	}
	
	/**
	 * <b>Purpose:</b> Checks to make sure the input is and integer and falls<br>
	 * in the range of 1 to <i>lenght</i>.<br><br>
	 * <b>Precondition:</b> A length must be given, depends on intCheck() method.<br>
	 * @param length
	 * @return valid input
	 */
	private int intCheck (int length){
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
