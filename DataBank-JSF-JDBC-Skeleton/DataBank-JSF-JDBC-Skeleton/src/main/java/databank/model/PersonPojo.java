/******************************************************
 * File: PersonPojo.java Course materials (22W) CST8277
 *
 * @author Teddy Yap
 * @author Shariar (Shawn) Emami
 * @author (original) Mike Norman
 */
package databank.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import javax.faces.view.ViewScoped;

/**
 *
 * Description: model for the Person object <br>
 * a little read about @ViewScoped <br>
 * https://stackoverflow.com/a/6026009/764951
 */
@ViewScoped
public class PersonPojo implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String lastName;
	private String firstName;
	private String email;
	private String phoneNumber;
	private String city;
	private LocalDateTime created;

	public PersonPojo() {
		super();
	}

	public int getId() {
		return id;
	}

	/**
	 * @param id new value for id
	 */
	public void setId( int id) {
		this.id = id;
	}

	/**
	 * @return the value for firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName new value for firstName
	 */
	public void setFirstName( String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the value for lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName new value for lastName
	 */
	public void setLastName( String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail( String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber( String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getCity() {
		return city;
	}
	
	public void setCity( String city ) {
		this.city = city;
	}
	
	public void setCreated( LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getCreated() {
		return created;
	}
	
	// Use getter's for member variables
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		// only include member variables that really contribute to an object's identity
		// i.e. if variables like version/updated/name/etc. change throughout an object's lifecycle,
		// they shouldn't be part of the hashCode calculation
		return prime * result + Objects.hash(getId());
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}

		/* enhanced instanceof - yeah!
		 * As of JDK 14, no need for additional 'silly' cast:
		    if (animal instanceof Cat) {
		        Cat cat = (Cat)animal;
		        cat.meow();
                // other class Cat operations ...
            }
         * Technically, 'otherPersonPojo' is a <i>pattern</i> that becomes an in-scope variable binding.
         * Note: need to watch out just-in-case there is already a 'otherPersonPojo' variable in-scope!
		 */
		if (obj instanceof PersonPojo otherPersonPojo) {
			// see comment (above) in hashCode(): compare using only member variables that are
			// truely part of an object's identity
			return Objects.equals(this.getId(), otherPersonPojo.getId());
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append( "Person [id=").append( getId()).append( ", ");
		if ( getFirstName() != null) {
			builder.append( "firstName=").append( getFirstName()).append( ", ");
		}
		if ( getLastName() != null) {
			builder.append( "lastName=").append( getLastName()).append( ", ");
		}
		if ( getPhoneNumber() != null) {
			builder.append( "phoneNumber=").append( getPhoneNumber()).append( ", ");
		}
		//TODO add code to append the city
		if ( getCity() != null) {
			builder.append( "city=").append( getCity()).append( ", ");
		}
		if ( getEmail() != null) {
			builder.append( "email=").append( getEmail()).append( ", ");
		}
		builder.append( "]");
		return builder.toString();
	}

}