package org.countSort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
public class SortLongState {

    @Param({"100000", "1000000", "10000000"})
    int size;

    @Param({"JAVA_ARRAY_SORT", "COUNT_SORT"})
    SortAlgorithm type;

    // @Param({"2000", "2500", "3000", "3500", "4000", "4500", "5000"})
    int groupCount = 3500;

    Data<Long> data;
    long sum;
    AtomicLong compareCount = new AtomicLong();

    public SortAlgorithm getAlgorithm() {
        return type;
    }

    public void sort() {
        Long[] d = Arrays.copyOf(data.data, data.data.length);
        type.sort(d, groupCount, data.comparator);
    }
    
    @Setup(Level.Trial)
    public void init() {
        data = new Data<Long>();
        data.data = new Long[size];
        Random r = new Random(1);
        for (int i = 0; i < size; i++) {
            data.data[i] = r.nextLong();
            sum += data.data[i];
        }
        data.comparator = new Comparator<Long>() {
              @Override
              public int compare(Long o1, Long o2) {
                  compareCount.incrementAndGet();
                  return o1.compareTo(o2);
              }
          };
    }

}
