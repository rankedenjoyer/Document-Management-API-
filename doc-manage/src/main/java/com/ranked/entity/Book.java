package com.ranked.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
public class Book {
	@Id
	private String id; // Assuming Book has its own ID
	private String type;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "documentId")
	private Document document;

	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<BookState> bookStates;

	// Getters and setters

	public String getType() {
		return type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}

	public List<BookState> getBookStates() {
		return bookStates;
	}

	public void setBookStates(List<BookState> bookStates) {
		this.bookStates = bookStates;
	}
}
