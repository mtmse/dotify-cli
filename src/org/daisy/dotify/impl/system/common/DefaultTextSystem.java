package org.daisy.dotify.impl.system.common;

import java.util.ArrayList;
import java.util.Map;

import org.daisy.dotify.SystemKeys;
import org.daisy.dotify.input.InputManager;
import org.daisy.dotify.input.InputManagerFactoryMaker;
import org.daisy.dotify.obfl.ObflResourceLocator;
import org.daisy.dotify.obfl.ObflResourceLocator.ObflResourceIdentifier;
import org.daisy.dotify.system.InternalTask;
import org.daisy.dotify.system.LayoutEngineTask;
import org.daisy.dotify.system.TaskSystem;
import org.daisy.dotify.system.TaskSystemException;
import org.daisy.dotify.system.XsltTask;
import org.daisy.dotify.text.FilterLocale;
import org.daisy.dotify.translator.BrailleTranslator;
import org.daisy.dotify.translator.BrailleTranslatorFactory;
import org.daisy.dotify.translator.BrailleTranslatorFactoryMaker;
import org.daisy.dotify.translator.UnsupportedSpecificationException;
import org.daisy.dotify.writer.TextMediaWriter;


/**
 * <p>Transforms documents into text format.</p>
 * 
 * @author Joel Håkansson
 */
public class DefaultTextSystem implements TaskSystem {
	private final String name;
	private final FilterLocale context;
	
	public DefaultTextSystem(String name, FilterLocale context) {
		this.name = name;
		this.context = context;
	}
	
	public String getName() {
		return name;
	}

	public ArrayList<InternalTask> compile(Map<String, Object> p) throws TaskSystemException {

		ArrayList<InternalTask> setup = new ArrayList<InternalTask>();
		{

			//InputDetector
			InputManager idts = InputManagerFactoryMaker.newInstance().newInputManager(context, p.get(SystemKeys.INPUT_FORMAT).toString());
			setup.addAll(idts.compile(p));
			
			// Whitespace normalizer TransformerFactoryConstants.SAXON8
			setup.add(new XsltTask("OBFL whitespace normalizer",
									ObflResourceLocator.getInstance().getResourceByIdentifier(ObflResourceIdentifier.OBFL_WHITESPACE_NORMALIZER_XSLT), 
									null,
									p));
		}
		// Layout FLOW as text
		BrailleTranslator bt;
		try {
			bt = BrailleTranslatorFactoryMaker.newInstance().newTranslator(context, BrailleTranslatorFactory.MODE_BYPASS);
		} catch (UnsupportedSpecificationException e) {
			throw new TaskSystemException(e);
		}

		TextMediaWriter paged = new TextMediaWriter("UTF-8");
		setup.add(new LayoutEngineTask("OBFL to Text converter", bt, paged));

		return setup;
	}

}