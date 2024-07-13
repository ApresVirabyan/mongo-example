package com.example.mongo_example.mongo;

import reactor.core.publisher.Flux;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) {
        Flux.just(1, 2, 3)
                .map(s -> s + 1)
                .subscribe(System.out::println);

        Flux.just("1,2,3", "4,5,6")
                .flatMap(i -> Flux.fromIterable(Arrays.asList(i.split(","))))
                .collect(Collectors.toList())
                .subscribe(System.out::println);

        Flux.range(1, 10)
                .flatMap(v -> {
                    if(v < 5){
                        return Flux.just(v*v);
                    }
                    return Flux.<Integer>error(new IOException("Error: "));
                }).subscribe(System.out::println, Throwable::printStackTrace);

        List<String> list = Arrays.asList("a", "b", "c", "d", "e", "f");

        Flux.fromIterable(list)
                .flatMap(s -> Flux.just(s + "x"))
                .collect(Collectors.toList())
                .subscribe(System.out::println);

        ExecutorService executorService = Executors.newFixedThreadPool(5);


        Future<String> f = executorService.submit(() -> "Hello");
        try {
            f.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } finally {
            executorService.shutdown();
        }
    }
}
