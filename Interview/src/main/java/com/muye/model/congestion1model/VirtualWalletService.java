package com.muye.model.congestion1model;

import java.math.BigDecimal;

/**
 * @author gwh
 */
public class VirtualWalletService {

    /**
     * 通过构造函数或者 IOC 框架注入
     */
    private VirtualWalletRepository walletRepo;
    private VirtualWalletTransactionRepository transactionRepo;

    public VirtualWallet getVirtualWallet(Long walletId) {
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        VirtualWallet wallet = convert(walletEntity);
        return wallet;
    }

    private VirtualWallet convert(VirtualWalletEntity walletEntity) {
        return null;
    }

    public BigDecimal getBalance(Long walletId) {
        VirtualWalletService virtualWalletRepo = null;
        return virtualWalletRepo.getBalance(walletId);
    }

    public void debit(Long walletId, BigDecimal amount) throws Exception {
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        VirtualWallet wallet = convert(walletEntity);
        wallet.debit(amount);
        walletRepo.updateBalance(walletId, wallet.balance());
    }

    public void credit(Long walletId, BigDecimal amount) throws Exception {
        VirtualWalletEntity walletEntity = walletRepo.getWalletEntity(walletId);
        VirtualWallet wallet = convert(walletEntity);
        wallet.credit(amount);
        walletRepo.updateBalance(walletId, wallet.balance());
    }

    public void transfer(Long fromWalletId, Long toWalletId, BigDecimal amount) {
        //... 跟基于贫血模型的传统开发模式的代码一样...
    }
}