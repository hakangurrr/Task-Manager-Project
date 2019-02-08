package com.example.demo.web.web;

import com.example.demo.model.Item;
import com.example.demo.model.Todo;
import com.example.demo.model.User;
import com.example.demo.service.TodoService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.security.Principal;

@Controller
public class TodoController {

    private UserService userService;
    private TodoService todoService;

    @Autowired
    public TodoController(UserService userService, TodoService todoService) {
        this.userService = userService;
        this.todoService = todoService;
    }

    @RequestMapping(value = { "/","/todo/list"}, method = RequestMethod.GET)
    public ModelAndView todoList(Principal principal) {
        ModelAndView mav = new ModelAndView();
        User user = userService.findUserByUserName(principal.getName());
        mav.addObject("todo", new Todo());
        mav.addObject("todoList", user.getTodoList());
        mav.setViewName("add-delete-list-todo");
        return mav;
    }

    @RequestMapping(value = {"/", "/todo/list"}, method = RequestMethod.POST)
    public String handleFormTodoList(Principal principal, @ModelAttribute("todo") @Valid Todo todo, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", true);
            return "redirect:/todo/list";
        }

        User user = userService.findUserByUserName(principal.getName());
        todo.setUser(user);
        todoService.createTodo(todo);
        return "redirect:/todo/list";
    }

    @RequestMapping(value = "/todo/select/{id}", method = RequestMethod.GET)
    public ModelAndView todoSelect(@PathVariable Long id) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("selected-todo");
        Todo todo = todoService.findTodoById(id);
        mav.addObject("todo", todo);
        mav.addObject("newItem", new Item());
        mav.addObject("itemList", todo.getItems());
        return mav;
    }

    @RequestMapping(value = "/todo/select/{id}", method = RequestMethod.POST)
    public String formSubmitTodoSelect(Principal principal, @PathVariable Long id, @ModelAttribute("todo") @Valid Todo todo, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("message", true);
            return "redirect:/todo/select/" + id;
        }

        User user = userService.findUserByUserName(principal.getName());
        todo.setUser(user);
        todoService.update(todo);
        redirectAttributes.addFlashAttribute("updated", true);
        return "redirect:/todo/select/" + id;
    }

    @RequestMapping(value = "/todo/delete/{id}", method = RequestMethod.GET)
    public String todoDelete(@PathVariable Long id) {
        todoService.deleteTodo(id);
        return "redirect:/todo/list";
    }

}