package jersey.issue5878.rest.data;

import java.util.UUID;

public class SampleData {
	private final String uuid;

	public SampleData() {
		super();
		this.uuid = UUID.randomUUID().toString();
	}

	public String getUuid() {
		return uuid;
	}
}
