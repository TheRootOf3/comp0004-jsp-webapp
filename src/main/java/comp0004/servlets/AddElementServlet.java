package comp0004.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addElement.html")
public class AddElementServlet extends AbstractServlet {

    public AddElementServlet() throws IOException {
        super();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        updateModel();
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
            response.sendRedirect("/mainListView.html");
        } else {
            response.sendRedirect("/itemListView.html?list=" + listID);
        }

    }
}
