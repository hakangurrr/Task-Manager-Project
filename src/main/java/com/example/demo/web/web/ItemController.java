package com.example.demo.web.web;

import com.example.demo.model.Item;
import com.example.demo.model.Todo;
import com.example.demo.service.ItemService;
import com.example.demo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class ItemController {

    private TodoService todoService;
    private ItemService itemService;

    @Autowired
    public ItemController(TodoService todoService, ItemService itemService) {
        this.todoService = todoService;
        this.itemService = itemService;
    }

    @RequestMapping(value = "/todo/select/{todoId}/item/{itemId}", method = RequestMethod.GET)
    public ModelAndView itemSelect(@PathVariable("itemId") Long itemId, HttpSession httpSession) {
        ModelAndView mav = new ModelAndView();
        Item item = itemService.findItemById(itemId);
        mav.addObject("item", item);
        httpSession.setAttribute("todo", item.getTodo());
        mav.setViewName("selected-item");
        return mav;
    }

    @RequestMapping(value = "/todo/select/{todoId}/item/{itemId}", method = RequestMethod.POST)
    public String formSubmitItemSelect(HttpSession httpSession, @PathVariable("todoId") Long todoId, @PathVariable("itemId") Long itemId, @ModelAttribute("item") @Valid Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            return "redirect:/todo/select/" + todoId + "/item/" + itemId;
        }
        Todo todo = (Todo) httpSession.getAttribute("todo");
        item.setTodo(todo);
        item.setId(itemId);
        itemService.update(item);

        redirectAttributes.addFlashAttribute("updatedItem", true);
        return "redirect:/todo/select/" + todoId;
    }

    @RequestMapping(value = "/todo/select/{todoId}/item/delete/{itemId}", method = RequestMethod.GET)
    public String todoDelete(@PathVariable("todoId") Long todoId,@PathVariable("itemId") Long itemId) {
        itemService.deleteItem(itemId);
        return "redirect:/todo/select/" + todoId;
}

    @RequestMapping(value = "/todo/select/{todoId}/item/add", method = RequestMethod.GET)
    public ModelAndView loadFormAddItem() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("add-item");
        mav.addObject("newItem", new Item());
        return mav;
    }

    @RequestMapping(value = "/todo/select/{todoId}/item/add", method = RequestMethod.POST)
    public String formSubmitAddItem(@PathVariable("todoId") Long todoId, @ModelAttribute("newItem") @Valid Item item, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "add-item";
        }

        Todo todo = todoService.findTodoById(todoId);
        item.setTodo(todo);
        itemService.createItem(item);

        return "redirect:/todo/select/" + todoId;
    }

}
