package comp0004.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addThing.html")
public class AddThingServlet extends AbstractServlet {

    public AddThingServlet() throws IOException {
        super();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int itemID = Integer.parseInt(request.getParameter("list"));
        String content = request.getParameter("thing_content");
        String type = request.getParameter("type");

        dataModel.addNewThingToItem(type, content, itemID, -1);
        if (model.isAutoSave())
            model.saveAll(true);

        request.setAttribute("list", dataModel.getElement(itemID));


        // Invoke the JSP.
        // A JSP page is actually converted into a Java class, so behind the scenes everything is Java.
        response.sendRedirect("/itemListView.html?list=" + itemID);
    }
}
