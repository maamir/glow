package uk.co.appdevelopers.component.pane;

/**
 * File: Flow.java
 * 
 * @author aamirm
 *
 */
public interface Flow<T> {

	/**
	 * Return next question within flow.
	 * 
	 * @return
	 */
	public T getNextQuestion();
	
	/**
	 * Return previous question within flow.
	 * 
	 * @return
	 */
	public T getPreviousQuestion();
}
