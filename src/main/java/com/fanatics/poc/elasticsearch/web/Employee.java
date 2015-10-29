package com.fanatics.poc.elasticsearch.web;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Employee {

	private long age;
	private String about;
	private String city;
	private List<String> interests;
	private String nonInterest;
	private Date publish_date;
	private Name name;
	
	/*private Employee(EmployeeBuilder builder) {
		this.age = builder.age;
		this.about = builder.about;
		this.city = builder.about;
		this.interests = builder.interests;
		this.publish_date = builder.publish_date;
		this.name = builder.name;
	}*/

	public String getNonInterest() {
		return nonInterest;
	}

	public long getAge() {
		return age;
	}

	public void setAge(long age) {
		this.age = age;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<String> getInterests() {
		return interests;
	}

	public void setInterests(List<String> interests) {
		this.interests = interests;
	}

	public Date getPublish_date() {
		return publish_date;
	}

	public void setPublish_date(Date publish_date) {
		this.publish_date = publish_date;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public static class Name {
		private String first_name;
		private String last_name;

		public String getFirst_name() {
			return first_name;
		}

		public void setFirst_name(String first_name) {
			this.first_name = first_name;
		}

		public String getLast_name() {
			return last_name;
		}

		public void setLast_name(String last_name) {
			this.last_name = last_name;
		}

	}
	///

/*	public static class EmployeeBuilder {

		private long age;
		private String about;
		private String city;
		private List<String> interests;
		private Date publish_date;
		private Name name;

		public EmployeeBuilder age(int age) {

			this.age = age;

			return this;

		}

		public EmployeeBuilder about(String about) {

			this.about = about;

			return this;

		}

		public EmployeeBuilder city(String city) {

			this.city = city;

			return this;

		}
		
		public EmployeeBuilder interests(List<String> interests) {

			this.interests = interests;

			return this;

		}
		
		public EmployeeBuilder publishDate(Date publishDate) {

			this.publish_date = publishDate;

			return this;

		}
		
		public EmployeeBuilder name(Name name) {

			this.name = name;

			return this;

		}

		public Employee build() {

			return new Employee(this);

		}

	}*/

	///
}
