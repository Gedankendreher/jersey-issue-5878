package jersey.issue5878.rest.factories;

import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.ext.Provider;
import jersey.issue5878.rest.data.TestContext2;

public class TestContext2Factory implements Factory<TestContext2> {
	private static final Logger LOGGER = LoggerFactory.getLogger(Context1Factory.class);

	@Provider
	public static class Binder extends AbstractBinder {
		public Binder() {
			super();
		}
		@Override
		protected void configure() {
			bindFactory(TestContext2Factory.class)
				.to(TestContext2.class)
				.in(RequestScoped.class);
		}
	}

	private String pathValue;

	public TestContext2Factory(@PathParam("ctx2") @DefaultValue("") String ctx1) {
		this.pathValue = ctx1;
		LOGGER.info("created factory for context 2");
	}

	@Override
	public TestContext2 provide() {
		return new TestContext2(pathValue);
	}

	@Override
	public void dispose(TestContext2 instance) {
		LOGGER.info("disposedcontext 2 ({})", instance.getValue());
	}
}
