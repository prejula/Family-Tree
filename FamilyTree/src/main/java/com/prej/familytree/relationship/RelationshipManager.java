package com.prej.familytree.relationship;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.prej.familytree.person.Person;
import com.prej.familytree.person.PersonManager;

public class RelationshipManager {

	private Map<RelationEnum, Relationship> relationshipClassMapping;

	private PersonManager personManager;

	public RelationshipManager() {

		relationshipClassMapping = new HashMap<RelationEnum, Relationship>();

		relationshipClassMapping.put(RelationEnum.BROTHERS, new Brothers());
		relationshipClassMapping.put(RelationEnum.BROTHERINLAW,
				new BrotherInLaw());
		relationshipClassMapping.put(RelationEnum.CHILDREN, new Children());
		relationshipClassMapping.put(RelationEnum.COUSINS, new Cousins());
		relationshipClassMapping.put(RelationEnum.DAUGHTER, new Daughter());
		relationshipClassMapping.put(RelationEnum.FATHER, new Father());
		relationshipClassMapping.put(RelationEnum.MOTHER, new Mother());
		relationshipClassMapping.put(RelationEnum.GRANDDAUGHTER,
				new GrandDaughter());
		relationshipClassMapping.put(RelationEnum.MATERNALAUNT,
				new MaternalAunt());
		relationshipClassMapping.put(RelationEnum.MATERNALUNCLE,
				new MaternalUncle());
		relationshipClassMapping.put(RelationEnum.PATERNALAUNT,
				new PaternalAunt());
		relationshipClassMapping.put(RelationEnum.PATERNALUNCLE,
				new PaternalUncle());
		relationshipClassMapping.put(RelationEnum.SISTERINLAW,
				new SisterInLaw());
		relationshipClassMapping.put(RelationEnum.SISTERS, new Sisters());
		relationshipClassMapping.put(RelationEnum.SON, new Son());
	}

	public Set<Person> getPeopleWithRelationship(String name,
			String relationship, Person head) {

		Relationship relation = relationshipClassMapping.get(Enum.valueOf(
				RelationEnum.class, relationship.toUpperCase()));

		return relation.getInstance().get(head, name);
	}

	public String getRelationship(String name1, String name2, Person head) {

		Person firstPerson = personManager.getPersonWithName(head, name1);
		Person secondPerson = personManager.getPersonWithName(head, name2);

		String relation = Father.isFather(firstPerson, secondPerson) ? RelationEnum.FATHER.toString()
				: Mother.isMother(firstPerson, secondPerson) ? RelationEnum.MOTHER.toString()
			    : Son.isSon(firstPerson, secondPerson) ? RelationEnum.SON.toString()
				: Daughter.isDaughter(firstPerson, secondPerson) ? RelationEnum.DAUGHTER.toString()
				: Brothers.isBrother(firstPerson, secondPerson) ? RelationEnum.BROTHERS.toString()
			    : Sisters.isSister(firstPerson, secondPerson) ? RelationEnum.SISTERS.toString()
				: GrandDaughter.isGrandDaughter(firstPerson, secondPerson) ? RelationEnum.GRANDDAUGHTER.toString()
				: PaternalUncle.isPaternalUncle(firstPerson, secondPerson) ? RelationEnum.PATERNALUNCLE.toString()
			    : PaternalAunt.isPaternalAunt(firstPerson, secondPerson) ? RelationEnum.PATERNALAUNT.toString()
			    : MaternalUncle.isMaternalUncle(firstPerson, secondPerson) ? RelationEnum.MATERNALUNCLE.toString()
				: MaternalAunt.isMaternalAunt(firstPerson, secondPerson) ? RelationEnum.MATERNALAUNT.toString()
				: SisterInLaw.isSisterInLaw(firstPerson, secondPerson) ? RelationEnum.SISTERINLAW.toString()
				: BrotherInLaw.isBrotherInLaw(firstPerson, secondPerson) ? RelationEnum.BROTHERINLAW.toString()
				: Cousins.isCousin(firstPerson, secondPerson) ? RelationEnum.COUSINS.toString() : null;

		return relation;
	}

	public void setPersonManager(PersonManager personManager) {
		this.personManager = personManager;
	}
}
