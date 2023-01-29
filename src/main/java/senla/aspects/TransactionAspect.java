package senla.aspects;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import senla.util.ConnectionHolder;

@Component
@Aspect
@RequiredArgsConstructor
public class TransactionAspect {

    private final ConnectionHolder connectionHolder;

    @Pointcut("@annotation(senla.annotations.Transaction)")
    public void transactionPointсut(){
    }

    @AfterReturning("transactionPointсut()")
    public void afterReturning(){
        String threadName = Thread.currentThread().getName();

        connectionHolder.commit(threadName);
    }

    @AfterThrowing("transactionPointсut()")
    public void afterThrowing(){
        String threadName = Thread.currentThread().getName();

        connectionHolder.rollback(threadName);
    }
}
