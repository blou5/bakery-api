package org.example.services;

import org.example.dto.mapper.ChangeReserveLogMapper;
import org.example.dto.mapper.ChangeReserveLogMapperImpl;
import org.example.dto.request.LiquidifyRequestDTO;
import org.example.entity.ChangeReserveLog;
import org.example.entity.ChangeReserveLog.Status;
import org.example.repositories.ChangeReserveLogRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

class ChangeReserveLogServiceTest {

    private ChangeReserveLogRepository repository;
    private ChangeReserveLogService service;

    @BeforeEach
    void setup() {
        repository = mock(ChangeReserveLogRepository.class);
        ChangeReserveLogMapper changeReserveLogMapper = new ChangeReserveLogMapperImpl();
        service = new ChangeReserveLogService(repository, changeReserveLogMapper);
    }

    private ChangeReserveLog createLog(int quantity, int denomination, Status status) {
        int amount = quantity * denomination;
        ChangeReserveLog log = new ChangeReserveLog();
        log.setQuantity(quantity);
        log.setDenomination(denomination);
        log.setAmount(amount);
        log.setStatus(status);
        log.setCreatedAt(LocalDateTime.now());
 // can be set if needed
        return log;
    }

    @Test
    void test_fullLiquidification() {
        var log1 = createLog(10, 100, Status.PENDING);
        var log2 = createLog(5, 100, Status.PENDING);
        when(repository.findByStatusAndDenominationOrdered(Status.PENDING, 100))
                .thenReturn(List.of(log1, log2));

        var request = new LiquidifyRequestDTO();
        request.setDenomination(100);
        request.setTargetAmount(1500);

        var response = service.liquidify(request);

        assertThat(response.getLiquidifiedAmount()).isEqualTo(1500);
        verify(repository).saveAll(List.of(log1, log2));
        assertThat(log1.getStatus()).isEqualTo(Status.LIQUIDIFIED);
        assertThat(log2.getStatus()).isEqualTo(Status.LIQUIDIFIED);
    }

    @Test
    void test_partialLiquidificationWithSplitting() {
        var log1 = createLog(10, 100, Status.PENDING);
        var log2 = createLog(5, 100, Status.PENDING);
        when(repository.findByStatusAndDenominationOrdered(Status.PENDING, 100))
                .thenReturn(List.of(log1, log2));

        var request = new LiquidifyRequestDTO();
        request.setDenomination(100);
        request.setTargetAmount(1200);

        ArgumentCaptor<List<ChangeReserveLog>> captor = ArgumentCaptor.forClass(List.class);

        var response = service.liquidify(request);

        verify(repository).saveAll(captor.capture());
        List<ChangeReserveLog> saved = captor.getValue();

        assertThat(response.getLiquidifiedAmount()).isEqualTo(1200);
        assertThat(log1.getStatus()).isEqualTo(Status.LIQUIDIFIED);
        assertThat(log2.getQuantity()).isEqualTo(3);
        assertThat(log2.getAmount()).isEqualTo(300);
        System.out.println(log2.toString());
        verify(repository).save(argThat(split -> split.getAmount() == 200 && split.getStatus() == Status.LIQUIDIFIED));
    }

    @Test
    void test_noLiquidification_whenTooSmallTarget() {
        var log1 = createLog(10, 100, Status.PENDING);
        when(repository.findByStatusAndDenominationOrdered(Status.PENDING, 100))
                .thenReturn(List.of(log1));

        var request = new LiquidifyRequestDTO();
        request.setDenomination(100);
        request.setTargetAmount(50);

        var response = service.liquidify(request);

        assertThat(response.getLiquidifiedAmount()).isEqualTo(0);
        verify(repository, never()).saveAll(any());
    }

    @Test
    void test_noLiquidification_whenNoRecords() {
        when(repository.findByStatusAndDenominationOrdered(Status.PENDING, 100)).thenReturn(List.of());

        var request = new LiquidifyRequestDTO();
        request.setDenomination(100);
        request.setTargetAmount(500);

        var response = service.liquidify(request);

        assertThat(response.getLiquidifiedAmount()).isEqualTo(0);
        verify(repository, never()).saveAll(any());
    }

}