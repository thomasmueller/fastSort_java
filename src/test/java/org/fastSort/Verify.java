package org.fastSort;

import java.util.Comparator;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

import org.fastSort.SortAlgorithm;
import org.junit.Test;

public class Verify {

    @Test
    public void test() {
        int size = 10_000_000;
        Long[] data = new Long[size];
        Random r = new Random(1);
        AtomicLong count = new AtomicLong();
        long sum = 0;
        for (int i = 0; i < size; i++) {
            data[i] = r.nextLong();
            sum += data[i];
        }
        SortAlgorithm.SAMPLE_SORT.sort(data, 3500, new Comparator<Long>() {
            @Override
            public int compare(Long o1, Long o2) {
                count.incrementAndGet();
                return o1.compareTo(o2);
            }
        });
        System.out.println("comp " + count);
        long sum2 = data[0];
        for (int i = 1; i < size; i++) {
            sum2 += data[i];
            if (data[i - 1] > data[i]) {
                throw new AssertionError();
            }
        }
        if (sum != sum2) {
            throw new AssertionError();
        }
    }
}
