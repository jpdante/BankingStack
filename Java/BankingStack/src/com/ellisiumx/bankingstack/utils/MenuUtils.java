package com.ellisiumx.bankingstack.utils;

public class MenuUtils {

    public static final char HeadLeftChar   = '╔';
    public static final char HeadRightChar  = '╗';
    public static final char HorizontalChar = '═';
    public static final char VerticalChar   = '║';
    public static final char FootLeftChar   = '╚';
    public static final char FootRightChar  = '╝';

    public static void printWindow(String[] content) {
        printWindow("", content, "");
    }

    public static void printWindow(String headTitle, String[] content) {
        printWindow(headTitle, content, "");
    }

    public static void printWindow(String headTitle, String[] content, String footTitle) {
        int largestLength = headTitle.length();
        if(footTitle.length() > largestLength) largestLength = footTitle.length();
        for (String s : content) if(s.length() > largestLength) largestLength = s.length();
        largestLength += 2;
        printHead(headTitle, largestLength);
        printContent(content, largestLength);
        printFoot(footTitle, largestLength);
    }

    public static void printHead(String title, int size) {
        String head;
        if(title.length() != 0) head = centerString(" " + title + " ", size, HorizontalChar);
        else head = centerString("", size, HorizontalChar);
        System.out.println(HeadLeftChar + head + HeadRightChar);
    }

    public static void printContent(String[] content, int size) {
        for(String data : content) {
            String centerContent = centerString(data, size, ' ');
            System.out.println(VerticalChar + centerContent + VerticalChar);
        }
    }

    public static void printFoot(String title, int size) {
        String foot;
        if(title.length() != 0) foot = centerString(" " + title + " ", size, HorizontalChar);
        else foot = centerString("", size, HorizontalChar);
        System.out.println(FootLeftChar + foot + FootRightChar);
    }

    public static String centerString(String s, int size, char pad) {
        if (s == null || size <= s.length()) return s;
        StringBuilder sb = new StringBuilder(size);
        for (int i = 0; i < (size - s.length()) / 2; i++) {
            sb.append(pad);
        }
        sb.append(s);
        while (sb.length() < size) {
            sb.append(pad);
        }
        return sb.toString();
    }
}
