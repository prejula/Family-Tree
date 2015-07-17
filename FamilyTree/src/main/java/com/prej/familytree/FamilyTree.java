package com.prej.familytree;

import com.prej.familytree.person.Person;

public class FamilyTree {

	private Person head;

	public Person getHead() {
		return head;
	}

	public void addKing(Person person) {
		head = person;
		person.setLevelInTree(0);
	}

	public void addQueen(Person queen) {
		if (null != head) {
			head.setSpouse(queen);
			queen.setLevelInTree(0);
		}
	}

	public void addSpouse(Person person) {
		if (null != person.getSpouse()) {
			Person temp = head;
			addSpouse(person, temp);
		}
	}

	private boolean addSpouse(Person person, Person temp) {
		boolean spouseAdded = false;
		while (null != temp) {
			if (temp.equals(person.getSpouse())) {
				person.setLevelInTree(temp.getLevelInTree());
				temp.setSpouse(person);
				spouseAdded = true;
			}
			if (null != temp.getChildren() && !spouseAdded) {
				for (Person p : temp.getChildren()) {
					spouseAdded = !spouseAdded ? addSpouse(person, p)
							: spouseAdded;
				}
			}
			temp = null;
		}
		return spouseAdded;
	}

	public void addChild(Person person) {
		if (null != person.getMother()) {
			addChild(person, head);
		}
	}

	private boolean addChild(Person child, Person temp) {
		boolean childAdded = false;
		while (null != temp) {

			childAdded = addChild(child, temp, childAdded);

			if (null != temp.getSpouse() && !childAdded) {
				temp = temp.getSpouse();
				childAdded = addChild(child, temp, childAdded);
			}

			if (null != temp.getChildren() && !childAdded) {
				for (Person p : temp.getChildren()) {
					childAdded = addChild(child, p);
				}
			}
			temp = null;
		}
		return childAdded;
	}

	private boolean addChild(Person child, Person temp, boolean childAdded) {
		if (temp.equals(child.getMother())) {

			child.setLevelInTree(temp.getLevelInTree() + 1);
			child.setFather(temp.getSpouse());
			
			temp.addChildren(child);
			temp.getSpouse().addChildren(child);
			childAdded = true;
		}
		return childAdded;
	}

	@Override
	public String toString() {

		StringBuilder builder = new StringBuilder();
		createFamilyString(builder, head);

		return builder.toString();
	}

	private void createFamilyString(StringBuilder builder, Person temp) {

		while (null != temp) {

			builder.append(temp.getName() + "\n");

			if (null != temp.getSpouse()) {
				temp = temp.getSpouse();
				builder.append(temp.getName() + "\n");
			}

			if (null != temp.getChildren()) {
				for (Person p : temp.getChildren()) {
					createFamilyString(builder, p);
				}
			}
		}
	}
}