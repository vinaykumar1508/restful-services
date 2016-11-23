package messanger.model;

import java.util.Date;

public class Comment {

	private long id;
	private String comment;
	private Date created;
	private String author;
	private long messageId;
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public long getMessageId() {
		return messageId;
	}
	public void setMessageId(long messageId) {
		this.messageId = messageId;
	}
	
	
	
	public Comment(long id, String comment, Date created, String author,
			long messageId) {
		super();
		this.id = id;
		this.comment = comment;
		this.created = created;
		this.author = author;
		this.messageId = messageId;
	}
	public Comment() {
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Comment [id=" + id + ", comment=" + comment + ", created="
				+ created + ", author=" + author + ", messageId=" + messageId
				+ "]";
	}
	
	
	
}
