package code.practice.leetcode;

import java.util.Arrays;

public class AssignCookies {
    public int findContentChildren(int[] g, int[] s) {
        //Assumption is that both arrays are sorted
        int cookieCount = 0;
        int gcnt = 0,scnt = 0;
        Arrays.sort(g);
        Arrays.sort(s);
        //int loopCntr = s.length < g.length ? s.length : g.length;
        int maxCookieSize = s[s.length-1];

        while(true)
        {
            if(g[gcnt] > maxCookieSize) break;
            if(s[scnt] >= g[gcnt]) {
                cookieCount++;
                scnt++; gcnt++;
            } else scnt++;

            if(scnt==s.length || gcnt==g.length) break;
        }

        return cookieCount;
    }

    public static void main(String a[]) {
        int g[] = {10,9,8,7},
            s[] = {5,6,7,8};
            AssignCookies ac = new AssignCookies();    
        System.out.println(ac.findContentChildren(g,s));
    }
}