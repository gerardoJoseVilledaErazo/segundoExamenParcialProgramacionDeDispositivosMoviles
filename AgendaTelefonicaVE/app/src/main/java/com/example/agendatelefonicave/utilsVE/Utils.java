package com.example.agendatelefonicave.utilsVE;

import android.widget.EditText;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Utils {

    public static boolean verifyEditText(EditText editText) {
        if (editText.getText().toString().isEmpty()) {
            editText.setError("This field is required.");
            editText.requestFocus();
            return false;
        } else if (!verifyChars(editText)) {
            editText.setError("Only letters are allowed.");
            editText.requestFocus();
            return false;
        }
        return true;
    }

    public static boolean verifyChars(EditText editText) {
        //Validamos solo caracteres Expresion regular
        Pattern ps = Pattern.compile("^[a-zA-Z ]+$");
        Matcher ms = ps.matcher(editText.getText().toString());
        boolean bs = ms.matches();
        return bs;
    }

    // Para PHONENUMBER
    public static boolean verifyEditTextNumber(EditText editText ) {
        if (editText.getText().toString().isEmpty() ) {
            editText.setError("This field is required." );
            editText.requestFocus();
            return false;
        }
        return true;
    }

}