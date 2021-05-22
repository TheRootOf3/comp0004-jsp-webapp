package comp0004.servlets;

import comp0004.model.DataModel;
import comp0004.model.Model;
import comp0004.model.ModelFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/searchInLabels.html")
public class SearchInLabelsServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Get the data from the model
        Model model = ModelFactory.getModel();
        DataModel dataModel = model.getDataModel();
        String keyword = request.getParameter("keyword_to_search");

        request.setAttribute("traces", dataModel.searchInElementLabels(keyword));

        // Invoke the JSP.
        // A JSP page is actually converted into a Java class, so behind the scenes everything is Java.
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = context.getRequestDispatcher("/searchInLabels.jsp");
        dispatch.forward(request, response);
    }
}
