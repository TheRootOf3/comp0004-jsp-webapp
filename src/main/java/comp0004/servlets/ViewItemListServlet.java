package comp0004.servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/itemListView.html")
public class ViewItemListServlet extends AbstractServlet {

    public ViewItemListServlet() throws IOException {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        updateModel();

        int elementID = Integer.parseInt(request.getParameter("list"));
        request.setAttribute("list", dataModel.getElement(elementID));
        request.setAttribute("parent_list", dataModel.getElement(elementID).getParent());


        // Invoke the JSP.
        // A JSP page is actually converted into a Java class, so behind the scenes everything is Java.
        ServletContext context = getServletContext();
        RequestDispatcher dispatch = null;

//        Direct differently depending on what is the type of the element
        if (dataModel.getElement(elementID).getType().equals("list")) {
            dispatch = context.getRequestDispatcher("/listView.jsp");
        } else if (dataModel.getElement(elementID).getType().equals("item")) {
            dispatch = context.getRequestDispatcher("/itemListView.jsp");
        }
        if (dispatch != null)
            dispatch.forward(request, response);
    }
}
