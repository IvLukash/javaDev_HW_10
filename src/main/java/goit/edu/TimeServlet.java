package goit.edu;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/time")
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html; charset=utf-8");
        String timezone = req.getAttribute("timezone").toString();
        String formattedDateTime = Formatter.getFormattedDateTime(timezone);
        resp.setStatus(HttpServletResponse.SC_OK);
        resp.getWriter().write("<h1>%s</h1>".formatted(formattedDateTime));
    }
}
