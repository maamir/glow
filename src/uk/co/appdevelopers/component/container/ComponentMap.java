package uk.co.appdevelopers.component.container;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.Map;

/**
 * @author aamirm
 *
 */
public class ComponentMap<E> implements Serializable, Cloneable {

	/** The Constant serialVersionUID **/
	private static final long serialVersionUID = 232364272246603000L;
	
	private Component<E> first = new Component<E>(null);
	private transient int size = 0; 
	
	public void add(E element) {
		first = new Component<E>(element);
		first.next.put("1", first.component);
		size++;
	}
	
	public Component<E> getFirst() {
		return first;
	}

	private static class Component<E> {
		private E component;
		private Map<Object, E> next = new Hashtable<Object, E>();
	
		public Component(E component) {
			this.component = component;
		}
	}
}
