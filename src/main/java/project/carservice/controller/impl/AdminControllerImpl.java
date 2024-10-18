package project.carservice.controller.impl;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import project.carservice.controller.AdminController;
import project.carservice.model.dto.OrderDTO;
import project.carservice.model.dto.editDTO.EditOrderDTO;
import project.carservice.model.dto.editDTO.EditUserDTO;
import project.carservice.model.entity.Order;
import project.carservice.service.OrderService;
import project.carservice.service.UserService;
import project.carservice.service.exceptions.UserNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Controller
public class AdminControllerImpl implements AdminController {

    private final OrderService orderService;
    private final UserService userService;


    public AdminControllerImpl(OrderService orderService, UserService userService) {
        this.orderService = orderService;
        this.userService = userService;
    }

    @Override
    public String getUnsignedOrders(Model model) {
        model.addAttribute("unsignedOrders", orderService.getUnassignedOrders());
        model.addAttribute("mechanics", userService.AllMechanics());
        model.addAttribute("editOrderDTO", EditOrderDTO.empty());

        return "unsigned-orders";
    }

    @Override
    public String assignOrder(EditOrderDTO editOrderDTO) {
        orderService.assignOrder(editOrderDTO);
        return "redirect:/admin/unsigned-orders";
    }

    @Override
    public String manageUsers(Model model) {
        model.addAttribute("users", userService.AllUsers());
        model.addAttribute("mechanics", userService.AllMechanics());
        model.addAttribute("admins", userService.AllAdmins());
        return "manage-users";
    }

    @Override
    public String manageUser(UUID id, String action, RedirectAttributes redirectAttributes) {
        switch (action) {
            case "edit":
                return "redirect:/admin/edit-user/" + id;
            case "makeMechanic":
                userService.makeMechanic(id);
                redirectAttributes.addFlashAttribute("message", "User role updated to mechanic successfully.");
                break;
            case "remove":
                userService.removeUser(id);
                redirectAttributes.addFlashAttribute("message", "User removed successfully.");
                break;
            case "makeAdmin":
                userService.makeAdmin(id);
                redirectAttributes.addFlashAttribute("message", "User role updated to admin successfully.");
                break;
            default:
                redirectAttributes.addFlashAttribute("message", "Invalid action.");
                break;
        }
        return "redirect:/admin/manage-users";
    }

    @Override
    public String edit(UUID id, Model model) {
        EditUserDTO editUserDTO = userService.getUserEditById(id);
        if (!model.containsAttribute("editUserDTO")) {
            model.addAttribute("editUserDTO", editUserDTO);
        }

        return "edit-user";
    }

    @Override
    public String editUser(EditUserDTO editUserDTO, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes
                    .addFlashAttribute("editUserDTO", editUserDTO)
                    .addFlashAttribute("org.springframework.validation.BindingResult.editUserDTO", result);

            return "redirect:/admin/edit-user/" + editUserDTO.getId();
        }
        userService.editUser(editUserDTO);
        return "redirect:/admin/manage-users";
    }


    @Override
    public String removeUser(UUID id) {
        userService.removeUser(id);
        return "redirect:/admin/manage-users";
    }

    @Override
    public String removeOrder(UUID id) {
        orderService.removeOrder(id);
        return "redirect:/admin/unsigned-orders";
    }

    @Override
    public String searchByDate(LocalDate date, Model model) {
        List<OrderDTO> orders = orderService.findByDate(date);
        model.addAttribute("orders", orders);
        return "search-by-date";
    }

    @Override
    public String searchByRegistration(String registration, Model model) {
        List<OrderDTO> orders = orderService.findByCarRegistrationNumber(registration);
        model.addAttribute("orders", orders);
        return "search-by-registration";
    }

    @Override
    public String removeMechanic(UUID id) {
        userService.removeMechanic(id);
        return "redirect:/admin/manage-users";
    }

    @Override
    public String removeAdmin(UUID id) {
        userService.removeAdmin(id);
        return "redirect:/admin/manage-users";
    }

    @Override
    public ModelAndView handleUserNotFoundException(UserNotFoundException exception) {

        ModelAndView modelAndView = new ModelAndView("user-not-found");
        modelAndView.addObject("message", exception.getMessage());
        return modelAndView;
    }
}
