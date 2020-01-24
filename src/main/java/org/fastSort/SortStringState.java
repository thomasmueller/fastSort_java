package org.fastSort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import org.openjdk.jmh.annotations.*;

@State(Scope.Benchmark)
public class SortStringState {

    @Param({"100000", "1000000", "10000000"})
    int size;

    @Param({"JAVA_ARRAY_SORT", "COUNT_SORT"})
    SortAlgorithm type;

    // @Param({"2000", "2500", "3000", "3500", "4000", "4500", "5000"})
    int groupCount = 3500;

    Data<String> data;
    long sum;
    AtomicLong compareCount = new AtomicLong();

    public SortAlgorithm getAlgorithm() {
        return type;
    }

    public void sort() {
        String[] d = Arrays.copyOf(data.data, data.data.length);
        type.sort(d, groupCount, data.comparator);
    }
    
    @Setup(Level.Trial)
    public void init() {
        data = new Data<String>();
        data.data = new String[size];
        Random r = new Random(1);
        for (int i = 0; i < size; i++) {
            data.data[i] = "X" + r.nextLong();
            sum += data.data[i].hashCode();
        }
        data.comparator = new Comparator<String>() {
              @Override
              public int compare(String o1, String o2) {
                  compareCount.incrementAndGet();
                  return o1.compareTo(o2);
              }
          };
    }

}
