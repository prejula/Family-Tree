package com.prej.familytree.person;

import java.util.HashSet;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

public class PersonManager {

	public Set<Person> getMaxGirlChildMother(Person head) {
		NavigableMap<Integer, Set<Person>> motherMap = new TreeMap<Integer, Set<Person>>();
		return getMotherWithMaxGirlChild(head, motherMap);
	}

	private Set<Person> getMotherWithMaxGirlChild(Person temp,
			NavigableMap<Integer, Set<Person>> motherMap) {

		if (temp.getGender().equals(Gender.FEMALE)) {
			addMothertoMap(temp, motherMap);
		} else if (null != temp.getSpouse()
				&& temp.getSpouse().getGender().equals(Gender.FEMALE)) {
			addMothertoMap(temp.getSpouse(), motherMap);
		}

		if (null != temp.getChildren()) {
			for (Person p : temp.getChildren()) {
				getMotherWithMaxGirlChild(p, motherMap);
			}
		}
		return motherMap.lastEntry().getValue();
	}

	private void addMothertoMap(Person temp,
			NavigableMap<Integer, Set<Person>> motherMap) {

		int count = 0;

		if (null != temp.getChildren()) {
			for (Person p : temp.getChildren()) {
				count = p.getGender().equals(Gender.FEMALE) ? count + 1 : count;
			}

			if (motherMap.containsKey(count)) {
				motherMap.get(count).add(temp);
			} else {
				Set<Person> tempSet = new HashSet<Person>();
				tempSet.add(temp);
				motherMap.put(count, tempSet);
			}
		}
	}

	public Person getGenerationGap(String name1, String name2, Person head) {
		Person temp = head;
		Person firstPerson = getPersonWithName(temp, name1);
		Person secondPerson = getPersonWithName(temp, name2);
		return firstPerson.getLevelInTree() < secondPerson.getLevelInTree() ? firstPerson
				: secondPerson;
	}

	public Person getPersonWithName(Person temp, String name) {

		Person person = null;

		if (temp.getName().equalsIgnoreCase(name)) {
			return temp;
		} else if (null != temp.getSpouse()
				&& temp.getSpouse().getName().equalsIgnoreCase(name)) {
			return temp.getSpouse();
		}

		if (null != temp.getChildren()) {
			for (Person p : temp.getChildren()) {
				person = getPersonWithName(p, name);
				if (null != person) {
					break;
				}
			}
		}
		return person;
	}
}
