package uk.ac.ucl.servlets;

import uk.ac.ucl.model.DataFrame;
import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;
import uk.ac.ucl.model.element.Element;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

// The servlet invoked to display a list of patients.
// The url http://localhost:8080/patientList.html is mapped to calling doGet on the servlet object.
// The servlet object is created automatically, you just provide the class.
@WebServlet("/mainListView2.html")
public class ViewMainListServlet extends HttpServlet
{

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    // Get the data from the model
    Model model = ModelFactory.getModel();
    DataFrame dataFrame = model.getDataFrame();
    request.setAttribute("main_list", dataFrame.getElement(0));

    for (Map.Entry<Integer, Element> entry : dataFrame.getElementHashMap().entrySet()){
      System.out.println(entry.getKey() + " " + entry.getValue().getLabel());
    }

    // Invoke the JSP.
    // A JSP page is actually converted into a Java class, so behind the scenes everything is Java.
    ServletContext context = getServletContext();
    RequestDispatcher dispatch = context.getRequestDispatcher("/mainListView2.jsp");
    dispatch.forward(request, response);
  }
}
