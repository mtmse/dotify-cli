package org.daisy.dotify.formatter;

import org.daisy.dotify.paginator.SectionProperties;




/**
 * Specifies the layout of a paged media.
 * @author Joel Håkansson, TPB
 */
public interface LayoutMaster extends SectionProperties {

	/**
	 * Gets the template for the specified page number
	 * @param pagenum the page number to get the template for
	 * @return returns the template
	 */
	public PageTemplate getTemplate(int pagenum);

}