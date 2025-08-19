package org.example.controllers;

import lombok.RequiredArgsConstructor;
import org.example.dto.request.create.WithdrawalsCreateDto;
import org.example.dto.request.update.WithdrawalsUpdateDTO;
import org.example.entity.Withdrawals;
import org.example.services.WithdrawalsService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/withdrawals")
@RequiredArgsConstructor
public class WithdrawalsController {


    private final WithdrawalsService withdrawalsService;

    @GetMapping("/findAll")
    public ResponseEntity<List<Withdrawals>> findAll() {
        List<Withdrawals> withdrawals = withdrawalsService.findAll();
        if (withdrawals == null || withdrawals.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(withdrawals);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Withdrawals> findById(@PathVariable Integer id) {
        return withdrawalsService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/add")
    public ResponseEntity<Withdrawals> create(@RequestBody WithdrawalsCreateDto withdrawalsCreateDto) {
        Withdrawals saved = withdrawalsService.save(withdrawalsCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/filteredWithdrawals/{date}")
    public ResponseEntity<List<Withdrawals>> getFilteredWithdrawals(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        List<Withdrawals> withdrawFromDate = this.withdrawalsService.getWithdrawFromDate(date);
        return ResponseEntity.ok().body(withdrawFromDate);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Withdrawals> updateWithdrawal(
            @PathVariable Integer id,
            @RequestBody WithdrawalsUpdateDTO dto) {
        System.out.println(id);
        System.out.println(dto.toString());
        Withdrawals updated = withdrawalsService.update(dto);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        withdrawalsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
