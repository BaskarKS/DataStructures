package com.baskarks.dynamic.programming;

public class DecodeWays {
    public static void main(String[] args) {
        var decodedString = "2227"; // BBAD, VAD, BUD, BBN, VN
                          //11235   // 2214. '22'14, 2'21'4, 22'14', '22''14'
                            //2227 //2227, '22'27, 2'22'7, 22'27', '22'27'
        var ways = decodeWays(decodedString);
        System.out.println(ways);
    }
    public static int decodeWays(String str) {
        int[] dp = new int[str.length() + 1];
        dp[0] = 1;
        dp[1] = str.charAt(0) == 0 ? 0 : 1;
        for (var idx = 2; idx <= str.length(); idx++) {
            int firstDigit = Integer.parseInt(str.substring(idx - 1, idx));
            int secondDigit = Integer.parseInt(str.substring(idx - 2, idx));

            if (firstDigit > 0)
                dp[idx] += dp[idx - 1];

            if (secondDigit >= 10 && secondDigit <= 26)
                dp[idx] += dp[idx - 2];
        }
        return dp[str.length()];
    }
}
