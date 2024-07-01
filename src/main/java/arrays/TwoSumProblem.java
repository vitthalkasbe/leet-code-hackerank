package arrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSumProblem {

    /**
     * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
     *
     * You may assume that each input would have exactly one solution, and you may not use the same element twice.
     *
     * You can return the answer in any order.
     * Example 1:
     *
     * Input: nums = [2,7,11,15], target = 9
     * Output: [0,1]
     * Explanation: Because nums[0] + nums[1] == 9, we return [0, 1].
     * Example 2:
     *
     * Input: nums = [3,2,4], target = 6
     * Output: [1,2]
     * Example 3:
     *
     * Input: nums = [3,3], target = 6
     * Output: [0,1]
     * @param args
     */
    public static void main(String[] args) {
        int[] input={2,7,11,15,22};
        int target=9;
        int[] solution1 = solution1(input, target);

        Arrays.stream(solution1).forEach(System.out::println);

        int[] solution2On2 = solution2On2(input, target);

        Arrays.stream(solution2On2).forEach(System.out::println);

        int[] solution3On2 = solution3On2(input, target);

        Arrays.stream(solution3On2).forEach(System.out::println);
    }

    //it does not pass all the test cases
    public static int[] solution1(int input[],int target)
    {
        int a[]=new int[2];

        for(int i=0;i<input.length-1;i++)
        {
            if(input[i]+input[i+1]==target)
            {
                a[0]=i;
                a[1]=i+1;
                break;
            }

        }
        return a;
    }

    // It will be accepted but it is having complexity of O(n2)
    public static int[] solution2On2(int input[],int target)
    {
        for(int i=0;i<input.length;i++)
        {
            for (int j=i+1;j<input.length;j++)
            {
                int difference=target-input[i];
                if(input[j]==difference)
                    return new int[]{i,j};
            }
        }
        throw new IllegalArgumentException("no match found");
    }

    //using the hasmap approach
    public static int[] solution3On2(int nums[],int target)
    {
        Map<Integer,Integer> mapOfNumbers=new HashMap<>();
        for(int i=0;i<nums.length;i++)
        {
            int difference=target-nums[i];
            if(mapOfNumbers.containsKey(difference))
                return new int[]{mapOfNumbers.get(difference),i};
            mapOfNumbers.put(nums[i],i);

        }
        throw new IllegalArgumentException("No Match found!");
    }
}
