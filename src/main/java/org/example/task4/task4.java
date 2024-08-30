package org.example.task4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.io.*;
import java.util.*;

public class task4 {

    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("Usage: java task4 <file_with_nums>");
            return;
        }

        String numsFile = args[0];
        int minStepsResult = 0;

        try (BufferedReader numsReader = new BufferedReader(new FileReader(numsFile))) {

            List<Integer> numbersList = new ArrayList<>();
            String line;

            while ((line = numsReader.readLine()) != null) {
                numbersList.add(Integer.parseInt(line));
            }

            int[] nums = numbersList.stream().mapToInt(Integer::intValue).toArray();
            Arrays.sort(nums);
            int median = nums[nums.length / 2];

            for (int num : nums) {
                minStepsResult += Math.abs(num - median);
            }

        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        System.out.println(minStepsResult);
    }
}
