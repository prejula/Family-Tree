package com.prej.familytree.relationship;

import java.util.HashSet;
import java.util.Set;

import com.prej.familytree.person.Gender;
import com.prej.familytree.person.Person;

public class Son implements Relationship {
	
	private Set<Person> relative;

	public Son getInstance() {
		return new Son();
	}

	public Set<Person> get(Person head, String name) {
		if (name.equalsIgnoreCase(head.getName())) {
			relative = new HashSet<Person>();
			for (Person p : head.getChildren()) {
				if (Gender.MALE.equals(p.getGender())) {
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

	public static boolean isSon(Person firstPerson, Person secondPerson) {

		return Gender.MALE.equals(secondPerson.getGender())
				&& ((null != secondPerson.getFather() && secondPerson
						.getFather().equals(firstPerson)) || (null != secondPerson
						.getMother() && secondPerson.getMother().equals(
						firstPerson)));
	}
}
