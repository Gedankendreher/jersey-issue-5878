package jersey.issue5878.rest.factories;

import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.inject.Inject;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.ext.Provider;
import jersey.issue5878.rest.data.RequestId;
import jersey.issue5878.rest.filter.RequestScopeFilter;

public class RequestIdFactory implements Factory<RequestId> {
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestIdFactory.class);
	@Provider
	public static class Binder extends AbstractBinder {

		@Override
		protected void configure() {
			bindFactory(RequestIdFactory.class)
				.to(RequestId.class)
				.in(RequestScoped.class);
		}
	}

	private final ContainerRequestContext requestContext;

	@Inject
	public RequestIdFactory(final ContainerRequestContext requestContext) {
		this.requestContext = requestContext;
		LOGGER.info("request context id factory created");
	}

	@Override
	public RequestId provide() {
		return RequestScopeFilter.reqId(requestContext);
	}

	@Override
	public void dispose(RequestId instance) {
		LOGGER.info("request context id disposed; ID={}", instance.uuid());
	}
}
