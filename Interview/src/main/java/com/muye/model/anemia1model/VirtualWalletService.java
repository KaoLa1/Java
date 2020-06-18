package com.muye.model.anemia1model;

import com.muye.model.congestion1model.VirtualWalletEntity;
import com.muye.model.congestion1model.VirtualWalletRepository;
import com.muye.model.congestion1model.VirtualWalletTransactionRepository;

import java.math.BigDecimal;

/**
 * @author gwh
 * @DESC
 */
public class VirtualWalletService {
    private VirtualWalletRepository walletRepo;
    private VirtualWalletTransactionRepository transactionRepo;

    public VirtualWalletBo getVirtualWallet(Long walletId) {
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        VirtualWalletBo walletBo = convert(walletEntity);
        return walletBo;
    }

    private VirtualWalletBo convert(VirtualWalletEntity walletEntity) {
        return null;
    }

    public BigDecimal getBalance(Long walletId) {
        VirtualWalletService virtualWalletRepo = null;
        return virtualWalletRepo.getBalance(walletId);
    }

    public void debit(Long walletId, BigDecimal amount) throws Exception {
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        BigDecimal balance = walletEntity.getBalance();
        if (balance.compareTo(amount) < 0) {
            throw new Exception();
        }
        walletRepo.updateBalance(walletId, balance.subtract(amount));
    }

    public void credit(Long walletId, BigDecimal amount) {
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        BigDecimal balance = walletEntity.getBalance();
        walletRepo.updateBalance(walletId, balance.add(amount));
    }

    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount) throws Exception {
        VirtualWalletTransactionEntity transactionEntity = new VirtualWalletTransactionEntity();
        transactionEntity.setAmount(amount);
        transactionEntity.setCreateTime(System.currentTimeMillis());
        transactionEntity.setFromWalletId(fromWalletId);
        transactionEntity.setToWalletId(toWalletId);
        transactionEntity.setStatus(1);


        Long transactionId = transactionRepo.saveTransaction(transactionEntity);
        try {
            debit(fromWalletId, amount);
            credit(toWalletId, amount);
        } catch (Exception e) {
            transactionRepo.updateStatus(transactionId, 1);

        }
        transactionRepo.updateStatus(transactionId, 1);
    }
}