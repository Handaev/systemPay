package com.example.SystemPay.service;

import com.example.SystemPay.dto.TransferDtoRequest;
import com.example.SystemPay.dto.TransferDtoResponse;
import com.example.SystemPay.entity.Transfer;
import com.example.SystemPay.mapper.TransferDtoMapper;
import com.example.SystemPay.repository.TransferRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Service
public class TransferService {

    @Autowired
    private TransferRepository transferRepository;

    @Autowired
    private KafkaTemplate<String, Transfer> kafkaTemplate;

    @Autowired
    private TransferDtoMapper transferDtoMapper;

    public List<TransferDtoResponse> findAll() {
        return transferRepository.findAll().stream()
                .map(transferDtoMapper::transferToTransferDtoResponse)
                .toList();
    }

    public TransferDtoResponse findById(long id) {
        Transfer transfer = transferRepository.findById(id);
        return transferDtoMapper.transferToTransferDtoResponse(transfer);
    }

    public TransferDtoResponse insert(TransferDtoRequest transferDtoRequest) throws ExecutionException, InterruptedException {
        Transfer transfer = transferRepository.insert(
                transferDtoMapper.transferDtoRequestToTransfer(transferDtoRequest)
        );
        TransferDtoResponse response = transferDtoMapper.transferToTransferDtoResponse(transfer);

        CompletableFuture<SendResult<String, Transfer>> future =
                kafkaTemplate.send("transfer-created-topic",
                        transfer.getId().toString(),
                        transfer);

        return response;
    }

    public TransferDtoResponse update(TransferDtoRequest transferDtoRequest) {
        Transfer updatedTransfer = transferRepository.update(
                transferDtoMapper.transferDtoRequestToTransfer(transferDtoRequest));
        return transferDtoMapper.transferToTransferDtoResponse(updatedTransfer);
    }

    public TransferDtoResponse delete(long id) {
        return transferDtoMapper.transferToTransferDtoResponse(transferRepository.delete(id));
    }

//    public List<TransferDtoResponse> findByFromAccount(String fromAccount) {
//        return transferRepository.findByFromAccount(fromAccount).stream()
//                .map(transferDtoMapper::transferToTransferDtoResponse)
//                .toList();
//    }
//
//    public List<TransferDtoResponse> findByToAccount(String toAccount) {
//        return transferRepository.findByToAccount(toAccount).stream()
//                .map(transferDtoMapper::transferToTransferDtoResponse)
//                .toList();
//    }
//
//    public List<TransferDtoResponse> findByStatus(String status) {
//        return transferRepository.findByStatus(status).stream()
//                .map(transferDtoMapper::transferToTransferDtoResponse)
//                .toList();
//    }
//
//    public List<TransferDtoResponse> findByAmountRange(Double minAmount, Double maxAmount) {
//        return transferRepository.findByAmountRange(minAmount, maxAmount).stream()
//                .map(transferDtoMapper::transferToTransferDtoResponse)
//                .toList();
//    }
}