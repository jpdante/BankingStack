package com.ellisiumx.bankingstack.utils;

public class MenuUtils {
    public static final char HeadLeftChar   = '╔';
    public static final char HeadRightChar  = '╗';
    public static final char HorizontalChar = '═';
    public static final char VerticalChar   = '║';
    public static final char FootLeftChar   = '╚';
    public static final char FootRightChar  = '╝';

    public static String getWindow(String[] content) { return getWindow("", content, ""); }

    public static String getWindow(String headTitle, String[] content) { return getWindow(headTitle, content, ""); }

    public static String getWindow(String[] content, String footTitle) { return getWindow("", content, footTitle); }

    public static String getWindow(String headTitle, String[] content, String footTitle) {
        int largestLength = headTitle.length();
        if(footTitle.length() > largestLength) largestLength = footTitle.length();
        for (String s : content) if(s.length() > largestLength) largestLength = s.length();
        largestLength += 2;
        StringBuilder window = new StringBuilder();
        window.append(getHead(headTitle, largestLength));
        window.append(getContent(content, largestLength));
        window.append(getFoot(footTitle, largestLength));
        return window.toString();
    }

    public static void printWindow(String[] content) {
        printWindow("", content, "");
    }

    public static void printWindow(String headTitle, String[] content) {
        printWindow(headTitle, content, "");
    }

    public static void printWindow(String[] content, String footTitle) {
        printWindow("", content, footTitle);
    }

    public static void printWindow(String headTitle, String[] content, String footTitle) {
        int largestLength = headTitle.length();
        if(footTitle.length() > largestLength) largestLength = footTitle.length();
        for (String s : content) if(s.length() > largestLength) largestLength = s.length();
        largestLength += 2;
        System.out.println(HeadLeftChar + getHead(headTitle, largestLength) + HeadRightChar);
        System.out.println(getContent(content, largestLength));
        System.out.println(FootLeftChar + getFoot(footTitle, largestLength) + FootRightChar);
    }

    public static String getHead(String title, int size) {
        String head;
        if(title.length() != 0) {
            head = " " + title + " ";
            head = stringDirection(StringDirection.Center, head, size, 0, HorizontalChar);
        }
        else head = stringDirection(StringDirection.Center, "", size, 0, HorizontalChar);
        return head;
    }

    public static String getContent(String[] content, int size) {
        StringBuilder contentString = new StringBuilder();
        int currentLine = 0;
        for(String data : content) {
            if(data.length() >= 2 && data.charAt(0) == '#') {
                String directedString = data.substring(2);
                switch (data.charAt(1)) {
                    case 'l':
                    case 'L':
                        directedString = stringDirection(StringDirection.Left, directedString, size - 2, 0,' ');
                        break;
                    case 'r':
                    case 'R':
                        directedString = stringDirection(StringDirection.Right, directedString, size, 0,' ');
                        break;
                    default:
                        directedString = stringDirection(StringDirection.Center, directedString, size - 2, 0,' ');
                        break;
                }
                if(currentLine != 0) contentString.append(System.lineSeparator());
                contentString.append(VerticalChar).append(" ").append(directedString).append(" ").append(VerticalChar);
            } else {
                String centerContent = stringDirection(StringDirection.Center, data, size, 0, ' ');
                if(currentLine != 0) contentString.append(System.lineSeparator());
                contentString.append(VerticalChar).append(centerContent).append(VerticalChar);
            }
            currentLine++;
        }
        return contentString.toString();
    }

    public static String getFoot(String title, int size) {
        String foot;
        if(title.length() != 0) {
            foot = " " + title + " ";
            foot = stringDirection(StringDirection.Center, foot, size, 0, HorizontalChar);
        }
        else foot = stringDirection(StringDirection.Center, title, size, 0, HorizontalChar);
        return foot;
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
}
