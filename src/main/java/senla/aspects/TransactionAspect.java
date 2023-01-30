package senla.aspects;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import senla.util.ConnectionHolder;

import java.sql.Connection;
import java.sql.SQLException;

@Component
@Aspect
@RequiredArgsConstructor
public class TransactionAspect {

    private final ConnectionHolder connectionHolder;

    @Pointcut("@annotation(senla.annotations.Transaction)")
    public void transactionPointсut(){
    }

    @Before("transactionPointсut()")
    public void before(){
        String threadName = Thread.currentThread().getName();

        try {
            connectionHolder.getConnection(threadName).setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterReturning("transactionPointсut()")
    public void afterReturning(){
        String threadName = Thread.currentThread().getName();

        Connection connection = connectionHolder.getConnection(threadName);
        try {
            connectionHolder.commit(threadName);
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @AfterThrowing("transactionPointсut()")
    public void afterThrowing(){
        String threadName = Thread.currentThread().getName();

        Connection connection = connectionHolder.getConnection(threadName);
        try {
            connectionHolder.rollback(threadName);
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
