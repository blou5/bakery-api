package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dto.mapper.ChangeReserveLogMapper;
import org.example.dto.request.create.ChangeReserveLogRequestDTO;
import org.example.dto.response.ChangeReserveLogResponseDto;
import org.example.dto.sealedDto.ChangeReserveActionResponse;
import org.example.entity.ChangeReserveLog;
import org.example.services.ChangeReserveLogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reserves")
@RequiredArgsConstructor
public class ChangeReserveLogController {
    private final ChangeReserveLogMapper changeReserveLogMapper;
    private final ChangeReserveLogService changeReserveLogService;


    @GetMapping("/findall")
    public ResponseEntity<Page<ChangeReserveLog>> findAll(@RequestParam(defaultValue = "0") int page,
                                                          @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<ChangeReserveLog> reserves = changeReserveLogService.findAll(pageable);
        if (reserves == null || reserves.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(reserves);
    }

    @GetMapping("/pending-summary")
    public ResponseEntity<List<ChangeReserveLogResponseDto>> getPendingSummary() {
        return ResponseEntity.ok(changeReserveLogService.getPendingSummaryByDenomination());
    }


    @GetMapping("/{id}")
    public ResponseEntity<ChangeReserveLog> findById(@PathVariable Integer id) {
        return changeReserveLogService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/create")
    public ResponseEntity<ChangeReserveActionResponse> create(@RequestBody ChangeReserveLogRequestDTO reserve) {
        ChangeReserveActionResponse save = changeReserveLogService.save(reserve);
        return ResponseEntity.status(HttpStatus.CREATED).body(save);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        changeReserveLogService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
