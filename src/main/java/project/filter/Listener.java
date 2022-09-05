package project.filter;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
/* Слухач створення та знищення сесії */

public class Listener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        se.getSession().setAttribute("lang", LangFilter.DEFAULT_LANGUAGE);
    }
}