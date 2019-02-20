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
		//Fields 
		//**************************************************
				
		private Person[] people;
		
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
			PersonDataManager list = new PersonDataManager(arrSize); 
			try {
				Scanner listIn = new Scanner(new File(location));
				listIn.useDelimiter(split); 
				while(listIn.hasNextLine()) {
					indiv = new String[5]; 
					for(int i = 0; i < 5;i++) {
						indiv[i] = listIn.next(); 
					}
					count++; 
					
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		
					
					
				
			
			return list; 
			
		}
		public void addPerson(Person newPerson) throws PersonAlreadyExistsException{
			
		}
	}