package idat.edu.pe.bodegaGalan.controller.backoffice;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/backoffice/nosotros")
public class NosotrosController {
    @GetMapping("")
    public String frmMantNosotros(Model model) {
        return "backoffice/nosotros";
    }

    @GetMapping("/principal")
    public String mostrarMenuPrincipal(Model model) {
        return "frontoffice/menuprincipal";
    }
}
