package org.daisy.dotify.formatter;





/**
 * BlockStruct is a pull interface for the first step of the layout process
 * @author Joel Håkansson
 */
public interface BlockStruct {
	
	/**
	 * Gets the block sequence interable
	 * @return returns the block sequence interable
	 */
	public Iterable<BlockSequence> getBlockSequenceIterable();

}