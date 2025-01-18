package method;

import java.util.ArrayList;
import java.util.List;

public class primaryKey {
    public String incrementPrimaryKey(List<String> primaryArr) {
        List<String> mainStr = new ArrayList<>();
        List<Integer> mainInt = new ArrayList<>();

        for (String primaryKey : primaryArr) {
            StringBuilder stringPart = new StringBuilder();
            StringBuilder intPart = new StringBuilder();

            for (char key : primaryKey.toCharArray()) {
                if (Character.isDigit(key)) {
                    intPart.append(key);
                } else {
                    stringPart.append(key);
                }
            }

            mainStr.add(stringPart.toString());
            // Only add numeric part if it is not empty
            if (intPart.length() > 0) {
                mainInt.add(Integer.parseInt(intPart.toString()));
            }
        }

        // Ensure there are numbers to process
        if (mainInt.isEmpty()) {
            throw new IllegalArgumentException("No valid numeric IDs found in the input list.");
        }

        // Find the maximum value in the list
        int maxValue = mainInt.get(0);
        for (int i = 1; i < mainInt.size(); i++) {
            int num = mainInt.get(i);
            if (num > maxValue) {
                maxValue = num;
            }
        }

        // Use the string part of the last valid ID
        String stringPart = mainStr.get(mainStr.size() - 1);

        return stringPart + (maxValue + 1);
    }

}
