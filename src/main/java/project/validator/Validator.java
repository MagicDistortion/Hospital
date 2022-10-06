package project.validator;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;
import project.dao.UsersDAO;
import javax.servlet.http.HttpServletRequest;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/*класс для перевірки даних потрібних для реєстрації нового користувача */
public class Validator {
    private static final List<String> errors = new ArrayList<>();
    private static final UsersDAO usersDAO = new UsersDAO();
    /* метод запускає перевірку отриманих з Http запиту данних, та повертає лист з помилками якщо вони є */
    public List<String> registerValidate(HttpServletRequest req) {
        errors.clear();
        errors.add(validateName(req.getParameter("name")));
        errors.add(validateSurname(req.getParameter("surname")));
        errors.add(validateLogin(req.getParameter("login")));
        errors.add(validatePassword(req.getParameter("password"), req.getParameter("repassword")));
        errors.add(validatePhoneNumber(req.getParameter("tel")));
        errors.add(validateDateOfBirth(req.getParameter("date_of_birth")));
        errors.removeIf(Objects::isNull);
        return errors;
    }
    /* перевірка імені */
    private String validateName(String name) {
        if (name != null && name.length() >= 2 && name.length() < 32) return null;
        return "name is wrong";
    }
    /* перевірка прізвища */
    private String validateSurname(String surname) {
        if (surname != null && surname.length() >= 2 && surname.length() < 32) return null;
        return "surname is wrong";
    }
    /* перевірка логіну */
    private String validateLogin(String login) {
        if (usersDAO.findUserByLogin(login) != null) return "The login is already in use";
        if (login != null && login.length() >= 2 && login.length() < 32) return null;
        return "login is wrong";
    }
    /* перевірка паролю*/
    private String validatePassword(String password, String rePassword) {
        if (!password.equals(rePassword)) return "passwords not a same";
        if (password.length() >= 4 && password.length() < 32) return null;
        return "password is wrong";
    }
    /* перевірка телефону */
    private String validatePhoneNumber(String number) {
        if (number == null) return "phone number is wrong";
        String finalTel = finalTel(number);
        if (finalTel.length() < 12) return "phone number is wrong";
        if (usersDAO.findAllUsers().stream().anyMatch(x -> x.getTel().equals(finalTel)))
            return "phone number is already in use";
        return null;
    }
    /* перевірка дати народження */
    private String validateDateOfBirth(String date) {
        LocalDate dateOfBirth;
        if (date == null) return "date is wrong";
        try {
            dateOfBirth = LocalDate.parse(date);
        } catch (DateTimeException e) {
            return "date is wrong";
        }
        if (dateOfBirth.isBefore(LocalDate.of(1900, 1, 1)) || dateOfBirth.isAfter(LocalDate.now()))
            return "date is wrong";
        return null;
    }
    /* приводить номер телефону до потрібного вигляду */
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

