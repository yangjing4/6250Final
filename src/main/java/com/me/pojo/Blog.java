package com.me.pojo;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name="blog_table")
@PrimaryKeyJoinColumn(name = "blogID")
public class Blog {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="blogID", unique = true, nullable = false)
    private long id;
	
	@Column(name="title")
    private String title;
	
	@Column(name="content")
    private String content;
	
	@Column(name="date")
	private String date;
	
//	@OneToMany(mappedBy="blog")
//	private Set<Comment> comments = new HashSet<Comment>();
	
	@ManyToOne
	private User user;
	
	@ManyToMany(mappedBy="blogs")
	private Set<Category> categories = new HashSet<Category>();
	
	@Transient
	int postedBy;

    public Blog(String title, String content, User user, Category catergory) {
        this.title = title;
        this.content = content;
        this.user = user;
        
        //this.categories.add(catergory);
    }

    public Blog() {
    }

	public long getId() {
		return id;
	}

	public void setId(long id) {
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
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}

	public int getPostedBy() {
		return postedBy;
	}

	public void setPostedBy(int postedBy) {
		this.postedBy = postedBy;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

//	public Set<Comment> getComments() {
//		return comments;
//	}
//
//	public void setComments(Set<Comment> comments) {
//		this.comments = comments;
//	}

	

	
    
  

   
}