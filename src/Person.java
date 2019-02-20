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
		public Person(int age, double height, double weight, String name, String gender) {
			this.age = age; 
			this.height = height; 
			this.weight = weight; 
			this.name = name; 
			this.gender = gender; 
		}
		public Person(String[] individual){
			this.name = individual[0]; 
			this.gender = individual[1];
			this.age = Integer.parseInt(individual[2]);
			this.height = Double.parseDouble(individual[3]);
			this.weight = Double.parseDouble(individual[3]);
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
			return ""; 
		}
	}