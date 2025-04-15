package org.lois.demoapp31.web;

import org.lois.demoapp31.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping("/products")
    public String products(Model model) {
        model.addAttribute("productsList", productRepository.findAll());
        return "products";
    }

//    @PostMapping("/delete")
//    public void delete(Long id) {
//        productRepository.deleteById(id);
//    }

    @GetMapping("/delete")
    public String delete(Long id) {
        productRepository.deleteById(id);
        return "redirect:/products";

    }
}
