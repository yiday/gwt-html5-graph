package org.drawx.gef.sample.client.tool.example.model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;


/*
 * ���f���̃v���p�e�B�ύX�ʒm���s���@�\���`�����N���X
 */
public abstract class AbstractModel {

	protected PropertyChangeSupport listeners = new PropertyChangeSupport(this);

	public void addPropertyChangeListener(PropertyChangeListener l) {
		listeners.addPropertyChangeListener(l);
	}

	public void firePropertyChange(String propName, Object oldValue, Object newValue) {
		listeners.firePropertyChange(propName, oldValue, newValue);
	}

	public void removePropertyChangeListener(PropertyChangeListener l) {
		listeners.removePropertyChangeListener(l);
	}
}
