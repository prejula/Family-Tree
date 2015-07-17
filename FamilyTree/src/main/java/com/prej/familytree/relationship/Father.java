package com.prej.familytree.relationship;

import java.util.HashSet;
import java.util.Set;

import com.prej.familytree.person.Person;

public class Father implements Relationship {

	private Set<Person> relative;
	
	public Father getInstance() {
		return new Father();
	}

	public Set<Person> get(Person head, String name) {
		
		if (head.getName().equalsIgnoreCase(name)) {
			addRelative(head);
		}

		if (null != head.getChildren() && null == relative)
			for (Person p : head.getChildren()) {
				get(p, name);
			}

		return relative;
	}

	private void addRelative(Person head) {
		relative = new HashSet<Person>();
		relative.add(head.getFather());
	}

	public static boolean isFather(Person firstPerson, Person secondPerson) {

		return null != firstPerson.getFather()
				&& firstPerson.getFather().equals(secondPerson);
	}
}
