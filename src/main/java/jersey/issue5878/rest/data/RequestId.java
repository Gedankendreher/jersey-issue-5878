package jersey.issue5878.rest.data;

import java.util.UUID;

public class RequestId {

    private UUID uuid;

	public RequestId()
	{
		super();
        this.uuid = UUID.randomUUID();
	}

	public UUID uuid()
	{
		return uuid;
	}

	@Override
	public String toString()
	{
		return uuid.toString();
	}
}
