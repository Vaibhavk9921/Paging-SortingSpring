package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
public class MyController {
	@Autowired
	StudentRepo stud;
	@GetMapping("/next")
	public List<Student> getNext(@RequestParam("num") int num) {
		List<Student> list = stud.findAll(PageRequest.of(num, 2)).getContent();
		return list;
	}

	@GetMapping("/next1")
	public List<Student> getNext1(HttpSession session) {
		Integer x = (Integer) session.getAttribute("page");
		if (x == null) {
			x = 0;
			session.setAttribute("page", x);
		} else {
			x++;
			session.setAttribute("page", x);
		}
		List<Student> list = stud.findAll(PageRequest.of(x, 2)).getContent();
		return list;
	}

	@GetMapping("previous")
	public List<Student> getPrevious1(HttpSession session) {
		Integer x = (Integer) session.getAttribute("page");
		if (x == null) {
			x = 0;
			session.setAttribute("page", x);
		} else {
			if (x != 0) {
				x--;
				session.setAttribute("page", x);
			}
		}
		List<Student> list = stud.findAll(PageRequest.of(x, 2)).getContent();
		return list;
	}

}
