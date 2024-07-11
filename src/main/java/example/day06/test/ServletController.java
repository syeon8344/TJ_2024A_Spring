package example.day06.test;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/day06/test")
public class ServletController extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String value = req.getParameter("value");
        int val = Integer.parseInt(value);
        resp.getWriter().print(val+2);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String value = req.getParameter("value");
        int val = Integer.parseInt(value);
        resp.getWriter().print(val*2);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String value = req.getParameter("value");
        int val = Integer.parseInt(value);
        resp.getWriter().print(val/2);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String value = req.getParameter("value");
        int val = Integer.parseInt(value);
        resp.getWriter().print(val%2);
    }
}
