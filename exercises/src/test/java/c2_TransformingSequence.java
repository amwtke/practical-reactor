import org.junit.jupiter.api.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;
import reactor.test.StepVerifier;

/**
 * It's time to do some data manipulation!
 * <p>
 * Read first:
 * <p>
 * https://projectreactor.io/docs/core/release/reference/#which.values
 * <p>
 * Useful documentation:
 * <p>
 * https://projectreactor.io/docs/core/release/reference/#which-operator
 * https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Mono.html
 * https://projectreactor.io/docs/core/release/api/reactor/core/publisher/Flux.html
 *
 * @author Stefan Dragisic
 */
public class c2_TransformingSequence extends TransformingSequenceBase {

    /***
     * Your task is simple:
     *  Increment each number emitted by the numerical service
     */
    @Test
    public void transforming_sequence() {
        Flux<Integer> numbersFlux = numerical_service()
                //todo change only this line
                .map(i -> i + 1);

        //StepVerifier is used for testing purposes
        //ignore it for now, or explore it independently

        //就是DefaultStepVerifierBuilder -> DefaultVerifySubscriber这是一个标准的subscriber，跟lambdaSubscriber一样 -> expectNext方法把预期的值与预期要经过的步骤注册到DefaultVerifySubscriber里面，后面request取数据会校验步骤与数值 -> 调用verifyComplete开始启动publisher.subscribe(DefaultVerifySubscriber)启动整个flux -> 在onSubscribe onNext方法中验证中验证相应的值与步骤。

        //本质上就是启动flux然后拿到数据后的处理其实是校验数据

        StepVerifier.create(numbersFlux)
                .expectNext(2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
                .verifyComplete();
    }

    /***
     * Transform given number sequence to:
     *   - ">": if given number is greater than 0
     *   - "=": if number is equal to 0
     *   - "<": if given number is lesser then 0
     */
    @Test
    public void transforming_sequence_2() {
        Flux<Integer> numbersFlux = numerical_service_2();

        //todo: do your changes here
        Flux<String> resultSequence = numbersFlux.map(i -> {
            if (i > 0) {
                return ">";
            }
            if (i == 0) {
                return "=";
            }
            return "<";
        });

        //don't change code below
        StepVerifier.create(resultSequence)
                .expectNext(">", "<", "=", ">", ">")
                .verifyComplete();
    }

    /**
     * `object_service()` streams sequence of Objects, but if you peek into service implementation, you can see
     * that these items are in fact strings!
     * Casting using `map()` to cast is one way to do it, but there is more convenient way.
     * Remove `map` operator and use more appropriate operator to cast sequence to String.
     */
    @Test
    public void cast() {
        Flux<String> numbersFlux = object_service()
                .cast(String.class);
//                .map(i -> (String) i); //todo: change this line only


        StepVerifier.create(numbersFlux)
                .expectNext("1", "2", "3", "4", "5")
                .verifyComplete();
    }

    /**
     * `maybe_service()` may return some result.
     * In case it doesn't return any result, return value "no results".
     */
    @Test
    public void maybe() {
        Mono<String> result = maybe_service()
                .defaultIfEmpty("no results")
                //todo: change this line only
                ;

        StepVerifier.create(result)
                .expectNext("no results")
                .verifyComplete();
    }

    /**
     * Reduce the values from `numerical_service()` into a single number that is equal to sum of all numbers emitted by
     * this service.
     */
    @Test
    public void sequence_sum() {
        Mono<Integer> sum = numerical_service()
                .parallel()
                .runOn(Schedulers.parallel())
                //todo reduce怎么在多线程下执行？
                .doOnNext(s -> {
                    System.out.println("--Thread - " + Thread.currentThread().getName() + " s=" + s);
                })
                .reduce((a, b) -> {
                    System.out.println("Thread - " + Thread.currentThread().getName() + " a:" + a + " b:" + b + " a+b=" + (a + b));
                    return a + b;
                });

        //don't change code below
        StepVerifier.create(sum)
                .expectNext(55)
                .verifyComplete();
    }

    /***
     *  Reduce the values from `numerical_service()` but emit each intermediary number
     *  Use first Flux value as initial value.
     */
    @Test
    public void sum_each_successive() {
        Flux<Integer> sumEach = numerical_service()
                //todo: do your changes here
                ;

        StepVerifier.create(sumEach)
                .expectNext(1, 3, 6, 10, 15, 21, 28, 36, 45, 55)
                .verifyComplete();
    }

    /**
     * A developer who wrote `numerical_service()` forgot that sequence should start with zero, so you must prepend zero
     * to result sequence.
     * <p>
     * Do not alter `numerical_service` implementation!
     * Use only one operator.
     */
    @Test
    public void sequence_starts_with_zero() {
        Flux<Integer> result = numerical_service()
                //todo: change this line only
                ;

        StepVerifier.create(result)
                .expectNext(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .verifyComplete();
    }
}
