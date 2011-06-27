package org.daisy.dotify.formatter.impl;

import org.daisy.dotify.formatter.BlockProperties;

public class TocEventImpl extends BlockEventImpl {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1378970818712629309L;
	private final String refId;
	private final String tocId;
	
	public TocEventImpl(String refId, String tocId, BlockProperties props) {
		super(props);
		this.refId = refId;
		this.tocId = tocId;
	}

	public ContentType getContentType() {
		return ContentType.TOC_ENTRY;
	}
	
	public String getRefId() {
		return refId;
	}
	
	public String getTocId() {
		return tocId;
	}

}