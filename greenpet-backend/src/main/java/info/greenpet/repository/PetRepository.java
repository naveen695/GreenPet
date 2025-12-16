package info.greenpet.repository;

import info.greenpet.model.Pet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PetRepository extends MongoRepository<Pet, String> {
    Page<Pet> findAll(Pageable pageable);
}
