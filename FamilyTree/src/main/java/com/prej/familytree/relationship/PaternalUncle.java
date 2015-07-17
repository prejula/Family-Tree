package com.prej.familytree.relationship;

import java.util.HashSet;
import java.util.Set;

import com.prej.familytree.person.Gender;
import com.prej.familytree.person.Person;

public class PaternalUncle implements Relationship {

	private Set<Person> relatives;
	
	public PaternalUncle getInstance() {
		return new PaternalUncle();
	}

	public Set<Person> get(Person head, String name) {
		Person temp = head;
		if (name.equalsIgnoreCase(temp.getName())) {
			relatives = new HashSet<Person>();
			addRelative(name, temp, false);

			if (null != temp.getSpouse()) {
				addRelative(name, temp.getSpouse(), true);
			}
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
		if (null != father && null != father.getFather()) {
			for (Person person : father.getFather().getChildren()) {
				if (Gender.MALE.equals(person.getGender()))
					relatives.add(person);
			}
		}
	}

	public static boolean isPaternalUncle(Person firstPerson, Person secondPerson) {

		Person father = firstPerson.getFather();
		Person fathersFather = null != father ? father.getFather() : null;
		Person secondPersonsFather = secondPerson.getFather();
		Person secondPersonsSpousesFather = null != secondPerson.getSpouse() ? secondPerson
				.getSpouse().getFather() : null;

		return Gender.MALE.equals(secondPerson.getGender())
				&& ((null != fathersFather && null != secondPersonsFather && secondPersonsFather
						.equals(fathersFather)) || (null != fathersFather
						&& null != secondPersonsSpousesFather && secondPersonsSpousesFather
							.equals(fathersFather)));
	
	}
}
