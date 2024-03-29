package com.example.test.model;

import org.springframework.stereotype.Component;

@Component
public class Employee {

    private int id;
    private String name;
    private int age;
    private long salary;

    public Employee() {
    	System.out.println("Employee object created...!");
    }
    
    public Employee(int id, String name, int age, int salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
    }
    
    public int getId() {
        return id;
    }
    public void setId(int id) {
		this.id = id;
	}
    public String getName() {
        return name;
    }
    public void setName(String name) {
		this.name = name;
	}
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
		this.age = age;
	}
    public long getSalary() {
        return salary;
    }
    public void setSalary(long salary) {
		this.salary = salary;
	}
    
    @Override
    public String toString() {
        return "[id=" + this.id + ", name=" + this.name + ", age=" + this.age + ", salary=" +
                this.salary + "]";
    }

	public void show() {
		System.out.println("this is test...!");		
	}

}