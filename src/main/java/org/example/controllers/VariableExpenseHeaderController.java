package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dto.mapper.VariableExpenseHeaderMapper;
import org.example.dto.request.update.VariableExpenseHeaderUpdateDTO;
import org.example.dto.request.create.VariableExpenseHeaderCreateDTO;
import org.example.dto.response.VariableExpenseHeaderResponseDTO;
import org.example.entity.VariableExpenseHeader;
import org.example.services.VariableExpenseHeaderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class VariableExpenseHeaderController {


    private final VariableExpenseHeaderService variableExpenseHeaderService;
    private final VariableExpenseHeaderMapper mapper;

    @GetMapping("findAll")
    public ResponseEntity<List<VariableExpenseHeaderResponseDTO>> findAll() {
        List<VariableExpenseHeader> headers = variableExpenseHeaderService.findAll();
        List<VariableExpenseHeaderResponseDTO> collect = headers.stream().map(mapper::toDTO).collect(Collectors.toList());
        if (collect.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(collect);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VariableExpenseHeader> findById(@PathVariable Integer id) {
        return variableExpenseHeaderService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/add")
    public ResponseEntity<VariableExpenseHeader> create(@RequestBody VariableExpenseHeaderCreateDTO header) {
        System.out.println(header.toString());
        VariableExpenseHeader entity = mapper.toEntity(header);
        VariableExpenseHeader saved = variableExpenseHeaderService.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }


    @PutMapping("/update")
    public ResponseEntity<VariableExpenseHeaderResponseDTO> update(
            @RequestBody VariableExpenseHeaderUpdateDTO updatedDto) {
        Optional<VariableExpenseHeader> existingOpt = variableExpenseHeaderService.findById(updatedDto.getExpenseId());
        if (existingOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        VariableExpenseHeader updated = mapper.toEntity(updatedDto);
        updated.setLog(existingOpt.get().getLog());
        updated.setExpenseHeaders(existingOpt.get().getExpenseHeaders());
        updated.setExpenseDate(updated.getExpenseDate().plusDays(1));
        VariableExpenseHeader saved = variableExpenseHeaderService.save(updated);
        System.out.println(updatedDto.getExpenseDate());
        return ResponseEntity.ok(mapper.toDTO(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        System.out.println(id);
        variableExpenseHeaderService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
