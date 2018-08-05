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

import click.blogs.blogapi.model.User;
import click.blogs.blogapi.respository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {
	private static final Logger logger = LogManager.getLogger(UserController.class);
	
	@Autowired
    private UserRepository repository;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<User>> findAll(){
		List<User> users = StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
		if(logger.isDebugEnabled()) users.forEach(logger::debug);
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	
	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<String> create(@RequestBody User user) {
		logger.traceEntry();
		repository.save(user);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	
	@RequestMapping(value = "/{userName}", method = RequestMethod.POST)
	public ResponseEntity<String> update(@PathVariable String userName, @RequestBody User user) {
		repository.save(user);
		return new ResponseEntity<String>(HttpStatus.OK);
	}
	
	
	
	
}
