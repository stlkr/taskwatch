package com.xtech.taskwatch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import com.xtech.taskwatch.model.Task;
import com.xtech.taskwatch.model.TaskRepository;
import com.xtech.taskwatch.model.User;
import com.xtech.taskwatch.model.UserRole;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController implements ErrorController {

    @Autowired
    private TaskRepository taskRepo;

    private SimpleDateFormat dateFmt = new SimpleDateFormat("MM/dd/yyyy");

    @GetMapping("/")
    public String enterRoot() {
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String getTaskList(Map<String, Object> model, @RequestParam(required = false) String query,
            @AuthenticationPrincipal User current_user) {
        Iterable<Task> tasks;

        if (query != null)
            tasks = taskRepo.findByTaskDescriptionContainsAndOwner(query, current_user);
        else
            tasks = taskRepo.findByOwner(current_user);

        if (current_user.getUserRoles().contains(UserRole.ADMIN))
            model.put("admin", "1");

        model.put("session_user", current_user.getUsername());
        model.put("tasks", tasks);
        model.put("query", query);
        return "index";
    }

    @GetMapping("/edit/new")
    public String getEdit(Map<String, Object> model, @AuthenticationPrincipal User current_user) {
        model.put("selDate", dateFmt.format(new Date()));
        if (current_user.getUserRoles().contains(UserRole.ADMIN))
            model.put("admin", "1");

        model.put("session_user", current_user.getUsername());
        return "edit";
    }

    @GetMapping("/edit/existing")
    public String getEdit(Map<String, Object> model, @RequestParam Long id, @AuthenticationPrincipal User current_user) {
        Optional<Task> task_opt = taskRepo.findById(id);
        if (task_opt.isPresent()) {
            Task task = task_opt.get();
            model.put("name", task.getTaskName());
            model.put("id", task.getId());
            model.put("descr", task.getTaskDescription());
            if (current_user.getUserRoles().contains(UserRole.ADMIN))
                model.put("admin", "1");

            String s = dateFmt.format(task.getTaskEndDate());

            model.put("selDate", s);

            model.put("session_user", current_user.getUsername());
            return "edit";
        } else {
            model.put("errMsg", "Указанная задача не найдена!");
            return "error";
        }
    }

    @PostMapping("/edit/update")
    public String updateTask(Map<String, Object> model, @RequestParam Long id, @RequestParam String name,
            @RequestParam String descr, @RequestParam Date selDate) {
        Optional<Task> task_opt = taskRepo.findById(id);
        if (!task_opt.isPresent()) {
            model.put("errMsg", "Задача не найдена!");
            return "error";
        }

        Task task = task_opt.get();
        task.setTaskName(name);
        task.setTaskDescription(descr);
        task.setTaskEndDate(selDate);

        taskRepo.save(task);

        return "redirect:/";
    }

    @PostMapping("/edit/add")
    public String addTask(@RequestParam String name, @RequestParam String descr, @RequestParam Date selDate) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        Task task = new Task(name, descr, selDate, user);
        taskRepo.save(task);
        return "redirect:/";
    }

    @GetMapping("/delete")
    public String deleteTask(@RequestParam Long id) {
        Optional<Task> task_opt = taskRepo.findById(id);
        if (task_opt.isPresent())
            taskRepo.delete(task_opt.get());

        return "redirect:/";
    }

    @GetMapping("/error")
    public String error(Map<String, Object> model, HttpServletRequest request) {
        Integer status = (Integer)request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null) {
            model.put("errCode", status.toString());
            switch(status)
            {
            case 404:
                model.put("errMsg", "Страница не найдена!");
                break;
            
            
            case 500:
                model.put("errMsg", "Ошибка сервера!");
                break;
            }
        }

        return "err";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
    
}
