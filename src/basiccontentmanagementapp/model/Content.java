package basiccontentmanagementapp.model;

public class Content {
	
	private int id;
	private String title;
	private String content;
	private String created;
		
	public Content(int id, String title, String content, String created) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
		this.created = created;
	}
	
	public Content(String title, String content, String created) {
		super();
		this.title = title;
		this.content = content;
		this.created = created;
	}



	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}

}
