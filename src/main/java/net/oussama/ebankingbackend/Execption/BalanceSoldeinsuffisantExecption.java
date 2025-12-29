package net.oussama.ebankingbackend.Execption;

public class BalanceSoldeinsuffisantExecption extends RuntimeException {
    public BalanceSoldeinsuffisantExecption(String message) {
        super(message);
    }
}
