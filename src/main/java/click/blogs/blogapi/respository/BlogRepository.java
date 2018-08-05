package click.blogs.blogapi.respository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import click.blogs.blogapi.model.Blog;
import click.blogs.blogapi.model.User;

public interface BlogRepository extends CrudRepository<Blog, Long> {

	public List<Blog> findByUser(User user);

	@Query("select u from Blog u where u.blogId = :blogId")
	public Blog findOne(@Param("blogId") Long blogId);
}
