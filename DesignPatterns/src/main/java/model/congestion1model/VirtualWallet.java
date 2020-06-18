package model.congestion1model;

import java.math.BigDecimal;

/**
 * @DESC Domain 领域模型 (充血模型)
 * @author gwh
 */
public class VirtualWallet {
    private Long id;
    private Long createTime = System.currentTimeMillis();

    private BigDecimal balance = BigDecimal.ZERO;

    public VirtualWallet(Long preAllocatedId) {

        this.id = preAllocatedId;
    }

    public BigDecimal balance() {
        return this.balance;
    }

    /**
     * 出账
     * @param amount
     */
    public void debit(BigDecimal amount) throws Exception {
        if (this.balance.compareTo(amount) < 0) {
//            throw new InsufficientBalanceException();
            throw new Exception();
        }
        this.balance.subtract(amount);
    }

    /**
     * 入账
     * @param amount
     */
    public void credit(BigDecimal amount) throws Exception {
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
//            throw new InvalidAmountException();
            throw new Exception();
        }
        this.balance.add(amount);
    }
}