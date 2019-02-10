package by.training.lihodievski.xmlparsing.controller;

import by.training.lihodievski.xmlparsing.exception.ParserException;
import by.training.lihodievski.xmlparsing.factory.ParserFactory;
import by.training.lihodievski.xmlparsing.parser.AbstractFlowerParser;
import by.training.lihodievski.xmlparsing.validation.ValidatorXML;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/main")
@MultipartConfig
public class Controller extends HttpServlet {

    private static final Logger LOGGER = LogManager.getLogger (Controller.class);
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest (req,resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String typeParser = req.getParameter ("radios");
        Part part = req.getPart ("file");
        boolean status = ValidatorXML.validate (part.getInputStream ());
        req.setAttribute ("status", status);
        if(status) {
            try {
                AbstractFlowerParser parser = ParserFactory.getInstance ().createFlowerParser (typeParser);
                InputStream streamXml = part.getInputStream ();
                parser.buildSetFlowers (streamXml);
                req.setAttribute ("flowers", parser.getFlowers ());
                req.getRequestDispatcher ("WEB-INF/view/result.jsp").forward (req, resp);
            } catch (ParserException e) {
                LOGGER.error ("error in controller", e);
            }
        }else{
            req.getRequestDispatcher ("WEB-INF/view/result.jsp").forward (req, resp);
        }
    }
}
