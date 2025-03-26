package jersey.issue5878.rest.data;

public class IssueId {
	private final String id;

	public IssueId(String id) {
		this.id = id;
	}

	public String id() {
		return id;
	}
}
