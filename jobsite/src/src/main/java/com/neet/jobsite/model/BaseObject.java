package com.neet.jobsite.model;

public class BaseObject {

	protected Integer  Id;

	
	public void setId(Integer  id) {
		Id = id;
	}

	public Integer  getId() {
		return Id;
	}
	
	public ObjectState getState() {
		return State;
	}

	public void setState(ObjectState state) {
		State = state;
	}

	private ObjectState State;
}
