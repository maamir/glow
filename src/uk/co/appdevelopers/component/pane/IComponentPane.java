package uk.co.appdevelopers.component.pane;

import uk.co.appdevelopers.component.pane.listener.ComponentPaneActionListener;

/**
 * @author aamirm
 *
 */
public interface IComponentPane {

	public boolean isRender();
	public Object getSubmittedValue();
	public String getState();
	public ComponentPaneActionListener getActionListener();
}
