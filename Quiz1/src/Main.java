//Abdulkadir Parlak 2210765025

import java.io.*;//imports
import java.util.ArrayList;
import java.util.Comparator;

public class Main {
    public static void main(String[] args) {
        txtReader(args[0]);
    }

    public static String armstrongNums(int limit) {//The method that finds armstrong numbers
        String result = "";

        result += String.format("Armstrong number up to %d:\n", limit);
        for (int i = 1; i <= limit; i++) {//iterates over up to the limit value
            String str = String.valueOf(i);
            int sum = 0;
            int digitCount = str.length();
            for (int j = 0; j < digitCount; j++) {//iterates over up to the value to be checked
                sum += Math.pow(Character.getNumericValue(str.charAt(j)), digitCount);//mathematical calculations
            }
            if (sum == i) {
                result += (sum + " ");
            }
        }
        result += ("\n\n");
        return result;//result variable keeps the final situation of the output
    }


    public static String emirpNums(int limit) {
        String result = "";

        result += String.format("Emirp numbers up to %d:\n", limit);

        for (int i = 13; i <= limit; i++) {
            boolean isPrime = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                boolean isPrime2 = true;
                String I = String.valueOf(i);
                String reversed = "";
                for (int l = 0; l < I.length(); l++) {
                    reversed = I.charAt(l) + reversed;
                }
                int last = Integer.valueOf(reversed);

                for (int k = 2; k < last; k++) {
                    if (last % k == 0) {
                        isPrime2 = false;
                        break;
                    }
                }
                if (isPrime2 && i != last) {
                    result += (i + " ");
                }
            }
        }
        result += ("\n\n");
        return result;
    }


    public static String abundantNums(int limit) {
        String result = "";

        result += String.format("Abundant numbers up to %d:\n", limit);

        for (int i = 0; i <= limit; i++) {
            int sum = 0;
            for (int j = 1; j <= i / 2; j++) {
                if (i % j == 0) {
                    sum += j;
                }
            }
            if (sum > i) {
                result += (i + " ");
            }
        }
        result += ("\n\n");
        return result;
    }

    public static String ascendingSort(ArrayList<Integer> nums) {
        String result = "";

        nums.sort(Comparator.naturalOrder());
        for (int i = 0; i < nums.size(); i++) {
            result += (nums.get(i) + " ");
        }
        result += ("\n");
        return result;
    }


    public static String descendingSort(ArrayList<Integer> nums) {
        String result = "";

        nums.sort(Comparator.reverseOrder());
        for (int i = 0; i < nums.size(); i++) {
            result += (nums.get(i) + " ");
        }
        result += ("\n");
        return result;
    }


    public static void txtReader(String fileName) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("output.txt"));

            try {
                int limit = 0;
                BufferedReader reader = new BufferedReader(new FileReader(fileName));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("Armstrong")) {
                        limit = Integer.valueOf(reader.readLine());
                        writer.write(armstrongNums(limit));
                    } else if (line.startsWith("Emirp")) {
                        limit = Integer.valueOf(reader.readLine());
                        writer.write(emirpNums(limit));
                    } else if (line.startsWith("Abundant")) {
                        limit = Integer.valueOf(reader.readLine());
                        writer.write(abundantNums(limit));
                    } else if (line.startsWith("Ascending")) {
                        String line2 = "";
                        writer.write("Ascending order sorting:\n");
                        ArrayList<Integer> nums = new ArrayList<Integer>();
                        while (Integer.valueOf(line2 = reader.readLine()) != -1) {
                            nums.add(Integer.valueOf(line2));
                            writer.write(ascendingSort(nums));
                        }
                        writer.write("\n");
                    } else if (line.startsWith("Descending")) {
                        String line3 = "";
                        writer.write("Descending order sorting:\n");
                        ArrayList<Integer> nums = new ArrayList<Integer>();
                        while (Integer.valueOf(line3 = reader.readLine()) != -1) {
                            nums.add(Integer.valueOf(line3));
                            writer.write(descendingSort(nums));
                        }
                        writer.write("\n");
                    } else if (line.startsWith("Exit")) {
                        writer.write("Finished...");
                    }
                }
                reader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}