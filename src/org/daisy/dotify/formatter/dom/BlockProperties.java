package org.daisy.dotify.formatter.dom;

import org.daisy.dotify.formatter.dom.FormattingTypes.BreakBefore;
import org.daisy.dotify.formatter.dom.FormattingTypes.Keep;
import org.daisy.dotify.formatter.dom.FormattingTypes.ListStyle;


/**
 * BlockProperties defines properties specific for a block of text
 * @author Joel Håkansson, TPB
 */
public class BlockProperties {
	private final int leftMargin;
	private final int rightMargin;
	private final int topMargin;
	private final int bottomMargin;
	private final int textIndent;
	private final int firstLineIndent;
	private final ListStyle listType;
	private int listIterator;
	private final BreakBefore breakBefore;
	private final Keep keep;
	private final int keepWithNext;
	private final int blockIndent;
	private final String identifier;

	/**
	 * The Builder is used when creating a BlockProperties instance.
	 * @author Joel Håkansson, TPB
	 */
	public static class Builder {
		// Optional parameters
		int leftMargin = 0;
		int rightMargin = 0;
		int topMargin = 0;
		int bottomMargin = 0;
		int textIndent = 0;
		int firstLineIndent = 0;
		ListStyle listType = ListStyle.NONE;
		BreakBefore breakBefore = BreakBefore.AUTO;
		Keep keep = Keep.AUTO;
		int keepWithNext = 0;
		int blockIndent = 0;
		String identifier = "";
		
		/**
		 * Create a new Builder
		 */
		public Builder() { }
		
		/**
		 * Creates a new Builder based on existing properties
		 * @param b
		 */
		public Builder(BlockProperties b) {
			leftMargin = b.getLeftMargin();
			rightMargin = b.getRightMargin();
			topMargin = b.getTopMargin();
			bottomMargin = b.getBottomMargin();
			textIndent = b.getTextIndent();
			firstLineIndent = b.getFirstLineIndent();
			listType = b.getListType();
			breakBefore = b.getBreakBeforeType();
			keep = b.getKeepType();
			keepWithNext = b.getKeepWithNext();
			blockIndent = b.getBlockIndent();
			identifier = b.getIdentifier();
		}
		/**
		 * Set the left margin for the block, in characters.
		 * @param leftMargin left margin, in characters
		 * @return returns "this" object
		 */
		public Builder leftMargin(int leftMargin) {
			this.leftMargin = leftMargin;
			return this;
		}
		
		/**
		 * Set the right margin for the block, in characters.
		 * @param rightMargin right margin, in characters
		 * @return returns "this" object
		 */
		public Builder rightMargin(int rightMargin) {
			this.rightMargin = rightMargin;
			return this;
		}
		
		/**
		 * Set the top margin for the block, in characters.
		 * @param topMargin top margin, in characters
		 * @return returns "this" object
		 */
		public Builder topMargin(int topMargin) {
			this.topMargin = topMargin;
			return this;
		}
		
		/**
		 * Set the bottom margin for the block, in characters.
		 * @param bottomMargin bottom margin, in characters
		 * @return returns "this" object
		 */
		public Builder bottomMargin(int bottomMargin) {
			this.bottomMargin = bottomMargin;
			return this;
		}
		
		/**
		 * Set the text indent for the block, in characters.
		 * The text indent controls the indent of all text rows except 
		 * the first one, see {@link #firstLineIndent(int)}.
		 * The text indent is applied to text directly within 
		 * the block, but is not inherited to block children.
		 * @param textIndent the indent, in characters
		 * @return returns "this" object
		 */
		public Builder textIndent(int textIndent) {
			this.textIndent = textIndent;
			return this;
		}
		
		/**
		 * Set the first line indent for the block, in characters.
		 * The first line indent controls the indent of the first text 
		 * row in a block.
		 * The first line indent is applied to text directly within
		 * the block, but is not inherited to block children.
		 * @param firstLineIndent the indent, in characters.
		 * @return returns "this" object
		 */
		public Builder firstLineIndent(int firstLineIndent) {
			this.firstLineIndent = firstLineIndent;
			return this;
		}
		
		/**
		 * Set the list type for the block. The list type is
		 * applied to block's children.
		 * @param listType the type of list
		 * @return returns "this" object
		 */
		public Builder listType(FormattingTypes.ListStyle listType) {
			this.listType = listType;
			return this;
		}
		
		/**
		 * Set the break before property for the block.
		 * @param breakBefore the break before type
		 * @return returns "this" object
		 */
		public Builder breakBefore(FormattingTypes.BreakBefore breakBefore) {
			this.breakBefore = breakBefore;
			return this;
		}
		
		/**
		 * Set the keep property for the block.
		 * @param keep the keep type
		 * @return returns "this" object
		 */
		public Builder keep(FormattingTypes.Keep keep) {
			this.keep = keep;
			return this;
		}
		
		/**
		 * Set the keep with next property for the block.
		 * @param keepWithNext the number of rows in the next 
		 * block to keep together with this block
		 * @return returns "this" object
		 */
		public Builder keepWithNext(int keepWithNext) {
			this.keepWithNext = keepWithNext;
			return this;
		}
		
		/**
		 * Set the block indent for the block, in characters.
		 * The block indent controls the indent of child blocks
		 * in a block. This is useful when building lists.
		 * @param blockIndent the indent, in characters
		 * @return returns "this" object
		 */
		public Builder blockIndent(int blockIndent) {
			this.blockIndent = blockIndent;
			return this;
		}
		
		public Builder identifier(String identifier) {
			this.identifier = identifier;
			return this;
		}
		
		/**
		 * Build BlockProperties using the current state of the Builder.
		 * @return returns a new BlockProperties instance
		 */
		public BlockProperties build() {
			return new BlockProperties(this);
		}
	}

	private BlockProperties(Builder builder) {
		leftMargin = builder.leftMargin;
		rightMargin = builder.rightMargin;
		topMargin = builder.topMargin;
		bottomMargin = builder.bottomMargin;
		textIndent = builder.textIndent;
		firstLineIndent = builder.firstLineIndent;
		listType = builder.listType;
		listIterator = 0;
		breakBefore = builder.breakBefore;
		keep = builder.keep;
		keepWithNext = builder.keepWithNext;
		blockIndent = builder.blockIndent;
		identifier = builder.identifier;
	}
	
	/**
	 * Get left margin, in characters
	 * @return returns the left margin
	 */
	public int getLeftMargin() {
		return leftMargin;
	}
	
	/**
	 * Get right margin, in characters
	 * @return returns the right margin
	 */
	public int getRightMargin() {
		return rightMargin;
	}
	
	/**
	 * Get top margin, in characters
	 * @return returns the top margin
	 */
	public int getTopMargin() {
		return topMargin;
	}
	
	/**
	 * Get bottom margin, in characters
	 * @return returns the bottom margin
	 */
	public int getBottomMargin() {
		return bottomMargin;
	}
	
	/**
	 * Get text indent, in characters
	 * @return returns the text indent
	 */
	public int getTextIndent() {
		return textIndent;
	}
	
	/**
	 * Get first line indent, in characters
	 * @return returns the first line indent
	 */
	public int getFirstLineIndent() {
		return firstLineIndent;
	}
	
	/**
	 * Get block indent, in characters
	 * @return returns the block indent
	 */
	public int getBlockIndent() {
		return blockIndent;
	}
	
	/**
	 * Get list type
	 * @return returns the list type
	 */
	public FormattingTypes.ListStyle getListType() {
		return listType;
	}
	
	/**
	 * Increments the list iterator and returns the current list number
	 * @return returns the current list number
	 */
	public int nextListNumber() {
		listIterator++;
		return listIterator;
	}
	
	/**
	 * Get current list number
	 * @return returns the current list number
	 */
	public int getListNumber() {
		return listIterator;
	}

	/**
	 * Get break before type
	 * @return returns the break before type
	 */
	public FormattingTypes.BreakBefore getBreakBeforeType() {
		return breakBefore;
	}

	/**
	 * Get keep type
	 * @return returns the keep type
	 */
	public FormattingTypes.Keep getKeepType() {
		return keep;
	}

	/**
	 * Get the number of rows containing text in the next block that must be on the same page as this block
	 * @return returns the number of rows in the next block to keep with this block 
	 */
	public int getKeepWithNext() {
		return keepWithNext;
	}
	
	public String getIdentifier() {
		return identifier;
	}

}