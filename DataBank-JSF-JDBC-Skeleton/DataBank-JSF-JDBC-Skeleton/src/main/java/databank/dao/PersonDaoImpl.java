/******************************************************
 * File: PersonDaoImpl.java Course materials (22W) CST8277
 *
 * @author Teddy Yap
 * @author Shariar (Shawn) Emami
 * @author (original) Mike Norman
 */
package databank.dao;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.faces.context.ExternalContext;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

import databank.model.PersonPojo;

@SuppressWarnings("unused")
/**
 * Description: Implements the C-R-U-D API for the database
 */
//TODO don't forget this object is a managed bean with a application scope
public class PersonDaoImpl implements PersonDao, Serializable {
	/** explicitly set serialVersionUID */
	private static final long serialVersionUID = 1L;

	private static final String DATABANK_DS_JNDI = "java:app/jdbc/databank";
	private static final String READ_ALL = "select * from person";
	private static final String READ_PERSON_BY_ID = "select * from person where id = ?";
	private static final String INSERT_PERSON = "insert into person (last_name,first_name,email,phone,city,created) values(?,?,?,?,?,?)";
	private static final String UPDATE_PERSON_ALL_FIELDS = "update person set last_name = ?, first_name = ?, email = ?, phone = ?, city = ? where id = ?";
	private static final String DELETE_PERSON_BY_ID = "delete from person where id = ?";

	@Inject
	protected ExternalContext externalContext;

	private void logMsg( String msg) {
		( (ServletContext) externalContext.getContext()).log( msg);
	}

	@Resource( lookup = DATABANK_DS_JNDI)
	protected DataSource databankDS;

	protected Connection conn;
	protected PreparedStatement readAllPstmt;
	protected PreparedStatement readByIdPstmt;
	protected PreparedStatement createPstmt;
	protected PreparedStatement updatePstmt;
	protected PreparedStatement deleteByIdPstmt;

	@PostConstruct
	protected void buildConnectionAndStatements() {
		try {
			logMsg( "building connection and stmts");
			conn = databankDS.getConnection();
			readAllPstmt = conn.prepareStatement( READ_ALL);
			readByIdPstmt = conn.prepareStatement( READ_PERSON_BY_ID);
			createPstmt = conn.prepareStatement( INSERT_PERSON, RETURN_GENERATED_KEYS);
			//TODO initialize other PreparedStatements
			updatePstmt = conn.prepareStatement( UPDATE_PERSON_ALL_FIELDS, RETURN_GENERATED_KEYS);
			deleteByIdPstmt = conn.prepareStatement( DELETE_PERSON_BY_ID, RETURN_GENERATED_KEYS);
			
		} catch ( Exception e) {
			logMsg( "something went wrong getting connection from database: " + e.getLocalizedMessage());
		}
	}

	@PreDestroy
	protected void closeConnectionAndStatements() {
		try {
			logMsg( "closing stmts and connection");
			readAllPstmt.close();
			readByIdPstmt.close();
			createPstmt.close();
			//TODO close other PreparedStatements
			updatePstmt.close();
			deleteByIdPstmt.close();
			conn.close();
		} catch ( Exception e) {
			logMsg( "something went wrong closing stmts or connection: " + e.getLocalizedMessage());
		}
	}

	@Override
	public List< PersonPojo> readAllPeople() {
		logMsg( "reading all People");
		List< PersonPojo> people = new ArrayList<>();
		try ( ResultSet rs = readAllPstmt.executeQuery();) {

			while ( rs.next()) {
				PersonPojo newPerson = new PersonPojo();
				newPerson.setId( rs.getInt( "id"));
				newPerson.setFirstName( rs.getString( "first_name"));
				//TODO complete the person initialization
				newPerson.setLastName( rs.getString( "last_name"));
				newPerson.setEmail( rs.getString( "email"));
				newPerson.setPhoneNumber( rs.getString( "phone"));
				newPerson.setCity( rs.getString( "city"));
				newPerson.setCreated( rs.getTimestamp( "created").toLocalDateTime());			
				people.add( newPerson);
			}
		} catch ( SQLException e) {
			logMsg( "something went wrong accessing database: " + e.getLocalizedMessage());
		}
		return people;
	}

	@Override
	public PersonPojo createPerson( PersonPojo person) {
		logMsg( "creating an person");
		//TODO complete the insertion of a new person
		//TODO use try-and-catch statement
		try {
			createPstmt.setString( 1, person.getLastName());
			createPstmt.setString( 2, person.getFirstName());
			createPstmt.setString( 3, person.getEmail());
			createPstmt.setString( 4, person.getPhoneNumber());
			createPstmt.setString( 5, person.getCity());
			createPstmt.setTimestamp( 6, Timestamp.valueOf(person.getCreated()));
			
			createPstmt.execute();
			
			try ( ResultSet generatedKeys = createPstmt.getGeneratedKeys()) {
				if ( generatedKeys.next()) { //if a key is returned
					int id = generatedKeys.getInt( 1); //get the key
					//set the key to the person and print it
					person.setId( id);
				} else {
					logMsg( "could not retrieve generated Pk");
				}
			}
		} catch ( SQLException e) {
			logMsg( "something went wrong accessing database: " + e.getLocalizedMessage());
		}
		return person;
	}

	@Override
	public PersonPojo readPersonById( int personId) {
		logMsg( "read a specific person");
		//TODO complete the retrieval of a specific person by its id
		//TODO use try-and-catch statementtry {

		try {
			readByIdPstmt.setInt( 1, personId);
			ResultSet rs = readByIdPstmt.executeQuery();
		if ( rs.next()) {
			PersonPojo editPerson = new PersonPojo();
			editPerson.setId( rs.getInt( "id"));
			editPerson.setFirstName( rs.getString( "first_name")); 
			editPerson.setLastName( rs.getString( "last_name"));
			editPerson.setEmail( rs.getString( "email"));
			editPerson.setPhoneNumber( rs.getString( "phone"));
			editPerson.setCity( rs.getString( "city"));
			editPerson.setCreated( rs.getTimestamp( "created").toLocalDateTime());
			return editPerson;
		}
	} catch ( SQLException e) {
		logMsg( "something went wrong accessing database: " + e.getLocalizedMessage());
	}
	return null;
}

	@Override
	public void updatePerson( PersonPojo person) {
		logMsg( "updating a specific person");
		//TODO complete the update of a specific person
		//TODO use try-and-catch statement
		try {

			updatePstmt.setString( 1, person.getLastName());
			updatePstmt.setString( 2, person.getFirstName());
			updatePstmt.setString( 3, person.getEmail());
			updatePstmt.setString( 4, person.getPhoneNumber());
			updatePstmt.setString( 5, person.getCity());
			updatePstmt.setInt( 6, person.getId());
			
			updatePstmt.execute();
		} catch ( SQLException e) {
			logMsg( "something went wrong accessing database: " + e.getLocalizedMessage());
		}
	}

	@Override
	public void deletePersonById( int personId) {
		logMsg( "deleting a specific person");
		//TODO complete the deletion of a specific person
		//TODO use try-and-catch statement
		try {
			deleteByIdPstmt.setInt( 1, personId);
			
			deleteByIdPstmt.execute();
		} catch ( SQLException e) {
			logMsg( "something went wrong accessing database: " + e.getLocalizedMessage());
		}
	}

}
	