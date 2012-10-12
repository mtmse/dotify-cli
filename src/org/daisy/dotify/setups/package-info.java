/**
 * <p>
 * Provides setup configurations for different combinations of locale and
 * input/output format. A setup can be retrieved from the TaskSystemFactory.
 * </p>
 * <p>
 * Provides input format detection, validation and transformation.
 * </p>
 * <p>
 * Sub packages typically contain java classes or other files that shape the
 * result.
 * </p>
 * 
 * <p>
 * Adding a new locale requires some work, depending on your requirements. At
 * least the following items need to be considered:
 * </p>
 * <ul>
 * <li>Add a new package in "org.daisy.dotify.setups", for example "de_DE". All
 * locale specific code should be stored here except text filters, see Adding a
 * language below.</li>
 * <li>Create a class that implements TaskSystem, and add to it the tasks that
 * your locale needs, see below.</li>
 * </ul>
 * 
 * <h3>Adding input formats and customizing layout</h3>
 * <p>
 * Dotify only understands OBFL markup. Therefore, a new input format requires a
 * translation between the grammar in the input document and OBFL. This can be
 * achieved by applying an XSLT tailored for the task.
 * </p>
 * 
 * <p>
 * Page layout, such as headers and footers, as well as the interpretation of
 * elements in the input document are customized by writing a XSLT stylesheet so
 * that the input document's contents is expressed using the OBFL grammar. See
 * http://code.google.com/p/obfl/ for a description of this format.
 * </p>
 * 
 * <p>
 * If there already exists an XSLT for the input format for another locale, the
 * stylesheet could be copied into the desired locale's directory and then
 * modified. It is not recommended to extend another locale's stylesheets.
 * </p>
 * 
 * <p>
 * Detecting an XML input format is easy, thanks to the XMLInputManager. It is
 * specifically designed to inject the correct validation rules and XSLT
 * stylesheet for any XML-format and locale combination into the task chain.
 * </p>
 * 
 * <p>
 * Adding a new format involves the following:
 * </p>
 * <ol>
 * <li>Modify the input_format_catalog.xml</li>
 * <li>Add a selector file in the folder hierarchy</li>
 * <li>Add the desired validation rules and style sheets in folder hierarchy</li>
 * </ol>
 * 
 * <p>
 * 1. Modifying the input format catalog involves adding an entry for the new
 * format. The key must be the root element of the format followed by '@' and
 * the namespace of the root element. The value for this key should be a short
 * but descriptive filename for the format. Typically, the root element followed
 * by '.properties' will suffice as filename. However, the filename must be
 * unique throughout the file, so another name may have to be chosen.
 * </p>
 * 
 * <p>
 * 2. Add a selector file into the folder hierarchy in the appropriate location
 * of the folder hierarchy. The location of the selector file is subject to
 * change, and is therefore not documented here. The selector file should
 * contain two entries: a path to the validation file and a path to the
 * transformation file (relative to the root folder of the locale).
 * </p>
 * 
 * <p>
 * 3. Add the desired validation and stylesheets files to the folder hierarchy
 * as indicated by the selector file.
 * </p>
 * 
 * <h3>Implementing TaskSystem</h3>
 * 
 * <p>
 * The task system implementation is responsible for setting up the
 * LayoutEngineTask for the locale and adding post processing tasks (if needed).
 * A TaskSystem implementation should contain at least one step:
 * </p>
 * <ul>
 * <li>A OBFL format to a PagedMedia conversion, for example:
 * <p>
 * <code>setup.add(new LayoutEngineTask("OBFL to PEF converter", flow,
    paginator, paged));</code>
 * </p>
 * </li>
 * </ul>
 * @author Joel Håkansson
 */
package org.daisy.dotify.setups;