package org.example.controllers;

import  lombok.RequiredArgsConstructor;
import org.example.dto.mapper.DailyCashLogMapper;
import org.example.dto.request.create.DailyCashLogCreateDTO;
import org.example.entity.DailyCashLog;
import org.example.services.DailyCashLogService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/daily-cash")
@RequiredArgsConstructor
public class DailyCashLogController {

    private final DailyCashLogService dailyCashLogService;
    private final DailyCashLogMapper dailyCashLogMapper;

    @GetMapping("/find")
    public ResponseEntity<List<DailyCashLog>> findAll() {
        List<DailyCashLog> logs = dailyCashLogService.findAll();
        if (logs == null || logs.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(logs); // 200 OK
    }

    @GetMapping("/{id}")
    public ResponseEntity<DailyCashLog> findById(@PathVariable Integer id) {
        return dailyCashLogService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<DailyCashLog> save(@RequestBody DailyCashLogCreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(dailyCashLogService.create(dto));
    }

    @PutMapping("/{logId}")
    public ResponseEntity<DailyCashLog> update(
            @PathVariable Integer logId,
            @RequestBody DailyCashLog entity
    ) {
        DailyCashLog updated = dailyCashLogService.update(logId, entity);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        dailyCashLogService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/findLast/{date}")
    public ResponseEntity<DailyCashLog> getLastCashLog(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return dailyCashLogService.getLastCashLog(date)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @GetMapping("/getEstimatedMoney/{id}")
    public ResponseEntity<Integer> getEstimatedMoney(@PathVariable Integer id) {
        int expectedAmount = dailyCashLogService.calculateExpectedCash(id);
        return ResponseEntity.ok(expectedAmount);
    }
}
