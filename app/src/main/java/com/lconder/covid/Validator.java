package com.lconder.covid;

import java.util.regex.Pattern;

public class Validator {

    private Pattern emailPattern = Pattern.compile("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$");

    public boolean validateEmail(String email) {
        return emailPattern.matcher(email).matches();
    }

    public boolean validatePassword(String password) {
        return password.length() >= 6;
    }
}
