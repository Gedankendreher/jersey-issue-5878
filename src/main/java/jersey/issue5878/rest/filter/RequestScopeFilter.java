package jersey.issue5878.rest.filter;


import java.io.IOException;
import java.util.UUID;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.annotation.Priority;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.ext.Provider;

import jersey.issue5878.rest.data.RequestId;


@Provider
@PreMatching
@Priority(10)
public class RequestScopeFilter implements ContainerRequestFilter, ContainerResponseFilter
{
	private static final Logger LOGGER = LoggerFactory.getLogger(RequestScopeFilter.class);
	private static final String X_REQ_UUID = "X-REQUEST-UUID";


	private static <T> T fromRequest(final ContainerRequestContext requestContext, final Function<RequestId, T> provider)
	{
        if(requestContext.getProperty(X_REQ_UUID) instanceof RequestId requestId)
        {
            return provider.apply(requestId);
        }
        return null;
	}

	public static RequestId reqId(final ContainerRequestContext requestContext)
	{
		return fromRequest(requestContext, reqId -> reqId);
	}

	public static UUID uuid(final ContainerRequestContext requestContext)
	{
		return fromRequest(requestContext, reqId -> reqId.uuid());
	}

	public static String id(final ContainerRequestContext requestContext)
	{
		return fromRequest(requestContext, reqId -> reqId.toString());
	}

	public RequestScopeFilter()
	{
		super();
	}

	@Override
	public void filter(final ContainerRequestContext requestContext) throws IOException
	{
		final RequestId requestId = new RequestId();
		requestContext.setProperty(X_REQ_UUID, requestId);
		final String path = requestContext.getUriInfo().getPath();

		LOGGER.debug("[{}] request started; PATH={}", id(requestContext), path);
	}

	@Override
	public void filter(final ContainerRequestContext requestContext, final ContainerResponseContext responseContext) throws IOException
	{
	    fromRequest(requestContext, reqId ->
		{
			LOGGER.debug("[{}] request completed; STATUS={}", reqId.uuid().toString(), responseContext.getStatus());
			requestContext.removeProperty(X_REQ_UUID);
			return null;
		});
	}
}
