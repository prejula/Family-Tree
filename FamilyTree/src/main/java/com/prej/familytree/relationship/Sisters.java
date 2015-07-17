package com.prej.familytree.relationship;

import java.util.HashSet;
import java.util.Set;

import com.prej.familytree.person.Gender;
import com.prej.familytree.person.Person;

public class Sisters implements Relationship {

	public Sisters getInstance() {
		return new Sisters();
	}

	public Set<Person> get(Person head, String name) {
		Person temp = head;
		Set<Person> relatives = null;

		if (name.equalsIgnoreCase(temp.getName())) {
			relatives = new HashSet<Person>();
			for (Person person : temp.getFather().getChildren()) {
				if (!(name.equalsIgnoreCase(person.getName())
						|| Gender.MALE.equals(person.getGender())))
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

	public static boolean isSister(Person firstPerson, Person secondPerson) {
		return Gender.FEMALE.equals(secondPerson.getGender())
				&& null != secondPerson.getFather()
				&& secondPerson.getFather().equals(firstPerson.getFather());
	}
}
