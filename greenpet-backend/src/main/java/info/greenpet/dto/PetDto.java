package info.greenpet.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

import java.time.Instant;

public class PetDto {

    public static class PetRequest {
        @NotBlank
        private String name;
        @NotBlank
        private String type;
        @PositiveOrZero
        private Integer age;
        private String description;
        private String ownerId;

        // getters/setters
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
    }

    public static class PetResponse {
        private String id;
        private String name;
        private String type;
        private Integer age;
        private String description;
        private String ownerId;
        private Instant createdAt;

        public PetResponse() {}

        // getters/setters
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
}
