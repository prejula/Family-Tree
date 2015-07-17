package com.prej.familytree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.prej.familytree.person.Gender;
import com.prej.familytree.person.Person;
import com.prej.familytree.person.PersonManager;
import com.prej.familytree.relationship.RelationshipManager;

@RunWith(JUnit4.class)
public class FamilyTreeTest {

	private FamilyTree familyTree;
	private RelationshipManager relationshipManager;
	private PersonManager personManager;

	@Before
	public void setUp() {

		familyTree = new FamilyTree();
		personManager = new PersonManager();
		relationshipManager = new RelationshipManager();
		relationshipManager.setPersonManager(personManager);
	}

	@Test
	public void testRelationship() throws Exception {
		createFamilyTree();

		String name = "Ish";
		String relationship = "Brothers";

		Set<Person> personList = relationshipManager.getPeopleWithRelationship(name,
				relationship, familyTree.getHead());

		assertNotNull(personList);

		Person chit = new Person();
		chit.setName("chit");

		Person vich = new Person();
		vich.setName("vich");

		for (Person p : personList) {
			if (p.getName().equalsIgnoreCase("chit"))
				assertEquals(chit, p);
			else
				assertEquals(vich, p);
		}
		
		
		name = "Ish";
		relationship = "BrotherInLaw";

	    personList = relationshipManager.getPeopleWithRelationship(name,
				relationship, familyTree.getHead());

		assertNotNull(personList);
		
		Person vyan = new Person();
		vyan.setName("vyan");
		
		for (Person p : personList) {
			if (p.getName().equalsIgnoreCase("vyan"))
				assertEquals(vyan, p);
		}
		
		name = "Ish";
		relationship = "SisterInLaw";

	    personList = relationshipManager.getPeopleWithRelationship(name,
				relationship, familyTree.getHead());

		assertNotNull(personList);
		
		Person lika = new Person();
		lika.setName("lika");
		
		Person ambi = new Person();
		ambi.setName("ambi");
		
		for (Person p : personList) {
			if (p.getName().equalsIgnoreCase("lika"))
				assertEquals(lika, p);
			else
				assertEquals(ambi, p);
		}
		
		name = "kriya";
		relationship = "Cousins";

	    personList = relationshipManager.getPeopleWithRelationship(name,
				relationship, familyTree.getHead());

		assertNotNull(personList);
		
		Person misa = new Person();
		misa.setName("misa");
		
		for (Person p : personList) {
			if (p.getName().equalsIgnoreCase("misa"))
				assertEquals(misa, p);
		}
		
		name = "chit";
		relationship = "son";

	    personList = relationshipManager.getPeopleWithRelationship(name,
				relationship, familyTree.getHead());

		assertNotNull(personList);
		
		Person dritha = new Person();
		dritha.setName("dritha");
		
		Person vritha = new Person();
		vritha.setName("vritha");
		
		for (Person p : personList) {
			if (p.getName().equalsIgnoreCase("dritha"))
				assertEquals(dritha, p);
			else
				assertEquals(vritha, p);
		}
		
		name = "dritha";
		relationship = "daughter";

	    personList = relationshipManager.getPeopleWithRelationship(name,
				relationship, familyTree.getHead());

		assertNotNull(personList);
		
		Person driya = new Person();
		driya.setName("driya");
		
		assertEquals(driya, personList.toArray()[0]);

		name = "dritha";
		relationship = "children";

	    personList = relationshipManager.getPeopleWithRelationship(name,
				relationship, familyTree.getHead());

		assertNotNull(personList);
		
		Person jata = new Person();
		jata.setName("jata");
		
		for (Person p : personList) {
			if (p.getName().equalsIgnoreCase("driya"))
				assertEquals(driya, p);
			else
				assertEquals(jata, p);
		}
		
		name = "dritha";
		relationship = "father";

	    personList = relationshipManager.getPeopleWithRelationship(name,
				relationship, familyTree.getHead());

		assertNotNull(personList);
		assertEquals(chit, personList.toArray()[0]);
		
		name = "dritha";
		relationship = "mother";

	    personList = relationshipManager.getPeopleWithRelationship(name,
				relationship, familyTree.getHead());

		assertNotNull(personList);
		assertEquals(ambi, personList.toArray()[0]);
		
		name = "jata";
		relationship = "paternaluncle";

	    personList = relationshipManager.getPeopleWithRelationship(name,
				relationship, familyTree.getHead());

		assertNotNull(personList);
		assertEquals(vritha, personList.toArray()[0]);
		
		name = "dritha";
		relationship = "paternalaunt";

	    personList = relationshipManager.getPeopleWithRelationship(name,
				relationship, familyTree.getHead());

	    Person satya = new Person();
	    satya.setName("satya");
		assertNotNull(personList);
		assertEquals(satya, personList.toArray()[0]);
		
		name = "savya";
		relationship = "maternaluncle";

	    personList = relationshipManager.getPeopleWithRelationship(name,
				relationship, familyTree.getHead());

		assertNotNull(personList);
		
		Person ish = new Person();
		ish.setName("ish");
		
		for (Person p : personList) {
			if (p.getName().equalsIgnoreCase("vich"))
				assertEquals(vich, p);
			else if (p.getName().equalsIgnoreCase("chit"))
				assertEquals(chit, p);
			else
				assertEquals(ish, p);
		}
	}

	@Test
	public void testAddChildRelationship() throws Exception {
		createFamilyTree();

		Person lavnya = new Person();
		lavnya.setName("lavyna");

		Person vanya = new Person();
		vanya.setName("Vanya");
		vanya.setGender(Gender.FEMALE);
		vanya.setMother(lavnya);

		familyTree.addChild(vanya);

		Set<Person> personList = relationshipManager.getPeopleWithRelationship("jnki",
				"GrandDaughter", familyTree.getHead());

		assertNotNull(personList);

		for (Person p : personList)
		{
			assertEquals(vanya, p);
		}
	}

	@Test
	public void testMaxGirlChildMother() throws Exception
	{
		createFamilyTree();
		
		Set<Person> person = personManager.getMaxGirlChildMother(familyTree.getHead());
		assertNotNull(person);
	
		Person jaya = new Person();
		jaya.setName("jaya");
		
		Person drini = new Person();
		drini.setName("drini");
		drini.setMother(jaya);
		drini.setGender(Gender.FEMALE);
		
		familyTree.addChild(drini);
		
		person = personManager.getMaxGirlChildMother(familyTree.getHead());
		assertNotNull(person);
		assertEquals(jaya, person.toArray()[0]);
		
	}
	
	@Test
	public void testGetRelationship() throws Exception
	{
		createFamilyTree();
		
		String name1 = "Kriya";
		String name2 = "Savya";
		
		String relation = relationshipManager.getRelationship(name1, name2, familyTree.getHead());
		assertNotNull(relation);
		assertEquals("FATHER", relation);
		
		name1 = "Kriya";
		name2 = "Krpi";
		
		relation = relationshipManager.getRelationship(name1, name2, familyTree.getHead());
		assertNotNull(relation);
		assertEquals("MOTHER", relation);
		
		name1 = "Savya";
		name2 = "Kriya";
		
		relation = relationshipManager.getRelationship(name1, name2, familyTree.getHead());
		assertNotNull(relation);
		assertEquals("SON", relation);
		
		name1 = "dritha";
		name2 = "driya";
		
		relation = relationshipManager.getRelationship(name1, name2, familyTree.getHead());
		assertNotNull(relation);
		assertEquals("DAUGHTER", relation);
		
		name1 = "ish";
		name2 = "chit";
		
		relation = relationshipManager.getRelationship(name1, name2, familyTree.getHead());
		assertNotNull(relation);
		assertEquals("BROTHERS", relation);
		
		name1 = "vich";
		name2 = "satya";
		
		relation = relationshipManager.getRelationship(name1, name2, familyTree.getHead());
		assertNotNull(relation);
		assertEquals("SISTERS", relation);
		
		name1 = "ambi";
		name2 = "driya";
		
		relation = relationshipManager.getRelationship(name1, name2, familyTree.getHead());
		assertNotNull(relation);
		assertEquals("GRANDDAUGHTER", relation);
		
		name1 = "dritha";
		name2 = "ish";
		
		relation = relationshipManager.getRelationship(name1, name2, familyTree.getHead());
		assertNotNull(relation);
		assertEquals("PATERNALUNCLE", relation);
		
		name1 = "dritha";
		name2 = "satya";
		
		relation = relationshipManager.getRelationship(name1, name2, familyTree.getHead());
		assertNotNull(relation);
		assertEquals("PATERNALAUNT", relation);
		
		name1 = "satvy";
		name2 = "vich";
		
		relation = relationshipManager.getRelationship(name1, name2, familyTree.getHead());
		assertNotNull(relation);
		assertEquals("MATERNALUNCLE", relation);
		
		name1 = "kriya";
		name2 = "saayan";
		
		relation = relationshipManager.getRelationship(name1, name2, familyTree.getHead());
		assertNotNull(relation);
		assertEquals("PATERNALUNCLE", relation);
		
		
		name1 = "satvy";
		name2 = "lika";
		
		relation = relationshipManager.getRelationship(name1, name2, familyTree.getHead());
		assertNotNull(relation);
		assertEquals("MATERNALAUNT", relation);

		name1 = "chit";
		name2 = "lika";
		
		relation = relationshipManager.getRelationship(name1, name2, familyTree.getHead());
		assertNotNull(relation);
		assertEquals("SISTERINLAW", relation);
		
		name1 = "lika";
		name2 = "satya";
		
		relation = relationshipManager.getRelationship(name1, name2, familyTree.getHead());
		assertNotNull(relation);
		assertEquals("SISTERINLAW", relation);		
		
		name1 = "jaya";
		name2 = "vritha";
		
		relation = relationshipManager.getRelationship(name1, name2, familyTree.getHead());
		assertNotNull(relation);
		assertEquals("BROTHERINLAW", relation);
		
		name1 = "chit";
		name2 = "vyan";
		
		relation = relationshipManager.getRelationship(name1, name2, familyTree.getHead());
		assertNotNull(relation);
		assertEquals("BROTHERINLAW", relation);
		
		name1 = "Dritha";
		name2 = "satvy";
		
		relation = relationshipManager.getRelationship(name1, name2, familyTree.getHead());
		assertNotNull(relation);
		assertEquals("COUSINS", relation);
		
	}
	
	@Test
	public void testGetGenerationGap() throws Exception
	{
		createFamilyTree();
		
		String name1 = "lavyna";
		String name2 = "saayan";
		
		Person person = personManager.getGenerationGap(name1, name2, familyTree.getHead());
		
		assertNotNull(person);
		
		assertEquals(name2, person.getName());
		
		Person queenAnga = new Person();
		queenAnga.setName("Queen Anga");
		
		Person gasha = new Person();
		gasha.setName("gasha");
		gasha.setMother(queenAnga);
		gasha.setGender(Gender.FEMALE);
		
		familyTree.addChild(gasha);
		
		person = personManager.getGenerationGap("gasha", name2, familyTree.getHead());
		
		assertNotNull(person);
		assertEquals("gasha", person.getName());
		
	}
	
	@Test
	public void testCreateFamilyTree() throws Exception {
		createFamilyTree();

	}

	private void createFamilyTree() {
		Person king = new Person();
		king.setName("King Shan");
		king.setAge(370);
		king.setGender(Gender.MALE);

		familyTree.addKing(king);

		Person queen = new Person();
		queen.setName("Queen Anga");
		queen.setAge(365);
		queen.setGender(Gender.FEMALE);
		queen.setSpouse(king);

		familyTree.addQueen(queen);

		Person ish = new Person();
		ish.setName("Ish");
		ish.setFather(king);
		ish.setMother(queen);
		ish.setGender(Gender.MALE);

		familyTree.addChild(ish);

		Person chit = new Person();
		chit.setName("Chit");
		chit.setFather(king);
		chit.setMother(queen);
		chit.setGender(Gender.MALE);

		familyTree.addChild(chit);

		Person vich = new Person();
		vich.setName("Vich");
		vich.setFather(king);
		vich.setMother(queen);
		vich.setGender(Gender.MALE);

		familyTree.addChild(vich);

		Person satya = new Person();
		satya.setName("Satya");
		satya.setFather(king);
		satya.setMother(queen);
		satya.setGender(Gender.FEMALE);

		familyTree.addChild(satya);

		Person ambi = new Person();
		ambi.setName("Ambi");
		ambi.setSpouse(chit);
		ambi.setGender(Gender.FEMALE);

		familyTree.addSpouse(ambi);

		Person lika = new Person();
		lika.setName("Lika");
		lika.setSpouse(vich);
		lika.setGender(Gender.FEMALE);

		familyTree.addSpouse(lika);

		Person vyan = new Person();
		vyan.setName("Vyan");
		vyan.setSpouse(satya);
		vyan.setGender(Gender.MALE);

		familyTree.addSpouse(vyan);

		Person vritha = new Person();
		vritha.setName("Vritha");
		vritha.setMother(ambi);
		vritha.setFather(chit);
		vritha.setGender(Gender.MALE);

		familyTree.addChild(vritha);

		Person dritha = new Person();
		dritha.setName("Dritha");
		dritha.setMother(ambi);
		dritha.setFather(chit);
		dritha.setGender(Gender.MALE);

		familyTree.addChild(dritha);

		Person jaya = new Person();
		jaya.setName("Jaya");
		jaya.setGender(Gender.FEMALE);
		jaya.setSpouse(dritha);

		familyTree.addSpouse(jaya);

		Person jata = new Person();
		jata.setName("jata");
		jata.setMother(jaya);
		jata.setFather(dritha);
		jata.setGender(Gender.MALE);
		
		familyTree.addChild(jata);

		Person driya = new Person();
		driya.setName("driya");
		driya.setMother(jaya);
		driya.setFather(dritha);
		driya.setGender(Gender.FEMALE);

		familyTree.addChild(driya);

		Person manu = new Person();
		manu.setName("manu");
		manu.setGender(Gender.MALE);
		manu.setSpouse(driya);

		familyTree.addSpouse(manu);

		Person vila = new Person();
		vila.setName("vila");
		vila.setMother(lika);
		vila.setFather(vich);
		vila.setGender(Gender.MALE);

		familyTree.addChild(vila);

		Person jnki = new Person();
		jnki.setName("jnki");
		jnki.setGender(Gender.MALE);
		jnki.setSpouse(vila);

		familyTree.addSpouse(jnki);

		Person chika = new Person();
		chika.setName("chika");
		chika.setMother(lika);
		chika.setFather(vich);
		chika.setGender(Gender.FEMALE);

		familyTree.addChild(chika);

		Person kpila = new Person();
		kpila.setName("kpila");
		kpila.setGender(Gender.MALE);
		kpila.setSpouse(chika);

		familyTree.addSpouse(kpila);

		Person lavyna = new Person();
		lavyna.setName("lavyna");
		lavyna.setGender(Gender.FEMALE);
		lavyna.setMother(jnki);
		lavyna.setFather(vila);

		familyTree.addChild(lavyna);

		Person gru = new Person();
		gru.setName("gru");
		gru.setGender(Gender.MALE);
		gru.setSpouse(lavyna);

		familyTree.addSpouse(gru);

		Person satvy = new Person();
		satvy.setName("satvy");
		satvy.setMother(satya);
		satvy.setFather(vyan);
		satvy.setGender(Gender.MALE);

		familyTree.addChild(satvy);

		Person asva = new Person();
		asva.setName("asva");
		asva.setGender(Gender.MALE);
		asva.setSpouse(satvy);

		familyTree.addSpouse(asva);

		Person savya = new Person();
		savya.setName("savya");
		savya.setMother(satya);
		savya.setFather(vyan);
		savya.setGender(Gender.MALE);

		familyTree.addChild(savya);

		Person krpi = new Person();
		krpi.setName("krpi");
		krpi.setGender(Gender.FEMALE);
		krpi.setSpouse(savya);

		familyTree.addSpouse(krpi);

		Person kriya = new Person();
		kriya.setName("kriya");
		kriya.setGender(Gender.MALE);
		kriya.setMother(krpi);
		kriya.setFather(savya);

		familyTree.addChild(kriya);

		Person saayan = new Person();
		saayan.setName("saayan");
		saayan.setGender(Gender.MALE);
		saayan.setMother(satya);
		saayan.setFather(vyan);

		familyTree.addChild(saayan);

		Person mina = new Person();
		mina.setName("mina");
		mina.setGender(Gender.FEMALE);
		mina.setSpouse(saayan);

		familyTree.addSpouse(mina);

		Person misa = new Person();
		misa.setName("misa");
		misa.setGender(Gender.MALE);
		misa.setMother(mina);
		misa.setFather(saayan);

		familyTree.addChild(misa);
	}

}