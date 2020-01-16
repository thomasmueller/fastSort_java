package org.countSort;

import java.util.Arrays;
import java.util.Comparator;

public enum SortAlgorithm {
    JAVA_ARRAY_SORT {
        @Override
        <T> void sort(T[] array, int groupCount, Comparator<T> comp) {
            Arrays.sort(array, comp);
        }
    },
    COUNT_SORT {
        @Override
        <T> void sort(T[] array, int groupCount, Comparator<T> comp) {
            CountingComparisonSort.sort(array, groupCount, comp);
        }
    };
    
    abstract <T> void sort(T[] array, int groupCount, Comparator<T> comp);
}
