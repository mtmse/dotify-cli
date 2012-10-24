package org.daisy.dotify.l10n;

import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.daisy.dotify.text.FilterLocale;
import org.daisy.dotify.tools.AbstractResourceLocator;
import org.daisy.dotify.tools.ResourceLocator;

/**
 * Provides a resource locator for localized resources. Using this
 * class makes it possible to locate a resource using a path relative
 * to the localization base folder, without knowledge of the locale
 * to base folder mapping.
 * @author Joel Håkansson
 */
class LocalizationResourceLocator extends AbstractResourceLocator {
	private final Properties locales;
	
	/**
	 * Creates a new instance.
	 */
	LocalizationResourceLocator() {
		super();
		Logger logger = Logger.getLogger(this.getClass().getCanonicalName());
		locales = new Properties();
		try {
	        URL tablesURL = getResource("localization_catalog.xml");
	        if(tablesURL!=null){
	        	locales.loadFromXML(tablesURL.openStream());
	        } else {
	        	logger.warning("Cannot locate catalog file");
	        }
		} catch (IOException e) {
			logger.log(Level.WARNING, "Failed to load catalog.", e);
		}
	}

	private LocalizationResourceLocator(String basePath) {
		super(basePath);
		locales = new Properties();
	}

	/**
	 * Returns true if the specified locale is at all supported, that is to say
	 * that there is an entry for the locale in the localization catalog.
	 * @param locale the locale to test
	 * @return returns true if the locale is supported, false otherwise
	 */
	public boolean supportsLocale(FilterLocale locale) {
		return locales.getProperty(locale.toString())!=null;
	}
	
	/**
	 * Lists all supported locales, that is to say all locales that are
	 * in the localization catalog.
	 * @return returns a list of supported locales
	 */
	public Set<String> listSupportedLocales() {
		HashSet<String> ret = new HashSet<String>();
		for (Object key : locales.keySet()) {
			ret.add(key.toString());
		}
		return ret;
	}
	
	/**
	 * Gets a resource locator for the given locale.
	 * @param locale the locale to get a resource locator for
	 * @return returns a resource locator
	 * @throws IllegalArgumentException if the locale is not supported
	 */
	public ResourceLocator getResourceLocator(FilterLocale locale) {
		String languageFileRelativePath = locales.getProperty(locale.toString());
        if(languageFileRelativePath==null) {
        	throw new IllegalArgumentException("Locale not supported: " + locale.toString());
        } else {
        	return new LocalizationResourceLocator(languageFileRelativePath);
        }
	}
}
