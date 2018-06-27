package model;

import java.util.List;

import dao.seller.Comment;

public class CommentInfo {
	//´æ´¢bookµÄÐÅÏ¢
	private BookInfo bookInfo;
	private List<Comment> commentList;
	public BookInfo getBookInfo() {
		return bookInfo;
	}
	public void setBookInfo(BookInfo bookInfo) {
		this.bookInfo = bookInfo;
	}
	public List<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	
	
}
