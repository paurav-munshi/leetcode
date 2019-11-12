package code.practice.leetcode;

import java.util.Arrays;

public class NearestPalindrome {
    public String nearestPalindromic(String n) {

        //char[] number = n.toCharArray();
        //char[] newNumber = number;
        long numLen = n.length();
        long iNum = Long.parseLong(n);
        if(iNum < 10) return String.valueOf(--iNum);
        if(Math.pow(10, numLen) - 1 == iNum) return String.valueOf(iNum + 2);    
        long unitNumber = (int)Math.pow(10, (numLen-1));
        if(iNum == unitNumber || iNum == unitNumber + 1) return String.valueOf(unitNumber - 1);
        else if(iNum == (unitNumber - 1)) return String.valueOf(unitNumber + 1);
        
        String[] splits = splitNumberStr(n);
        String firstHalf = splits[0];
        String pivot = splits[1];
        
        String palin = mirrorToPalin(firstHalf, pivot);
        long iNumPalin = Long.parseLong(palin); 
        if(iNumPalin == iNum){
            String prevPalin = getPrevPalinFromFirstHalf(firstHalf, pivot);
            String nextPalin= getNextPalinFromFirstHalf(firstHalf, pivot);
            long iPrevPalin = Long.parseLong(prevPalin);
            long iNextPalin = Long.parseLong(nextPalin);

            if(Math.abs(iNextPalin - iNumPalin) < Math.abs(iNumPalin - iPrevPalin)) return String.valueOf(iNextPalin);
            else return String.valueOf(iPrevPalin);
        }else {
            String palinSplits[] = splitNumberStr(String.valueOf(palin));
            String  strAltPalin = iNumPalin < iNum ? getNextPalinFromFirstHalf(palinSplits[0], palinSplits[1]) : 
                getPrevPalinFromFirstHalf(palinSplits[0], palinSplits[1]);

            long iAltPalin = Long.parseLong(strAltPalin);
            
            if(Math.abs(iAltPalin - iNum) < Math.abs(iNum - iNumPalin)) return strAltPalin;
            else if(Math.abs(iAltPalin - iNum) > Math.abs(iNum - iNumPalin)) return palin;
            else return iAltPalin > iNumPalin ? palin : strAltPalin;
        }
    }

    private String[] splitNumberStr(String n)
    {
        int numLen = n.length();
        boolean isEquiLen = numLen % 2 == 0;

        int splitLen = isEquiLen ? numLen / 2 : (numLen - 1) / 2;
        int splitIndex = splitLen - 1;
        int secSplitInd = isEquiLen ? splitIndex + 1 : splitIndex + 2;

        String pivot = isEquiLen ? "" : n.substring(splitLen, splitLen+1);
        String firstHalf = n.substring(0,splitLen);
        String secondHalf = n.substring(secSplitInd,numLen);

        return new String[]{firstHalf,pivot,secondHalf};
    }

    private String mirrorToPalin(String num, String pivot) {
        StringBuilder sbRev = new StringBuilder(num);
        StringBuilder sbMirror = new StringBuilder();
        sbMirror.append(num).append(pivot).append(sbRev.reverse().toString());
        return sbMirror.toString();
    }

    private String getNextPalinFromFirstHalf(String firstHalf, String pivot) {
        StringBuilder sbHalf = new StringBuilder();
        sbHalf.append(firstHalf).append(pivot);
        long iHalf = Long.parseLong(sbHalf.toString());
        
        String newHalf = String.valueOf(iHalf + 1);
        String newPivot = "";
        if(newHalf.length() > sbHalf.length()) {
            newHalf = newHalf.substring(0, newHalf.length()-1);
            newPivot = pivot == null || pivot.equals("") ? "0" : "";
        }else if(pivot!=null && !pivot.equals("")){
            newPivot = newHalf.substring(newHalf.length() - 1, newHalf.length());
            newHalf = newHalf.substring(0, newHalf.length()-1);
        }

        return mirrorToPalin(newHalf, newPivot);    
    }

    private String getPrevPalinFromFirstHalf(String firstHalf, String pivot) {
        StringBuilder sbHalf = new StringBuilder();
        sbHalf.append(firstHalf).append(pivot);
        long iHalf = Long.parseLong(sbHalf.toString());
        
        String newHalf = String.valueOf(iHalf - 1);
        String newPivot = "";
        if(newHalf.length() < sbHalf.length()) {
            newPivot = pivot == null || pivot.equals("") ? "9" : "";
        }else if(pivot!=null && !pivot.equals("")){
            newPivot = newHalf.substring(newHalf.length() - 1, newHalf.length());
            newHalf = newHalf.substring(0, newHalf.length()-1);
        }

        return mirrorToPalin(newHalf, newPivot);    
    }

    public static void main(String a[]) {
        NearestPalindrome np = new NearestPalindrome();

        System.out.println(np.nearestPalindromic("807045053224792883"));
    }
}