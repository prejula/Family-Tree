package com.prej.familytree.relationship;

import java.util.Set;

import com.prej.familytree.person.Person;

public interface Relationship {

	public Set<Person> get(Person head, String name);

	public Relationship getInstance();
}
