package com.neet.jobsite.model;

public enum ObjectState {
	/*Unchanged State
	 * */
	Unchanged,
	
	/* Modified state could be for unsaved changes
	 * For object needed to be insert for the first time would have ID as null
	 * For object already added to database shall have ID as value.
	 * */
	Modified,
	
	/* When the object is deleted this state would be used to determine that object is deleted
	 * */
	Deleted
}
