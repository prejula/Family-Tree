package com.prej.familytree.relationship;

import java.util.HashSet;
import java.util.Set;

import com.prej.familytree.person.Person;

public class Cousins implements Relationship {

	private Set<Person> relatives;

	public Set<Person> get(Person head, String name) {

		Person temp = head;

		if (name.equalsIgnoreCase(temp.getName())) {

			relatives = new HashSet<Person>();

			addRelative(name, temp, false);
		}

		if (null != temp.getChildren() && null == relatives) {
			for (Person p : temp.getChildren()) {
				relatives = get(p, name);
			}
		}
		return relatives;
	}

	private void addRelative(String name, Person temp, boolean isSpouse) {
		Person father = temp.getFather();
		if (null != father) {
			for (Person person : father.getChildren()) {
				if (null != person.getChildren())
					relatives.addAll(person.getChildren());
			}
		}
	}

	public Cousins getInstance() {
		return new Cousins();
	}
	
	public static boolean isCousin(Person firstPerson, Person secondPerson) {

		Person firstParent = null != firstPerson.getFather()
				&& null != firstPerson.getFather().getFather() ? firstPerson
				.getFather().getFather() : null != firstPerson.getMother()
				&& null != firstPerson.getMother().getFather() ? firstPerson
				.getMother().getFather() : null;
				
		Person secondParent = null != secondPerson.getFather()
				&& null != secondPerson.getFather().getFather() ? secondPerson
				.getFather().getFather() : null != secondPerson.getMother()
				&& null != secondPerson.getMother().getFather() ? secondPerson
				.getMother().getFather() : null;

		return firstParent.equals(secondParent);
	}


}
