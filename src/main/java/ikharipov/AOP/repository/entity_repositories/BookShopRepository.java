package ikharipov.AOP.repository.entity_repositories;

import ikharipov.AOP.model.entity.BookShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface BookShopRepository extends JpaRepository<BookShop, UUID> {

    List<BookShop> findBookShopsByName(String name);

    List<BookShop> findBookShopByCreatedBefore(Date date);

    @Modifying
    @Query("update BookShop bs set bs.address = ?2 where bs.id = ?1")
    void updateBookShopAddressById(UUID id, String newAddress);

    @Modifying
    @Query("delete from BookShop bs where bs.id = ?1")
    void removeBookShopById(UUID id);
}
