package org.fastSort;

import java.util.Arrays;
import java.util.Comparator;

public class SampleSort {

    static <T> void sort(T[] data, int groups, Comparator<T> comp) {
        // number of elements to sort
        int size = data.length;
        
        if (size <= groups) {
            // too small
            Arrays.sort(data, comp);
            return;
        }
        
        // the samples are sentinels to group the data
        T[] sample = Arrays.copyOf(data, groups - 1);
        Arrays.sort(sample, comp);
        
        // (as in the regular counting sort)
        int[] counts = new int[groups];
        
        // for each entry, we remember in which group it belongs to
        int[] pos = new int[size];
        
        // calculate the groups, using binary search, and the counts
        for (int i = 0; i < size; i++) {
            int x = Arrays.binarySearch(sample, data[i], comp);
            x = x >= 0 ? x : (-x - 1);
            pos[i] = x;
            counts[x]++;
        }
        
        // sum up all the counts so we know start positions
        for (int i = 1; i < groups; i++) {
            counts[i] = counts[i - 1] + counts[i];
        }
        
        // we want to remember the sizes
        int[] counts2 = Arrays.copyOf(counts, counts.length);
        
        // place entries in the right bucket
        @SuppressWarnings("unchecked")
        T[] d2 = (T[]) new Object[size];
        for (int i = 0; i < size; i++) {
            d2[--counts[pos[i]]] = data[i];
        }
        
        // free up the positions (not really needed)
        pos = null;

        // copy the data back to the source array
        System.arraycopy(d2, 0, data, 0, size);
        d2 = null;
        
        // sort the buckets
        int start = 0;
        for (int i = 0; i < groups; i++) {
            int next = counts2[i];
            Arrays.sort(data, start, next, comp);
            start = next;
        }
    }
}
