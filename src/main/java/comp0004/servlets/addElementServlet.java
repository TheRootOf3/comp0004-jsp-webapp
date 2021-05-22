package comp0004.servlets;

import comp0004.model.DataModel;
import comp0004.model.Model;
import comp0004.model.ModelFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addElement.html")
public class addElementServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Get the data from the model
        Model model = ModelFactory.getModel();
        DataModel dataModel = model.getDataModel();
        int listID = Integer.parseInt(request.getParameter("list"));
        String label = request.getParameter("element_label");
        String type = request.getParameter("type");

        dataModel.addNewElementToList(label, listID, -1, type);

        if (model.isAutoSave())
            model.saveAll(false);

        // Invoke the JSP.
        // A JSP page is actually converted into a Java class, so behind the scenes everything is Java.

        //        Direct differently depending on the list type
        if (listID == 0) {
            request.setAttribute("main_list", dataModel.getElement(0));
            response.sendRedirect("/mainListView2.html");
        } else {
            response.sendRedirect("/itemListView.html?list=" + listID);
        }

    }
}
