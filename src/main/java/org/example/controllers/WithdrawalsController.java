package org.example.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.dto.request.create.WithdrawalsCreateDto;
import org.example.dto.request.update.WithdrawalsUpdateDTO;
import org.example.dto.response.WithdrawalsResponseDTO;
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
    public ResponseEntity<List<WithdrawalsResponseDTO>> findAll() {
        List<Withdrawals> withdrawals = withdrawalsService.findAll();
        if (withdrawals == null || withdrawals.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(withdrawals.stream().map(WithdrawalsResponseDTO::from).toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<WithdrawalsResponseDTO> findById(@PathVariable Integer id) {
        return withdrawalsService.findById(id)
                .map(WithdrawalsResponseDTO::from)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping("/add")
    public ResponseEntity<WithdrawalsResponseDTO> create(@Valid @RequestBody WithdrawalsCreateDto withdrawalsCreateDto) {
        Withdrawals saved = withdrawalsService.save(withdrawalsCreateDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(WithdrawalsResponseDTO.from(saved));
    }

    @GetMapping("/filteredWithdrawals/{date}")
    public ResponseEntity<List<WithdrawalsResponseDTO>> getFilteredWithdrawals(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {

        List<Withdrawals> withdrawFromDate = this.withdrawalsService.getWithdrawFromDate(date);
        return ResponseEntity.ok().body(withdrawFromDate.stream().map(WithdrawalsResponseDTO::from).toList());
    }

    @PutMapping("/{id}")
    public ResponseEntity<WithdrawalsResponseDTO> updateWithdrawal(
            @PathVariable Integer id,
            @Valid @RequestBody WithdrawalsUpdateDTO dto) {
        dto.setWithdrawalId(id);
        Withdrawals updated = withdrawalsService.update(dto);
        return ResponseEntity.ok(WithdrawalsResponseDTO.from(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        withdrawalsService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
