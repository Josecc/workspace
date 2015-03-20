/**
 * 
 * Modified by: Jose Canahui
 * for: returning a string.
 *
 */
public class OutPut // class that can be used to print one line of formatted
// output
{
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
}