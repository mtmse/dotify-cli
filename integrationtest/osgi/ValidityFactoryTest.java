package osgi;

import static org.ops4j.pax.exam.CoreOptions.junitBundles;
import static org.ops4j.pax.exam.CoreOptions.options;

import javax.inject.Inject;

import org.daisy.streamline.api.validity.ValidatorFactoryMakerService;
import org.junit.runner.RunWith;
import org.ops4j.pax.exam.Configuration;
import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.PaxExam;
import org.ops4j.pax.exam.spi.reactors.ExamReactorStrategy;
import org.ops4j.pax.exam.spi.reactors.PerClass;
import org.ops4j.pax.exam.util.Filter;

import base.ValidityFactoryTestbase;
import osgi.config.ConfigurationOptions;

@SuppressWarnings("javadoc")
@RunWith(PaxExam.class)
@ExamReactorStrategy(PerClass.class)
public class ValidityFactoryTest extends ValidityFactoryTestbase {

	@Configuration 
	public Option[] configure() {

		return options(
			ConfigurationOptions.felixDS(),
			ConfigurationOptions.dotifyTasks(),
			junitBundles()
			);
	}

	@Inject @Filter(timeout=5000)
	ValidatorFactoryMakerService validatorFactory;

	@Override
	public ValidatorFactoryMakerService getValidatorFMS() {
		return validatorFactory;
	}

}