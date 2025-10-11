package com.example.SystemPay.mapper;

import com.example.SystemPay.dto.AccountDtoRequest;
import com.example.SystemPay.dto.AccountDtoResponse;
import com.example.SystemPay.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountDtoMapper {
    Account AccountDtoRequestToAccount(AccountDtoRequest accountDto);
    AccountDtoResponse accountToAccountDtoResponse(Account account);
}