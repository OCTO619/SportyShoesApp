package com.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.model.Admin;
import com.model.Category;
import com.model.Product;
import com.model.Purchase;
import com.model.User;
import com.service.AdminService;
import com.service.ProductService;
import com.service.PurchaseService;
import com.service.UserService;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
    private AdminService adminService;

    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private PurchaseService purchaseService;
    
    @GetMapping("/login")
    public String showLoginForm() {
    	return "admin-login";
    }
    @PostMapping("/login")
    public String login(@RequestParam String username,@RequestParam String password,Model model) {
    	Admin admin =adminService.login(username,password);
    	if(admin!=null) {
    		model.addAttribute("admin",admin);
    		return "admin-dashboard";
    	}else {
    		 model.addAttribute("error", "Invalid credentials");
             return "admin-login";
    		}
    }
    @GetMapping("/admin/change-password")
    public String showChangePasswordForm() {
        return "change-password";
    }

    @PostMapping("/admin/change-password")
    public String changePassword(@RequestParam String currentPassword,@RequestParam String newPassword,Model model,HttpSession session) {
        String username = (String) session.getAttribute("adminUsername");

        if (username == null) {
            return "redirect:/admin/login";
        }

        Admin admin = adminService.findByUsername(username);

        if (admin == null || !admin.getPassword().equals(currentPassword)) {
            model.addAttribute("error", "Invalid current password.");
            return "change-password";
        }

        admin.setPassword(newPassword);
        adminService.updateAdmin(admin);

        model.addAttribute("success", "Password changed successfully!");
        return "change-password";
    }

    @GetMapping("/products")
    public String showProductPage(Model model) {
    	model.addAttribute("products", productService.getAllProducts());
        model.addAttribute("categories", productService.getAllCategories());
        return "manage-products";
    }
    @PostMapping("/products/add")
    public String addProduct(@RequestParam String name,@RequestParam float price,@RequestParam String categoryName, Model model) {
    	 Category category = productService.getAllCategories().stream().filter(c -> c.getCname().equalsIgnoreCase(categoryName)).findFirst().orElse(productService.addCategory(categoryName));
    	Product product = new Product();
        product.setProductname(name);
        product.setPrice(price);
        product.setCategory(category);

        productService.addProduct(product);
        return "redirect:/admin/products";
    }
    @GetMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable("id") int id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }
    @GetMapping("/users")
    public String showUsers(Model model,@RequestParam(required = false) String search) {
    	List<User> users = (search != null && !search.isEmpty())?userService.searchUsers(search): userService.getAllUsers();
    	model.addAttribute("users", users);
        model.addAttribute("search", search);
        return "manage-users";
    }
    @GetMapping("/reports")
    public String showReports(Model model,
                              @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                              @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                              @RequestParam(required = false) String category) {

        List<Purchase> purchases;

        if (startDate != null && endDate != null) {
            purchases = purchaseService.filterByDate(startDate, endDate);
        } else if (category != null && !category.isEmpty()) {
            purchases = purchaseService.filterByCategory(category);
        } else {
            purchases = purchaseService.getAllPurchases();
        }

        model.addAttribute("purchases", purchases);
        return "view-reports";
    }
}
