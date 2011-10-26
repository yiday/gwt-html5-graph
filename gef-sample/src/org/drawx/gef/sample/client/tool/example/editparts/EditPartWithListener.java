package org.drawx.gef.sample.client.tool.example.editparts;


import java.beans.PropertyChangeListener;

import org.drawx.gef.sample.client.tool.example.model.AbstractModel;
import org.eclipse.gef.editparts.AbstractGraphicalEditPart;



// ���f���v���p�e�B�̕ύX�ʒm���󂯂鏈�����s���N���X
abstract public class EditPartWithListener
	extends AbstractGraphicalEditPart
	implements PropertyChangeListener {

	/* (�� Javadoc)
	 * @see org.eclipse.gef.EditPart#activate()
	 */
	public void activate() {
		getAbstractModel().addPropertyChangeListener(this);
		super.activate();
	}

	/* (�� Javadoc)
	 * @see org.eclipse.gef.EditPart#deactivate()
	 */
	public void deactivate() {
		getAbstractModel().removePropertyChangeListener(this);
		super.deactivate();
	}

	public AbstractModel getAbstractModel(){
		return (AbstractModel)getModel();
	}
}
