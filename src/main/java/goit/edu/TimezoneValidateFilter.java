package goit.edu;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebFilter("/time")
public class TimezoneValidateFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        try {
            String timezone = req.getParameter("timezone");
            if (timezone == null || timezone.isBlank()) {
                timezone = Formatter.UTC_ZONE;
            } else {
                Formatter.getTimeOffset(timezone);
            }
            req.setAttribute("timezone", timezone);
            chain.doFilter(req, res);
        } catch (IllegalArgumentException ex) {
            res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            res.setContentType("text/plain; charset=utf-8");
            res.getWriter().write(ex.getMessage());
        } catch (Exception ex) {
            throw new ServletException("Server error", ex);
        }
    }
}
