package project.controller;

import project.appointments.Appointment;
import project.appointments.AppointmentDetails;
import project.categories.Categories;
import project.Constants;
import project.hospitalcard.HospitalCard;
import project.users.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    static DBManager instance;

    private DBManager() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized DBManager getInstance() {
        if (instance == null) instance = new DBManager();
        return instance;
    }

    public Connection getConnection(){
        Context ctx;
        Connection c = null;
        try {
            ctx = new InitialContext();
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/myConnectionPool");
            c = ds.getConnection();
        } catch (NamingException | SQLException e) {
            e.printStackTrace();
        }
        return c;
    }

    public ResultSet getResultSet(String url) throws SQLException {
        return getConnection().prepareStatement(url).executeQuery();
    }

    public PreparedStatement getPreparedStatement(String url) throws SQLException {
        return getConnection().prepareStatement(url, PreparedStatement.RETURN_GENERATED_KEYS);
    }

    private static User getUser(ResultSet resultSet) throws SQLException {
        User user = new User(resultSet.getString("surname")
                , resultSet.getString("name")
                , resultSet.getString("login")
                , resultSet.getString("password")
                , resultSet.getString("tel")
                , resultSet.getDate("date_of_birth").toLocalDate());
        user.setId(resultSet.getInt("id_users"));
        user.setRolesId(resultSet.getInt("id_roles"));
        return user;
    }

    public void insertUser(User user) {
        try (PreparedStatement preparedStatement = getPreparedStatement(Constants.INSERT_USERS)) {
            preparedStatement.setString(1, user.getSurname());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, String.valueOf(user.getPassword().hashCode()));
            preparedStatement.setString(5, user.getTel());
            preparedStatement.setObject(6, user.getDateOfBirth());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            user.setId(generatedKeys.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void insertSysAdmin(User user) {
        try (PreparedStatement preparedStatement = getPreparedStatement(Constants.INSERT_SYS_ADMIN)) {
            if (user.getRolesId() == 0) {
                preparedStatement.setInt(1, user.getId());
                preparedStatement.executeUpdate();
                updateUserRole(1, user.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void insertDoctor(User user) {
        try (PreparedStatement preparedStatement = getPreparedStatement(Constants.INSERT_DOCTORS)) {
            if (user.getRolesId() == 0) {
                preparedStatement.setInt(1, user.getId());
                preparedStatement.executeUpdate();
                updateUserRole(2, user.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void insertNurse(User user) {
        try (PreparedStatement preparedStatement = getPreparedStatement(Constants.INSERT_NURSE)) {
            if (user.getRolesId() == 0) {
                preparedStatement.setInt(1, user.getId());
                preparedStatement.executeUpdate();
                updateUserRole(3, user.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void insertPatient(User user) {
        try (PreparedStatement preparedStatement = getPreparedStatement(Constants.INSERT_PATIENTS)) {
            if (user.getRolesId() == 0) {
                preparedStatement.setInt(1, user.getId());
                preparedStatement.executeUpdate();
                updateUserRole(4, user.getId());
                insertHospitalCard(user.getId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void insertCategory(Categories category) {
        try (PreparedStatement preparedStatement = getPreparedStatement(Constants.INSERT_CATEGORY)) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            category.setId(generatedKeys.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void insertHospitalCard(int patientId) {
        try (PreparedStatement preparedStatement = getPreparedStatement(Constants.INSERT_HOSPITAL_CARD)) {
            preparedStatement.setInt(1, patientId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void insertAppointment(Appointment appoinment) {
        try (PreparedStatement preparedStatement = getPreparedStatement(Constants.INSERT_APPOINMENT)) {
            preparedStatement.setString(1, appoinment.getName());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            appoinment.setId(generatedKeys.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void insertAppointmentDetails(AppointmentDetails appointmentDetails) {
        try (PreparedStatement preparedStatement = getPreparedStatement(Constants.INSERT_APPOINTMENT_DETAIL)) {
            preparedStatement.setString(1, appointmentDetails.getText());
            preparedStatement.setInt(2, appointmentDetails.getNurseId());
            preparedStatement.setInt(3, appointmentDetails.getAppointmentId());
            preparedStatement.setInt(4, appointmentDetails.getDoctorsId());
            preparedStatement.setInt(5, appointmentDetails.getHospitalCardId());
            preparedStatement.setObject(6, appointmentDetails.getDate());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            appointmentDetails.setId(generatedKeys.getInt(1));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void updateUserRole(int role, int id) {
        try (PreparedStatement preparedStatement = getPreparedStatement(Constants.UPDATE_USER_ROLE)) {
            preparedStatement.setInt(1, role);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void updateDoctorCategory(int categoryId, int id) {
        try (PreparedStatement preparedStatement = getPreparedStatement(Constants.UPDATE_DOCTOR_CATEGORY)) {
            preparedStatement.setInt(1, categoryId);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void updateDiagnos(String diagnos, int id) {
        try (PreparedStatement preparedStatement = getPreparedStatement(Constants.UPDATE_DIAGNOS)) {
            preparedStatement.setString(1, diagnos);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void updateAppointmentStatus(String status, int id) {
        try (PreparedStatement preparedStatement = getPreparedStatement(Constants.UPDATE_APPOINTMENT_STATUS)) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void updateHospitalCardStatus(String status, int idCard) {
        try (PreparedStatement preparedStatement = getPreparedStatement(Constants.UPDATE_HOSPITAL_CARD_STATUS)) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, idCard);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void updateNumberOfPatients(int patients, int docId) {
        try (PreparedStatement preparedStatement = getPreparedStatement(Constants.UPDATE_NUMBER_OF_PATIENTS)) {
            preparedStatement.setInt(1, patients);
            preparedStatement.setInt(2, docId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void appointADoctor(Doctor doctor, int id) {
        try (PreparedStatement preparedStatement = getPreparedStatement(Constants.APPOINT_A_DOCTOR)) {
            preparedStatement.setInt(1, doctor.getId());
            preparedStatement.setInt(2, doctor.getNumberOfPatients() + 1);
            preparedStatement.setInt(3, id);
            preparedStatement.setInt(4, doctor.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void updateUseSurname(String surname, int id) {
        try (PreparedStatement preparedStatement = getPreparedStatement(Constants.UPDATE_USER_SURNAME)) {
            preparedStatement.setString(1, surname);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void updateUserName(String name, int id) {
        try (PreparedStatement preparedStatement = getPreparedStatement(Constants.UPDATE_USER_NAME)) {
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void updateUserLogin(String login, int id) {
        try (PreparedStatement preparedStatement = getPreparedStatement(Constants.UPDATE_USER_LOGIN)) {
            preparedStatement.setString(1, login);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void updateUserPassword(String password, int id) {
        try (PreparedStatement preparedStatement = getPreparedStatement(Constants.UPDATE_USER_PASSWORD)) {
            preparedStatement.setString(1, String.valueOf(password.hashCode()));
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void updateUserTel(String tel, int id) {
        try (PreparedStatement preparedStatement = getPreparedStatement(Constants.UPDATE_USER_TEL)) {
            preparedStatement.setString(1, tel);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void dischargePatient(int id) {
        try (PreparedStatement preparedStatement = getPreparedStatement(Constants.DISCHARGE_PATIENT)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public void updateUserDateOfBirth(LocalDate dateOfBtirth, int id) {
        try (PreparedStatement preparedStatement = getPreparedStatement(Constants.UPDATE_USER_DATE_OF_BIRTH)) {
            preparedStatement.setObject(1, dateOfBtirth);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    public List<User> findAllUsers() {
        List<User> userList = new ArrayList<>();
        try (ResultSet resultSet = getResultSet(Constants.FROM_USERS)) {
            while (resultSet.next()) {
                User user = getUser(resultSet);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return userList;
    }

    public List<User> findUsersWitchOutRole() {
        List<User> userList = new ArrayList<>();
        try (ResultSet resultSet = getResultSet(Constants.FROM_USERS_WITHOUT_ROLE)) {
            while (resultSet.next()) {
                User user = getUser(resultSet);
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return userList;
    }

    public List<SysAdmin> findAllSysAdmins() {
        List<SysAdmin> sysAdminesList = new ArrayList<>();
        try (ResultSet resultSet = getResultSet(Constants.FROM_SYS_ADMIN)) {
            while (resultSet.next()) {
                SysAdmin sysAdmin = new SysAdmin(getUser(resultSet));
                sysAdminesList.add(sysAdmin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return sysAdminesList;
    }

    public List<Doctor> findAllDoctores(String sorted) {
        List<Doctor> DoctoresList = new ArrayList<>();
        try (ResultSet resultSet = getResultSet(Constants.FROM_DOCTORS + sorted)) {
            while (resultSet.next()) {
                Doctor doctor = new Doctor(getUser(resultSet));
                doctor.setCategoryId(resultSet.getInt("category_id"));
                doctor.setNumberOfPatients(resultSet.getInt("number_of_patients"));
                doctor.setCategory(resultSet.getString("category.name"));
                DoctoresList.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return DoctoresList;
    }

    public List<Doctor> findDoctoresWithLimit(String sorted, int start, int total) {
        start = (start - 1) * total;
        String limit = " limit " + start + "," + total;
        List<Doctor> DoctoresList = new ArrayList<>();
        try (ResultSet resultSet = getResultSet(Constants.FROM_DOCTORS + sorted + limit)) {
            while (resultSet.next()) {
                Doctor doctor = new Doctor(getUser(resultSet));
                doctor.setCategoryId(resultSet.getInt("category_id"));
                doctor.setNumberOfPatients(resultSet.getInt("number_of_patients"));
                doctor.setCategory(resultSet.getString("category.name"));
                DoctoresList.add(doctor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return DoctoresList;
    }


    public List<Nurse> findAllNurse() {
        List<Nurse> nurseList = new ArrayList<>();
        try (ResultSet resultSet = getResultSet(Constants.FROM_NURSE)) {
            while (resultSet.next()) {
                Nurse nurse = new Nurse(getUser(resultSet));
                nurseList.add(nurse);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return nurseList;
    }

    public List<Patient> findAllPatients(String sorted) {
        List<Patient> patientList = new ArrayList<>();
        try (ResultSet resultSet = getResultSet(Constants.FROM_PATIENTS + sorted)) {
            while (resultSet.next()) {
                Patient patient = new Patient(getUser(resultSet));
                patient.setCurrentDoctorId(resultSet.getInt("current_doctor_id"));
                patientList.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return patientList;
    }

    public List<Patient> findPatientsWithLimit(String sorted, int start, int total) {
        start = (start - 1) * total;
        String limit = " limit " + start + "," + total;
        List<Patient> patientList = new ArrayList<>();
        try (ResultSet resultSet = getResultSet(Constants.FROM_PATIENTS + sorted + limit)) {
            while (resultSet.next()) {
                Patient patient = new Patient(getUser(resultSet));
                patient.setCurrentDoctorId(resultSet.getInt("current_doctor_id"));
                patientList.add(patient);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return patientList;
    }

    public List<Categories> findAllCategories() {
        List<Categories> categoriesList = new ArrayList<>();
        try (ResultSet resultSet = getResultSet(Constants.FROM_CATEGORIES)) {
            while (resultSet.next()) {
                Categories category = new Categories(resultSet.getString("name"));
                category.setId(resultSet.getInt("id"));
                categoriesList.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return categoriesList;
    }

    public List<Appointment> findAllAppointments() {
        List<Appointment> appointmentList = new ArrayList<>();
        try (ResultSet resultSet = getResultSet(Constants.FROM_APPOINTMENTS)) {
            while (resultSet.next()) {
                Appointment appointment = new Appointment(resultSet.getString("name"));
                appointment.setId(resultSet.getInt("id"));
                appointmentList.add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return appointmentList;
    }

    public List<AppointmentDetails> findAllAppointmentDetailsByID(int hospitalCardId) {
        List<AppointmentDetails> appointmentDetailsList = new ArrayList<>();
        try (PreparedStatement preparedStatement = getPreparedStatement(Constants.FROM_APPOINTMENT_DETAIL)) {
            preparedStatement.setInt(1, hospitalCardId);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                LocalDate date = resultSet.getDate("date").toLocalDate();
                if (date.equals(LocalDate.now()) || date.isAfter(LocalDate.now())) {
                    Doctor doctor = findDoctorById(resultSet.getInt("doctors_id"));

                    Nurse nurse = findNurseById(resultSet.getInt("nurse_id"));
                    AppointmentDetails appointmentDetails = new AppointmentDetails(resultSet.getString("text"), date);
                    appointmentDetails.setId(resultSet.getInt("id"));
                    appointmentDetails.setNurseId(resultSet.getInt("nurse_id"));
                    appointmentDetails.setAppointmentId(resultSet.getInt("appoinment_id"));
                    appointmentDetails.setDoctorsId(resultSet.getInt("doctors_id"));
                    appointmentDetails.setHospitalCardId(resultSet.getInt("hospital_card_id"));
                    appointmentDetails.setAppointment(resultSet.getString("name"));
                    appointmentDetails.setStatus(resultSet.getString("status"));

                    appointmentDetails.setDoctorFullName(doctor.getSurname() + " " + doctor.getName());
                    appointmentDetails.setNurseFullName(nurse.getSurname() + " " + nurse.getName());
                    appointmentDetailsList.add(appointmentDetails);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return appointmentDetailsList;
    }

    public List<AppointmentDetails> findAllAppointmentsForNurse(int nurseId) {
        List<AppointmentDetails> appointmentDetailsList = new ArrayList<>();
        try (PreparedStatement preparedStatement = getPreparedStatement(Constants.FROM_APPOINTMENTS_FOR_NURSE)) {
            preparedStatement.setInt(1, nurseId);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                String status = resultSet.getString("status");
                LocalDate date = resultSet.getDate("date").toLocalDate();
                if (!status.equals("done"))
                    if (date.equals(LocalDate.now()) || date.isAfter(LocalDate.now())) {
                        Doctor doctor = findDoctorById(resultSet.getInt("doctors_id"));

                        Patient patient = findPatientById(resultSet.getInt("hospital_card_id"));
                        AppointmentDetails appointmentDetails = new AppointmentDetails(resultSet.getString("text"), date);
                        appointmentDetails.setId(resultSet.getInt("id"));
                        appointmentDetails.setNurseId(resultSet.getInt("nurse_id"));
                        appointmentDetails.setAppointmentId(resultSet.getInt("appoinment_id"));
                        appointmentDetails.setDoctorsId(resultSet.getInt("doctors_id"));
                        appointmentDetails.setHospitalCardId(resultSet.getInt("hospital_card_id"));
                        appointmentDetails.setAppointment(resultSet.getString("name"));
                        appointmentDetails.setStatus(status);

                        appointmentDetails.setDoctorFullName(doctor.getSurname() + " " + doctor.getName());
                        appointmentDetails.setPatientFullName(patient.getSurname() + " " + patient.getName());
                        appointmentDetailsList.add(appointmentDetails);
                    }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return appointmentDetailsList;
    }

    public Categories findCategory(String name) {
        Categories category = null;
        try (PreparedStatement preparedStatement = getPreparedStatement(Constants.FIND_CATEGORY)) {
            preparedStatement.setString(1, name);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            while (resultSet.next()) {
                category = new Categories(resultSet.getString("name"));
                category.setId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return category;
    }

    public User findUserByLogin(String login) {
        User user = null;
        try (PreparedStatement preparedStatement = getPreparedStatement(Constants.FIND_BY_LOGIN)) {
            preparedStatement.setString(1, login);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                user = getUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return user;
    }

    public Appointment findAppointmentByName(String name) {
        Appointment appointment = null;
        try (PreparedStatement preparedStatement = getPreparedStatement(Constants.FIND_APPOINTMENT)) {
            preparedStatement.setString(1, name);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                appointment = new Appointment(resultSet.getString("name"));
                appointment.setId(resultSet.getInt("id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return appointment;
    }

    public User findUserByID(int id) {
        User user = null;
        try (PreparedStatement preparedStatement = getPreparedStatement(Constants.FIND_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                user = getUser(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return user;
    }

    public Doctor findDoctorById(int id) {
        Doctor doctor = null;
        try (PreparedStatement preparedStatement = getPreparedStatement(Constants.FIND_DOCTOR_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                doctor = new Doctor(getUser(resultSet));
                doctor.setCategoryId(resultSet.getInt("category_id"));
                doctor.setNumberOfPatients(resultSet.getInt("number_of_patients"));
                doctor.setCategory(resultSet.getString("category.name"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return doctor;
    }

    public Patient findPatientById(int id) {
        Patient patient = null;
        try (PreparedStatement preparedStatement = getPreparedStatement(Constants.FIND_PATIENT_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                patient = new Patient(getUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return patient;
    }

    public Nurse findNurseById(int id) {
        Nurse nurse = null;
        try (PreparedStatement preparedStatement = getPreparedStatement(Constants.FIND_NURSE_BY_ID)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                nurse = new Nurse(getUser(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return nurse;
    }

    public HospitalCard getHospitalCard(int id) {
        HospitalCard hospitalCard = null;
        try (PreparedStatement preparedStatement = getPreparedStatement(Constants.GET_A_HOSPITAL_CARD)) {
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                Doctor doctor = findDoctorById(resultSet.getInt("current_doctor_id"));

                hospitalCard = new HospitalCard(resultSet.getInt("id_card"));
                hospitalCard.setDiagnosis(resultSet.getString("diagnosis"));
                hospitalCard.setDoctorsId(resultSet.getInt("current_doctor_id"));
                hospitalCard.setCreateTime(resultSet.getDate("create_time").toLocalDate());
                hospitalCard.setPatientsName(resultSet.getString("name"));
                hospitalCard.setPatientsSurname(resultSet.getString("surname"));
                hospitalCard.setStatus(resultSet.getString("status"));

                if (doctor != null) {
                    hospitalCard.setCurrentDoctorName(doctor.getName());
                    hospitalCard.setCurrentDoctorSurname(doctor.getSurname());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return hospitalCard;
    }

    public List<HospitalCard> getAllHospitalCards() {
        List<HospitalCard> hospitalCardList = new ArrayList<>();
        try (ResultSet resultSet = getResultSet(Constants.GET_ALL_HOSPITAL_CARDS)) {
            while (resultSet.next()) {
                Doctor doctor = findDoctorById(resultSet.getInt("current_doctor_id"));

                HospitalCard hospitalCard = new HospitalCard(resultSet.getInt("id_card"));
                hospitalCard.setDiagnosis(resultSet.getString("diagnosis"));
                hospitalCard.setDoctorsId(resultSet.getInt("current_doctor_id"));
                hospitalCard.setCreateTime(resultSet.getDate("create_time").toLocalDate());
                hospitalCard.setPatientsName(resultSet.getString("name"));
                hospitalCard.setPatientsSurname(resultSet.getString("surname"));
                hospitalCard.setStatus(resultSet.getString("status"));

                if (doctor != null) {
                    hospitalCard.setCurrentDoctorName(doctor.getName());
                    hospitalCard.setCurrentDoctorSurname(doctor.getSurname());
                }
                hospitalCardList.add(hospitalCard);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return hospitalCardList;
    }

    public List<HospitalCard> getHospitalCardsWithLimit(int start, int total) {
        start = (start - 1) * total;
        String limit = " limit " + start + "," + total;
        List<HospitalCard> hospitalCardList = new ArrayList<>();
        try (ResultSet resultSet = getResultSet(Constants.GET_ALL_HOSPITAL_CARDS + limit)) {
            while (resultSet.next()) {
                Doctor doctor = findDoctorById(resultSet.getInt("current_doctor_id"));

                HospitalCard hospitalCard = new HospitalCard(resultSet.getInt("id_card"));
                hospitalCard.setDiagnosis(resultSet.getString("diagnosis"));
                hospitalCard.setDoctorsId(resultSet.getInt("current_doctor_id"));
                hospitalCard.setCreateTime(resultSet.getDate("create_time").toLocalDate());
                hospitalCard.setPatientsSurname(resultSet.getString("surname"));
                hospitalCard.setPatientsName(resultSet.getString("name"));
                hospitalCard.setStatus(resultSet.getString("status"));

                if (doctor != null) {
                    hospitalCard.setCurrentDoctorName(doctor.getName());
                    hospitalCard.setCurrentDoctorSurname(doctor.getSurname());
                }
                hospitalCardList.add(hospitalCard);

            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return hospitalCardList;
    }
}
