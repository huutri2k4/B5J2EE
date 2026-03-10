package com.example.demo.controller; // Nhớ check package

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // Cái này để gõ localhost:8080 là vào luôn
    public String home() {
        // Dòng này nghĩa là: "Đừng mở index nữa, chuyển hướng sang danh sách sách đi"
        return "redirect:/books";
    }

    @GetMapping("/home") // Giữ cái cũ cũng cho chuyển hướng luôn
    public String index() {
        return "redirect:/books";
    }
}