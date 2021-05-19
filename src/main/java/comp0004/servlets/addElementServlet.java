package comp0004.servlets;

import comp0004.model.DataFrame;
import comp0004.model.Model;
import comp0004.model.ModelFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/addElement.html")
public class addElementServlet extends HttpServlet
{

  public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
  {
    // Get the data from the model
    Model model = ModelFactory.getModel();
    DataFrame dataFrame = model.getDataFrame();
    int listID = Integer.parseInt(request.getParameter("list"));
    String label = request.getParameter("element_label");
    String type = request.getParameter("type");

    if (type.equals("list")){
      dataFrame.addNewListToList(label, listID, -1);
    }
    else if (type.equals("item")){
      dataFrame.addNewItemToList(label, listID, -1);
    }
    if (model.isAutoSave())
      dataFrame.saveAll(false);

    // Invoke the JSP.
    // A JSP page is actually converted into a Java class, so behind the scenes everything is Java.
    if (listID == 0) {
      request.setAttribute("main_list", dataFrame.getElement(0));
      response.sendRedirect("/mainListView2.html");
    }
    else {
      response.sendRedirect("/itemListView.html?list="+listID);
    }

  }
}
