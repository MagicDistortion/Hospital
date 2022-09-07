package project.models.users;


import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.io.Serializable;
import java.time.LocalDate;

/* Клас користувачів */

public class User implements Serializable {
    private int id;
    private String surname;
    private String name;
    private String login;
    private String password;
    private Phonenumber.PhoneNumber tel;
    private int rolesId;
    private LocalDate dateOfBirth;

    public User(String surname, String name, String login, String password, String tel, LocalDate dateOfBirth) {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();

        try {
            this.tel = phoneUtil.parse(tel, "UA");
        } catch (NumberParseException e) {
          e.printStackTrace();
        }
        this.surname = surname;
        this.name = name;
        this.login = login;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /* метод отримання номеру телефону у вигляді строки - код країни + номер телефону */
    public String getTel() {
        return tel.getCountryCode()+""+ tel.getNationalNumber();
    }

    /* метод призначення номера телефона,приймає строку та записує як клас "телефонний номер"  */

    public void setTel(String tel) {
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        try {
            this.tel = phoneUtil.parse(tel,"UA");
        } catch (NumberParseException e) {
            e.printStackTrace();
        }
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public int getRolesId() {
        return rolesId;
    }


    public void setRolesId(int rolesId) {
        this.rolesId = rolesId;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    @Override
    public String toString() {
        return "User "+ surname + " " + name + " " + login + " "  + getTel() + " " + dateOfBirth;
    }


}
