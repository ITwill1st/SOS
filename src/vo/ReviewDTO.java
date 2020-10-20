package vo;

import java.sql.Timestamp;

public class ReviewDTO {
	
	private int item_num;
	private String item_name;
	private int review_comment_num;
	private String review_comment;
	private int review_rating;
	private Timestamp review_datetime;
	private String review_re_comment;
	private int review_re_checker;
	private int mem_num;
	private String mem_nickname;
	
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
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
	public int getMem_num() {
		return mem_num;
	}
	public void setMem_num(int mem_num) {
		this.mem_num = mem_num;
	}
	public String getMem_nickname() {
		return mem_nickname;
	}
	public void setMem_nickname(String mem_nickname) {
		this.mem_nickname = mem_nickname;
	}

	
	
	
}
