package click.blogs.blogapi.model;

import java.io.Serializable;
import java.sql.Timestamp;

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
import javax.persistence.Transient;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "users_session")
public class UsersSession implements Serializable {
	
	@Id
	@Column(name="session_id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USERS_SESSION_SEQ")
    @SequenceGenerator(sequenceName = "users_session_sequence", allocationSize = 1, name = "USERS_SESSION_SEQ")
	private Long sessionId;
	
	@Column(name="logon_ts")
	private Timestamp logonTs;
	
	@Column(name="logout_ts")
	private Timestamp logoutTs;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="user_name")
	private User user;
	
	@Transient
	@JsonSerialize
	@JsonDeserialize
	@Value("${security.user.password}")
	private String basicAuth;
	
	public UsersSession() {	
	}

	public Long getSessionId() {
		return sessionId;
	}

	public void setSessionId(Long sessionId) {
		this.sessionId = sessionId;
	}

	public Timestamp getLogonTs() {
		return logonTs;
	}

	public void setLogonTs(Timestamp logonTs) {
		this.logonTs = logonTs;
	}

	public Timestamp getLogoutTs() {
		return logoutTs;
	}

	public void setLogoutTs(Timestamp logoutTs) {
		this.logoutTs = logoutTs;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getBasicAuth() {
		return basicAuth;
	}

	public void setBasicAuth(String basicAuth) {
		this.basicAuth = basicAuth;
	}

	@Override
    public String toString() {
        return "UsersSession{" +
                "userName='" + this.user.toString() + '\'' +
                ", logon_ts='" + this.logonTs + '\'' +
                ", sessionId='" + this.sessionId + '\'' +
                '}';
    }
	
}
