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
		public void PrintPeople(Person[] people) {
			
		}
		
		public static PersonDataManager buildFromFile(String location)throws IllegalArgumentException{    
			String bioData = ""; 
			String split = ","; 
			int arrSize = 0; 
			int count = 0; 
			String[] indiv; //A temporary array to hold a person's biostats
			try(BufferedReader reader = new BufferedReader(new FileReader(location))){
				while(reader.readLine() != null) {
					arrSize++; 
				}
				reader.close();
			}catch(FileNotFoundException e) {
				System.out.println("File not found, please try again"); 
			}catch(IOException e) {
				System.out.println("File read error"); 
			}
			PersonDataManager pdm = new PersonDataManager(arrSize); 
			
			try {
				Scanner listIn = new Scanner(new File(location)); 
				while(listIn.hasNextLine()) {
					if(count == 0) continue; 
					count++; 
					if(count == arrSize) {
						break; 
					}
					bioData = listIn.nextLine();
					indiv = bioData.split(split);
					Person patient = new Person(indiv); 
					pdm.addPerson(patient);
					System.out.println(pdm.people[count].toString());
					
					
					
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
			boolean flag = false; 
			int length; 
			
			for(int i = 0; i < people.length; i++) {
				if(people[i].getName().length() >= newPerson.getName().length()) {
					length = people[i].getName().length(); 
				}else {
					length = newPerson.getName().length(); 
				}
				for(int j = 0; j < length; j++) {
					if(people[i].getName().charAt(j) == newPerson.getName().charAt(j)) {
						if(j == people[i].getName().length() - 1) {
							flag = true; 
						}
						continue; 
					}else {
						break; 
					}
				}
			}
			if(flag) {
				throw new PersonAlreadyExistsException(); 
			}
			
			for(int i = 0; i < people.length; i++) {
				for(int j = 0; j < people[i].getName().length(); j++) {
					if(people[i].getName().charAt(j) == newPerson.getName().charAt(j)) {
						if(j == people[i].getName().length() - 1) {
							flag = true; 
						}
						continue; 
					}else {
						break; 
					}
				}
			}
			if(flag) {
				throw new PersonAlreadyExistsException(); 
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
		public boolean checkAlphabet(String a, String b) {
			for(int i = 0; i < a.length(); i++) {
				if(a.charAt(i) <= b.charAt(i)) {
					continue; 
				}else if(a.charAt(i) > b.charAt(i) ) {
					return true; 
				}
			}
			return false; 
		}
	}