package com.prej.familytree.relationship;

import java.util.HashSet;
import java.util.Set;

import com.prej.familytree.person.Gender;
import com.prej.familytree.person.Person;

public class MaternalUncle implements Relationship {

	private Set<Person> relatives;

	public MaternalUncle getInstance() {
		return new MaternalUncle();
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
		Person mother = temp.getMother();
		if (null != mother && null != mother.getFather()) {
			for (Person person : mother.getFather().getChildren()) {
				if (Gender.MALE.equals(person.getGender()))
					relatives.add(person);
			}
		}
	}

	public static boolean isMaternalUncle(Person firstPerson,
			Person secondPerson) {
		Person mother = firstPerson.getMother();
		Person mothersFather = null != mother ? mother.getFather() : null;
		Person secondPersonsFather = secondPerson.getFather();
		Person secondPersonsSpousesFather = null != secondPerson.getSpouse() ? secondPerson
				.getSpouse().getFather() : null;

		return Gender.MALE.equals(secondPerson.getGender())
				&& ((null != mothersFather && null != secondPersonsFather && secondPersonsFather
						.equals(mothersFather)) || (null != mothersFather
						&& null != secondPersonsSpousesFather && secondPersonsSpousesFather
							.equals(mothersFather)));
	}
}
