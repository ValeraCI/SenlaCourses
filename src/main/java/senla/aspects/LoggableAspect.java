package senla.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
@Aspect
public class LoggableAspect {
    private final Logger logger = LoggerFactory.getLogger(LoggableAspect.class);

    @Pointcut("@annotation(senla.annotations.Loggable)")
    public void transactionLoggable(){
    }

    @Around("transactionLoggable()")
    public Object afterReturning(ProceedingJoinPoint proceedingJoinPoint){
        Object result;

        try {
            result = proceedingJoinPoint.proceed();
        } catch (Throwable e) {
            logger.error("Method {} was throwing exception {}", proceedingJoinPoint.getSignature().getName(),
                    e.getMessage());
            throw new RuntimeException(e);
        }

        if(Objects.isNull(result)) {
            logger.info("Method {} was finished", proceedingJoinPoint.getSignature().getName());
        }
        else {
            logger.info("Method {} was finished and return {}", proceedingJoinPoint.getSignature().getName(),
                    result);
        }
        return result;
    }

}
