/** 
 * CSE 214 
 * Instructor: Praveen Tripathi
 * @author Xavier Velez
 * SBU ID: 105703437
 * Pull Test-Push Test
 * */ 
import java.util.*; 
import java.io.*; 


public class PersonManager {
	
	String userIn; 
	public static void main(String[] args) {
			
		PersonDataManager pdm = new PersonDataManager(PersonDataManager.buildFromFile("biostats.csv"));
		pdm.printTable();
			
			
			

	}
	
	public void printMenu() {
		System.out.println("(I) - Import from File");
		System.out.println("(A)- Add Person");
		System.out.println("(R)");
	}

	/** 
	 * Reads a .csv file and creates 
	 * Person objects that are later stored
	 * in an Array
	 * */ 
	
	
	
	
}

