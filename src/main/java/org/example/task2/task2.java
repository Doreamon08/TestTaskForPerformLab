package org.example.task2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;

public class task2 {

    public static void main(String[] args) {

        if (args.length < 2) {
            System.out.println("Usage: java task2 <circle_file> <points_file>");
            return;
        }

        String circleFile = args[0];
        String pointsFile = args[1];

        try (BufferedReader circleReader = new BufferedReader(new FileReader(circleFile));
             BufferedReader pointsReader = new BufferedReader(new FileReader(pointsFile))) {

            String[] centerCoords = circleReader.readLine().split(" ");
            BigDecimal centerX = new BigDecimal(centerCoords[0]);
            BigDecimal centerY = new BigDecimal(centerCoords[1]);
            BigDecimal radius = new BigDecimal(circleReader.readLine());

            String pointLine;
            while ((pointLine = pointsReader.readLine()) != null) {

                String[] pointCoords = pointLine.split(" ");
                BigDecimal pointX = new BigDecimal(pointCoords[0]);
                BigDecimal pointY = new BigDecimal(pointCoords[1]);

                BigDecimal dx = pointX.subtract(centerX);
                BigDecimal dy = pointY.subtract(centerY);
                BigDecimal distanceSquared = dx.multiply(dx).add(dy.multiply(dy));
                BigDecimal radiusSquared = radius.multiply(radius);

                if (distanceSquared.compareTo(radiusSquared) < 0) {
                    System.out.println(1);
                } else if (distanceSquared.compareTo(radiusSquared) == 0) {
                    System.out.println(0);
                } else {
                    System.out.println(2);
                }

            }
        } catch (IOException e) {
            System.err.println("Error reading files: " + e.getMessage());
        }
    }

}
