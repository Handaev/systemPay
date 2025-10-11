package com.example.SystemPay.mapper;


import com.example.SystemPay.dto.TransferDtoRequest;
import com.example.SystemPay.dto.TransferDtoResponse;
import com.example.SystemPay.entity.Transfer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransferDtoMapper {
    Transfer transferDtoRequestToTransfer(TransferDtoRequest transferDtoRequest);
    TransferDtoResponse transferToTransferDtoResponse(Transfer transfer);
}
