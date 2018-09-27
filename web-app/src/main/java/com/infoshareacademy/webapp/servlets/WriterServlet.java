package com.infoshareacademy.webapp.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

@WebServlet ("/write")
public class WriterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String text = "Ten tekst ma znaleźć się w pliku.";
        String textNext = "Oto następny tekst";


        File file = new File(getServletContext().getRealPath("/WEB-INF/resources/file.txt"));
        FileWriter fileWriter = new FileWriter(file);
        fileWriter.write(text);
        fileWriter.close();

    }
}
