/*
    Copyright (C) 2007-20013  BlueXML - www.bluexml.com

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License as
    published by the Free Software Foundation, either version 3 of the
    License, or (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU Affero General Public License for more details.

    You should have received a copy of the GNU Affero General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/
/**
 * 
 */
package com.bluexml.side.Integration.alfresco.xforms.webscript;

/**
 * Raw cumulative timer for debugging.
 * 
 * @author Amenel
 */
public class Timer {

	private long startTime = 0;
	private long endTime = 0;
	private long totalTime = 0;

	public void start() {
		this.startTime = System.nanoTime();
	}

	public void stop() {
		this.endTime = System.nanoTime();
		this.totalTime += (this.endTime - this.startTime);
	}

	public long getStartTime() {
		return this.startTime;
	}

	public long getEndTime() {
		return this.endTime;
	}

	public long getTotalTime() {
		return this.totalTime;
	}

	public void reset() {
		this.startTime = 0;
		this.endTime = 0;
		this.totalTime = 0;
	}

}
