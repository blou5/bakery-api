package org.example.controllers;
import lombok.RequiredArgsConstructor;
import org.example.dto.mapper.ProductionMapper;
import org.example.dto.request.create.ProductCreateDTO;
import org.example.dto.request.create.ProductionCreateDTO;
import org.example.dto.request.update.ProductProductionUpdate;
import org.example.dto.response.ProductionDto;
import org.example.entity.ProductProduction;
import org.example.services.ProductProductionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/production")
@RequiredArgsConstructor
public class ProductProductionController {

    private final ProductProductionService productProductionService;
    private final ProductionMapper productionMapper;
    @GetMapping("/all")
    public ResponseEntity<List<ProductionDto>> findAll() {

        List<ProductionDto> productions = productProductionService.findAll()
                .stream()
                .map(productionMapper::toDto)
                .collect(Collectors.toList());
        if (productions.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(productions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductProduction> findById(@PathVariable Integer id) {
        return productProductionService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<ProductionDto> create(@RequestBody ProductionCreateDTO production) {
        ProductProduction entity = productionMapper.toEntity(production);
        ProductProduction saved = productProductionService.save(entity);
        Optional<ProductProduction> byId = productProductionService.findDetailById(saved.getProductionId());
        ProductionDto dto = productionMapper.toDto(byId.get());
        return ResponseEntity.status(HttpStatus.CREATED).body(dto);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ProductionDto> update(
            @PathVariable Integer id,
            @RequestBody ProductProductionUpdate production) {

        Optional<ProductProduction> existing = productProductionService.findById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        System.out.println(production.toString());
        // Map DTO to Entity, preserving the existing ID
        ProductProduction updated = productionMapper.dtoToEntity(production);
        updated.setProductionId(id);

        ProductProduction saved = productProductionService.save(updated);
        Optional<ProductProduction> byId = productProductionService.findDetailById(saved.getProductionId());
        ProductionDto dto = productionMapper.toDto(byId.orElse(saved));

        return ResponseEntity.ok(dto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        productProductionService.deleteById(id);
        System.out.println(id);
        return ResponseEntity.noContent().build();
    }
}
