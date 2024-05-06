package ikharipov.AOP.repository.entity_repositories;

import ikharipov.AOP.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
    List<Book> findBooksByName(String bookName);

    List<Book> findBooksByCreatedBefore(Date date);

    @Modifying
    @Query("update Book b set b.count = ?2 where b.id = ?1")
    void updateBooksCountById(UUID id, int count);

    @Modifying
    @Query("delete from Book b where b.id = ?1")
    void removeBookById(UUID id);

    @Query("select b from Book b where b.bookShop.id = ?1")
    List<Book> findBooksByBookShopId(UUID id);
}