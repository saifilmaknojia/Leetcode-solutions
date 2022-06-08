import java.util.ArrayDeque;
import java.util.Deque;

public class Simplify_Path_71 {
    public String simplifyPath(String path) {
        // split by / --> Remove all / cells --> If two .., remove previous element

        String[] splitPath = path.split("/");
        // System.out.println(splitPath.length);

        Deque<String> stack = new ArrayDeque<>();

        for (String s : splitPath) {
            // Empty string
            if (s.length() == 0)
                continue;
            else if (s.equals("."))
                continue;

            if (s.equals("..")) {
                if (!stack.isEmpty())
                    stack.pollLast();
            } else {
                stack.offerLast(s);
            }
        }

        // System.out.println(stack);

        StringBuilder result = new StringBuilder();

        // Path start with "/"
        result.append("/");

        while (!stack.isEmpty()) {
            result.append(stack.pollFirst());
            result.append("/");
        }

        result.deleteCharAt(result.length() - 1);

        // System.out.println(result);

        return result.length() == 0 ? "/" : result.toString();
    }
}