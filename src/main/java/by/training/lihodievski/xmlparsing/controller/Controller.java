package by.training.lihodievski.xmlparsing.controller;

import by.training.lihodievski.xmlparsing.factory.ParserFactory;
import by.training.lihodievski.xmlparsing.parser.AbstractFlowerParser;
import by.training.lihodievski.xmlparsing.validation.ValidatorXML;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/main")
@MultipartConfig
public class Controller extends HttpServlet {

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
        try {
            AbstractFlowerParser parser = new ParserFactory ().createFlowerParser (typeParser);
            Part part = req.getPart ("file");
            boolean status = ValidatorXML.validate (part.getInputStream ());
            req.setAttribute ("status",status);
            InputStream streamXml = part.getInputStream ();
            parser.buildSetFlowers (streamXml);
            req.setAttribute ("flowers", parser.getFlowers ());
            req.getRequestDispatcher ("WEB-INF/view/result.jsp").forward (req, resp);
        } catch (ParserConfigurationException | SAXException e) {
            req.getRequestDispatcher ("WEB-INF/view/error.jsp").forward (req,resp);
        }


    }
}
