package system;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

/**
 * @(#)UserInterface.java
 * 
 * 
 * @author Ali Almousa
 * @version 1.00 2022/2/9
 * 
 * This class is a prototype for the LockedMe project.
 * The class provide the user with multiple operations such as
 * creating a file and writing to it, searching for a specific file,
 * deleting a file and fetching all files.
 *
 */
public class UserInterface {
	
	private static Scanner input = new Scanner(System.in);															//Scanner object to get inputs from users
	public static String direcatoryPath = "C:\\Users\\alooo\\eclipse-workspace\\LockedMePrototype\\LockedMeFiles";	//Path of the main directory
	
	/**
	 * 
	 * @param args
	 * @throws IOException
	 * 
	 * This method invokes all related methods to run the complete
	 * program properly.
	 */
	public static void main(String[] args) throws IOException {
		int choice;			//Receive the user's decision
		do {
			printWelcome();				//Printing the welcome message
			choice = input.nextInt();
			input.nextLine();
			switch (choice) {
			case 1:
				showAllFiles();			//Show all files in the main directory
				break;
			case 2:
				addFile();				//Create a new file & write to it
				break;
			case 3:
				deleteFile();			//Delete a user specified file
				break;
			case 4:
				searchFile();			//Search a specified user file
				break;
			case 5:
				System.out.println("Thanks for using LockedMe prototype. Take care!");
				System.exit(1);
			default:
				input.close();
				System.out.println("Not a valid input! Thanks for using LockedMe prototype.");
				break;
			}
		}while(choice != 5);
		
		
	}
	

	/**
	 *This method prints to the console the welcoming message 
	 *along with the list of all available operations 
	 */
	public static void printWelcome() {
		String welcome = "";
		 
		welcome += String.format("\n\n%s%s%s\n%s\n%s%s", "WELCOME TO THE PROTOTYPE ــــــــــــــــــــ\n", space(31)
				+ "|\n" + space(31) +"v\n", "Application name: lockedMe" + space(5) + "|",
				"Developer details: Ali Almousa" + space(1) + "|",
				space(31) + "^\n" + space(31) +"|\n", 
				"WELCOME TO THE PROTOTYPE ــــــــــــــــــــ\n");
		
		welcome += String.format("%s%s", "\n\nUSER INTERACTION INFORMATION ــــــــــــــــــــ\n", space(35) + "|\n" + space(35) +"v\n");
		
		welcome += String.format("\t%s\n\t%s\n\t%s\n\t%s\n\t%s\n\n%s", "1- Get the current file names in ascending order.",
				"2- Add a file to the existing directory list.",
				"3- Delete a file from the existing directory list",
				"4- Search a user specified file from the main directory.",
				"5- Navigate back to the main context (Exit)",
				"Enter the number of the desired opperation from the above list: ");
		
		System.out.print(welcome);
	}
	
	/**
	 * 
	 * @param num 
	 * @return <SPACE>
	 *Returns the desired number (num) of SPACE character
	 *to be used in string formating
	 */
	public static String space(int num) {

		String empty = "";
		
		for (int i = 0; i < num; i++) {
			empty += "\s";
		}
		
		return empty;
	}
	
	/**
	 *Prints all the names of the files in the main directory
	 */
	public static void showAllFiles() {
		
		try {
			File directory = new File(direcatoryPath);
			File[] filesList = directory.listFiles();
			
			if (filesList.length > 0) {
				for (File file : filesList) {
					System.out.println(file.getName());
				}
			}
			else {
				System.out.println("There are no files in the main directory!");
			}
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Sorry! an error has occured...");
			
		}
	}
	
	/**
	 *Creates a new file and write to it 
	 * specific lines entered by the user
	 */
	public static void addFile() {
		
		System.out.print("Enter the name of the file:");
		try {
			String fileName = input.next();
			File file = new File(direcatoryPath + "//" + fileName);
			if (!file.createNewFile()) {
				System.out.println("The file can not be created!");
			}
			else {
				System.out.print("Enter the number of lines you want to add: ");
				int lines = input.nextInt();
				input.nextLine();
				FileWriter output = new FileWriter(file);
				
				for (int i = 1; i <= lines; i++) {
					System.out.println("Enter the line number " + i + ":");
					String line = input.nextLine();
					output.write(line + "\n");
				}
				System.out.println("the file " + fileName + " was created successfuly!");
				output.close();
			}

		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Sorry! an error has occured...");
			
		}
	}
	
	/**
	 *Delete a user specified file from the main directory
	 */
	public static void deleteFile() {
		
		try {
			System.out.println("Enter the name of the file you desire to delete:");
			String fileName = input.next();
			File file = new File(direcatoryPath + "//" + fileName);
			
			if (file.exists()) {
				if (file.delete()) {
					System.out.println("The file " + fileName + " was deleted successfuly!");
				}
			}
			else {
				System.out.println("the file does not exist!");
			}
			input.nextLine();
			
		}catch(Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Sorry! an error has occured...");
			
		}
	}
	
	/**
	 *Search a user specified file in the main directory
	 *and inform the user if it was found or not
	 */
	public static void searchFile() {
		
			System.out.println("Enter the name of the file you desire to search for: ");
			String fileName = input.next();
			boolean found = false;
			try {
				File directory = new File(direcatoryPath);
				File[] filesList = directory.listFiles();
				
				if (filesList.length > 0) {
					for (File file : filesList) {
						if (file.getName().equals(fileName)) {
							System.out.println("the file " + fileName + " exsits in the main directory");
							found = true;
						}
					}
					if (!found) {
						System.out.println("the file " + fileName + " does not exsit in the main directory");
					}
				}
				else {
					System.out.println("There are no files in the main directory!");
				}
				input.nextLine();
			}catch(Exception e) {
				System.out.println(e.getMessage());
				System.out.println("Sorry! an error has occured...");
				
			}
		}
	
}


