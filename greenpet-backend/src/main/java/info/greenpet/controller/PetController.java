package info.greenpet.controller;

import info.greenpet.dto.PetDto.*;
import info.greenpet.model.Pet;
import info.greenpet.service.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;
import java.time.Instant;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/pets")
public class PetController {

    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    // List pets (page & size optional)
    @GetMapping
    public ResponseEntity<?> list(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "createdAt,desc") String sort) {

        // simple sort parsing: "field,dir"
        String[] parts = sort.split(",");
        Sort.Direction dir = parts.length > 1 && "asc".equalsIgnoreCase(parts[1]) ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort s = Sort.by(dir, parts[0]);

        var pageResult = petService.list(page, size, s);
        var dtos = pageResult.getContent().stream().map(this::toResponse).collect(Collectors.toList());
        return ResponseEntity.ok().body(
                java.util.Map.of(
                        "content", dtos,
                        "page", pageResult.getNumber(),
                        "size", pageResult.getSize(),
                        "totalElements", pageResult.getTotalElements(),
                        "totalPages", pageResult.getTotalPages()
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> get(@PathVariable String id) {
        return petService.findById(id)
                .map(p -> ResponseEntity.ok(toResponse(p)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(java.util.Map.of("error", "Pet not found")));
    }

    @PostMapping
    public ResponseEntity<?> create(@Valid @RequestBody PetRequest req) {
        Pet p = new Pet();
        p.setName(req.getName());
        p.setType(req.getType());
        p.setAge(req.getAge());
        p.setDescription(req.getDescription());
        p.setOwnerId(req.getOwnerId());
        p.setCreatedAt(Instant.now());

        Pet saved = petService.create(p);
        return ResponseEntity.created(URI.create("/api/pets/" + saved.getId()))
                .body(toResponse(saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable String id, @Valid @RequestBody PetRequest req) {
        Pet updated = new Pet();
        updated.setName(req.getName());
        updated.setType(req.getType());
        updated.setAge(req.getAge());
        updated.setDescription(req.getDescription());
        updated.setOwnerId(req.getOwnerId());

        return petService.update(id, updated)
                .map(p -> ResponseEntity.ok(toResponse(p)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(java.util.Map.of("error", "Pet not found")));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        petService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private PetResponse toResponse(Pet p) {
        PetResponse r = new PetResponse();
        r.setId(p.getId());
        r.setName(p.getName());
        r.setType(p.getType());
        r.setAge(p.getAge());
        r.setDescription(p.getDescription());
        r.setOwnerId(p.getOwnerId());
        r.setCreatedAt(p.getCreatedAt());
        return r;
    }
}
