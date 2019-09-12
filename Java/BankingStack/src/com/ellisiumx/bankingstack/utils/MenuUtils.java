package com.ellisiumx.bankingstack.utils;

public class MenuUtils {

    public static final char HeadLeftChar   = '╔';
    public static final char HeadRightChar  = '╗';
    public static final char HorizontalChar = '═';
    public static final char VerticalChar   = '║';
    public static final char FootLeftChar   = '╚';
    public static final char FootRightChar  = '╝';

    // Usando ID's de cores iguais ao minecraft &1 &2...
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

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
        if(title.length() != 0) {
            head = " " + title + " ";
            head = stringDirection(StringDirection.Center, head, size, stringSizeDifferenceColor(head), HorizontalChar);
        }
        else head = stringDirection(StringDirection.Center, "", size, stringSizeDifferenceColor(title), HorizontalChar);
        System.out.println(HeadLeftChar + parseToColor(head) + HeadRightChar);
    }

    public static void printContent(String[] content, int size) {
        for(String data : content) {
            if(data.length() >= 2 && data.charAt(0) == '#') {
                String directedString = data.substring(2);
                switch (data.charAt(1)) {
                    case 'l':
                    case 'L':
                        directedString = stringDirection(StringDirection.Left, directedString, size - 2, stringSizeDifferenceColor(directedString),' ');
                        break;
                    case 'r':
                    case 'R':
                        directedString = stringDirection(StringDirection.Right, directedString, size, stringSizeDifferenceColor(directedString),' ');
                        break;
                    default:
                        directedString = stringDirection(StringDirection.Center, directedString, size - 2, stringSizeDifferenceColor(directedString),' ');
                        break;
                }
                System.out.println(VerticalChar + " " + parseToColor(directedString) + " " + VerticalChar);
            } else {
                String centerContent = stringDirection(StringDirection.Center, data, size, stringSizeDifferenceColor(data), ' ');
                System.out.println(VerticalChar + parseToColor(centerContent) + VerticalChar);
            }
        }
    }

    public static void printFoot(String title, int size) {
        String foot;
        if(title.length() != 0) {
            foot = " " + title + " ";
            foot = stringDirection(StringDirection.Center, foot, size, stringSizeDifferenceColor(foot), HorizontalChar);
        }
        else foot = stringDirection(StringDirection.Center, title, size, stringSizeDifferenceColor(title), HorizontalChar);
        System.out.println(FootLeftChar + parseToColor(foot) + FootRightChar);
    }

    public enum StringDirection {
        Right, Center, Left
    }

    public static String stringDirection(StringDirection stringDirection, String s, int size, int subSize, char pad) {
        if (s == null) return null;
        switch (stringDirection) {
            case Left:
                StringBuilder sb1 = new StringBuilder(size);
                sb1.append(s);
                while (sb1.length() - subSize < size) {
                    sb1.append(pad);
                }
                return sb1.toString();
            case Center:
                if (size <= s.length()) return s;
                StringBuilder sb2 = new StringBuilder(size);
                for (int i = 0; i < (size - s.length() + subSize) / 2; i++) {
                    sb2.append(pad);
                }
                sb2.append(s);
                while (sb2.length() - subSize < size) {
                    sb2.append(pad);
                }
                return sb2.toString();
            case Right:
                StringBuilder sb3 = new StringBuilder(size);
                while ((sb3.length() - subSize) + s.length() + 2 < size) {
                    sb3.append(pad);
                }
                sb3.append(s);
                return sb3.toString();
            default:
                return s;
        }
    }

    public static String parseToColor(String data) {
        data = data.replace("&1", ANSI_BLACK);
        data = data.replace("&1", ANSI_BLUE);
        data = data.replace("&2", ANSI_GREEN);
        data = data.replace("&4", ANSI_RED);
        data = data.replace("&5", ANSI_PURPLE);
        data = data.replace("&e", ANSI_YELLOW);
        data = data.replace("&b", ANSI_CYAN);
        data = data.replace("&f", ANSI_WHITE);
        data = data.replace("&r", ANSI_RESET);
        return data;
    }

    public static int stringSizeDifferenceColor(String data) {
        int oldValue = data.length();
        data = data.replace("&1", "");
        data = data.replace("&1", "");
        data = data.replace("&2", "");
        data = data.replace("&4", "");
        data = data.replace("&5", "");
        data = data.replace("&e", "");
        data = data.replace("&b", "");
        data = data.replace("&f", "");
        data = data.replace("&r", "");
        return oldValue - data.length();
    }
}
