package info.greenpet.service;

import info.greenpet.model.Pet;
import info.greenpet.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PetService {

    private final PetRepository petRepository;

    @Autowired
    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public Page<Pet> list(int page, int size, Sort sort) {
        Pageable pageable = PageRequest.of(page, size, sort);
        return petRepository.findAll(pageable);
    }

    public Optional<Pet> findById(String id) {
        return petRepository.findById(id);
    }

    public Pet create(Pet pet) {
        return petRepository.save(pet);
    }

    public Optional<Pet> update(String id, Pet updated) {
        return petRepository.findById(id).map(existing -> {
            existing.setName(updated.getName());
            existing.setType(updated.getType());
            existing.setAge(updated.getAge());
            existing.setDescription(updated.getDescription());
            existing.setOwnerId(updated.getOwnerId());
            return petRepository.save(existing);
        });
    }

    public void delete(String id) {
        petRepository.deleteById(id);
    }
}
