package click.blogs.blogapi.respository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import click.blogs.blogapi.model.UsersSession;

public interface UsersSessionRepository extends CrudRepository<UsersSession, Long>{

    @Query("select u from UsersSession u where u.sessionId = :sessionId")
	UsersSession findOne(@Param("sessionId") Long sessionId);

}
