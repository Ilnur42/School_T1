package ikharipov.AOP.model.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "book_shops", schema = "entities")
public class BookShop extends CurrentEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "phone_number")
    private String phoneNumber;

    public BookShop() {
    }

    private BookShop(BookShop.Builder builder) {
        setId(builder.id);
        setName(builder.name);
        setAddress(builder.address);
        setPhoneNumber(builder.phoneNumber);
        setCreated(builder.created);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public static final class Builder {
        private UUID id;
        private String name;
        private String address;
        private String phoneNumber;
        private Date created;

        public Builder() {
        }

        public BookShop.Builder withId(UUID val) {
            id = val;
            return this;
        }

        public BookShop.Builder withName(String val) {
            name = val;
            return this;
        }

        public BookShop.Builder withAddress(String val) {
            address = val;
            return this;
        }

        public BookShop.Builder withPhoneNumber(String val) {
            phoneNumber = val;
            return this;
        }

        public BookShop.Builder withCreated(Date val) {
            created = val;
            return this;
        }

        public BookShop build() {
            return new BookShop(this);
        }
    }
}
