package comp0004.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteThing.html")
public class DeleteThingServlet extends AbstractServlet {

    public DeleteThingServlet() throws IOException {
        super();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        int listID = Integer.parseInt(request.getParameter("list"));
        int deleteID = Integer.parseInt(request.getParameter("thing"));

        dataModel.deleteElementFromListCollect(deleteID, listID);
        if (model.isAutoSave())
            model.saveAll(true);

        request.setAttribute("list", dataModel.getElement(listID));

        // Invoke the JSP.
        // A JSP page is actually converted into a Java class, so behind the scenes everything is Java.
        response.sendRedirect("/itemListView.html?list=" + listID);
    }
}
