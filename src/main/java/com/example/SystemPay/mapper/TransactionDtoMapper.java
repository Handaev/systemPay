package com.example.SystemPay.mapper;

import com.example.SystemPay.dto.TransactionDtoRequest;
import com.example.SystemPay.dto.TransactionDtoResponse;
import com.example.SystemPay.entity.Transaction;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransactionDtoMapper {
    Transaction transactionDtoRequestToTransaction(TransactionDtoRequest transaction);
    TransactionDtoResponse transactionToTransactionDtoResponse(Transaction transaction);
}
