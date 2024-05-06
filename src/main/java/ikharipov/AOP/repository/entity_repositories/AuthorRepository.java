package ikharipov.AOP.repository.entity_repositories;

import ikharipov.AOP.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
@Transactional
public interface AuthorRepository extends JpaRepository<Author, UUID> {

    List<Author> findAuthorByFirstName(String firstName);

    List<Author> findAuthorByCreatedBefore(Date date);

    @Modifying
    @Query("update Author a set a.age = ?2 where a.id = ?1")
    void updateAuthorAgeById(UUID id, int age);

    @Modifying
    @Query("delete from Author a where a.id = ?1")
    void removeAuthorById(UUID id);
}
