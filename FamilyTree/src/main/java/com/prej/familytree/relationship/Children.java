package com.prej.familytree.relationship;

import java.util.HashSet;
import java.util.Set;

import com.prej.familytree.person.Person;

public class Children implements Relationship {
	
	private Set<Person> relative;
	
	public Set<Person> get(Person head, String name) {

		if (name.equalsIgnoreCase(head.getName())) {
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
		
		for (Person p : head.getChildren()) {
				relative.add(p);
		}
	}

	public Children getInstance() {
		return new Children();
	}
}
