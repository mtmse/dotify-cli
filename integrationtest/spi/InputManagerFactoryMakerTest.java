package spi;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.daisy.dotify.common.text.FilterLocale;
import org.daisy.dotify.consumer.cr.InputManagerFactoryMaker;
import org.junit.Test;

public class InputManagerFactoryMakerTest {

	@Test
	public void testFactoryExists() {
		//Setup
		InputManagerFactoryMaker factory = InputManagerFactoryMaker.newInstance();
		
		//Test
		assertNotNull("Factory exists.", factory);
	}
	
	@Test
	public void testSupportedFileFormats() {
		//Setup
		InputManagerFactoryMaker factory = InputManagerFactoryMaker.newInstance();
		Set<String> formats = factory.listSupportedFileFormats();

		//Test
		assertEquals(5, formats.size());
		assertTrue(formats.contains("dtbook"));
		assertTrue(formats.contains("text"));
		assertTrue(formats.contains("xml"));
		assertTrue(formats.contains("txt"));
		assertTrue(formats.contains("obfl"));	
	}
	
	@Test
	public void testSupportedLocales() {
		//Setup
		InputManagerFactoryMaker factory = InputManagerFactoryMaker.newInstance();
		Set<String> locales = factory.listSupportedLocales();

		//Test
		assertEquals(2, locales.size());
		assertTrue(locales.contains("sv-SE"));
		assertTrue(locales.contains("en-US"));
	}

	@Test
	public void testGetFactoryForSwedish() {
		//Setup
		InputManagerFactoryMaker factory = InputManagerFactoryMaker.newInstance();
		FilterLocale locale = FilterLocale.parse("sv-SE");
		
		//Test
		assertNotNull(factory.getFactory(locale.toString(), "xml"));
		assertNotNull(factory.getFactory(locale.toString(), "text"));
		assertNotNull(factory.getFactory(locale.toString(), "obfl"));
		assertNotNull(factory.getFactory(locale.toString(), "txt"));
		assertNotNull(factory.getFactory(locale.toString(), "dtbook"));
	}
	
	@Test
	public void testGetFactoryForEnglish() {
		//Setup
		InputManagerFactoryMaker factory = InputManagerFactoryMaker.newInstance();
		FilterLocale locale = FilterLocale.parse("en-US");
		
		//Test
		assertNotNull(factory.getFactory(locale.toString(), "xml"));
		assertNotNull(factory.getFactory(locale.toString(), "text"));
		assertNotNull(factory.getFactory(locale.toString(), "obfl"));
		assertNotNull(factory.getFactory(locale.toString(), "txt"));
		assertNotNull(factory.getFactory(locale.toString(), "dtbook"));
	}
}