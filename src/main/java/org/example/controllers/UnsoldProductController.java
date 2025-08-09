package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dto.mapper.UnsoldProductMapper;
import org.example.dto.request.create.UnsoldProductCreateDTO;
import org.example.dto.request.update.UnsoldUpdateProductDto;
import org.example.dto.response.UnsoldProductDto;
import org.example.entity.UnsoldProduct;
import org.example.services.UnsoldProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api/unsold-products")
@RequiredArgsConstructor
public class UnsoldProductController {


    private final UnsoldProductService unsoldProductService;
    private final UnsoldProductMapper unsoldProductMapper;

    @GetMapping("findAll")
    public ResponseEntity<List<UnsoldProductDto>> findAll() {
        List<UnsoldProduct> unsoldProducts = unsoldProductService.findAll();
        List<UnsoldProductDto> unsoldProductsDto = unsoldProducts.stream().map(unsoldProductMapper::toDto)
                .collect(toList());
        if (unsoldProducts.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(unsoldProductsDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnsoldProduct> findById(@PathVariable Integer id) {
        return unsoldProductService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/add")
    public ResponseEntity<UnsoldProductDto> create(@RequestBody UnsoldProductCreateDTO unsoldProduct) {
        UnsoldProduct entity = unsoldProductMapper.toEntity(unsoldProduct);
        UnsoldProduct saved = unsoldProductService.save(entity);
        List<UnsoldProductDto> list = unsoldProductService.findWithDetailsById(saved.getUnsoldId()).stream().map(unsoldProductMapper::toDto).toList();
        System.out.println(list.get(0).toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(list.get(0));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UnsoldProductDto> update(
            @PathVariable Integer id,
            @RequestBody UnsoldUpdateProductDto unsoldUpdateProductDto) {

        Optional<UnsoldProduct> existing = unsoldProductService.findWithDetailsById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        UnsoldProduct unsoldProduct = existing.get();
        unsoldProduct.setQuantityUnsold(unsoldUpdateProductDto.getQuantityUnsold());
        UnsoldProduct save = unsoldProductService.save(unsoldProduct);
        UnsoldProductDto dto = unsoldProductMapper.toDto(save);
        System.out.println(dto.toString());
        return ResponseEntity.ok(dto);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        unsoldProductService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
