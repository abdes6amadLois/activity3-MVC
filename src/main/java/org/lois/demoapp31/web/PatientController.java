package org.lois.demoapp31.web;

import org.lois.demoapp31.entities.Patient;
import org.lois.demoapp31.repository.PatientRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PatientController {
    private PatientRepository patientRepository;

    public PatientController(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @GetMapping("/patients")
    public String patients(Model model, @RequestParam(name = "page",defaultValue = "0") int page,@RequestParam(name = "size",defaultValue = "2") int size , @RequestParam(name = "keyword",defaultValue = "") String keyword ) {
        Page<Patient> patients = patientRepository.findByNomContains(keyword,PageRequest.of(page, size));
        model.addAttribute("patientList", patients);
        model.addAttribute("size", size);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        int count = patients.getTotalPages();
        if(count % 2 !=0)
            count = count + 1;
        model.addAttribute("count",  patients.getTotalPages()-1);
        return "patients";
    }

//    @GetMapping("/patients")
//    public String patients(Model model,@RequestParam("keyword") String keyword) {
//        model.addAttribute("patientList",patientRepository.findByNomContains(keyword));
//        model.addAttribute("size", 2);
//        model.addAttribute("page", 0);
//        int count = (int) patientRepository.count();
//        if(count % 2 !=0)
//            count = count + 1;
//        model.addAttribute("count", count/2);
//        return "patients";
//    }

    @GetMapping("/patients/delete")
    public String delete(Long id,int page,int size) {
        patientRepository.deleteById(id);
        return "redirect:/patients?page="+page+"&size="+size;
    }


}
