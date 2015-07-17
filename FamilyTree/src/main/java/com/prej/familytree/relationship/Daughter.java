package com.prej.familytree.relationship;

import java.util.HashSet;
import java.util.Set;

import com.prej.familytree.person.Gender;
import com.prej.familytree.person.Person;

public class Daughter implements Relationship {
	
	private	Set<Person> relative;

	public Daughter getInstance() {
		return new Daughter();
	}

	public Set<Person> get(Person head, String name) {
		if (name.equalsIgnoreCase(head.getName())) {
			relative = new HashSet<Person>();
			for (Person p : head.getChildren()) {
				if (Gender.FEMALE.equals(p.getGender())) {
					relative.add(p);
				}
			}
		}

		if (null != head.getChildren() && null == relative)
			for (Person p : head.getChildren()) {
				get(p, name);
			}

		return relative;
	}


	public static boolean isDaughter(Person firstPerson, Person secondPerson) {

		return Gender.FEMALE.equals(secondPerson.getGender())
				&& ((null != secondPerson.getFather() && secondPerson
						.getFather().equals(firstPerson)) || (null != secondPerson
						.getMother() && secondPerson.getMother().equals(
						firstPerson)));
	}
}
