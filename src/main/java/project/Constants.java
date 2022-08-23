package project;

public class Constants {
    public static final String URL = "jdbc:mysql://localhost:3306/mydb?user=root&password=root";

    public static final String INSERT_USERS = "INSERT INTO users (surname,name, login, password,tel,date_of_birth) VALUES (?,?,?,?,?,?)";
    public static final String INSERT_SYS_ADMIN = "INSERT INTO sys_admin (id) VALUES (?)";
    public static final String INSERT_DOCTORS = "INSERT INTO doctors (id) VALUES (?)";
    public static final String INSERT_NURSE = "INSERT INTO nurse (id) VALUES (?)";
    public static final String INSERT_PATIENTS = "INSERT INTO patients (id ) VALUES (?)";
    public static final String INSERT_CATEGORY = "INSERT INTO category (name) VALUES (?)";
    public static final String INSERT_HOSPITAL_CARD = "INSERT INTO hospital_card (id_card) VALUES (?)";
    public static final String INSERT_APPOINMENT= "INSERT INTO appointment (name) VALUES (?)";
    public static final String INSERT_APPOINTMENT_DETAIL = "INSERT INTO appoinment_detail (text,nurse_id,appoinment_id,doctors_id,hospital_card_id,date) VALUES (?,?,?,?,?,?)";
    public static final String UPDATE_USER_ROLE = "UPDATE users SET `id_roles` = ? WHERE (`id_users` = ?)";
    public static final String UPDATE_DOCTOR_CATEGORY = "UPDATE doctors SET `category_id` = ? WHERE (`id` = ?)";
    public static final String UPDATE_DIAGNOS= "UPDATE hospital_card SET diagnosis = ? WHERE id_card =?";
    public static final String UPDATE_APPOINTMENT_STATUS= "UPDATE appoinment_detail SET status = ? WHERE id = ? ";
    public static final String UPDATE_HOSPITAL_CARD_STATUS= "UPDATE hospital_card SET status = ? WHERE id_card = ? ";
    public static final String APPOINT_A_DOCTOR = "UPDATE hospital_card,doctors SET `current_doctor_id` = ? , doctors.number_of_patients= ? WHERE id_card = ? AND doctors.id=?";
    public static final String FROM_USERS = "SELECT * FROM users ";
    public static final String FROM_USERS_WITHOUT_ROLE = "SELECT * FROM users where id_roles is null";
    public static final String FROM_SYS_ADMIN = "SELECT * FROM sys_admin LEFT JOIN users on id_users=id ";
    public static final String FROM_DOCTORS = "SELECT * FROM doctors LEFT JOIN users on id_users=id LEFT JOIN category on category_id=category.id ";
    public static final String FROM_NURSE = "SELECT * FROM nurse LEFT JOIN users on id_users=id ";
    public static final String FROM_PATIENTS = "SELECT * FROM patients LEFT JOIN users on id_users=id LEFT JOIN hospital_card on id_card = id ";
    public static final String FROM_CATEGORIES = "SELECT * FROM category ";
    public static final String FROM_APPOINTMENTS = "SELECT * FROM appointment ";
    public static final String FROM_APPOINTMENT_DETAIL = "SELECT * FROM appoinment_detail left join appointment on appoinment_id = appointment.id where hospital_card_id = ?";
    public static final String FROM_APPOINTMENTS_FOR_NURSE = "SELECT * FROM appoinment_detail left join appointment on appoinment_id = appointment.id where nurse_id = ?";
    public static final String FIND_CATEGORY = "SELECT * FROM category WHERE name = ?";
    public static final String FIND_BY_LOGIN = "SELECT * FROM users WHERE login = ? ";
    public static final String FIND_APPOINTMENT= "SELECT * FROM appointment WHERE name = ? ";
    public static final String FIND_BY_ID = "SELECT * FROM users WHERE id_users = ? ";
    public static final String FIND_DOCTOR_BY_ID = "SELECT * FROM doctors LEFT JOIN users on id_users = id LEFT JOIN category on category_id = category.id WHERE doctors.id = ?";
    public static final String FIND_PATIENT_BY_ID = "SELECT * FROM patients LEFT JOIN users on id_users = id  WHERE patients.id = ?";
    public static final String FIND_NURSE_BY_ID = "SELECT * FROM nurse LEFT JOIN users on id_users = id WHERE id = ?";
    public static final String GET_A_HOSPITAL_CARD = "SELECT * FROM hospital_card LEFT JOIN users on id_card=id_users WHERE id_card = ?";
    public static final String GET_ALL_HOSPITAL_CARDS = "SELECT * FROM hospital_card LEFT JOIN users on id_card=id_users";
    public static final String UPDATE_USER_SURNAME = "UPDATE users SET `surname` = ? WHERE (`id_users` = ?)";
    public static final String UPDATE_USER_NAME = "UPDATE users SET `name` = ? WHERE (`id_users` = ?)";
    public static final String UPDATE_USER_LOGIN = "UPDATE users SET `login` = ? WHERE (`id_users` = ?)";
    public static final String UPDATE_USER_PASSWORD = "UPDATE users SET `password` = ? WHERE (`id_users` = ?)";
    public static final String UPDATE_USER_TEL = "UPDATE users SET `tel` = ? WHERE (`id_users` = ?)";
    public static final String UPDATE_USER_DATE_OF_BIRTH = "UPDATE users SET `date_of_birth`= ?  WHERE `id_users` = ?";


}