/** 
 * CSE 214 
 * Instructor: Praveen Tripathi
 * @author Xavier Velez
 * SBU ID: 105703437
 * */ 
public class Person{
		
		//**************************************************
		//Fields 
		//**************************************************
		
		private int age; 
		private double height; 
		private double weight; 
		private String name; 
		private String gender; 
		//**************************************************
		//Constructors 
		//**************************************************
		public Person(String name, String gender, int age, double height, double weight) {
			this.age = age; 
			this.height = height; 
			this.weight = weight; 
			this.name = name; 
			this.gender = gender; 
		}
		public Person(String[] individual){
			//Valid data test
			individual = validatePersonArray(individual); 
			this.name = individual[0].substring(1, individual[0].length()-1); 
			this.gender = individual[1].substring(1, individual[1].length()-1);
			this.age = Integer.parseInt(individual[2]);
			this.height = Double.parseDouble(individual[3]);
			this.weight = Double.parseDouble(individual[4]);
		}
		
		private String[] validatePersonArray(String[] individual){
			
			for(int i = 0; i < individual.length;i++) {
				individual[i] = individual[i].trim(); 
			}
			return individual; 
		}
		//**************************************************
		//Getters and Setters 
		//**************************************************
		
		public int getAge() {
			return this.age;
		} 
		public double getHeight() {
			return this.height; 
		}
		public double getWeight() {
			return this.weight; 
		}
		public String getName() {
			return this.name; 
		}
		public String getGender() {
			return this.gender; 
		}
		
		/**
		 * Provides the information for a person in tabular form
		 * 
		 * @return A String of information about the Person in tabular form. 
		 *  */ 
		public String toString() {
			return this.name + " " + this.gender + " " + this.age + " " + this.height + " " + this.weight; 
		}
		
		/** 
		 * 
		 * @param Person a A Person object containing biodata
		 * @param Person b A Person object containing biodata
		 * @return A boolean value signifying whether the two Person objects passed in are identical. 
		 * 
		 * */ 
		public boolean samePerson(Person a, Person b) {
			boolean flag = true; 
			if(!(a.getName().equalsIgnoreCase(b.getName()))) {
				flag = false; 
			}else if(a.getAge() != b.getAge()) {
				flag = false; 
			}else if((a.getGender().equalsIgnoreCase(b.getGender()))) {
				flag = false; 
			}else if(a.getHeight() != b.getHeight()) {
				flag = false; 
			}else if(a.getWeight() != b.getWeight()) {
				flag = false; 
			}
			return flag; 
		}
	}