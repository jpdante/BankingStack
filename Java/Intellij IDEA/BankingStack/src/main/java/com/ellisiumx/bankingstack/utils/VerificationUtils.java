package com.ellisiumx.bankingstack.utils;

public class VerificationUtils {

    public static boolean validateCPF(String cpf) {
        if(cpf.length() != 11) return false;
        int[] cpfNumbers = ConversionUtils.stringToIntArray(cpf);
        int checkDigit = 0;
        for(int i = 0; i < 9; i++) {
            checkDigit += cpfNumbers[i] * (10 - i);
        }
        checkDigit = (checkDigit * 10) % 11;
        if(checkDigit == 10) checkDigit = 0;
        if(checkDigit != cpfNumbers[9]) return false;

        checkDigit = 0;
        for(int i = 0; i < 10; i++) {
            checkDigit += cpfNumbers[i] * (11 - i);
        }
        checkDigit = (checkDigit * 10) % 11;
        if(checkDigit == 10) checkDigit = 0;
        if(checkDigit != cpfNumbers[10]) return false;
        return true;
    }

    public static boolean validatePhone(String phone) {
        return phone.matches("\\(?\\+[0-9]{1,3}\\)? ?-?[0-9]{1,3} ?-?[0-9]{3,5} ?-?[0-9]{4}( ?-?[0-9]{3})?");
    }

}
