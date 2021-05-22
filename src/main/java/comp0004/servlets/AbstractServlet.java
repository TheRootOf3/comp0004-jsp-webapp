package comp0004.servlets;

import comp0004.model.DataModel;
import comp0004.model.Model;
import comp0004.model.ModelFactory;

import javax.servlet.http.HttpServlet;
import java.io.IOException;

public abstract class AbstractServlet extends HttpServlet {
    protected Model model;
    protected final DataModel dataModel;

    protected AbstractServlet() throws IOException {
        this.model = ModelFactory.getModel();
        this.dataModel = model.getDataModel();
    }

    protected void reloadModel() throws IOException {
        this.model = ModelFactory.reloadModel();
    }

}
