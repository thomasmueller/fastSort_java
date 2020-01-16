package org.countSort;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;

@OutputTimeUnit(TimeUnit.SECONDS)
public class SortLongBenchmark {

    @Benchmark
    public void sortLong(SortLongState state) {
        state.sort();
    }

}
