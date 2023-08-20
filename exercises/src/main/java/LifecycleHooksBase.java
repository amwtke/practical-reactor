import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Stefan Dragisic
 */
public class LifecycleHooksBase {

    public Flux<Integer> room_temperature_service() {
        return Flux.interval(Duration.ofMillis(100), Duration.ofMillis(100))
                .take(20)
                .map(i -> {
                    System.out.println("thread:" + Thread.currentThread().getName() + " i:" + i);
                    return ThreadLocalRandom.current().nextInt(10, 30);
                });
    }
}
