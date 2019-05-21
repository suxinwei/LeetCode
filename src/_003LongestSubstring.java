import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * created by suxinwei at 2019/4/5
 * description:https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/
 * <p>
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
class _003LongestSubstring {

    public static void main(String[] args) {
        String s = "acbb1234a";
        String s2 = "bbbbb";
        String s3 = "au";
        System.out.println(lengthOfLongestSubstring(s));
    }

    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int maxLength = 0, i = 0, j = 0;
        int[] arr = new int[128];
        while (j < n) {
            char key = s.charAt(j);
            j++;
            int index = arr[key];
            i = Math.max(index, i);
            arr[key] = j;
            maxLength = Math.max(maxLength, j - i);
        }
        return maxLength;
    }

    public static int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        int maxLength = 0, i = 0, j = 0;
        Map<Character, Integer> map = new HashMap<>();
        while (j < n) {
            char key = s.charAt(j);
            j++;
            Integer index = map.put(key, j);
            if (index != null) {
                i = Math.max(index, i);
            }
            maxLength = Math.max(maxLength, j - i);
        }
        return maxLength;
    }

    public static int lengthOfLongestSubstring3(String s) {
        int n = s.length();
        int maxLength = 0, i = 0, j = 0;
        Set<Character> characterSet = new HashSet<>();
        while (j < n) {
            if (characterSet.add(s.charAt(j))) {
                j++;
                maxLength = Math.max(maxLength, j - i);
            } else {
                characterSet.remove(s.charAt(i));
                i++;
            }
        }
        return maxLength;
    }

    public static int lengthOfLongestSubstring4(String s) {
        if (s.length() == 0) {
            return 0;
        }
        int maxLength = 1;
        char[] chars = s.toCharArray();
        int length;
        Set<Character> characterSet = new HashSet<>();
        for (int i = 0; i < chars.length - 1; i++) {
            length = 1;
            characterSet.clear();
            characterSet.add(chars[i]);
            for (int j = i + 1; j < chars.length; j++) {
                if (characterSet.add(chars[j])) {
                    length++;
                } else {
                    break;
                }
            }
            maxLength = Math.max(maxLength, length);
        }
        return maxLength;
    }
}
