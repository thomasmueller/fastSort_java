package org.fastSort;

import java.util.Arrays;
import java.util.Comparator;

public enum SortAlgorithm {
    JAVA_ARRAY_SORT {
        @Override
        <T> void sort(T[] array, int groupCount, Comparator<T> comp) {
            Arrays.sort(array, comp);
        }
    },
    SAMPLE_SORT {
        @Override
        <T> void sort(T[] array, int groupCount, Comparator<T> comp) {
            SampleSort.sort(array, groupCount, comp);
        }
    };
    
    abstract <T> void sort(T[] array, int groupCount, Comparator<T> comp);
}
