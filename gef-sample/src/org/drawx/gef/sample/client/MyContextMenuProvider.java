package org.drawx.gef.sample.client;

import org.drawx.gef.ui.menu.IMenuManager;
import org.drawx.gef.ui.menu.Menu;
import org.eclipse.gef.ContextMenuProvider;
import org.eclipse.gef.EditPartViewer;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.ui.actions.ActionFactory;


public class MyContextMenuProvider extends ContextMenuProvider{

	public MyContextMenuProvider(EditPartViewer viewer,ActionRegistry actionRegistry) {
		super(viewer,actionRegistry);
	}

	@Override
	public void buildContextMenu(IMenuManager menu) {
		menu.add(this.getActionRegistry().getAction(ActionFactory.UNDO.getId()));
		menu.add(this.getActionRegistry().getAction(ActionFactory.REDO.getId()));
		menu.addSeprator();
		menu.add(this.getActionRegistry().getAction(ActionFactory.SELECT_ALL.getId()));
		menu.add(this.getActionRegistry().getAction(ActionFactory.DELETE.getId()));
		menu.addSeprator();
		Menu m = menu.addMenu("Zoom");
		m.add(this.getActionRegistry().getAction(GEFActionConstants.ZOOM_IN));
		m.add(this.getActionRegistry().getAction(GEFActionConstants.ZOOM_OUT));

		
		m = menu.addMenu("Match");
		m.add(this.getActionRegistry().getAction(GEFActionConstants.MATCH_HEIGHT));
		m.add(this.getActionRegistry().getAction(GEFActionConstants.MATCH_WIDTH));
		
		m = menu.addMenu("Align");
		m.add(this.getActionRegistry().getAction(GEFActionConstants.ALIGN_BOTTOM));
		m.add(this.getActionRegistry().getAction(GEFActionConstants.ALIGN_LEFT));
		m.add(this.getActionRegistry().getAction(GEFActionConstants.ALIGN_TOP));
		m.add(this.getActionRegistry().getAction(GEFActionConstants.ALIGN_RIGHT));
		m.add(this.getActionRegistry().getAction(GEFActionConstants.ALIGN_CENTER));
		m.add(this.getActionRegistry().getAction(GEFActionConstants.ALIGN_MIDDLE));
		
		m = menu.addMenu("Options");
		m.add(this.getActionRegistry().getAction(GEFActionConstants.TOGGLE_GRID_VISIBILITY));
		
		menu.addSeprator();
		menu.add(this.getActionRegistry().getAction(ActionFactory.SAVE.getId()));
//		menu.addMenuItem(new TestAction());
//		menu.addMenuItem(new TestAction());
//		menu.addMenuItem(new TestAction());
//		Menu m = menu.addMenu("sub");
//		m.addMenuItem(new TestAction());
//		m.addSeprator();
//		m.addMenuItem(new TestAction());
		
		
	}

}
