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
