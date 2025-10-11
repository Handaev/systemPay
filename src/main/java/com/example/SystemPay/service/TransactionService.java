package com.example.SystemPay.service;

import com.example.SystemPay.dto.TransactionDtoRequest;
import com.example.SystemPay.dto.TransactionDtoResponse;
import com.example.SystemPay.entity.Transaction;
import com.example.SystemPay.mapper.TransactionDtoMapper;
import com.example.SystemPay.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private KafkaTemplate<String, Transaction> kafkaTemplate;

    @Autowired
    private TransactionDtoMapper transactionDtoMapper;

    public List<TransactionDtoResponse> findAll() {
        return transactionRepository.findAll().stream()
                .map(transactionDtoMapper::transactionToTransactionDtoResponse)
                .toList();
    }

    public TransactionDtoResponse findById(long id) {
        return transactionDtoMapper.transactionToTransactionDtoResponse(transactionRepository.findById(id));
    }

    public TransactionDtoResponse insert(TransactionDtoRequest transactionDtoRequest) throws ExecutionException, InterruptedException {
        Transaction transaction = transactionRepository.insert(
                transactionDtoMapper.transactionDtoRequestToTransaction(transactionDtoRequest));
        return transactionDtoMapper.transactionToTransactionDtoResponse(transaction);
    }

    public TransactionDtoResponse update(TransactionDtoRequest transactionDtoRequest) {
        Transaction updatedTransaction = transactionRepository.update(
                transactionDtoMapper.transactionDtoRequestToTransaction(transactionDtoRequest));
        return transactionDtoMapper.transactionToTransactionDtoResponse(updatedTransaction);
    }

    public TransactionDtoResponse delete(long id) {
        Transaction deletedTransaction = transactionRepository.delete(id);
        return transactionDtoMapper.transactionToTransactionDtoResponse(deletedTransaction);
    }

}