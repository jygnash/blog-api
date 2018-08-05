package click.blogs.blogapi.web;

import java.sql.Timestamp;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import click.blogs.blogapi.model.User;
import click.blogs.blogapi.model.UsersSession;
import click.blogs.blogapi.respository.UserRepository;
import click.blogs.blogapi.respository.UsersSessionRepository;

@RestController
public class LoginController {
	private static final Logger logger = LogManager.getLogger(LoginController.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UsersSessionRepository usersSessionRepository;
	
	@Autowired
	private String basicAuth;
	
	@RequestMapping(value="/login", method=RequestMethod.PUT)
	public ResponseEntity<?> logon(@RequestBody User user) {
		logger.traceEntry();
		ResponseEntity<UsersSession> response = null;
		User existingUser = userRepository.findByUserNameAndPassword(user.getUserName(), user.getPassword());
		if(null != existingUser) {
			
			logger.debug("--- logging on with user: { " + user.toString() + " } ---");
			UsersSession session = new UsersSession();
			session.setUser(existingUser);
			session.setLogonTs(new Timestamp(System.currentTimeMillis()));
			session = usersSessionRepository.save(session);
			response = new ResponseEntity<UsersSession>(session, HttpStatus.OK);
		}else {
			response = new ResponseEntity<UsersSession>(HttpStatus.NOT_FOUND);
		}
		logger.traceExit();
		return response;
	}
	
	
	@RequestMapping(value="/logout", method=RequestMethod.GET)
	public ResponseEntity<?> logout(@RequestBody UsersSession userSession) {
		logger.traceEntry();
		ResponseEntity<UsersSession> response = null;
		UsersSession existingSession = usersSessionRepository.findOne(userSession.getSessionId());
		if(null != existingSession) {
			logger.debug("--- logging on with user: { " + existingSession.toString() + " } ---");
			existingSession.setLogoutTs(new Timestamp(System.currentTimeMillis()));
			existingSession = usersSessionRepository.save(existingSession);
			existingSession.setBasicAuth(this.basicAuth);
			response = new ResponseEntity<UsersSession>(existingSession, HttpStatus.OK);
		}else {
			response = new ResponseEntity<UsersSession>(HttpStatus.NOT_FOUND);
		}
		logger.traceExit();
		return response;
	}
}
