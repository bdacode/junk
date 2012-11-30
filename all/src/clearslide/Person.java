package clearslide;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author mpermana to write the data to mysql, use hibernate, annotate them as
 *         follows, need to create mapping for the children, i ran out of time
 */
public class Person {

	public Person() {

	}

	private Long id;

	public Long getId() {
		return id;
	}

	private void setId(Long id) {
		this.id = id;
	}

	ArrayList<Person> children = new ArrayList<Person>();
	Person mom;
	Person dad;
	Date birthdate;

	public ArrayList<Person> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<Person> children) {
		this.children = children;
	}

	public Person getMom() {
		return mom;
	}

	public void setMom(Person mom) {
		this.mom = mom;
	}

	public Person getDad() {
		return dad;
	}

	public void setDad(Person dad) {
		this.dad = dad;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

}
