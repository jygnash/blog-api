package click.blogs.blogapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "blogs")
public class Blog implements Serializable {

	@Id
	@Column(name = "blog_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BLOG_SEQ")
    @SequenceGenerator(sequenceName = "blog_sequence", allocationSize = 1, name = "BLOG_SEQ")
	private Long blogId;
	
	@Column(name = "blog_post")
	private String blogPost;
	
	@Column(name = "blog_description")
	private String blogDescription;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_name")
	private User user;
	
	
	public Blog() {}


	public Long getBlogId() {
		return blogId;
	}


	public void setBlogId(Long blogId) {
		this.blogId = blogId;
	}


	public String getBlogPost() {
		return blogPost;
	}


	public void setBlogPost(String blogPost) {
		this.blogPost = blogPost;
	}


	public String getBlogDescription() {
		return blogDescription;
	}


	public void setBlogDescription(String blogDescription) {
		this.blogDescription = blogDescription;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public String toString() {
		return "Blog [blogId=" + blogId + ", blogPost=" + blogPost + ", blogDescription=" + blogDescription + ", user="
				+ user.toString() + "]";
	}
	
	
}
