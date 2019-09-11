package com.ellisiumx.bankingstack.utils;

public class VerificationUtils {

    public static boolean validateCPF(String cpf) {
        int[] cpfNumbers = ConversionUtils.stringToIntArray(cpf);
        int checkDigit = 0;
        for(int i = 0; i < 9; i++) {
            checkDigit += cpfNumbers[i] * (10 - i);
        }
        checkDigit = checkDigit / 11;
        if(checkDigit <= 1) checkDigit = 0;
        else checkDigit = 11 - checkDigit;
        if(checkDigit != cpfNumbers[9]) return false;

        checkDigit = 0;
        for(int i = 0; i < 10; i++) {
            checkDigit += cpfNumbers[i] * (11 - i);
        }
        checkDigit = checkDigit / 11;
        if(checkDigit <= 1) checkDigit = 0;
        else checkDigit = 11 - checkDigit;
        if(checkDigit != cpfNumbers[10]) return false;
        return true;
    }

}
