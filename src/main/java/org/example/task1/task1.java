package org.example.task1;

import java.util.ArrayList;
import java.util.List;

public class task1 {
    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println("Usage: java task1 <n> <m>");
            return;
        }

        int n = Integer.parseInt(args[0]);
        int m = Integer.parseInt(args[1]);

        List<Integer> list = new ArrayList<>();

        int endNum = 0;

        int num = 1;

        StringBuilder result = new StringBuilder();

        while (endNum != 1) {

            for (int i = 0; i < m; i++) {

                num = num % (n + 1);
                if (num == 0)
                    num = 1;
                list.add(num);
                num++;

            }

            num--;
            result.append(list.get(0));
            endNum = list.get(m - 1);
            list.clear();

        }

        System.out.println(result);

    }

}