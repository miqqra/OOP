package ru.nsu.krasnikov;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class BenchmarkStreams {
    List<Integer> numbers = Stream.iterate(1000000007, i -> i).limit(100).toList();

    @Benchmark
    public void setupConsistent(Blackhole blackhole) {
        blackhole.consume(new PrimeNumbersConsistent().hasPrimeNumber(numbers));
    }

    @Benchmark
    public void setupParallelStream(Blackhole blackhole) {
        blackhole.consume(new PrimeNumbersParallelStream().hasPrimeNumber(numbers));
    }
}
