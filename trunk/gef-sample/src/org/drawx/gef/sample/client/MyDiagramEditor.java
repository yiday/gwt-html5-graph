package org.drawx.gef.sample.client;

import org.drawx.gef.sample.client.tool.example.model.CanvasModel;
import org.drawx.gef.sample.client.tool.example.model.MyConnectionModel;
import org.drawx.gef.sample.client.tool.example.model.OrangeModel;
import org.drawx.gef.ui.editor.DiagramEditorWithPalette;
import org.drawx.gef.ui.editor.ToolPalette;
import org.eclipse.gef.EditPartFactory;
import org.eclipse.gef.requests.CreationFactory;
import org.eclipse.swt.widgets.internal.menu.MenuManager;

import com.google.gwt.user.client.ui.Image;

public class MyDiagramEditor extends DiagramEditorWithPalette{

	public MyDiagramEditor(boolean b) {
		super(b);
	}

	@Override
	public EditPartFactory getEditPartFactory() {
		return new org.drawx.gef.sample.client.tool.example.editparts.MyEditPartFactory();
	}

	@Override
	public Object getContents() {
		return new CanvasModel();
	}

	@Override
	public void initPalette(ToolPalette palette) {
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
		
	}

	@Override
	protected MenuManager getContextMenu() {
		return new MyContextMenuProvider(this.getGraphicalViewer(), this
				.getActionRegistry());
	}

}
