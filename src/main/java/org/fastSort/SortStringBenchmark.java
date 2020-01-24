package org.fastSort;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;

@OutputTimeUnit(TimeUnit.SECONDS)
public class SortStringBenchmark {

    @Benchmark
    public void sortLong(SortStringState state) {
        state.sort();
    }

}
