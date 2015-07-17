package com.prej.familytree.relationship;

import java.util.HashSet;
import java.util.Set;

import com.prej.familytree.person.Person;

public class Mother implements Relationship {
	
	private Set<Person> relative;
	
	public Mother getInstance() {
		return new Mother();
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
		relative.add(head.getMother());
	}
	

	public static boolean isMother(Person firstPerson, Person secondPerson) {
		return null != firstPerson.getMother()
				&& firstPerson.getMother().equals(secondPerson);
	}

}
