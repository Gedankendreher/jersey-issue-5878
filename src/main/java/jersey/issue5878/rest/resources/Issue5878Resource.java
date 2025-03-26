package jersey.issue5878.rest.resources;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import jersey.issue5878.rest.data.Context1;
import jersey.issue5878.rest.data.IssueId;
import jersey.issue5878.rest.data.RequestId;
import jersey.issue5878.rest.data.TestContext2;

@Path("/jersey-5878")
public class Issue5878Resource {
	private static final Logger LOGGER = LoggerFactory.getLogger(Issue5878Resource.class);

	private final IssueId issueId;
	private final RequestId requestId;

	@Inject
	public Issue5878Resource(@Context RequestId requestId, @Context IssueId issueId) {
		super();
		this.issueId = issueId;
		this.requestId = requestId;
	}

	@GET
	@Path("{ctx1}/{ctx2}")
	public String trigger(@Context Context1 context1, @Context TestContext2 context2) {
		LOGGER.info("[{}] processing request; ID={}", requestId.uuid(), issueId.id());
		return issueId.id() + ": " + context1.getValue() + '/' + context2.getValue();
	}
}
