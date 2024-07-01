package com.examples.ibmtest;

import java.util.*;
import java.util.stream.Collectors;

public class Example1 {
    //consider a 1 - based array of n integers, arr [ n ] . ﻿for each element arr [ i ] ﻿where 1 < = ﻿i < ﻿n
    // determine the value of arr [ i ] arr [ i + 1 ] ﻿modulo ( 1 0 ^ 9 + 7 ) ﻿return the lowest
    // index of the highest modulo value.

    public static void main(String[] args) {

        int lowestIndex = findLowestIndex(new int[]{3, 5, 4, 5, 2, 10});

        System.out.println("minIndex is " + lowestIndex);
    }

    public static int findLowestIndex(int arr[]) {
        //find out const modulo value
        int constModuloValue = (int) Math.pow(10, 9) + 7;

        //lets create a intermediate map to store indexes and their final modulo values
        Map<Integer, Integer> intermediateMap = new HashMap<>();

        for (int i = 0; i < arr.length - 1; i++) {
            int powerValue = (int) Math.pow(arr[i], arr[i + 1]);

            int finalValue = powerValue % constModuloValue;
            intermediateMap.put(i, finalValue);
        }


        System.out.println(intermediateMap);

        //lets sort the intermediate map using sorted(). Here we are comparing the values and reversing index to get the highest modulo values in the first map
        // then we are storing the values into linkedhashmap
        LinkedHashMap<Integer, Integer> sortedMapUsingCollectors = intermediateMap.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors
                        .toMap
                                (
                                        e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new
                                )
                );
        System.out.println(sortedMapUsingCollectors);

        // here we are grouping the modulo values to get the indexes only
        Map<Integer, List<Map.Entry<Integer, Integer>>> groupingByValueMap = sortedMapUsingCollectors.
                entrySet()
                .stream()
                .collect(Collectors.groupingBy(e -> e.getValue()));
        System.out.println(groupingByValueMap);

        // here we are trying to get the first indexed value from previous groupmap and then we will get the indexes value
        List<Integer> maxModuloValueIndexes = groupingByValueMap.entrySet().stream().map(e -> e.getValue()).findFirst().stream().flatMap(e -> e.stream()).map(e -> e.getKey()).collect(Collectors.toList());


        //get min index
        Optional<Integer> minIndex = maxModuloValueIndexes.stream().min(Integer::compareTo);

        return minIndex.get().intValue();
    }
}
