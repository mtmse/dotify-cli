package org.daisy.dotify.cli.pefinfo;

import java.util.Arrays;

import org.daisy.braille.utils.pef.PEFBook;

public class URIDetail implements Detail {

	@Override
	public String getTitle() {
		return "URI";
	}

	@Override
	public Iterable<String> getDetails(PEFBook book) {
		return Arrays.asList(book.getURI().toString());
	}

}
