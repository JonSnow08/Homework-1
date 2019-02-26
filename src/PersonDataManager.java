import java.io.*; 
import java.util.Scanner;

/** 
 * CSE 214 
 * Instructor: Praveen Tripathi
 * @author Xavier Velez
 * SBU ID: 105703437
 * Date:2/16/2019
 * */ 
public class PersonDataManager{
		//**************************************************
		//Fields 1
		//**************************************************
				
		private Person[] people;
		private int counter = 0; 
		
		//**************************************************
		//Constructors
		//**************************************************
				
		public PersonDataManager(int size) {
			people = new Person[size]; 
		}
		public PersonDataManager(Person[] oldPeople, int index) {
			people = new Person[oldPeople.length + 1];
			for(int i = 0; i < oldPeople.length; i++) {
				
			}
		}
		public PersonDataManager(PersonDataManager oldPDM) {
			this.people = new Person[oldPDM.people.length + 1]; 
		}
		public void PrintPeople(Person[] people) {
			
		}
		
		public static PersonDataManager buildFromFile(String location)throws IllegalArgumentException{    
			String bioData = ""; 
			String split = ","; 
			int arrSize = 0; 
			int count = 0; 
			Scanner listIn = null; 
			String[] indiv; //A temporary array to hold a person's biostats
			BufferedReader rdr = null; 
			try{
				rdr = new BufferedReader(new FileReader(location)); 
				while(rdr.readLine() != null) {
					arrSize++; 
				}
			}catch(FileNotFoundException e) {
				System.out.println("File not found, please try again"); 
			}catch(IOException e) {
				System.out.println("File read error"); 
			}finally {
				try{rdr.close();}catch(Exception e) {
					System.out.println("Thrown");
				}
			}
			PersonDataManager pdm = new PersonDataManager(arrSize); 
			
			try {
				
				listIn = new Scanner(new File(location)); 
				
				while(listIn.hasNextLine()) {
					if(count == 0) {
						listIn.nextLine();
						count++;
					} 
					if(count++ == arrSize - 2) {
						break; 
					}
					bioData = listIn.nextLine();
					indiv = bioData.split(split);
					Person patient = new Person(indiv); 
					pdm.addPerson(patient);
					System.out.println("a");
//					System.out.println(pdm.people[count].toString());
					
					
					
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(PersonAlreadyExistsException e) {
				e.printStackTrace();
			}
			
		
					
					
				
			
			return pdm; 
			
		}


		
		public void addPerson(Person newPerson) throws PersonAlreadyExistsException{
			boolean checkFlag = false; 
			int length; 
			boolean flag = false; 
			int index = 0; 
			Person temp; 
			
//			for(int i = 0; i < people.length; i++) {
//				if(people[i].getName().length() >= newPerson.getName().length()) {
//					length = people[i].getName().length(); 
//				}else {
//					length = newPerson.getName().length(); 
//				}
//				for(int j = 0; j < Math.min(newPerson.getName().length(), people[i].getName().length()); j++) {
//					if(people[i].getName().charAt(j) == newPerson.getName().charAt(j)) {
//						if(j == Math.min(newPerson.getName().length(), people[i].getName().length())) {
//							checkFlag = true; 
//						}
//						continue; 
//					}else {
//						break; 
//					}
//				}
//			}
//			if(checkFlag) {
//				throw new PersonAlreadyExistsException(); 
//			}
			
//			for(int i = 0; i < people.length; i++) {
//				
//				if(checkAlphabet(people[i].getName(), newPerson.getName())) {
//					
//					
//				}
//				
//			}	
//			
	}
		
		public boolean doesPersonExist(Person newPerson) {
			private boolean flag;  
			for(int i = 0; i < this.people.length;i++) {
				if(this.people[i].getName().equalsIgnoreCase(newPerson.getName())) {
					flag = true; 
				}else {
					flag = false; 
				}
				if(this.people[i].getGender().equalsIgnoreCase(newPerson.getGender())) {
					
				}else {
					flag = false;
				}
			}
		}
		
		public void removePerson(String name) throws PersonDoesNotExistException{
			boolean flag = true; 
			for(int i = 0; i < this.people.length; i++) {
				if(people[i].getName().equalsIgnoreCase(name) ) {
					flag = false; 
					for(int j = i ; j < this.people.length; j++) {
						if(j  == this.people.length - 1) {
							break; 
						}
						people[j] = people [j + 1];  
						
					}
				}
			}
			if(flag) {
				throw new PersonDoesNotExistException(); 
			}
		}
		
		/** 
		 * @param String a A string value containing a name
		 * @param String b A string value containing a name 
		 * @return Returns a boolean value that is true when String a is 
		 * alphabetically greater than String b. 
		 * */ 
		
		// Fix this method, explanation in texts 
		public boolean checkAlphabet(String a, String b) {
			boolean flag = true; 
			for(int i = 0; i < Math.min(a.length(), b.length()); i++) {
				if(a.charAt(i) <= b.charAt(i)) {
					if(a.charAt(i) != b.charAt(i)) {
						flag = false; 
					}else if(i == Math.min(a.length(), b.length()) && flag == true) {
						return false; 
					}
					continue; 
				}else if(a.charAt(i) > b.charAt(i) ) {
					return true; 
				}
			}
			return false; 
		}
		
		
	}