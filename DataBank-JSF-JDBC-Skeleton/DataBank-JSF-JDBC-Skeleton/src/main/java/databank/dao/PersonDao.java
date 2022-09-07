/******************************************************
 * File: PersonDao.java Course materials (22W) CST8277
 *
 * @author Teddy Yap
 * @author Shariar (Shawn) Emami
 * @author (original) Mike Norman
 */
package databank.dao;

import java.util.List;

import databank.model.PersonPojo;

/**
 * Description: API for the database C-R-U-D operations
 */
public interface PersonDao {

	// C
	public PersonPojo createPerson( PersonPojo person);

	// R
	public PersonPojo readPersonById( int personId);

	public List< PersonPojo> readAllPeople();

	// U
	public void updatePerson( PersonPojo person);

	// D
	public void deletePersonById( int personId);

}