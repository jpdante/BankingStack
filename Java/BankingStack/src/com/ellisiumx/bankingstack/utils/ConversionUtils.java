package com.ellisiumx.bankingstack.utils;

public class ConversionUtils {

    public static int[] stringToIntArray(String data) {
        int[] array = new int[data.length()];
        for(int i = 0; i < array.length; i++) {
            char[] dataArray = data.toCharArray();
            IntConversionResponse response = stringToInt(String.valueOf(dataArray[i]));
            if(response.success) {
                array[i] = response.result;
            }
        }
        return array;
    }

    public static IntConversionResponse stringToInt(String data) {
        try {
            return new IntConversionResponse(true, Integer.parseInt(data));
        } catch(Exception e) { }
        return new IntConversionResponse(false, 0);
    }

    public static String cpfToString(String cpf) {
        String data = "";
        int[] cpfNumbers = ConversionUtils.stringToIntArray(cpf);
        data += cpfNumbers[0] + "" + cpfNumbers[1] + "" + cpfNumbers[2] + ".";
        data += cpfNumbers[2] + "" + cpfNumbers[3] + "" + cpfNumbers[4] + ".";
        data += cpfNumbers[5] + "" + cpfNumbers[6] + "" + cpfNumbers[7] + "-";
        data += cpfNumbers[8] + "" + cpfNumbers[9];
        return data;
    }

    public static class IntConversionResponse {
        private boolean success;
        private int result;

        public IntConversionResponse(boolean success, int result) {
            this.success = success;
            this.result = result;
        }

        public boolean isSuccess() { return this.success; }
        public int getResult() { return this.result; }
    }

}
