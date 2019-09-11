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

    static class IntConversionResponse {
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
