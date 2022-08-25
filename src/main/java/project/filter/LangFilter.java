package project.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LangFilter implements Filter {


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServlet = (HttpServletRequest) servletRequest;
        Object lang = httpServlet.getSession().getAttribute("lang");
        if (lang == null){
            httpServlet.getSession().setAttribute("lang", "UA");
            lang = "UA";
        }
        if (lang.toString().equals("UA")) {
            httpServlet.getSession().setAttribute("langSysAdmin", "Системний адміністратор");
            httpServlet.getSession().setAttribute("langDoctor", "Лікар");
            httpServlet.getSession().setAttribute("langNurse", "Медсестра");
            httpServlet.getSession().setAttribute("langPatient", "Пацієнт");
            httpServlet.getSession().setAttribute("langEmpty", "порожньо");
            httpServlet.getSession().setAttribute("langSetLanguage", "Мова");
            httpServlet.getSession().setAttribute("langUkrainian", "Українська");
            httpServlet.getSession().setAttribute("langEnglish", "Англійська");
            httpServlet.getSession().setAttribute("langLogin", "Логін");
            httpServlet.getSession().setAttribute("langPassword", "Пароль");
            httpServlet.getSession().setAttribute("langGo", "Вперед!");
            httpServlet.getSession().setAttribute("langMain", "Головна");
            httpServlet.getSession().setAttribute("langAboutUs", "Про нас");
            httpServlet.getSession().setAttribute("langContacts", "Контакти");
            httpServlet.getSession().setAttribute("langDoctors", "Лікарі");
            httpServlet.getSession().setAttribute("langGetThem", "Отримати їх!");
            httpServlet.getSession().setAttribute("langSorted", "Сортувати по");
            httpServlet.getSession().setAttribute("langBySurname", "по Прізвищу");
            httpServlet.getSession().setAttribute("langByCategory", "за категорією");
            httpServlet.getSession().setAttribute("langByNOP", "за кількістю пацієнтів");
            httpServlet.getSession().setAttribute("langSurname", "Прізвище");
            httpServlet.getSession().setAttribute("langName", "Ім'я");
            httpServlet.getSession().setAttribute("langCategory", "Категорія");
            httpServlet.getSession().setAttribute("langNOP", "Кількість пацієнтів");
            httpServlet.getSession().setAttribute("langRegister", "Реєстрація");
            httpServlet.getSession().setAttribute("langEMP", "Редагувати профіль");
            httpServlet.getSession().setAttribute("langLogIn", "Увійти");
            httpServlet.getSession().setAttribute("langLogOut", "Вийти");
            httpServlet.getSession().setAttribute("langGivingARole", "Призначити роль");
            httpServlet.getSession().setAttribute("langGivingACategory", "Призначити категорію");
            httpServlet.getSession().setAttribute("langPatients", "Пацієнти");
            httpServlet.getSession().setAttribute("langAppointADoctor", "Призначити лікаря");
            httpServlet.getSession().setAttribute("langAddCategory", "Додати Категорію");
            httpServlet.getSession().setAttribute("langAppointADoctorForPatient", "Призначити лікаря пацієнту");
            httpServlet.getSession().setAttribute("langByDateOfBirth", "за датою народження");
            httpServlet.getSession().setAttribute("langGetPatientsWithOutADoctor", "Отримати пацієнтів без лікаря!");
            httpServlet.getSession().setAttribute("langPick", "Обрати");
            httpServlet.getSession().setAttribute("langPickACategory", "Обрати категорію");
            httpServlet.getSession().setAttribute("langPickADoctor", "Обрати лікаря");
            httpServlet.getSession().setAttribute("langGiveACategoryForDoctor", "Призначити категорію лікарю");
            httpServlet.getSession().setAttribute("langGetADoctors", "Отримати лікарів");
            httpServlet.getSession().setAttribute("langPickARole", "Обрати роль");
            httpServlet.getSession().setAttribute("langGetUsers", "Отримати користувачів");
            httpServlet.getSession().setAttribute("langGetAllPatients", "Отримати всіх пацієнтів");
            httpServlet.getSession().setAttribute("langDateOfBirth", "Дата народження");
            httpServlet.getSession().setAttribute("langMyPatients", "Мої пацієнти");
            httpServlet.getSession().setAttribute("langHospitalCards", "Лікарняні карти");
            httpServlet.getSession().setAttribute("langAddAppointment", "Додати призначення");
            httpServlet.getSession().setAttribute("langGetMyPatients", "Мої пацієнти");
            httpServlet.getSession().setAttribute("langGetDetail", "Детальніше");
            httpServlet.getSession().setAttribute("langGoTo", "перейти");
            httpServlet.getSession().setAttribute("langGetHospitalCards", "Отримати лікарняні карти");
            httpServlet.getSession().setAttribute("langGetAppointmentsOfPatient", "Отримати призначення пацієнта");
            httpServlet.getSession().setAttribute("langAppointment", "Призначення");
            httpServlet.getSession().setAttribute("langAppointmentDetails", "Деталі Призначення");
            httpServlet.getSession().setAttribute("langCurrentDoctor", "Поточний Лікар");
            httpServlet.getSession().setAttribute("langDesignatedNurse", "Призначена Медсестра");
            httpServlet.getSession().setAttribute("langDate", "Дата");
            httpServlet.getSession().setAttribute("langStatus", "Статус");
            httpServlet.getSession().setAttribute("langStatusUpdated", "Статус оновлено");
            httpServlet.getSession().setAttribute("langPickAPoint", "Обрати призначення");
            httpServlet.getSession().setAttribute("langDetails", "Деталі");
            httpServlet.getSession().setAttribute("langInsertNewAppointment", "Додати нове призначення");
            httpServlet.getSession().setAttribute("langPickANurse", "Обрати медсестру");
            httpServlet.getSession().setAttribute("langPatientSurname", "Прізвище пацієнта");
            httpServlet.getSession().setAttribute("langPatientName", "Ім'я пацієнта");
            httpServlet.getSession().setAttribute("langDateOfRegistration", "Дата Реєстрації");
            httpServlet.getSession().setAttribute("langDoctorSurname", "Прізвище лікаря");
            httpServlet.getSession().setAttribute("langDoctorName", "Ім'я лікаря");
            httpServlet.getSession().setAttribute("langCurrentDiagnosis", "Поточний діагноз");
            httpServlet.getSession().setAttribute("langChangeDiagnosis", "Змінити діагноз");
            httpServlet.getSession().setAttribute("langEnterNewDiagnosis", "Введіть новий діагноз");
            httpServlet.getSession().setAttribute("langSetStatus", "Встановити статус");
            httpServlet.getSession().setAttribute("langGoBack", "Повернутись");
            httpServlet.getSession().setAttribute("langMyAppointments", "Мої призначення");
            httpServlet.getSession().setAttribute("langGetMyAppointments", "Отримати мої призначення");
            httpServlet.getSession().setAttribute("langGoToLogin", "Повернутись на головну");
            httpServlet.getSession().setAttribute("langNotExistYet", "Ще не встановлено");
            httpServlet.getSession().setAttribute("langNotAssigned", "Не призначено");
            httpServlet.getSession().setAttribute("langGetMyHospitalCard", "Отримати мою лікарняну карту");
            httpServlet.getSession().setAttribute("langGet", "Отримати");
            httpServlet.getSession().setAttribute("langNeedHelpAgain", "Знову потрібна допомга");
            httpServlet.getSession().setAttribute("langNotFound", "не знайдено");
            httpServlet.getSession().setAttribute("langWrongPassword", "пароль некорректний");
            httpServlet.getSession().setAttribute("langNewPassword", "Новий пароль");
            httpServlet.getSession().setAttribute("langRePassword", "Повторіть пароль");
            httpServlet.getSession().setAttribute("langTel", "Телефон");
            httpServlet.getSession().setAttribute("langEditNow", "Редагувати зараз!");
            httpServlet.getSession().setAttribute("langBackToMain", "Повернутись на головну");
            httpServlet.getSession().setAttribute("langToday", "сьогодні");
            httpServlet.getSession().setAttribute("langEnterSurname", "Введіть Прізвище");
            httpServlet.getSession().setAttribute("langEnterName", "Введіть Ім'я");
            httpServlet.getSession().setAttribute("langEnterLogin", "Введіть Логін");
            httpServlet.getSession().setAttribute("langEnterPassword", "Введіть пароль");
            httpServlet.getSession().setAttribute("langEnterTel", "Введіть телефон");
        } else {
            httpServlet.getSession().setAttribute("langSysAdmin", "System administrator");
            httpServlet.getSession().setAttribute("langDoctor", "Doctor");
            httpServlet.getSession().setAttribute("langNurse", "Nurse");
            httpServlet.getSession().setAttribute("langPatient", "Patient");
            httpServlet.getSession().setAttribute("langEmpty", "empty");
            httpServlet.getSession().setAttribute("langSetLanguage", "Language");
            httpServlet.getSession().setAttribute("langUkrainian", "Ukrainian");
            httpServlet.getSession().setAttribute("langEnglish", "English");
            httpServlet.getSession().setAttribute("langLogin", "Login");
            httpServlet.getSession().setAttribute("langPassword", "Password");
            httpServlet.getSession().setAttribute("langGo", "Go!");
            httpServlet.getSession().setAttribute("langMain", "Main");
            httpServlet.getSession().setAttribute("langAboutUs", "About Us");
            httpServlet.getSession().setAttribute("langContacts", "Contacts");
            httpServlet.getSession().setAttribute("langDoctors", "Doctors");
            httpServlet.getSession().setAttribute("langGetThem", "Get them!");
            httpServlet.getSession().setAttribute("langSorted", "Sorted by");
            httpServlet.getSession().setAttribute("langBySurname", "by Surname");
            httpServlet.getSession().setAttribute("langByCategory", "by Category");
            httpServlet.getSession().setAttribute("langByNOP", "by number of patients");
            httpServlet.getSession().setAttribute("langSurname", "Surname");
            httpServlet.getSession().setAttribute("langName", "Name");
            httpServlet.getSession().setAttribute("langCategory", "Category");
            httpServlet.getSession().setAttribute("langNOP", "Number of patients");
            httpServlet.getSession().setAttribute("langRegister", "Register now");
            httpServlet.getSession().setAttribute("langEMP", "Edit my Profile");
            httpServlet.getSession().setAttribute("langLogIn", "Log in");
            httpServlet.getSession().setAttribute("langLogOut", "Log out");
            httpServlet.getSession().setAttribute("langGivingARole", "Giving a role");
            httpServlet.getSession().setAttribute("langGivingACategory", "Giving a Category");
            httpServlet.getSession().setAttribute("langPatients", "Patients");
            httpServlet.getSession().setAttribute("langAppointADoctor", "Appoint a Doctor");
            httpServlet.getSession().setAttribute("langAddCategory", "Add Category");
            httpServlet.getSession().setAttribute("langAppointADoctorForPatient", "Appoint a doctor for patient");
            httpServlet.getSession().setAttribute("langByDateOfBirth", "by date of birth");
            httpServlet.getSession().setAttribute("langGetPatientsWithOutADoctor", "Get patients Without a Doctor!");
            httpServlet.getSession().setAttribute("langPickADoctor", "Pick a Doctor");
            httpServlet.getSession().setAttribute("langPick", "Pick");
            httpServlet.getSession().setAttribute("langPickACategory", "Pick a category");
            httpServlet.getSession().setAttribute("langGiveACategoryForDoctor", "Give a category for doctor");
            httpServlet.getSession().setAttribute("langGetADoctors", "Get a doctors");
            httpServlet.getSession().setAttribute("langPickARole", "Pick a role");
            httpServlet.getSession().setAttribute("langGetUsers", "Get users");
            httpServlet.getSession().setAttribute("langGetAllPatients", "Get All Patients");
            httpServlet.getSession().setAttribute("langDateOfBirth", "Date of Birth");
            httpServlet.getSession().setAttribute("langMyPatients", "My patients");
            httpServlet.getSession().setAttribute("langHospitalCards", "Hospital cards");
            httpServlet.getSession().setAttribute("langAddAppointment", "add Appointment");
            httpServlet.getSession().setAttribute("langGetMyPatients", "My patients");
            httpServlet.getSession().setAttribute("langGetDetail", "get Detail");
            httpServlet.getSession().setAttribute("langGoTo", "go to");
            httpServlet.getSession().setAttribute("langGetHospitalCards", "Get Hospital cards");
            httpServlet.getSession().setAttribute("langGetAppointmentsOfPatient", "Get Appointments of Patient");
            httpServlet.getSession().setAttribute("langAppointment", "Appointment");
            httpServlet.getSession().setAttribute("langAppointmentDetails", "Appointment Details");
            httpServlet.getSession().setAttribute("langCurrentDoctor", "Current Doctor");
            httpServlet.getSession().setAttribute("langDesignatedNurse", "Designated Nurse");
            httpServlet.getSession().setAttribute("langDate", "Date");
            httpServlet.getSession().setAttribute("langStatus", "Status");
            httpServlet.getSession().setAttribute("langStatusUpdated", "Status updated");
            httpServlet.getSession().setAttribute("langPickAPoint", "Pick appointment");
            httpServlet.getSession().setAttribute("langDetails", "Details");
            httpServlet.getSession().setAttribute("langInsertNewAppointment", "Insert new Appointment");
            httpServlet.getSession().setAttribute("langPickANurse", "Pick a nurse");
            httpServlet.getSession().setAttribute("langPatientSurname", "Patient`s Surname");
            httpServlet.getSession().setAttribute("langPatientName", "Patient`s Name");
            httpServlet.getSession().setAttribute("langDateOfRegistration", "Date of Registration");
            httpServlet.getSession().setAttribute("langDoctorSurname", "Doctor`s Surname");
            httpServlet.getSession().setAttribute("langDoctorName", "Doctor`s Name");
            httpServlet.getSession().setAttribute("langCurrentDiagnosis", "Current Diagnosis");
            httpServlet.getSession().setAttribute("langChangeDiagnosis", "Change Diagnosis");
            httpServlet.getSession().setAttribute("langEnterNewDiagnosis", "Enter New Diagnosis");
            httpServlet.getSession().setAttribute("langSetStatus", "Set status");
            httpServlet.getSession().setAttribute("langGoBack", "go back");
            httpServlet.getSession().setAttribute("langMyAppointments", "My appointments");
            httpServlet.getSession().setAttribute("langGetMyAppointments", "Get my appointments");
            httpServlet.getSession().setAttribute("langGoToLogin", "Go to Login");
            httpServlet.getSession().setAttribute("langNotExistYet", "Not exist yet");
            httpServlet.getSession().setAttribute("langNotAssigned", "Not assigned");
            httpServlet.getSession().setAttribute("langGetMyHospitalCard", "Get my hospital card");
            httpServlet.getSession().setAttribute("langGet", "Get");
            httpServlet.getSession().setAttribute("langNeedHelpAgain", "Need help again");
            httpServlet.getSession().setAttribute("langNotFound", "not found");
            httpServlet.getSession().setAttribute("langWrongPassword", "password is wrong");
            httpServlet.getSession().setAttribute("langNewPassword", "New Password");
            httpServlet.getSession().setAttribute("langRePassword", "Re-enter Password");
            httpServlet.getSession().setAttribute("langTel", "Tel");
            httpServlet.getSession().setAttribute("langEditNow", "Edit now!");
            httpServlet.getSession().setAttribute("langBackToMain", "Back to Main");
            httpServlet.getSession().setAttribute("langToday", "today");
            httpServlet.getSession().setAttribute("langEnterSurname", "Enter Surname");
            httpServlet.getSession().setAttribute("langEnterName", "Enter Name");
            httpServlet.getSession().setAttribute("langEnterLogin", "Enter Login");
            httpServlet.getSession().setAttribute("langEnterPassword", "Enter Password");
            httpServlet.getSession().setAttribute("langEnterTel", "Enter Tel");
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }
}
