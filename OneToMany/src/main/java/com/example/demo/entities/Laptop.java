package com.example.demo.entities;



import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name="jpa_laptops")

	public class Laptop {
		
		@Id
		private int laptopId;
		private String modelNumber;
		private String brand;
		
		@OneToOne
		@JoinColumn(name="student_id")
		private Student student;

		public int getLaptopId() {
			return laptopId;
		}

		public void setLaptopId(int laptopId) {
			this.laptopId = laptopId;
		}

		public String getModelNumber() {
			return modelNumber;
		}

		public void setModelNumber(String modelNumber) {
			this.modelNumber = modelNumber;
		}

		public String getBrand() {
			return brand;
		}

		public void setBrand(String brand) {
			this.brand = brand;
		}

		public Student getStudent() {
			return student;
		}

		public void setStudent(Student student) {
			this.student = student;
		}

		public Laptop(int laptopId, String modelNumber, String brand, Student student) {
			super();
			this.laptopId = laptopId;
			this.modelNumber = modelNumber;
			this.brand = brand;
			this.student = student;
		}

		public Laptop() {
			super();
			// TODO Auto-generated constructor stub
		}

		@Override
		public String toString() {
			return "Laptop [laptopId=" + laptopId + ", modelNumber=" + modelNumber + ", brand=" + brand + ", student="
					+ student + "]";
		}

}
