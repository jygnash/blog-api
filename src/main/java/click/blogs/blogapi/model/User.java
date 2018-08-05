package click.blogs.blogapi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Created by riu364 on 6/29/17.
 */
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @Column(name = "user_name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name="user_type")
    private String userType;

    public User() {
        //default constructor
    }

    @Override
    public String toString() {
        return "Users{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }

    public User(String userName, String password, String userType) {
        this.userName = userName;
        this.password = password;
        this.userType = userType;
    }

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}


}
