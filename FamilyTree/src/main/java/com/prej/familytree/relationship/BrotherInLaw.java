package com.prej.familytree.relationship;

import java.util.HashSet;
import java.util.Set;

import com.prej.familytree.person.Gender;
import com.prej.familytree.person.Person;

public class BrotherInLaw implements Relationship {

	private Set<Person> relatives;

	public Set<Person> get(Person head, String name) {

		Person temp = head;

		if (name.equalsIgnoreCase(temp.getName())) {

			addRelative(name, temp);
		}

		if (null != temp.getChildren() && null == relatives) {
			for (Person p : temp.getChildren()) {
				relatives = get(p, name);
			}
		}
		return relatives;
	}

	private void addRelative(String name, Person temp) {
		
		relatives = new HashSet<Person>();

		addRelative(name, temp, false);

		if (null != temp.getSpouse()) {
			addRelative(name, temp.getSpouse(), true);
		}
	}

	private void addRelative(String name, Person temp, boolean isSpouse) {
		
		Person father = temp.getFather();
		
		if (null != father) {
			
			for (Person person : father.getChildren()) {

				if (!(name.equalsIgnoreCase(person.getName())) && !isSpouse
						&& Gender.FEMALE.equals(person.getGender())
						&& null != person.getSpouse())

					relatives.add(person.getSpouse());

				else if (!(name.equalsIgnoreCase(person.getName())) && isSpouse
						&& Gender.MALE.equals(person.getGender()))

					relatives.add(person);
			}
		}
	}

	public BrotherInLaw getInstance() {
		return new BrotherInLaw();
	}

	public static boolean isBrotherInLaw(Person firstPerson, Person secondPerson) {

		Person firstSpouse = firstPerson.getSpouse();
		Person fatherOfSpouse = null != firstSpouse ? firstSpouse.getFather()
				: null;
		Person fatherOfFirstPerson = firstPerson.getFather();
		Person fatherOfSecondPerson = secondPerson.getFather();
		Person secondSpouse = secondPerson.getSpouse();
		Person fatherOfSecondSpouse = null != secondSpouse ? secondSpouse
				.getFather() : null;

		return secondPerson.getGender().equals(Gender.MALE)
				&& ((null != fatherOfSpouse && null != fatherOfSecondPerson && fatherOfSecondPerson
						.equals(fatherOfSpouse)) || (null != fatherOfFirstPerson
						&& null != fatherOfSecondSpouse && fatherOfFirstPerson
							.equals(fatherOfSecondSpouse)));
	}

}
