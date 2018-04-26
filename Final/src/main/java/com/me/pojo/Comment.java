package com.me.pojo;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="comment_table")
public class Comment {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="commentID", unique = true, nullable = false)
	private long commentID;
	
	
	@Column(name="content")
    private String content;
	
	@Column(name="date")
	private String date;
	
	
	@ManyToOne
	private Blog blog;
	
	@ManyToOne
	private User user;
	
	@Transient
	int postedBy;
	
	@OneToMany(mappedBy="comment")
	private Set<Reply> replys = new HashSet<Reply>();

	public Comment(String content,User user, Blog blog) {
        this.content = content;
        this.user=user;
        this.blog=blog;
    }
	
	
	public Comment() {
		
	}

	public long getCommentID() {
		return commentID;
	}

	public void setCommentID(long commentID) {
		this.commentID = commentID;
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


	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	
	
	
	public Set<Reply> getReplys() {
		return replys;
	}


	public void setReplys(Set<Reply> replys) {
		this.replys = replys;
	}


	@Override 
	public String toString(){
		return content;
	}
}
