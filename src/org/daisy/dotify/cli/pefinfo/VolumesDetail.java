package org.daisy.dotify.cli.pefinfo;

import java.util.Arrays;

import org.daisy.braille.utils.pef.PEFBook;

public class VolumesDetail implements Detail {

	@Override
	public String getTitle() {
		return "Volumes";
	}

	@Override
	public Iterable<String> getDetails(PEFBook book) {
		return Arrays.asList(""+book.getVolumes());
	}

}
