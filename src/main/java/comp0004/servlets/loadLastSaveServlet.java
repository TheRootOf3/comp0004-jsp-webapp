package comp0004.servlets;

import comp0004.model.DataFrame;
import comp0004.model.Model;
import comp0004.model.ModelFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/loadLastSave.html")
public class loadLastSaveServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Get the data from the model

        Model model = ModelFactory.reloadModel();
        DataFrame dataFrame = model.getDataFrame();
        int listID = Integer.parseInt(request.getParameter("list"));

        // Invoke the JSP.
        // A JSP page is actually converted into a Java class, so behind the scenes everything is Java.
        if (listID == 0) {
            request.setAttribute("main_list", dataFrame.getElement(0));
            response.sendRedirect("/mainListView2.html");
        } else {
            response.sendRedirect("/itemListView.html?list=" + listID);
        }

    }
}
