package jersey.issue5878.rest.resources;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.MediaType;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;

import jersey.issue5878.rest.data.IssueId;
import jersey.issue5878.rest.data.RequestId;
import jersey.issue5878.rest.data.SampleData;



@Path("simple")
public class OtherResource {
	private static final Logger LOGGER = LoggerFactory.getLogger(OtherResource.class);

	private final IssueId issueId;
	private final RequestId requestId;

	@Inject
	public OtherResource(@Context RequestId requestId, @Context IssueId issueId) {
		this.requestId = requestId;
		this.issueId = issueId;
	}

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	@Produces(MediaType.APPLICATION_JSON_VALUE)
	public SampleData process() {
		return new SampleData();
	}
}
