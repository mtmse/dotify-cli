package spi;

import org.daisy.dotify.api.text.Integer2TextFactoryMakerService;
import org.daisy.dotify.api.text.Integer2TextFactoryMaker;

import base.Integer2TextFactoryTestbase;

@SuppressWarnings("javadoc")
public class Integer2TextFactoryTest extends Integer2TextFactoryTestbase {

	@Override
	public Integer2TextFactoryMakerService getInteger2TextFMS() {
		return Integer2TextFactoryMaker.newInstance();
	}
}