package uk.co.slc.component.pane;

import uk.co.slc.component.pane.listener.ComponentPaneActionListener;

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
