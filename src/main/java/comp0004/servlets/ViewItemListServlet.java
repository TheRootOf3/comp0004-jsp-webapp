package comp0004.servlets;

import comp0004.model.DataFrame;
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

@WebServlet("/itemListView.html")
public class ViewItemListServlet extends HttpServlet
{

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    // Get the data from the model
    Model model = ModelFactory.getModel();
    DataFrame dataFrame = model.getDataFrame();
    int elementID = Integer.parseInt(request.getParameter("list"));
    request.setAttribute("list", dataFrame.getElement(elementID));
    request.setAttribute("parent_list", dataFrame.getElement(elementID).getParent());


    // Invoke the JSP.
    // A JSP page is actually converted into a Java class, so behind the scenes everything is Java.
    ServletContext context = getServletContext();
    RequestDispatcher dispatch = null;

    if (dataFrame.getElement(elementID).getType().equals("list")){
      dispatch = context.getRequestDispatcher("/listView.jsp");
    }
    else if (dataFrame.getElement(elementID).getType().equals("item")){
      dispatch = context.getRequestDispatcher("/itemListView.jsp");
    }
    if (dispatch != null)
      dispatch.forward(request, response);
  }
}