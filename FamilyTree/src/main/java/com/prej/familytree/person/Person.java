package com.prej.familytree.person;

import java.util.HashSet;
import java.util.Set;

public class Person {

	private String name;

	private int age;

	private Gender gender;

	private int levelInTree;

	private Person spouse;

	private Set<Person> children;

	private Set<Person> siblings;

	private Person mother;

	private Person father;

	public Person getMother() {
		return mother;
	}

	public void setMother(Person mother) {
		this.mother = mother;
	}

	public Person getFather() {
		return father;
	}

	public void setFather(Person father) {
		this.father = father;
	}

	public Set<Person> getSiblings() {
		return siblings;
	}

	public void setSiblings(Set<Person> siblings) {
		this.siblings = siblings;
	}

	public Person getSpouse() {
		return spouse;
	}

	public void setSpouse(Person spouse) {
		this.spouse = spouse;
	}

	public Set<Person> getChildren() {
		return children;
	}

	public void addChildren(Person person) {

		this.children = null == this.children ? new HashSet<Person>()
				: this.children;

		this.children.add(person);
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public int getLevelInTree() {
		return levelInTree;
	}

	public void setLevelInTree(int levelInTree) {
		this.levelInTree = levelInTree;
	}

	@Override
	public String toString() {
		return this.name;
	}
	
	@Override
	public boolean equals(Object person)
	{
		if(!(person instanceof Person))
		{
			return false;
		}
		
		return ((Person)person).getName().equalsIgnoreCase(this.getName()) ? true : false;
	}
	
	@Override
	public int hashCode()
	{
	
		return this.getName().length() * 31;
	}
}
