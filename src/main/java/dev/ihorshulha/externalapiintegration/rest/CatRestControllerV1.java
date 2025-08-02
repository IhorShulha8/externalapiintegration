package dev.ihorshulha.externalapiintegration.rest;

import dev.ihorshulha.externalapiintegration.service.CatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cats")
@RequiredArgsConstructor
public class CatRestControllerV1 {

    private final CatService catService;

    @GetMapping
    public ResponseEntity<?> getCats() {
        return ResponseEntity.ok(catService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCatById(@PathVariable final Long id) {
        return ResponseEntity.ok(catService.getById(id));
    }
}
