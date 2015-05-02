import java.text.DecimalFormat;


public class StudentData {
	
	private double tuition;
	private double lateFee;
	private double incidental;
	private double healthCare;
	private String foodPlan;
	private double mealPlan;
	private double total;
	private boolean isInState;
	private String university;
	
	
	public StudentData(Student student, University university){
		//Setting variables from Student object
		int creditsEnrolled = student.getCredits();
		isInState = student.isInState();
		boolean lateFeeAssessed = student.isLateFees();
		boolean hasHealthCare = student.isHealthPlan();
		foodPlan = student.getFoodPlan();
		this.setUniversity(university.toString());
		
		//Setting variables from University object
		double inState1to11 = university.getInState1to11();
		double inState12to18 = university.getInState12to18();
		double inStateOver18 = university.getInStateOver18();
		double outState1to11 = university.getOutState1to11();
		double outState12to18 = university.getOutState12to18();
		double outStateOver18 = university.getOutStateOver18();
		double mealA = university.getMealA();
		double mealB = university.getMealB();
		double mealC = university.getMealC(); // D = no college meal plan
		double health1to10 = university.getHealth1to10();
		double health11to15 = university.getHealth11to15();
		double healthOver15 = university.getHealthOver15();
		double lateFee = university.getLateFee();
		double incidentalFee = university.getIncidentalFee();
		double incidentalFeeMax = university.getIncidentalFeeMax();
		
		//Check and set credits
		if (creditsEnrolled < 12)
			calcTuition(inState1to11, outState1to11, creditsEnrolled);
		else
			calcTuition(inState1to11, outState1to11, 11);
		if (creditsEnrolled <= 18 && creditsEnrolled > 11)
			calcTuition(inState12to18, outState12to18, (creditsEnrolled - 11));
		else if(creditsEnrolled > 11)
			calcTuition(inState12to18, outState12to18, 7);
		if (creditsEnrolled > 18)
			calcTuition(inStateOver18, outStateOver18, (creditsEnrolled - 18));
		
		//Check and set late fee
		if (lateFeeAssessed)
			this.lateFee = tuition * .1;
		
		//Check and set incidental
		if (creditsEnrolled < 13)
			incidental = creditsEnrolled * incidentalFee;
		else
			incidental = incidentalFeeMax;
		
		//Check and set health care
		if(hasHealthCare){
			if(creditsEnrolled > 10)
				healthCare = health1to10 * 10;
			else
				healthCare = health1to10 * creditsEnrolled;
			if(creditsEnrolled <= 15 && creditsEnrolled > 10)
				healthCare = healthCare + health11to15 * (creditsEnrolled - 10);
			else if(creditsEnrolled > 10)
				healthCare = healthCare + health11to15 * 5;
			
			if (creditsEnrolled > 15)
				healthCare = healthCare + healthOver15 * (creditsEnrolled - 15);
		}
		
		//Check and set meal plan
		if(foodPlan.equals("A"))
			mealPlan = mealA;
		else if (foodPlan.equals("B"))
			mealPlan = mealB;
		else if (foodPlan.equals("C"))
			mealPlan = mealC;
		else
			mealPlan = 0;
		
		total = tuition + lateFee + incidentalFee + healthCare + mealPlan;
		
		
	}
	
	/**
	 * <b>Purpose:</b> Makes the code shorter for the tuition calculation.<br><br>
	 * <b>Precondition:</b> parameters are passed, credits are within range.<br>
	 * @param inRate
	 * @param outRate
	 * @param credits
	 */
	private void calcTuition(double inRate, double outRate, int credits){
		if (isInState)
			tuition = tuition + inRate * credits;
		else
			tuition = tuition + outRate * credits;
	}
	
	public boolean isInState(){
		return isInState;
	}
	
	public String foodPlan(){
		return foodPlan;
	}
	
	public double getTuition(){
		return tuition;
	}
	
	public double getLate(){
		return lateFee;
	}
	
	public double getincidental(){
		return incidental;
	}
	
	public double getHealthCare(){
		return healthCare;
	}
	
	public double getTotal(){
		return total;
	}
	
	public String toString(){
		String result;
		DecimalFormat dollarFormat = new DecimalFormat("$#,###,###.00");
		result =  OutPut.printStringLeft(25, "TUITION") + dollarFormat.format(tuition) + "\n";
		result += OutPut.printStringLeft(25, "LATE FEE") + dollarFormat.format(lateFee) + "\n";
		result += OutPut.printStringLeft(25, "INCIDENTAL") + dollarFormat.format(incidental) + "\n";
		result += OutPut.printStringLeft(25, "HEALTH CARE") + dollarFormat.format(healthCare) + "\n";
		result += OutPut.printStringLeft(25, "MEAL PLAN") + dollarFormat.format(mealPlan);
		result += OutPut.printStringLeft(65, "")+"TOTAL      "+dollarFormat.format(total) + "\n";
		return result;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}
}
