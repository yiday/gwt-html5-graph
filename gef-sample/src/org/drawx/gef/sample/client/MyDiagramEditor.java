package org.drawx.gef.sample.client;

import org.drawx.gef.sample.client.tool.example.model.CanvasModel;
import org.drawx.gef.sample.client.tool.example.model.MyConnectionModel;
import org.drawx.gef.sample.client.tool.example.model.OrangeModel;
import org.drawx.gef.ui.editor.DiagramEditorWithPalette;
import org.drawx.gef.ui.editor.ToolPalette;
import org.drawx.gef.ui.menu.MenuManager;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.gef.ui.actions.DeleteAction;
import org.eclipse.gef.ui.actions.SaveAction;
import org.eclipse.gef.ui.actions.SelectAllAction;
import org.eclipse.jface.action.IAction;
import org.eclipse.ui.IWorkbenchPart;

import com.google.gwt.user.client.ui.Image;

public class MyDiagramEditor extends DiagramEditorWithPalette{

//	public MyDiagramEditor() {
//		super(null);
//	}

	@Override
	public EditPartFactory getEditPartFactory() {
		return new org.drawx.gef.sample.client.tool.example.editparts.MyEditPartFactory();
	}

	@Override
	public Object getContents() {
		return new CanvasModel();
	}

	@Override
	public void initPalette(ToolPalette palette1) {
		ToolPalette.Group palette = palette1.addGroup("Tools",true);
		palette.addCreationTool("Create Node", new Image(Images.INSTANCE.newModel().getURL()), new CreationFactory() {

			public Object getNewObject() {
				return new OrangeModel();
			}

			public Object getObjectType() {
				return OrangeModel.class;
			}
		});
		palette.addConnectionTool("Create Connection", new Image(Images.INSTANCE.newConnection().getURL()), new CreationFactory() {

			public Object getNewObject() {
				return new MyConnectionModel();
			}

			public Object getObjectType() {
				return MyConnectionModel.class;
			}
		});
	}

	@Override
	protected void doSave() {
		
	}

	@Override
	protected void createAppActions() {

		IAction action = new SelectAllAction(this);
		this.getActionRegistry().registerAction(action);

		action = new DeleteAction((IWorkbenchPart) this);
		this.getActionRegistry().registerAction(action);
		getSelectionActions().add(action.getId());

		action = new SaveAction(this);
		this.getActionRegistry().registerAction(action);
		getPropertyActions().add(action.getId());
	}

	@Override
	protected MenuManager getContextMenu() {
		return new MyContextMenuProvider(this.getGraphicalViewer(), this
				.getActionRegistry());
	}

}
