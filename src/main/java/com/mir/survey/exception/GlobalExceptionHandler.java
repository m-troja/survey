package com.mir.survey.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler  {


	 @RequestMapping("/error")
	    public String handleError(HttpServletRequest request, Model model) {
	        Integer statusCode = (Integer) request.getAttribute("jakarta.servlet.error.status_code");
	        if (statusCode == null) {
	            statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
	        }

	        if (statusCode != null && statusCode == 404) {
	            model.addAttribute("errorCode", 404);
	            model.addAttribute("errorMessage", "Strona nie została znaleziona");
                log.debug("return 404");
	            return "error";
	        }

	        model.addAttribute("errorCode", statusCode);
	        model.addAttribute("errorMessage", "Wystąpił błąd");
            log.debug("return " + statusCode);
	        return "error";
	    }
}
