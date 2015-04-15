import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 
 * Modified by: Jose Canahui
 * for: returning a string.
 * && added capabilities for user-friendly FileIO, creating menus and getting responses, querying for strings & ints..
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
//		if(keyboard.hasNext("\n"))
//			keyboard.next();
//		System.out.println("clean?");
		//Does not work because .hasNext blocks while it searches for input....
	}
	
	/**
	 * <b>Purpose:</b> Checks whether the file exists. If not, it will create a new file.<br>
	 * <b>Precondition:</b> File name is passed. <br>
	 * <b>Postcondition:</b> A dependable file name is returned. Makes sure the file to be worked with exists.
	 * @return
	 */
	public static String validateFileName(){
		Boolean validFile = false;
		String fileName = null;
		
		do{
			fileName = OutPut.queryString("Name the file you wish to work with:", false);
			try {
				Scanner file = new Scanner(new File(fileName + ".txt"));
				validFile = true;
			} catch (FileNotFoundException e) {
				System.out.println("File " + fileName + ".txt" + " not found!!!\n");
				System.out.println("Create new file named \"" + fileName + ".txt\"?");
				int response = menu(new String[] {"Yes", "No"});
				if(response == 1){
					PrintWriter fileOutputStream = null;
					try {
						fileOutputStream = new PrintWriter(new FileOutputStream(fileName + ".txt"));
						validFile = true;
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
			System.out.println("You are now using \"" + fileName + ".txt\"\n");
		}while(!validFile);
		
		return fileName;
	}
	
	/**
	 * <b>Purpose:</b> Abstracts the creation of file. Uses the <i>validateFileName()</i> method to make sure<br>
	 * 				file exists. Returns a file that was created in a user-friendly way.<br>
	 * <b>Precondition:</b> A file name is passed, and is validated through the <i>validateFileName()</i> method.<br>
	 * <b>Postcondition:</b> A file created in a user-friendly manner is created. Its re-usable code.
	 * @param fileName
	 * @return <b>Scanner file</b>
	 */
	public static Scanner getFile(String fileName){
		Boolean validFile = false;
		Scanner file = null;
		
		do{
			try {
				file = new Scanner(new File(fileName + ".txt"));
				validFile = true;
			} catch (FileNotFoundException e) {
				System.out.println("File " + fileName + ".txt" + " not found!!!\n");
			}
		}while(!validFile);
		
		return file;
	}
}