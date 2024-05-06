package ikharipov.AOP.model.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "authors", schema = "entities")
public class Author extends CurrentEntity {

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "age")
    private int age;

    @Column(name = "country")
    private String country;

    public Author() {
    }
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    private Author(Author.Builder builder) {
        setId(builder.id);
        setFirstName(builder.firstName);
        setLastName(builder.lastName);
        setAge(builder.age);
        setCountry(builder.country);
        setCreated(builder.created);
    }

    public static final class Builder {
        private UUID id;
        private String firstName;
        private String lastName;
        private int age;
        private String country;

        private Date created;

        public Builder() {
        }

        public Author.Builder withId(UUID val) {
            id = val;
            return this;
        }

        public Author.Builder withFirstName(String val) {
            firstName = val;
            return this;
        }

        public Author.Builder withLastName(String val) {
            lastName = val;
            return this;
        }

        public Author.Builder withAge(int val) {
            age = val;
            return this;
        }

        public Author.Builder withCountry(String val) {
            country = val;
            return this;
        }

        public Author.Builder withCreated(Date val) {
            created = val;
            return this;
        }

        public Author build() {
            return new Author(this);
        }
    }
}
