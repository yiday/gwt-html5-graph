package org.drawx.gef.sample.client;

import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.swt.widgets.internal.menu.IMenuManager;
import org.eclipse.swt.widgets.internal.menu.Menu;
import org.eclipse.ui.actions.ActionFactory;


public class MyContextMenuProvider extends ContextMenuProvider{

	public MyContextMenuProvider(EditPartViewer viewer,ActionRegistry actionRegistry) {
		super(viewer,actionRegistry);
	}

	@Override
	public void buildContextMenu(IMenuManager menu) {
		menu.addMenuItem(this.getActionRegistry().getAction(ActionFactory.UNDO.getId()));
		menu.addMenuItem(this.getActionRegistry().getAction(ActionFactory.REDO.getId()));
		menu.addSeprator();
		menu.addMenuItem(this.getActionRegistry().getAction(ActionFactory.SELECT_ALL.getId()));
		menu.addMenuItem(this.getActionRegistry().getAction(ActionFactory.DELETE.getId()));
		menu.addSeprator();
		Menu m = menu.addMenu("Zoom");
		m.addMenuItem(this.getActionRegistry().getAction(GEFActionConstants.ZOOM_IN));
		m.addMenuItem(this.getActionRegistry().getAction(GEFActionConstants.ZOOM_OUT));

		
		m = menu.addMenu("Match");
		m.addMenuItem(this.getActionRegistry().getAction(GEFActionConstants.MATCH_HEIGHT));
		m.addMenuItem(this.getActionRegistry().getAction(GEFActionConstants.MATCH_WIDTH));
		
		m = menu.addMenu("Align");
		m.addMenuItem(this.getActionRegistry().getAction(GEFActionConstants.ALIGN_BOTTOM));
		m.addMenuItem(this.getActionRegistry().getAction(GEFActionConstants.ALIGN_LEFT));
		m.addMenuItem(this.getActionRegistry().getAction(GEFActionConstants.ALIGN_TOP));
		m.addMenuItem(this.getActionRegistry().getAction(GEFActionConstants.ALIGN_RIGHT));
		m.addMenuItem(this.getActionRegistry().getAction(GEFActionConstants.ALIGN_CENTER));
		m.addMenuItem(this.getActionRegistry().getAction(GEFActionConstants.ALIGN_MIDDLE));
		
		m = menu.addMenu("Options");
		m.addMenuItem(this.getActionRegistry().getAction(GEFActionConstants.TOGGLE_GRID_VISIBILITY));
		
		menu.addSeprator();
		menu.addMenuItem(this.getActionRegistry().getAction(ActionFactory.SAVE.getId()));
//		menu.addMenuItem(new TestAction());
//		menu.addMenuItem(new TestAction());
//		menu.addMenuItem(new TestAction());
//		Menu m = menu.addMenu("sub");
//		m.addMenuItem(new TestAction());
//		m.addSeprator();
//		m.addMenuItem(new TestAction());
		
		
	}

}
