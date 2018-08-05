package click.blogs.blogapi.web;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import click.blogs.blogapi.model.Blog;
import click.blogs.blogapi.model.User;
import click.blogs.blogapi.respository.BlogRepository;
import click.blogs.blogapi.respository.UserRepository;

@RestController
public class BlogController {
	private static final Logger logger = LogManager.getLogger(BlogController.class);
	
	@Autowired
	private BlogRepository blogRepository;
	
	@Autowired
	private UserRepository usersRepository;
	
	@RequestMapping(
            value = "/users/**",
            method = RequestMethod.OPTIONS
    )
    public ResponseEntity handle() {
        return new ResponseEntity(HttpStatus.OK);
    }
	
	@RequestMapping(value="/users/{userName}/blogs", method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<Blog>> listByUserName(@PathVariable String userName) {
		logger.traceEntry();
		User aUser = usersRepository.findOne(userName); 
		List<Blog> blogs = blogRepository.findByUser(aUser);
		ResponseEntity<List<Blog>> response = new ResponseEntity<List<Blog>>(blogs,  HttpStatus.OK);
		return response;
	}
	
	
	@RequestMapping(value="/users/{userName}/blogs", method=RequestMethod.PUT)
	public ResponseEntity<?> create(@PathVariable String userName, @RequestBody Blog blog) {
		logger.traceEntry();
		User aUser = usersRepository.findOne(userName); 
		blog.setUser(aUser);
		blogRepository.save(blog);
		ResponseEntity<?> response = new ResponseEntity<String>(HttpStatus.OK);
		logger.traceExit();
		return response;
	}
	
	
	@RequestMapping(value="/users/{userName}/blogs/{blogId}", method=RequestMethod.GET)
	public ResponseEntity<Blog> getABlog(@PathVariable String userName, @PathVariable Long blogId) {
		logger.traceEntry();
		Blog existingBlog = blogRepository.findOne(blogId);
		ResponseEntity<Blog> response = new ResponseEntity<Blog>(existingBlog, HttpStatus.OK);
		logger.traceExit();
		return response;
	}
	
	
	@RequestMapping(value="/users/{userName}/blogs/{blogId}", method=RequestMethod.POST)
	public ResponseEntity<Blog> update(@PathVariable String userName, @PathVariable Long blogId, @RequestBody Blog blog) {
		logger.traceEntry();
		User aUser = usersRepository.findOne(userName); 
		Blog existingBlog = blogRepository.findOne(blogId);
		if(null != existingBlog && existingBlog.getBlogId().equals(blogId)) {
			if(existingBlog.getUser().getUserName().equals(aUser.getUserName())) {
				existingBlog.setBlogPost(blog.getBlogPost());
				existingBlog.setBlogDescription(blog.getBlogDescription());
				blogRepository.save(existingBlog);
			}
		}
		ResponseEntity<Blog> response = new ResponseEntity<Blog>(existingBlog, HttpStatus.OK);
		logger.traceExit();
		return response;
	}
	
	
	@RequestMapping(value="/users/blogs", method=RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<Blog>> listAll() {
		logger.traceEntry();
		List<Blog> blogs = null;
		try {
			blogs = StreamSupport.stream(blogRepository.findAll().spliterator(), false).collect(Collectors.toList());
		}catch(Exception exceptions) {
			exceptions.printStackTrace();
		}
		blogs.forEach(System.out::println);
		if(logger.isDebugEnabled()) blogs.forEach(logger::debug);
		ResponseEntity<List<Blog>> response = new ResponseEntity<List<Blog>>(blogs,  HttpStatus.OK);
		logger.traceExit();
		return response;
	}
	
	
}
