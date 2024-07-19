package project.carservice.model.entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import org.hibernate.annotations.JdbcTypeCode;

import java.util.UUID;

import static java.sql.Types.VARCHAR;

@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)
    @JdbcTypeCode(VARCHAR)
    private UUID id;

    protected BaseEntity() {
    }
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
