package jersey.issue5878.rest.factories;


import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.inject.Inject;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.ext.Provider;

import jersey.issue5878.rest.data.IssueId;



public class IssueIdFactory implements Factory<IssueId> {
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestIdFactory.class);
	@Provider
	public static class Binder extends AbstractBinder {
		@Override
		protected void configure() {
			bindFactory(IssueIdFactory.class)
				.to(IssueId.class)
				.in(RequestScoped.class);
		}
	}

	private final String issueId;

	@Inject
	public IssueIdFactory(final @HeaderParam("X-Jersey-Issue-ID") String issueId) {
		this.issueId = issueId;
		LOGGER.info("issue id factory created");
	}

	@Override
	public IssueId provide() {
		LOGGER.info("issue ID to be provided; ID={}", issueId);
		return new IssueId(issueId);
	}

	@Override
	public void dispose(IssueId instance) {
		LOGGER.info("issue id disposed; ID={}", instance.id());
	}
}
