package click.blogs.blogapi.respository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import click.blogs.blogapi.model.User;

/**
 * Created by riu364 on 6/29/17.
 */
public interface UserRepository extends CrudRepository<User, String> {

    @Query("select u from User u where u.userName = :userName and u.password = :password")
    User findByUserNameAndPassword(@Param("userName") String userName, @Param("password") String password);

    @Query("select u from User u where u.userName = :userName")
	User findOne(@Param("userName") String userName);
    
}
