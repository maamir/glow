package uk.co.slc.graph.impl;

import java.io.Serializable;

/**
 * @author aamirm
 *
 */
public interface Graph<N, E> extends Serializable, Cloneable {

	void add(N n);
	E get(N n);
}
