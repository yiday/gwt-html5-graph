下面这个整合案例来自http://code.google.com/p/gwt-html5-graph/

实际的运行案例：

Demo1(GEF Sample): http://euml.trufun.net/drawx/gef-sample/Gef_sample.html



源码：

1、在gwt.xml中加入对GEF的依赖：



&lt;inherits name='GEF' /&gt;



2、按照GEF API，开发相应的editparts, figures, commands, editpolicies,actions,context menus,以及图形数据的序列化

3、整合GEF和GWT。

3.1)继承drawx提供的DiagramEditorWithPalette，如下：




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




> public MyDiagramEditor(boolean b) {


> super(b);


> }




> @Override


> public EditPartFactory getEditPartFactory() {


> return new org.drawx.gef.sample.client.tool.example.editparts.MyEditPartFactory();


> }




> @Override


> public Object getContents() {


> return new CanvasModel();


> }




> @Override


> public void initPalette(ToolPalette palette) {


> palette.addCreationTool("Create Node", new Image(Images.INSTANCE.newModel().getURL()), new CreationFactory() {




> public Object getNewObject() {


> return new OrangeModel();


> }




> public Object getObjectType() {


> return OrangeModel.class;


> }


> });


> palette.addConnectionTool("Create Connection", new Image(Images.INSTANCE.newConnection().getURL()), new CreationFactory() {




> public Object getNewObject() {


> return new MyConnectionModel();


> }




> public Object getObjectType() {


> return MyConnectionModel.class;


> }


> });


> }




> @Override


> protected void doSave() {





> }




> @Override


> protected void createAppActions() {





> }




> @Override


> protected MenuManager getContextMenu() {


> return new MyContextMenuProvider(this.getGraphicalViewer(), this


> .getActionRegistry());


> }




}



3.2）将diagram editor加入到WEB页面上


private void createTab1(TabLayoutPanel p) {


> DiagramEditorWithPalette editor = new MyDiagramEditor(true);


> p.add(editor.getWidget(), "Fill Canvas");


> }