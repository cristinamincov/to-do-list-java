package controller;

import config.TemplateEngineUtil;
import model.Task;
import model.ToDoList;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import service.ApplicationService;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "toDoListServlet", urlPatterns = {"/"}, loadOnStartup = 1)
public class HomeServlet extends HttpServlet {
    HttpSession httpSession;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        TemplateEngine templateEngine = TemplateEngineUtil.getTemplateEngine(request.getServletContext());
        WebContext webContext = new WebContext(request, response, request.getServletContext());

        ApplicationService applicationService = ApplicationService.getInstance();
        Integer id = Integer.valueOf(request.getPathInfo().substring(1));
        ToDoList toDoList = applicationService.toDoListDao.get(id);
        List<Task> task = applicationService.taskDao.getAllByToDoList(toDoList.getId());


        httpSession = request.getSession(true);

        webContext.setVariable("toDoList", toDoList);
        webContext.setVariable("tasks", task);
        templateEngine.process("index.html", webContext, response.getWriter());
    }
}
