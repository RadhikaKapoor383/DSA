import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public String simplifyPath(String path) {
        Deque<String> stack = new ArrayDeque<>();
        String[] parts = path.split("/");

        for (String part : parts) {
            if (part.isEmpty() || part.equals(".")) {
                continue; // skip empty segments (from consecutive slashes) and current-dir markers
            } else if (part.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop(); // go up one directory, if possible
                }
                // if stack is empty, ".." at root does nothing
            } else {
                stack.push(part); // valid directory/file name
            }
        }

        // Build the canonical path from the bottom of the stack to the top
        StringBuilder sb = new StringBuilder();
        // Need to reverse order since stack pops in LIFO order
        String[] arr = stack.toArray(new String[0]);
        for (int i = arr.length - 1; i >= 0; i--) {
            sb.append("/").append(arr[i]);
        }

        return sb.length() == 0 ? "/" : sb.toString();
    }
}