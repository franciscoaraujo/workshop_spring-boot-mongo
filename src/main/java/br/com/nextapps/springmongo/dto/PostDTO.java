package br.com.nextapps.springmongo.dto;

import java.io.Serializable;
import java.util.Date;

import br.com.nextapps.springmongo.domain.Post;

public class PostDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private Date data;
	private String title;
	private String body;
	private AuthorDTO author;

	public PostDTO() {
		// TODO Auto-generated constructor stub
	}

	public PostDTO(Post obj) {
		super();
		id = obj.getId();
		data = obj.getData();
		title = obj.getTitle();
		body = obj.getBody();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public AuthorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
	}

	public static Post fromPostDTO(PostDTO obj) {
		return new Post(null, obj.getData(), obj.getTitle(), obj.getBody(), obj.getAuthor());
	}
}
