package org.example.task4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class task4 {

    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println("Usage: java task4 <file_with_nums>");
            return;
        }

        String numsFile = args[0];
        int minStepsResult = -1;

        try (BufferedReader numsReader = new BufferedReader(new FileReader(numsFile))) {
            List<Integer> numbersList = new ArrayList<>();
            String line;

            while ((line = numsReader.readLine()) != null) {
                numbersList.add(Integer.parseInt(line));
            }

            int[] nums = numbersList.stream().mapToInt(Integer::intValue).toArray();

            for (int i = 0; i < nums.length; i++) {

                int minSteps = 0;
                for (int j = 0; j < nums.length; j++) {

                    if (i == j)
                        continue;
                    minSteps += Math.abs(nums[i] - nums[j]);

                }

                if (minSteps < minStepsResult || minStepsResult <= 0)
                    minStepsResult = minSteps;

            }

        } catch (IOException e) {
            System.err.println("Error reading files: " + e.getMessage());
        }

        System.out.println(minStepsResult);

    }

}
