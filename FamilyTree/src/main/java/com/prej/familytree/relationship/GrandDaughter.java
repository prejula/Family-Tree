package com.prej.familytree.relationship;

import java.util.HashSet;
import java.util.Set;

import com.prej.familytree.person.Gender;
import com.prej.familytree.person.Person;

public class GrandDaughter implements Relationship {

	private Set<Person> relatives;

	public GrandDaughter getInstance() {
		return new GrandDaughter();
	}

	public Set<Person> get(Person head, String name) {
		Person temp = head;

		if (name.equalsIgnoreCase(temp.getName())) {
			relatives = addGrandDaughter(temp);
		} else if (null != temp.getSpouse()
				&& name.equalsIgnoreCase(temp.getSpouse().getName())) {
			relatives = addGrandDaughter(temp);
		}

		if (null != temp.getChildren() && null == relatives) {
			for (Person p : temp.getChildren()) {
				relatives = get(p, name);
			}
		}
		return relatives;

	}

	private Set<Person> addGrandDaughter(Person temp) {
		Set<Person> relatives = new HashSet<Person>();

		for (Person person : temp.getChildren()) {
			if (null != person.getChildren()) {
				for (Person child : person.getChildren()) {
					if (Gender.FEMALE.equals(child.getGender()))
						relatives.add(child);
				}
			}
		}
		return relatives;
	}

	public static boolean isGrandDaughter(Person firstPerson,
			Person secondPerson) {

		Set<Person> children = firstPerson.getChildren();

		if (null != children && Gender.FEMALE.equals(secondPerson.getGender())) {
			return ((null != secondPerson.getFather() && children
					.contains(secondPerson.getFather())) || (null != secondPerson
					.getMother() && children.contains(secondPerson.getMother())));
		}

		return false;
	}
}
