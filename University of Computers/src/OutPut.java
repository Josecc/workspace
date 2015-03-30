import java.util.Scanner;

/**
 * 
 * Modified by: Jose Canahui
 * for: returning a string.
 *
 */
public class OutPut // class that can be used to print one line of formatted
// output
{
	private static Scanner keyboard = new Scanner(System.in);
	
	public static String printStringLeft(int size, String formatted)
// Blank fills and left justifies a string in a field of size characters
	{
		String output = "";
		int length = formatted.length();
		output = formatted;
		while(size > length)
		{
			output = output + " ";
			size--;
		} // End while (size > length)
		return output;
	} // End function printString

	public static String printStringRight(int size, String formatted)
// Blank fills and right justifies a string in a field of size characters
	{
		String output = "";
		int length = formatted.length();
		while(size > length)
		{
			output = output + " ";
			size--;
		} // End while (size > length)
		output = output + formatted;
		return output;
	} // End function printString
	
	/**
	 * <b>Purpose:</b> Requests for user input, can be next() or nextLine() true | false<br>
	 * <b>Precondition:</b> String question and nextline options must be supplied<br>
	 * <b>Postcondition:</b> User's response is returned
	 * @param
	 */
	public static String queryString(String question, Boolean fullLine){
		String response = null;
		
		System.out.println(question);
		if(fullLine)
			response = keyboard.nextLine();
		else
			response = keyboard.next();
		
		cleanBuffer();
		return response;
	}
	
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
	public static int menu(String[] options){
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
	public static int intCheck(){
		boolean valid = false;
		int number = 0;
		
		do{
			try{
				number = keyboard.nextInt();
				valid = true;
			}
			catch(Exception e){
				System.err.println("Please input an integer.");
				keyboard.next();
			}
		}while(!valid);
		
		cleanBuffer();
		return number;
	}
	
	/**
	 * <b>Purpose:</b> Checks to make sure the input is and integer and falls<br>
	 * in the range of 1 to <i>length</i>.<br><br>
	 * <b>Precondition:</b> A length must be given, depends on intCheck() method.<br>
	 * @param length
	 * @return valid input
	 */
	public static int intCheck (int length){
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
	
	/**
	 * <b>Purpose:</b> Makes sure buffer is clean for next input<br>
	 * <b>Precondition:</b> Keyboard variable must be initialized<br>
	 * <b>Postcondition:</b> If the buffer has an empty carriage return, it clears it.
	 * 
	 */
	public static void cleanBuffer(){
		if(keyboard.hasNext())
			keyboard.nextLine();
	}
	
	public static String validateFileName(){
		Boolean validFile = false;
		String fileName = null;
		
		do{
			fileName = OutPut.queryString("Name the file you wish to work with:", false);
			//make sure file exists
		}while(!validFile);
		
		return fileName;
	}
}