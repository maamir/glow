package uk.co.appdevelopers.component.model;

import java.io.Serializable;

/**
 * @author aamirm
 *
 */
public class ComponentModel implements Serializable {
		
	/** The Constant serialVersionUID**/
	private static final long serialVersionUID = 5316540449086892804L;
	
	public enum State {
		NEW("new"), ADD("add"), EDIT("edit"), SUBMIT("submit");
		
		String value;
		
		private State(String value) {
			this.value = value;
		}
		
		public String toString() {
			return value;
		}
	}

	private State state;
	private Object value;
	
	public ComponentModel() {
		this.state = State.NEW; // look into it, why new state fist time.
		this.value = "NONE";
	}
	
	public ComponentModel(State state, Object value) {
		this.state = state;
		this.value = value;
	}
	
	public boolean isSubmitted() {
		return !State.SUBMIT.equals(state);
	}
	
	/**
	 * @return the state
	 */
	public State getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(State state) {
		this.state = state;
	}
	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}
	
	public boolean hasValue() {
		return !"NONE".equals(value);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComponentModel other = (ComponentModel) obj;
		if (state != other.state)
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}
}
