package org.daisy.dotify.impl.system.common;

import org.daisy.dotify.SystemKeys;
import org.daisy.dotify.system.TaskSystem;
import org.daisy.dotify.system.TaskSystemFactory;
import org.daisy.dotify.system.TaskSystemFactoryException;
import org.daisy.dotify.text.FilterLocale;

/**
 * Provides a default text system factory.
 * 
 * @author Joel Håkansson
 */
public class DefaultTextSystemFactory implements TaskSystemFactory {

	public boolean supportsSpecification(FilterLocale locale, String outputFormat) {
		return SystemKeys.TEXT_FORMAT.equals(outputFormat);
	}

	public TaskSystem newTaskSystem(FilterLocale locale, String outputFormat) throws TaskSystemFactoryException {
		if (SystemKeys.TEXT_FORMAT.equals(outputFormat)) {
			return new DefaultTextSystem("DefaultTextSystem", locale);			
		}
		throw new TaskSystemFactoryException("Unsupported specification: " + locale + "/" + outputFormat);
	}

}
