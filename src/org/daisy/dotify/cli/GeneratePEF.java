/*
 * Braille Utils (C) 2010-2011 Daisy Consortium 
 * 
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 * 
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with this library; if not, write to the Free Software Foundation, Inc.,
 * 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 */
package org.daisy.dotify.cli;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.daisy.braille.utils.pef.PEFGenerator;
import org.daisy.streamline.cli.Argument;
import org.daisy.streamline.cli.CommandDetails;
import org.daisy.streamline.cli.CommandParser;
import org.daisy.streamline.cli.ExitCode;
import org.daisy.streamline.cli.OptionalArgument;
import org.daisy.streamline.cli.SwitchArgument;
import org.daisy.streamline.cli.SwitchMap;

/**
 * Provides a UI for generating PEF-files. Not for public use.
 * This class is a package class. Use DotifyCLI
 * @author Joel Håkansson
 */
class GeneratePEF implements CommandDetails {
	/**
	 * Prefix used for required arguments in the arguments map
	 */
	public static final String ARG_PREFIX = "required-";
	private final List<Argument> reqArgs;
	private final List<OptionalArgument> optionalArgs;
	private final SwitchMap switches;
	private final CommandParser parser;

	public GeneratePEF() {
		reqArgs = new ArrayList<Argument>();
		reqArgs.add(new Argument("path_to_file", "Path to the output file"));
		optionalArgs = new ArrayList<OptionalArgument>();
		optionalArgs.add(newOptionalArgument(PEFGenerator.KEY_VOLUMES, "Number of volumes to generate"));
		optionalArgs.add(newOptionalArgument(PEFGenerator.KEY_SPV, "Number of sections in each volume"));
		optionalArgs.add(newOptionalArgument(PEFGenerator.KEY_PPV, "Number of pages in each volume"));
		//optionalArgs.add(newOptionalArgument(PEFGenerator.KEY_EIGHT_DOT, "Set to true to generate 8-dot braille"));
		this.switches = new SwitchMap.Builder()
				.addSwitch(new SwitchArgument('f', "full-range", PEFGenerator.KEY_EIGHT_DOT, "true", "Use the full range of braille patterns, i.e. including eight dot patterns"))
				.addSwitch(new SwitchArgument('s', "simplex", PEFGenerator.KEY_DUPLEX, "false", "Create single sided PEF-files"))
				.build();
		this.parser = CommandParser.create(this);
		optionalArgs.add(newOptionalArgument(PEFGenerator.KEY_ROWS, "Maximum number of rows on a page"));
		optionalArgs.add(newOptionalArgument(PEFGenerator.KEY_COLS, "Maximum number of cols on a row"));
		//optionalArgs.add(newOptionalArgument(PEFGenerator.KEY_DUPLEX, "Set the duplex property"));
	}

	private OptionalArgument newOptionalArgument(String key, String desc) {
		return new OptionalArgument(key, desc, PEFGenerator.getDefaultValue(key));
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		GeneratePEF ui = new GeneratePEF();
		if (args.length<1) {
			System.out.println("Expected at least one more argument.");
			System.out.println();
			ui.parser.displayHelp(System.out);
			ExitCode.MISSING_ARGUMENT.exitSystem();
		}
		Map<String, String> p = ui.parser.parse(args).toMap(ARG_PREFIX);
		// remove required argument
		File output = new File(""+p.remove(ARG_PREFIX+0));
		// pass the optional arguments to the generator
		PEFGenerator generator = new PEFGenerator(p);
		// generate
		System.out.println("Generating test book...");
		generator.generateTestBook(output);
		System.out.println("Done!");
	}

	@Override
	public List<Argument> getRequiredArguments() {
		return reqArgs;
	}

	@Override
	public List<OptionalArgument> getOptionalArguments() {
		return optionalArgs;
	}

	@Override
	public String getName() {
		return DotifyCLI.GENERATE;
	}
	
	@Override
	public String getDescription() {
		return "Generates a random PEF-file for testing purposes.";
	}

	@Override
	public SwitchMap getSwitches() {
		return switches;
	}

}
