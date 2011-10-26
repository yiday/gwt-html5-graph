package org.drawx.gef.sample.client.tool.example.editparts;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.draw2d.ConnectionEndpointLocator;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PolygonDecoration;
import org.eclipse.draw2d.PolylineConnection;
import org.eclipse.draw2d.geometry.PointList;
import org.eclipse.gef.editparts.AbstractConnectionEditPart;


public class MyConnectionEditPart extends AbstractConnectionEditPart {

	/*
	 * (�� Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractEditPart#createEditPolicies()
	 */
	protected void createEditPolicies() {
	}

	/*
	 * (�� Javadoc)
	 * 
	 * @see org.eclipse.gef.editparts.AbstractGraphicalEditPart#createFigure()
	 */
	protected IFigure createFigure() {
		PolylineConnection connection = new PolylineConnection();
		PolylineConnection c=connection;
		PolygonDecoration decoration = new PolygonDecoration();

		// �R�l�N�V�����ɕH�`��ǉ�
		PointList pointList = new PointList();
		pointList.addPoint(0, 0);
		pointList.addPoint(-2, 2);
		pointList.addPoint(-4, 0);
		pointList.addPoint(-2, -2);
		decoration.setTemplate(pointList);

		decoration.setBackgroundColor(ColorConstants.white);
		decoration.setOpaque(true);

		connection.setTargetDecoration(decoration);

		ConnectionEndpointLocator targetEndpointLocator = 
		        new ConnectionEndpointLocator(c, true);
		targetEndpointLocator.setVDistance(15);
		Label targetMultiplicityLabel = new Label("1..*");
		c.add(targetMultiplicityLabel, targetEndpointLocator);

		ConnectionEndpointLocator sourceEndpointLocator = 
			new ConnectionEndpointLocator(c, false);
		sourceEndpointLocator.setVDistance(15);
		Label sourceMultiplicityLabel = new Label("1");
		c.add(sourceMultiplicityLabel, sourceEndpointLocator);
		
		ConnectionEndpointLocator relationshipLocator = new ConnectionEndpointLocator(
				connection, true);
		relationshipLocator.setUDistance(10);
		relationshipLocator.setVDistance(-20);
		org.eclipse.draw2d.Label relationshipLabel = new org.eclipse.draw2d.Label(
				"contains");
		connection.add(relationshipLabel, relationshipLocator);

		return connection;

	}

}
