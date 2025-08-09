package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dto.mapper.VariableExpenseItemMapper;
import org.example.dto.request.create.VariableExpenseItemCreateDTO;
import org.example.dto.request.update.VariableExpenseItemUpdateDTO;
import org.example.entity.VariableExpenseItem;
import org.example.services.VariableExpenseItemService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/expenseItem")
@RequiredArgsConstructor
public class VariableExpenseItemController {

    private final VariableExpenseItemService variableExpenseItemService;
    private final VariableExpenseItemMapper variableExpenseItemMapper;
    @GetMapping("findAll")
    public ResponseEntity<List<VariableExpenseItem>> findAll() {
        List<VariableExpenseItem> headers = variableExpenseItemService.findAll();
        if (headers == null || headers.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(headers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VariableExpenseItem> findById(@PathVariable Integer id) {
        return variableExpenseItemService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/add")
    public ResponseEntity<VariableExpenseItem> create(@RequestBody VariableExpenseItemCreateDTO header) {
        VariableExpenseItem entity = variableExpenseItemMapper.toEntity(header);
        VariableExpenseItem saved = variableExpenseItemService.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved );
    }


    @PutMapping("/update")
    public ResponseEntity<VariableExpenseItem> update(@RequestBody VariableExpenseItemUpdateDTO updateDTO) {
        System.out.println(updateDTO);
        VariableExpenseItem entity = variableExpenseItemMapper.updateEntity(updateDTO);
        VariableExpenseItem saved = variableExpenseItemService.save(entity);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        variableExpenseItemService.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
