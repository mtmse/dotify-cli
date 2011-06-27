package org.daisy.dotify.formatter.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class SimpleSplitterDataTest {

	@Test
	public void breakpoints() {
		int i = 479;
		SimpleSplitterData ssd = new SimpleSplitterData(i, 49);
		for (int j=1; j<i; j++) {
			if (j==48 || j==96 || j==144 || j==192 || j==240 || j==288 || j==336 || j==384 || j==432) {
				assertTrue("Assert that sheet is a break point: " + j, ssd.isBreakpoint(j));
			} else {
				assertTrue("Assert that sheet is not a break point: " + j, !ssd.isBreakpoint(j));
			}
		}
	}
	
	@Test
	public void volumeNumber() {
		int i = 479;
		SimpleSplitterData ssd = new SimpleSplitterData(i, 49);
		int vol = 1;
		for (int j=1; j<i; j++) {
			if (j==48 || j==96 || j==144 || j==192 || j==240 || j==288 || j==336 || j==384 || j==432) {
				vol++;
			}
			assertEquals("Assert that sheet "+ j +" is in the right volume.", vol, ssd.getVolumeForSheet(j));
		}
	}
	
	@Test
	public void volumeCount() {
		int i = 479;
		SimpleSplitterData ssd = new SimpleSplitterData(i, 49);
		assertTrue("Assert that number of volumes is correct: " + ssd.volumeCount(), ssd.volumeCount()==10);
	}
	
	@Test (expected=IndexOutOfBoundsException.class)
	public void sheetZeroBreakpoint() {
		SimpleSplitterData ssd = new SimpleSplitterData(479, 49);
		ssd.isBreakpoint(0);
	}
	
	@Test (expected=IndexOutOfBoundsException.class)
	public void sheetLimitBreakpoint() {
		SimpleSplitterData ssd = new SimpleSplitterData(479, 49);
		ssd.isBreakpoint(480);
	}
	
	@Test (expected=IndexOutOfBoundsException.class)
	public void sheetZeroVolumeNumber() {
		SimpleSplitterData ssd = new SimpleSplitterData(479, 49);
		ssd.getVolumeForSheet(0);
	}
	
	@Test (expected=IndexOutOfBoundsException.class)
	public void sheetLimitVolumeNumber() {
		SimpleSplitterData ssd = new SimpleSplitterData(479, 49);
		ssd.getVolumeForSheet(480);
	}
	
	@Test (expected=IndexOutOfBoundsException.class)
	public void negativeSheet() {
		SimpleSplitterData ssd = new SimpleSplitterData(479, 49);
		ssd.isBreakpoint(-1);
	}
	
	@Test (expected=IndexOutOfBoundsException.class)
	public void negativeSheetVolumeNumber() {
		SimpleSplitterData ssd = new SimpleSplitterData(479, 49);
		ssd.getVolumeForSheet(-1);
	}


}