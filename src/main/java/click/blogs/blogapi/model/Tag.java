package click.blogs.blogapi.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="tags")
public class Tag implements Serializable {
	
	@Id
	@Column(name="tag_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TAG_SEQ")
    @SequenceGenerator(sequenceName = "tag_sequence", allocationSize = 1, name = "TAG_SEQ")
	private Long tagId;
	
	@Column(name="tag")
	private String tagName; //they are stored as comma seperated.
	
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="blog_id")
	private Blog blog;
	
	public Tag() { }

	@Override
    public String toString() {
        return "Tag{" +
                "tagId='" + this.tagId + '\'' +
                ", blogId='" + this.blog.getBlogId() + '\'' +
                ", tagName='" + this.tagName + '\'' +
                '}';
    }
	
	public Long getTagId() {
		return tagId;
	}

	public void setTagId(Long tagId) {
		this.tagId = tagId;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}
	
	
}
