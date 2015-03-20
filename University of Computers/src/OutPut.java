public class OutPut // class that can be used to print one line of formatted
// output
{
	public static void printStringLeft(int size, String formatted)
// Blank fills and left justifies a string in a field of size characters
	{
		int length = formatted.length();
		System.out.print(formatted);
		while(size > length)
		{
			System.out.print(" ");
			size--;
		} // End while (size > length)
	} // End function printString

	public static void printStringRight(int size, String formatted)
// Blank fills and right justifies a string in a field of size characters
	{
		int length = formatted.length();
		while(size > length)
		{
			System.out.print(" ");
			size--;
		} // End while (size > length)
		System.out.print(formatted);
	} // End function printString
}