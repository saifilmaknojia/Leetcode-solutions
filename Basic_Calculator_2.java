import java.util.*;

// 10 ms solution
class Basic_Calculator_2 {
    public int calculate(String s) {
        Deque<Integer> numbers = new ArrayDeque<>();
        Deque<Character> operation = new ArrayDeque<>();

        char[] arr = s.toCharArray();
        int len = arr.length;
        int longerNumber = 0;
        // boolean doNow = false;
        for (int i = 0; i < len; i++) {
            char curr = arr[i];
            if (curr == ' ')
                continue;

            // is digit
            if (Character.isDigit(curr)) {
                longerNumber = longerNumber * 10 + (curr - '0');
            } else if (curr == '*' || curr == '/') { // we perform multiplication and division on the go
                int firstNumber = longerNumber;

                i++;
                // ignore spaces
                while (i < len && arr[i] == ' ')
                    i++;

                // fetch next number
                int nextNumber = 0;
                while (i < len && Character.isDigit(arr[i])) {
                    nextNumber = nextNumber * 10 + (arr[i] - '0');
                    i++;
                }
                int afterOperationNumber = curr == '*' ? firstNumber * nextNumber : firstNumber / nextNumber;

                longerNumber = afterOperationNumber;

                // to pull back i one behind, because it is already going to increment in for
                // loop
                i -= 1;
            } else {
                numbers.offer(longerNumber);
                operation.offer(curr);
                longerNumber = 0;
            }
        }
        if (longerNumber >= 0)
            numbers.offer(longerNumber);

        while (!operation.isEmpty()) {
            char perform = operation.pollFirst();

            int num1 = numbers.pollFirst();
            int num2 = numbers.pollFirst();
            // System.out.println(" Num 1 = "+num1 + " Num2 = "+num2);
            int newNumber = 0;
            if (perform == '+')
                newNumber = num1 + num2;
            else
                newNumber = num1 - num2;

            numbers.offerFirst(newNumber);
        }

        return numbers.pollFirst();
    }
}