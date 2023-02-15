package ru.nsu.krasnikov;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.infra.Blackhole;

@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class BenchmarkThread {
    @Param({"1", "2", "3", "4", "5", "6", "7", "8"})
    private int coreNumber = 8;
    List<Integer> numbers = Stream.iterate(1000000007, i -> i).limit(100).toList();

    @Benchmark
    public void setupThreads(Blackhole blackhole) throws InterruptedException {
        blackhole.consume(new PrimeNumbersThread(coreNumber).hasPrimeNumber(numbers));
    }
}
