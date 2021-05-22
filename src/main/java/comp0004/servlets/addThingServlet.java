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

@WebServlet("/addThing.html")
public class addThingServlet extends HttpServlet {

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        // Get the data from the model
        Model model = ModelFactory.getModel();
        DataModel dataModel = model.getDataModel();
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
