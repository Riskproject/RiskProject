/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.risk.model;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Shape;
import java.awt.geom.Arc2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JComponent;
import javax.swing.JFrame;

import edu.uci.ics.jung.visualization.layout.PersistentLayout.Point;

/**
 * This Class Stores all the constant values for countryID and Colors etc.
 * 
 * @author Arij
 * 
 */
public class Constants {
	private static Point p = new Point(5, 5);
	public final static int COUNTRY1ID = 1;
	public final static int COUNTRY2ID = 2;
	public final static int COUNTRY3ID = 3;
	public final static int COUNTRY4ID = 4;
	public final static Color COUNTRY1COLOR = Color.GREEN;
	public final static Color COUNTRY2COLOR = Color.BLUE;
	public final static Color COUNTRY3COLOR = Color.CYAN;
	public final static Color COUNTRY4COLOR = Color.YELLOW;
	public final static int CONTINENT1ID = 1;
	public final static int CONTINENT2ID = 2;
	public final static int CONTINENT3ID = 3;
	public final static int CONTINENT4ID = 4;
	public final static int CONTINENT5ID = 5;
	public final static int CONTINENT6ID = 6;
	public final static int CONTINENT7ID = 7;
	public final static Shape CONTINENT1SHAPE = new Ellipse2D.Float(-15, -15,
			30, 30);
	public final static Shape CONTINENT2SHAPE = new Rectangle2D.Float(-15, -15,
			30, 30);
	public final static Shape CONTINENT3SHAPE = downTriangle();
	public final static Shape CONTINENT4SHAPE = createStar(3, p, 15, 15);
	public final static Shape CONTINENT5SHAPE = new RoundRectangle2D.Float(-15,
			-15, 30, 30, 15, 15);
	public final static Shape CONTINENT6SHAPE = upTriangle();
	public final static Shape CONTINENT7SHAPE = createStar(4, p, 15, 15);
	public final static int PRODUCTION_STAGE = 1;
	public final static int STRATEGIC_INTELLIGENCE_STAGE = 2;
	public final static int MILITARY_THEATRE_STAGE = 3;
	public final static int STRATEGIC_MOVEMENT_STAGE = 4;
	public final static int SIMULATION_SMOOTH = 1;
	public final static int SIMULATION_PAUSE_AFTER_EACH_PHASE = 2;
	public final static int SIMULATION_PAUSE_AFTER_EACH_COUNTRY_TURN = 3;
	public final static int SIMULATION_PAUSE_AFTER_ALL_COUNTRIES_TURN = 4;

	/**
	 * This method is used to draw triangle pointing down
	 * 
	 * @return - Shape of down triangle
	 */
	private final static Shape downTriangle() {
		final GeneralPath p0 = new GeneralPath();
		p0.moveTo(0.0f, 15);
		p0.lineTo(15, -15);
		p0.lineTo(-15, -15);
		p0.closePath();
		return p0;
	}

	/**
	 * This method is used to draw triangle pointing up
	 * 
	 * @return - Shape of up triangle
	 */
	private final static Shape upTriangle() {
		final GeneralPath p0 = new GeneralPath();
		p0.moveTo(0.0f, -15);
		p0.lineTo(15, 15);
		p0.lineTo(-15, 15);
		p0.closePath();
		return p0;
	}

	/**
	 * This method is used to create a Star
	 * 
	 * @param arms
	 *            - Specification for Star Figure
	 * @param center
	 *            - Specification for Star Figure
	 * @param rOuter
	 *            - Specification for Star Figure
	 * @param rInner
	 *            - Specification for Star Figure
	 * @return
	 * 			  - Shape of star
	 */
	private static Shape createStar(int arms, Point center, double rOuter,
			double rInner) {
		double angle = Math.PI / arms;
		GeneralPath path = new GeneralPath();
		for (int i = 0; i < 2 * arms; i++) {
			double r = (i & 1) == 0 ? rOuter : rInner;
			Point2D.Double p = new Point2D.Double(center.x
					+ Math.cos(i * angle) * r, center.y + Math.sin(i * angle)
					* r);
			if (i == 0)
				path.moveTo(p.getX(), p.getY());
			else
				path.lineTo(p.getX(), p.getY());
		}
		path.closePath();
		return path;
	}

}
