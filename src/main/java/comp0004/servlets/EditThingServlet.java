package comp0004.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/editThing.html")
public class EditThingServlet extends AbstractServlet {

    public EditThingServlet() throws IOException {
        super();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        updateModel();


        int itemID = Integer.parseInt(request.getParameter("list"));
        int thingID = Integer.parseInt(request.getParameter("thing"));
        String newContent = request.getParameter("thing_content");

        dataModel.editThing(newContent, thingID);
        if (model.isAutoSave())
            model.saveAll(true);

        request.setAttribute("list", dataModel.getElement(itemID));


        // Invoke the JSP.
        // A JSP page is actually converted into a Java class, so behind the scenes everything is Java.
        response.sendRedirect("/itemListView.html?list=" + itemID);
    }
}
