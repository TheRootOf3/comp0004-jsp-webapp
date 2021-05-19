package comp0004.servlets;

import comp0004.model.DataFrame;
import comp0004.model.Model;
import comp0004.model.ModelFactory;
import comp0004.model.element.Element;
import comp0004.model.element.ElementList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

// The servlet invoked to display a list of patients.
// The url http://localhost:8080/patientList.html is mapped to calling doGet on the servlet object.
// The servlet object is created automatically, you just provide the class.
@WebServlet("/searchInLabels.html")
public class SearchInLabelsServlet extends HttpServlet
{

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    // Get the data from the model
    Model model = ModelFactory.getModel();
    DataFrame dataFrame = model.getDataFrame();
//    request.setAttribute("main_list", dataFrame.getElement(0));
    String keyword = request.getParameter("keyword_to_search");
//    request.setAttribute("main_list", dataFrame.getElement(0));

    request.setAttribute("traces", dataFrame.searchInElementLabels(keyword));

    for (ArrayList<ElementList> arraylist : dataFrame.searchInElementLabels(keyword)){
      for (ElementList element : arraylist){
        System.out.print(element.getLabel() + " <= ");
      }
      System.out.println();
    }

    // Invoke the JSP.
    // A JSP page is actually converted into a Java class, so behind the scenes everything is Java.
    ServletContext context = getServletContext();
    RequestDispatcher dispatch = context.getRequestDispatcher("/searchInLabels.jsp");
    dispatch.forward(request, response);
  }
}
