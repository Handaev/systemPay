package com.example.SystemPay.mapper;

import com.example.SystemPay.dto.AccountDto;
import com.example.SystemPay.entity.Account;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AccountDtoAccountMapper {
    Account accountDtoToAccount(AccountDto accountDto);
    AccountDto accountToAccountDto(Account account);
}