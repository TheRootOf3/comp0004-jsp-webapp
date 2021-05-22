package comp0004.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/mainListView.html")
public class ViewMainListServlet extends AbstractServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        updateModel();

        request.setAttribute("main_list", dataModel.getElement(0));
        request.setAttribute("autosave", String.valueOf(model.isAutoSave()));

        // Invoke the JSP.
        // A JSP page is actually converted into a Java class, so behind the scenes everything is Java.
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/mainListView.jsp");
        dispatch.forward(request, response);
    }
}
