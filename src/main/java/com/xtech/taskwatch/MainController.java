package com.xtech.taskwatch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Optional;

import com.xtech.taskwatch.model.Task;
import com.xtech.taskwatch.model.TaskRepository;
import com.xtech.taskwatch.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {

    @Autowired
    private TaskRepository taskRepo;

    private SimpleDateFormat dateFmt = new SimpleDateFormat("MM/dd/yyyy");

    @GetMapping("/")
    public String enterRoot() {
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String getTaskList(
        Map<String, Object> model, 
        @RequestParam(required = false) String query,
        @AuthenticationPrincipal User user
    ) {
        Iterable<Task> tasks;

        String originalQuery = query;
        
        if (query == null)
            query = "%";
        else {
            query = String.format("%%%s%%", query);
        }

        tasks = taskRepo.findByTaskDescriptionLikeAndOwner(query, user);
        
        model.put("tasks", tasks);
        model.put("query", originalQuery);
        return "index";
    }

    @GetMapping("/edit/new")
    public String getEdit(Map<String, Object> model) {
        model.put("selDate", dateFmt.format(new Date()));
        return "edit";
    }

    @GetMapping("/edit/existing")
    public String getEdit(Map<String, Object> model, @RequestParam Long id) {
        Optional<Task> task_opt = taskRepo.findById(id);
        if (task_opt.isPresent()) {
            Task task = task_opt.get();
            model.put("name", task.getTaskName());
            model.put("id", task.getId());
            model.put("descr", task.getTaskDescription());

            String s = dateFmt.format(task.getTaskEndDate());

            model.put("selDate", s);
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
    public String addTask(
        @RequestParam String name, 
        @RequestParam String descr, 
        @RequestParam Date selDate) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
            
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
    public String GetError() {
        return "redirect:hello";
    }
}
