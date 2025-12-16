package info.greenpet.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.Instant;

@Document(collection = "pets")
public class Pet {
    @Id
    private String id;

    private String name;
    private String type; // e.g., "Dog", "Cat"
    private Integer age; // years
    private String description;
    private String ownerId; // reference to User.id or email
    private Instant createdAt;

    public Pet() { this.createdAt = Instant.now(); }

    // getters / setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getOwnerId() { return ownerId; }
    public void setOwnerId(String ownerId) { this.ownerId = ownerId; }
    public Instant getCreatedAt() { return createdAt; }
    public void setCreatedAt(Instant createdAt) { this.createdAt = createdAt; }
}
