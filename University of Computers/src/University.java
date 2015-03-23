import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * 
 * @author Jose Canahui
 * Goal: Create a class that can hold data for the entire university. IN future versions, it would be better
 * to save the students as objects in an array list or linked list, rather than just their data.
 */
public class University {
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
	

	public void colectDataForReport(Student person) {
		numOfStudents++;
		if (person.getInstate())
			instate++;
		else
			outOfState++;
		//Needs to check what type of meal plan it is
		double mealPlan = person.getMealPlan();
		if(mealPlan == 4999)
			mealStuff += 4999;
		else if (mealPlan == 3499)
			mealNasty += 3499;
		else
			mealAnorexic += 2599;
		
		 tuition += person.getTuition();
		 lateFees += person.getLateFee();
		 incidental += person.getIncidental();
		 healthCare += person.getHealthCare();
		 total += person.getTotal();
	}
	
	//NOTE: this code should be simplified by abstracting output into more methods *for future reference*
	public void printDataForSchoolReport() {
		DecimalFormat dollarFormat = new DecimalFormat("$#,###,###.00");
		System.out.println(OutPut.printStringRight(40, "UNIVERSITY OF COMPUTERS"));
		System.out.println(OutPut.printStringLeft(30, "NUMBER OF STUDNTS") + numOfStudents);
		System.out.println(OutPut.printStringLeft(30, "INSTATE") + instate);
		System.out.println(OutPut.printStringLeft(30, "OUT OF STATE") + outOfState);
		System.out.println(OutPut.printStringLeft(15, "MEAL PLAN") + OutPut.printStringLeft(35, "STUFF-YOUR-FACE") + OutPut.printStringLeft(5, ""+(int)(mealStuff / 4999)) + dollarFormat.format(mealStuff));
		System.out.println(OutPut.printStringLeft(15, "") + OutPut.printStringLeft(35, "I-CAN’T-STAND-THIS-FOOD") + OutPut.printStringLeft(5, ""+(int)(mealNasty / 3499)) + dollarFormat.format(mealNasty));
		System.out.println(OutPut.printStringLeft(15, "") + OutPut.printStringLeft(35, "I’M-ON-A-DIET") + OutPut.printStringLeft(5, ""+(int)(mealAnorexic / 2599)) + dollarFormat.format(mealAnorexic));
		System.out.println(OutPut.printStringRight(50, "FOOD SUBTOTAL") + OutPut.printStringRight(14, ""+dollarFormat.format((mealStuff+mealAnorexic+mealNasty))));
		System.out.println(OutPut.printStringLeft(30, "TUITION") + dollarFormat.format(tuition));
		System.out.println(OutPut.printStringLeft(30, "LATE FEE") + dollarFormat.format(lateFees));
		System.out.println(OutPut.printStringLeft(30, "INCEDINTAL") + dollarFormat.format(incidental));
		System.out.println(OutPut.printStringLeft(30, "HEALH CARE") + OutPut.printStringLeft(15, dollarFormat.format(healthCare)) + "TOTAL " + dollarFormat.format(total));
	}

}
