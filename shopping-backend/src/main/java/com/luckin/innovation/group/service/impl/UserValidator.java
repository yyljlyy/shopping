package com.luckin.innovation.group.service.impl;

import com.luckin.innovation.group.entity.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import javax.annotation.Resource;
import java.util.regex.Pattern;

/**
 * @author Jasper Lee (Jian) 创建于 2018-11-23 上午12:16
 * @company Luckin Coffe AI Group
 * @description com.luckin.innovation.group.service.impl
 * 版权所有 违法必究
 */
@Component
public class UserValidator implements Validator {
    private static final Pattern EMAIL_REGEX =
            Pattern.compile("^[\\w\\d._-]+@[\\w\\d.-]+\\.[\\w\\d]{2,6}$");

    private static final Pattern PHONE_REGEX =
            Pattern.compile("^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\\\d{8}$");

    private static final Integer MIN_LENGTH = 6;
    private static final Integer MAX_LENGTH = 6;

    private static final Integer PASS_MIN_LENGTH = 5;
    private static final Integer PASS_MAX_LENGTH = 15;

    @Resource
    private UserServiceImpl userService;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz == User.class;
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "login", "user.name.empty");
        ValidationUtils.rejectIfEmpty(errors, "password", "user.password.empty");
        ValidationUtils.rejectIfEmpty(errors, "email", "user.email.empty");

        User user = (User) target;
        if (user.getUserName() != null && user.getUserName().length() < MIN_LENGTH ||
                user.getUserName().length() > MAX_LENGTH) {
            errors.rejectValue("login", "Size.userForm.username");
        }

        if (userService.findByUsername(user.getUserName()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        if (user.getPassWord() != null && user.getPassWord().contains(" ")) {
            errors.rejectValue("password", "user.password.space");
        }

        if (user.getPassWord() != null && user.getPassWord().length() < PASS_MIN_LENGTH &&
                user.getPassWord().length() > PASS_MAX_LENGTH) {
            errors.rejectValue("password", "user.password.size");
        }

        if (user.getEmail() != null && !EMAIL_REGEX.matcher(user.getEmail()).matches()) {
            errors.rejectValue("email", "user.email.invalid");
        }

        if (user.getPhoneNumber() != null && !EMAIL_REGEX.matcher(user.getPhoneNumber()).matches()) {
            errors.rejectValue("email", "user.phone.invalid");
        }
    }
}
