package com.example.SystemPay.service;

import com.example.SystemPay.entity.Card;
import com.example.SystemPay.entity.Transfer;
import com.example.SystemPay.repository.CardRepository;
import com.example.SystemPay.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransferService {

    @Autowired
    private TransferRepository transferRepository;

    public List<Transfer> findAll(){
        return transferRepository.findAll();
    }

    public Transfer findById(long id){
        return transferRepository.findById(id);
    }

    public void insert(Transfer transfer){
        transferRepository.insert(transfer);
    }

    public void update(Transfer transfer){
        transferRepository.update(transfer);
    }

    public void delete(long id){
        transferRepository.delete(id);
    }

}
