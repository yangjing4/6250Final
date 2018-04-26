package com.me.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="reply_table")
public class Reply {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="replyID", unique = true, nullable = false)
	private long replyID;
	
	
	@Column(name="content")
    private String content;
	
	@Column(name="date")
	private String date;
	
	@ManyToOne
	private Comment comment;
	
	@ManyToOne
	private User user;
	
	
	public Reply(String content,User user, Comment comment) {
        this.content = content;
        this.user=user;
        this.comment=comment;
    }
	
	
	public Reply() {
		
	}


	public long getReplyID() {
		return replyID;
	}


	public void setReplyID(long replyID) {
		this.replyID = replyID;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public Comment getComment() {
		return comment;
	}


	public void setComment(Comment comment) {
		this.comment = comment;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	

}
