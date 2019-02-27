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
	static PersonDataManager manager; 
	
	public static void main(String[] args) {
			
		System.out.println("Starting...");
		printMenu(); 
		Scanner sysIn = new Scanner(System.in); 
		String userIn = sysIn.nextLine(); 
		userIn = userIn.trim().toUpperCase(); 
		boolean flag = true; 
		while(flag) {
			
			switch(userIn) {
			case "I": 
				System.out.println("Please enter a location:"); 
				userIn = sysIn.nextLine(); 
				manager = PersonDataManager.buildFromFile(userIn);
				printMenu(); 
				userIn = sysIn.nextLine(); 
				break; 
			case "P": 
				manager.printTable();
				printMenu(); 
				 
				userIn = sysIn.nextLine(); 
				break;
			case "G":
				System.out.println("Please enter the name of the user");
				userIn = sysIn.nextLine();
				try{manager.getPerson(userIn);}catch(Exception e) {}
				printMenu(); 
				userIn = sysIn.nextLine(); 
				break; 
			case "A": 
				Person newPerson; 
				System.out.println("Please enter the name of the person");
				String name = sysIn.nextLine();
				System.out.println("Please enter age");
				int age = sysIn.nextInt();
				System.out.println("Please enter gender (M or F)");
				String gender =  sysIn.nextLine();
				System.out.println("Please enter the height(in inches)");
				double height = sysIn.nextDouble();
				System.out.println("Please enter the weight(in lbs)");
				double weight = sysIn.nextDouble();
				newPerson = new Person(name, gender, age , height, weight);
				try {
					manager.addPerson(newPerson, false);
					System.out.println(name +  " has been added!");
				}catch(Exception e) {
					
				}
				printMenu(); 
				 
				userIn = sysIn.nextLine(); 
				break; 
			case "R":
				System.out.println("What is the name of the person you want to remove");
				name = sysIn.nextLine(); 
				try {manager.removePerson(name);
						System.out.println("Person Removed!");
				}catch(Exception e) {
					
				}
				printMenu(); 
				 
				userIn = sysIn.nextLine(); 
				break; 
			case "Q":
				System.out.println("Have a great day!"); 
				System.exit(0);
				printMenu(); 
				 
				userIn = sysIn.nextLine(); 
				break; 
			case "S":
				System.out.println("Please enter a name for the file");
				userIn = sysIn.nextLine(); 
				try {
					manager.saveTable(userIn);}catch(Exception e){
						
					}
				
			}
			
				
			
		}
			
			
			

	}
	
	public static void printMenu() {
		System.out.println("Menu:");
		System.out.println("(I) - Import from File");
		System.out.println("(A)- Add Person");
		System.out.println("(R)- Remove Person");
		System.out.println("(G) – Get Information on Person");
		System.out.println("(P)- Print Table");
		System.out.println("(S)- Save to File");
		System.out.println("(Q)- Quit");
		System.out.println("Please select an option");
	}

	/** 
	 * Reads a .csv file and creates 
	 * Person objects that are later stored
	 * in an Array
	 * */ 
	
	
	
	
}

