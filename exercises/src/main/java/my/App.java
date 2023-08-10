package my;

import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class App {
    public static void main(String[] args) {
//        Flux<String> flux= Flux.never();
        Flux<String> flux = Flux.just("x", "j");
        flux.map(t -> t + "333").map(s -> s + "1111").subscribe(System.out::println);




//        flux.flatMap(e -> Flux.just(e + "+1")//.delayElements(Duration.ofSeconds(1))
//                )
//                .subscribe(System.out::println);
//        try {
//            TimeUnit.SECONDS.sleep(10);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }
}
