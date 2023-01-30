package senla.eventListeners;

import lombok.AllArgsConstructor;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import senla.annotations.Loggable;
import senla.util.ConnectionHolder;

@Component
@AllArgsConstructor
public class ContextEventListener {
    private final ConnectionHolder connectionHolder;

    @Loggable
    @EventListener(ContextClosedEvent.class)
    public void handleContextRefreshEvent() {
        connectionHolder.close();
    }
}
