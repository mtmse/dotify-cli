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
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.daisy.braille.utils.pef.PEFFileMerger;
import org.daisy.braille.utils.pef.PEFFileMerger.SortType;
import org.daisy.streamline.api.validity.Validator;
import org.daisy.streamline.api.validity.ValidatorFactoryMaker;
import org.daisy.streamline.api.validity.ValidatorFactoryMakerService;
import org.daisy.streamline.cli.Argument;
import org.daisy.streamline.cli.CommandDetails;
import org.daisy.streamline.cli.CommandParser;
import org.daisy.streamline.cli.Definition;
import org.daisy.streamline.cli.ExitCode;
import org.daisy.streamline.cli.OptionalArgument;

/**
 * Provides a UI for merging PEF-files. Not for public use. This class is a package class. Use DotifyCLI
 * @author Joel Håkansson
 */
class MergePEF implements CommandDetails {
	/**
	 * Prefix used for required arguments in the arguments map
	 */
	public static final String ARG_PREFIX = "required-";
	private final CommandParser parser;
	
	public MergePEF() {
		this.parser = CommandParser.create(this);
	}

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
		MergePEF ui = new MergePEF();
		if (args.length<3) {
			System.out.println("Expected three arguments.");
			System.out.println();
			ui.parser.displayHelp(System.out);
			ExitCode.MISSING_ARGUMENT.exitSystem();
		}
		ValidatorFactoryMakerService factory = ValidatorFactoryMaker.newInstance();
		Validator validator = factory.newValidator("application/x-pef+xml");
		if (validator==null) {
			ExitCode.INTERNAL_ERROR.exitSystem("Failed to locate a validator");
		}
		PEFFileMerger merger = new PEFFileMerger(url->validator.validate(url).isValid());
		File input = new File(args[0]);
		File output = new File(args[1]);
		SortType sort = SortType.STANDARD;
		
		if (args.length>3) {
			Map<String, String> p = ui.parser.parse(args).toMap(ARG_PREFIX);
			String sortString = p.remove("sort");
			if (sortString.equalsIgnoreCase("alpha")) {
				sort = SortType.STANDARD;
			} else if (sortString.equalsIgnoreCase("number")) {
				sort = SortType.NUMERAL_GROUPING;
			} else {
				ExitCode.ILLEGAL_ARGUMENT_VALUE.exitSystem("Illegal value for argument sort: " + sortString);
			}
		}
		merger.merge(input, new FileOutputStream(output), args[2], sort);
	}

	@Override
	public String getName() {
		return DotifyCLI.MERGE;
	}
	
	@Override
	public String getDescription() {
		return "Merges several PEF files into one. The purpose is to facilitating the " +
				"use of PEF-files with braille editors that do not support multi volume files.";
	}

	@Override
	public List<Argument> getRequiredArguments() {
		ArrayList<Argument> ret = new ArrayList<Argument>();
		ret.add(new Argument("input_directory", "Path to input directory containing only PEF-files to merge"));
		ret.add(new Argument("ouput_file", "Path to output file"));
		ret.add(new Argument("identifier", "Publication identifier"));
		return ret;
	}

	@Override
	public List<OptionalArgument> getOptionalArguments() {
		ArrayList<OptionalArgument> ret = new ArrayList<OptionalArgument>();
		ArrayList<Definition> values = new ArrayList<Definition>();
		values.add(new Definition("alpha", "Sort in alphabetical order (character by character from left to right)"));
		values.add(new Definition("number", "Sort groups of digits as numbers (from smaller to larger)"));
		ret.add(new OptionalArgument("sort", "Sorting method to use when determining file order based on file name", values, "alpha"));
		return ret;
	}

}
