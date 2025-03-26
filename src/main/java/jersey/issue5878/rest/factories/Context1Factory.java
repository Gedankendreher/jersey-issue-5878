package jersey.issue5878.rest.factories;

import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.ext.Provider;
import jersey.issue5878.rest.data.Context1;

public class Context1Factory implements Factory<Context1> {
	private static final Logger LOGGER = LoggerFactory.getLogger(Context1Factory.class);

	@Provider
	public static class Binder extends AbstractBinder {
		public Binder() {
			super();
		}
		@Override
		protected void configure() {
			bindFactory(Context1Factory.class)
				.to(Context1.class)
				.in(RequestScoped.class);
		}
	}

	private String pathValue;

	public Context1Factory(@PathParam("ctx1") @DefaultValue("") String ctx1) {
		this.pathValue = ctx1;
		LOGGER.info("created factory for context 1");
	}

	@Override
	public Context1 provide() {
		return new Context1(pathValue);
	}

	@Override
	public void dispose(Context1 instance) {
		LOGGER.info("disposedcontext 1 ({})", instance.getValue());
	}
}
