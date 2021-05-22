package comp0004.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/deleteElement.html")
public class DeleteElementServlet extends AbstractServlet {

    public DeleteElementServlet() throws IOException {
        super();
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int listID = Integer.parseInt(request.getParameter("list"));
        int deleteID = Integer.parseInt(request.getParameter("item_to_delete"));

        dataModel.deleteElementFromListCollect(deleteID, listID);
        if (model.isAutoSave())
            model.saveAll(true);


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
