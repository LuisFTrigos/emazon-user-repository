package com.example.emazon_aux.domain.util.validations;

import com.example.emazon_aux.domain.exception.UserAlreadyExistsException;
import com.example.emazon_aux.domain.model.UserModel;
import com.example.emazon_aux.domain.util.constants.Constants;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validations {

    private Validations() {
    }

    private static final String REGEX_EMAIL = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";
    private static final String REGEX_PHONE = "^\\+?\\d+$";

    public static void validationsUser(UserModel userModel){
        emptyStringValidate(userModel.getName());
        emptyStringValidate(userModel.getLastName());
        emptyStringValidate(userModel.getEmail());
        emptyStringValidate(userModel.getDocument());
        emailValidate(userModel.getEmail());
        validateAge(userModel.getBirthday());
        numberPhoneValidate(userModel.getPhone());
    }

    private static void emptyStringValidate(String data){
        if (data.isEmpty()) {
            throw new UserAlreadyExistsException(Constants.FIELD_CAN_T_BE_EMPTY);
        }
    }

    private static void emailValidate(String email){
        Pattern pattern = Pattern.compile(REGEX_EMAIL);
        Matcher mather = pattern.matcher(email);

        if (!mather.find()){
            throw new UserAlreadyExistsException(Constants.MAIL_BAD_REQUEST);
        }
    }

    private static void numberPhoneValidate(String phone){
        Pattern pattern = Pattern.compile(REGEX_PHONE);
        Matcher mather = pattern.matcher(phone);
        if(phone.length() > 13) {
            throw new UserAlreadyExistsException(Constants.PHONE_NOT_BE_LONGER);
        }
        if(!mather.find()) {
            throw new UserAlreadyExistsException(Constants.INVALID_PHONE);
        }
    }

    private static void validateAge(LocalDate birthday){
        LocalDate today = LocalDate.now();
        int age = Period.between(birthday, today).getYears();
        if (age < 18 ){
            throw new UserAlreadyExistsException(Constants.WRONG_AGE);
        }
    }

}
