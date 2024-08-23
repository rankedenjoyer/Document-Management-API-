package com.ranked.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.UniqueConstraint;
import javax.persistence.Table;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"readerId", "employeeId"})})
public class Borrowing {
    @Id
    private String id;
   
    @ManyToOne
    @JoinColumn(name = "readerId")
    private Reader reader;
    
    @ManyToOne
    @JoinColumn(name = "employeeId")
    private Employee employee;
    
    @OneToMany(mappedBy = "borrowing", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BookState> bookStates;

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<BookState> getBookStates() {
		return bookStates;
	}

	public void setBookStates(List<BookState> bookStates) {
		this.bookStates = bookStates;
	}

	public Reader getReader() {
        return reader;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}


