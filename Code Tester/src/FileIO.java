import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * 
 * @author Jose Canahui
 *
 */
public class FileIO {
	public static Scanner keyboard = new Scanner(System.in);
	public static Scanner file;
	public static void main(String[] args){
		System.out.println("What file would u like to write?");
		String filename = keyboard.nextLine();
		PrintWriter fileOutputStream = null;
		try{
			fileOutputStream = new PrintWriter(new FileOutputStream(filename + ".txt"));
		}
		catch(FileNotFoundException e1){
			System.out.println(e1.getMessage());
			System.out.println("Error opening file: " + filename);
			System.exit(0);
		}
		System.out.println("Please enter three lines of text:");
		for (int i = 0; i < 3; i++){
			String newLine = keyboard.nextLine();
			fileOutputStream.println(newLine);
		}
		fileOutputStream.close();
		
		try{
			file = new Scanner(new File(filename + ".txt"));
		}
		catch(FileNotFoundException e2){
			System.out.println(e2.getMessage());
			System.out.println("Error opening file: " + filename);
			System.exit(0);
		}
		while(file.hasNextLine())
			System.out.println(file.nextLine());
		file.close();
	}

}
