package project.validator;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import project.controller.DBManager;
import project.users.User;

import javax.servlet.http.HttpServletRequest;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ValidatorEditForm {
    private static final List<String> errors = new ArrayList<>();
    private static final DBManager dbManager = DBManager.getInstance();
    private static User user;

    public synchronized static List<String> editValidate(HttpServletRequest req) {
        errors.clear();
        User oldUser = (User) req.getSession().getAttribute("user");
         user = new User(req.getParameter("surname"), req.getParameter("name"), req.getParameter("login")
                , req.getParameter("password"), req.getParameter("tel"), LocalDate.parse(req.getParameter("date_of_birth")));
        user.setId(oldUser.getId());

        if (!user.getSurname().equals(oldUser.getSurname())&&!user.getSurname().isEmpty())
            errors.add(validateSurname(user.getSurname()));
        if (!user.getName().equals(oldUser.getName())&&!user.getName().isEmpty())
            errors.add(validateName(user.getName()));
        if (!user.getLogin().equals(oldUser.getLogin())&&!user.getLogin().isEmpty())
            errors.add(validateLogin(user.getLogin()));
        if (!String.valueOf(user.getPassword().hashCode()).equals(oldUser.getPassword())&&!user.getPassword().isEmpty())
            errors.add(validatePassword(user.getPassword(), req.getParameter("repassword")));
        if (!user.getTel().equals(oldUser.getTel())&&!user.getTel().isEmpty())
            errors.add(validatePhoneNumber(user.getTel()));
        if (!user.getDateOfBirth().equals(oldUser.getDateOfBirth())&&!user.getDateOfBirth().toString().isEmpty())
            errors.add(validateDateOfBirth(user.getDateOfBirth().toString()));

        errors.removeIf(Objects::isNull);
        return errors;
    }
    private static String validateSurname(String surname) {
        if (surname != null && surname.length() >= 2 && surname.length() < 32){
            dbManager.updateUseSurname(surname, user.getId());
            return "surname is changed";
        }
        return "surname is wrong";
    }
    private static String validateName(String name) {
        if (name != null && name.length() >= 2 && name.length() < 32){
            dbManager.updateUserName(name,user.getId());
            return "name is changed";
        }
        return "name is wrong";
    }

    private static String validateLogin(String login) {
        if (dbManager.findUserByLogin(login) != null) return "The login is already in use";
        if (login != null && login.length() >= 2 && login.length() < 32){
            dbManager.updateUserLogin(login, user.getId());
            return "login is changed";
        }
        return "login is wrong";
    }

    private static String validatePassword(String password, String rePassword) {
        if (!password.equals(rePassword)) return "passwords not a same";
        if (password.length() >= 4 && password.length() < 32) {
            dbManager.updateUserPassword(password, user.getId());
            return "password is changed";
        }
        return "password is wrong";
    }

    private static String validatePhoneNumber(String number) {
        if (number == null) return "phone number is wrong";
        String finalTel = finalTel(number);
        if (finalTel.length() < 12) return "phone number is wrong";
        if (dbManager.findAllUsers().stream().anyMatch(x -> x.getTel().equals(finalTel)))
            return "phone number is already in use";
        dbManager.updateUserTel(finalTel, user.getId());
        return "phone number is changed";
    }

    private static String validateDateOfBirth(String date) {
        LocalDate dateOfBirth;
        if (date == null) return "date is wrong";
        try {
            dateOfBirth = LocalDate.parse(date);
        } catch (DateTimeException e) {
            return "date is wrong";
        }
        if (dateOfBirth.isBefore(LocalDate.of(1900, 1, 1)) || dateOfBirth.isAfter(LocalDate.now()))
            return "date is wrong";
        dbManager.updateUserDateOfBirth(dateOfBirth, user.getId());
        return "date of birth is changed";
    }

    public static String finalTel(String number) {
        if (number == null) return null;
        Phonenumber.PhoneNumber tel = null;
        try {
            tel = PhoneNumberUtil.getInstance().parse(number, "UA");
        } catch (NumberParseException ignored) {
        }
        return Objects.requireNonNull(tel).getCountryCode() + "" + tel.getNationalNumber();
    }
}

