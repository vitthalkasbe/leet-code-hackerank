package com.examples.ibmtest;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Example2 {
    //Given a string of words separated by spaces. The task is to find the first maximum length
    // even word from the string. Eg: “You are given an array of n numbers” The answer would be “an” and not “of” because “an” comes before “of”.
    //Examples:
    //
    //Input:  "this is a test string"
    //Output:  string
    //Even length words are this, is, test, string. Even
    //maximum length word is string.
    //Input:  "geeksforgeeks is a platform for geeks"
    //Output:  platform
    //Only even length word is platform.

    public static void main(String[] args) {

        getMaxLengthStringEvenWord("This vikass is a string");
    }

    public static void getMaxLengthStringEvenWord(String str)
    {
        String[] inputString = str.split(" ");

        //Collect length of string in a map
        LinkedHashMap<String, Integer> lengthOfStringMap = Arrays.asList(inputString).stream()
                .collect(Collectors.toMap(e -> e, e -> e.length(), (e1, e2) -> e1, LinkedHashMap::new));

        LinkedHashMap<String, Integer>  evenWordsMap= lengthOfStringMap.entrySet().stream().filter(e -> e.getValue() % 2 == 0).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));

        System.out.println(evenWordsMap);
        String maxLengthEvenString = evenWordsMap.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
        System.out.println(maxLengthEvenString);


    }
}
