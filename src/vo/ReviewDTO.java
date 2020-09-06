package vo;

import java.sql.Timestamp;

public class ReviewDTO {
	
	int item_num;
	int review_comment_num;
	String review_comment;
	int review_rating;
	Timestamp review_datetime;
	String review_re_comment;
	int review_re_checker;
	int member_num;
	
	public int getItem_num() {
		return item_num;
	}
	public void setItem_num(int item_num) {
		this.item_num = item_num;
	}
	public int getReview_comment_num() {
		return review_comment_num;
	}
	public void setReview_comment_num(int review_comment_num) {
		this.review_comment_num = review_comment_num;
	}
	public String getReview_comment() {
		return review_comment;
	}
	public void setReview_comment(String review_comment) {
		this.review_comment = review_comment;
	}
	public int getReview_rating() {
		return review_rating;
	}
	public void setReview_rating(int review_rating) {
		this.review_rating = review_rating;
	}
	public Timestamp getReview_datetime() {
		return review_datetime;
	}
	public void setReview_datetime(Timestamp review_datetime) {
		this.review_datetime = review_datetime;
	}
	public String getReview_re_comment() {
		return review_re_comment;
	}
	public void setReview_re_comment(String review_re_comment) {
		this.review_re_comment = review_re_comment;
	}
	public int getReview_re_checker() {
		return review_re_checker;
	}
	public void setReview_re_checker(int review_re_checker) {
		this.review_re_checker = review_re_checker;
	}
	public int getMember_num() {
		return member_num;
	}
	public void setMember_num(int member_num) {
		this.member_num = member_num;
	}
	
	
}
