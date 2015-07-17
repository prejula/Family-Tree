package com.prej.familytree.relationship;

import java.util.HashSet;
import java.util.Set;

import com.prej.familytree.person.Gender;
import com.prej.familytree.person.Person;

public class Brothers implements Relationship {

	private Set<Person> relatives;
	
	public Set<Person> get(Person head, String name) {

		Person temp = head;
		
		if (name.equalsIgnoreCase(temp.getName())) {

			relatives = new HashSet<Person>();

			for (Person person : temp.getFather().getChildren()) {
			
				if (!(name.equalsIgnoreCase(person.getName())
						|| Gender.FEMALE.equals(person.getGender())))

					relatives.add(person);
			}
		}

		if (null != temp.getChildren() && null == relatives) {
			for (Person p : temp.getChildren()) {
				relatives = get(p, name);
			}
		}
		return relatives;
	}

	public Brothers getInstance() {
		return new Brothers();
	}

	public static boolean isBrother(Person firstPerson, Person secondPerson) {

		return secondPerson.getGender().equals(Gender.MALE)
				&& null != secondPerson.getFather()
				&& secondPerson.getFather().equals(firstPerson.getFather());
	}
}