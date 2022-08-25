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
            httpServlet.getSession().setAttribute("langSysAdmin", "системний адміністратор");
            httpServlet.getSession().setAttribute("langDoctor", "лікар");
            httpServlet.getSession().setAttribute("langNurse", "медсестра");
            httpServlet.getSession().setAttribute("langPatient", "пацієнт");
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
        } else {
            httpServlet.getSession().setAttribute("langSysAdmin", "system administrator");
            httpServlet.getSession().setAttribute("langDoctor", "doctor");
            httpServlet.getSession().setAttribute("langNurse", "nurse");
            httpServlet.getSession().setAttribute("langPatient", "patient");
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





        }
        filterChain.doFilter(servletRequest, servletResponse);

    }
}
