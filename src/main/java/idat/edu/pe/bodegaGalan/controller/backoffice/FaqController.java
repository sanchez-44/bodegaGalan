package idat.edu.pe.bodegaGalan.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/backoffice/faq")
public class FaqController {
    @GetMapping("")
    public String frmMantFaq(Model model) {
        return "backoffice/faq";
    }

    @GetMapping("/principal")
    public String mostrarMenuPrincipal(Model model) {
        return "frontoffice/menuprincipal";
    }
}
