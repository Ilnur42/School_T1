package ikharipov.AOP.model.entity;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

@MappedSuperclass
public class CurrentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created")
    private Date created;

    public CurrentEntity() {
    }

    @PrePersist
    protected void onCreate() {
        created = new Date();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
