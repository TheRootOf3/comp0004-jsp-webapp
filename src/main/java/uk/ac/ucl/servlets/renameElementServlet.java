package uk.ac.ucl.servlets;

import uk.ac.ucl.model.DataFrame;
import uk.ac.ucl.model.Model;
import uk.ac.ucl.model.ModelFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// The servlet invoked to display a list of patients.
// The url http://localhost:8080/patientList.html is mapped to calling doGet on the servlet object.
// The servlet object is created automatically, you just provide the class.
@WebServlet("/renameElement.html")
public class renameElementServlet extends HttpServlet
{

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    // Get the data from the model
    Model model = ModelFactory.getModel();
    DataFrame dataFrame = model.getDataFrame();
    int listID = Integer.parseInt(request.getParameter("list"));
    String newLabel = request.getParameter("element_label");

    dataFrame.renameElement(newLabel, listID);

    // Invoke the JSP.
    // A JSP page is actually converted into a Java class, so behind the scenes everything is Java.
    if (listID == 0) {
      request.setAttribute("main_list", dataFrame.getElement(0));
      response.sendRedirect("/mainListView2.html");
    }
    else {
//      request.setAttribute("list", dataFrame.getElement(listID));
      response.sendRedirect("/itemListView.html?list="+listID);
    }

  }
}
