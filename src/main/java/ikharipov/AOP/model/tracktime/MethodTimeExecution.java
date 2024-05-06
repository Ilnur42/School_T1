package ikharipov.AOP.model.tracktime;

import jakarta.persistence.*;

import java.util.Date;
import java.util.UUID;

/**
 * Класс содержит информацию-представление о времени выполнения методов.
 */
@Entity
@Table(name = "methods_track_time", schema = "track_times")
public class MethodTimeExecution {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "method_name")
    private String methodName;

    @Column(name = "execution_time_millis")
    private long executionTimeMillis;

    @Column(name = "created")
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "type")
    private MethodType type;

    public MethodTimeExecution() {
    }
    private MethodTimeExecution(Builder builder) {
        setId(builder.id);
        setMethodName(builder.methodName);
        setExecutionTimeMillis(builder.executionTimeMillis);
        setCreated(builder.created);
        setType(builder.type);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public long getExecutionTimeMillis() {
        return executionTimeMillis;
    }

    public void setExecutionTimeMillis(long executionTimeMillis) {
        this.executionTimeMillis = executionTimeMillis;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public MethodType getType() {
        return type;
    }

    public void setType(MethodType type) {
        this.type = type;
    }

    public static final class Builder {
        private UUID id;
        private String methodName;
        private long executionTimeMillis;
        private Date created;
        private MethodType type;

        public Builder() {
        }

        public Builder withId(UUID val) {
            id = val;
            return this;
        }
        public Builder withMethodName(String val) {
            methodName = val;
            return this;
        }
        public Builder withExecutionTimeMillis(long val) {
            executionTimeMillis = val;
            return this;
        }
        public Builder withCreated(Date val) {
            created = val;
            return this;
        }
        public Builder withMethodType(MethodType val) {
            type = val;
            return this;
        }
        public MethodTimeExecution build() {
            return new MethodTimeExecution(this);
        }
    }
}

