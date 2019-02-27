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
			PersonDataManager pdm = new PersonDataManager(arrSize - 2); 
			
			try {
				
				listIn = new Scanner(new File(location)); 
				
				while(listIn.hasNextLine()) {
					if(count == 0) {
						listIn.nextLine();
						count++;
					} 
					if(count++ == arrSize - 1) {
						break; 
					}
					bioData = listIn.nextLine();
					indiv = bioData.split(split);
					Person patient = new Person(indiv); 
					pdm.addPerson(patient, true);
					
					
					
					
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch(PersonAlreadyExistsException e) {
				e.printStackTrace();
			}
			return pdm; 
			
		}
		
		public void printTable() {
			int colWidth = 15;
			System.out.printf(padString(colWidth, "Name") + "|" + padString(colWidth, "Age") + "|"+ padString(colWidth, "Gender") + "|" + padString(colWidth, "Height") + "|" + padString(colWidth, "Weight") + "\n" );
			System.out.println("=========================================================================================");
			for(int i = 0; i < this.people.length; i++) {
				System.out.printf(padString(colWidth, this.people[i].getName()) + "|" + padInt(colWidth, this.people[i].getAge()) + "|"+ padString(colWidth, this.people[i].getGender()) + "|" + padDouble(colWidth, this.people[i].getHeight()) + "|" + padDouble(colWidth, this.people[i].getWeight()) + "\n" );
			}
			
		}
		public void saveTable(String fileName) throws IOException{
			int colWidth = 15;
			FileWriter fw = new FileWriter(fileName);
			fw.append(padString(colWidth, "Name") + "," + padString(colWidth, "Age") + ","+ padString(colWidth, "Gender") + "," + padString(colWidth, "Height") + "," + padString(colWidth, "Weight") + "\n" ); 
			
			for(int i = 0; i < people.length; i++){
				fw.append(padString(colWidth, this.people[i].getName()) + "," + padInt(colWidth, this.people[i].getAge()) + ","+ padString(colWidth, this.people[i].getGender()) + "," + padDouble(colWidth, this.people[i].getHeight()) + "," + padDouble(colWidth, this.people[i].getWeight()) + "\n" );
			
			}
			
			
		}
		
		
		public void getPerson(String name) throws PersonDoesNotExistException{
			boolean flag = true; // True when a person does not exist
			for(int i = 0; i < this.people.length;i++) {
				if(this.people[i].getName().equalsIgnoreCase( name )  ) {
					System.out.println(people[i].toString());
					flag = false; 
				}
			}
			if(flag) throw new PersonDoesNotExistException(); 
		}
		
		
		/** 
		 * 	A method that adds a new Person object into the Person[] people
		 * 
		 * @param newPerson the new Person object to be added 
		 * @param fromFile a boolean that is true when building from file and 
		 * false when user is adding Person 
		 * 
		 * */ 
		public void addPerson(Person newPerson, boolean fromFile) throws PersonAlreadyExistsException{
			int index; 	
			for(int i = 0; i < people.length;i++) {
				if(fromFile) {
					if(people[i] == null) {
						people[i] = newPerson; 
						break; 
					}
				}
				if(!fromFile) {
					
					if(doesPersonExist(newPerson)) { 
						throw new PersonAlreadyExistsException();
						} 
					if( checkAlphabet ( newPerson.getName(), people[i].getName() )  ) {
						ensureCapacity(); 
						shiftArrayRight(i); 
						people[i] = newPerson; 
						System.out.println(people[i].toString());
						break; 
						
					}
					if( !( checkAlphabet ( newPerson.getName(), people[i].getName() ) ) && i == people.length - 1 ) {
						ensureCapacity(); 
						people[i + 1] = newPerson;
						System.out.println(people[i].toString());
						break; 
					}
				}
			}
			
			
		
		}
		/** 
		 * 
		 * A method that takes a Person object and compares it to the people already in the array. 
		 * 
		 * @param  newPerson A person object containing bio data
		 * @return A boolean value that is true when this objects Person array people contains someone with identical 
		 * stats as the newPerson passed into the method
		 * */ 
		public boolean doesPersonExist(Person newPerson) {
			for(int i = 0; i < this.people.length;i++) {
				if(this.people[i].getName().equalsIgnoreCase( newPerson.getName() )  &&
						this.people[i].getGender().equalsIgnoreCase( newPerson.getGender() ) &&
						this.people[i].getAge() == newPerson.getAge() && this.people[i].getHeight() == newPerson.getHeight() 
						&& this.people[i].getWeight() == newPerson.getWeight()) {
					return true; 
				}
			}
			return false; 
		} 
		
		/** 
		 * Adds an index to the Person[] people for this object to ensure there is capacity to add a person. 
		 * 
		 * 
		 * @return Returns a Person array that is one index larger than the current Person[] people
		 * 
		 * */ 
		
		public void ensureCapacity() {
			Person[] newPeople = new Person[this.people.length + 1]; 
			for(int i = 0; i < this.people.length;i++) {
				newPeople[i] = this.people[i]; 
			}
			this.people = newPeople;  
		}
		
		/**
		 * Trims the Person[] people to the needed size when removing people 
		 *  
		 * 
		 *  @return Returns a Person[] while removing the last value 
		 *  */ 
		
		public Person[] trimCapacity() {
			Person[] newPeople = new Person[this.people.length - 1]; 
			for(int i = 0; i < this.people.length - 1;i++) {
				newPeople[i] = this.people[i]; 
			}
			return newPeople; 
		}
		
		
		
		public void removePerson(String name) throws PersonDoesNotExistException{
			boolean flag = true;
			for(int i = 0; i < this.people.length;i++) {
				if(this.people[i].getName().equalsIgnoreCase( name )  ) {
					shiftArrayLeft(i);
					this.people = trimCapacity(); 
				}
			}
		}
		
		/** 
		 * @param  first A string value containing a name
		 * @param  second A string value containing a name 
		 * @return Returns a boolean value that is true when String first is 
		 * alphabetically greater than String second. 
		 * */ 
		
		public boolean checkAlphabet(String first, String second) {
			first = first.toLowerCase(); 
			second = second.toLowerCase(); 
			for(int i = 0; i < Math.min(first.length(), second.length()) ; i++) {
				int a = first.charAt(i); 
				int b = second.charAt(i); 
				if(a > b) {
					return false; 
				}else if(b > a) {
					return true; 
				}
				
			}
			return true; 
		}
		
		/** 
		 * A method that shifts the elements of the people array right from the index passed in
		 * 
		 * @param  index The index from which the array will be shifted right 
		 * 
		 * */ 
		public void shiftArrayRight(int index) {
			for(int i = this.people.length - 2; i >= index;i--) {
				people[i + 1] = people[i];
			}
		}
		/** 
		 * A method that shifts the elements of the people array left from the index passed in
		 * 
		 * @param  index The index from which the array will be shifted left 
		 * 
		 * */ 
		public void shiftArrayLeft(int index) {
			for(int i = index ; i < people.length - 2 ; i++ ) {
				people[i] = people[i+1];
			}
		}
		public String padString(int column, String str) {
			int leftPadding = (column - str.length()) / 2;
			var rightPadding = column - str.length() - leftPadding;
			return String.format("%s%s%s", " ".repeat(leftPadding), str, " ".repeat(rightPadding));
		}
		public String padInt(int column, int num) {
			int leftPadding = (column - 3) / 2;
			var rightPadding = column - 3 - leftPadding;
			return String.format("%s%s%s", " ".repeat(leftPadding), num, " ".repeat(rightPadding));
		}
		public String padDouble(int column, double num) {
			int leftPadding = (column - 3) / 2;
			var rightPadding = column - 3 - leftPadding;
			return String.format("%s%s%s", " ".repeat(leftPadding), num, " ".repeat(rightPadding));
		}
		public int countArray() {
			return this.people.length; 
		}
	}