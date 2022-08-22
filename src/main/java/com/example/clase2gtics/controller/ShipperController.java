package com.example.clase2gtics.controller;

import com.example.clase2gtics.entity.Shippers;
import com.example.clase2gtics.repository.ShipperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("/shipper")
public class ShipperController {

    @Autowired
    ShipperRepository shipperRepository;

    @GetMapping("/listar")
    public String listarTransportistas(Model model) {
        List<Shippers> shipperList = shipperRepository.findAll();
        model.addAttribute("shipperList", shipperList);
        return "shipper/lista";
    }

    @GetMapping("/crear")
    public String crearShipper() {
        return "shipper/new";
    }

    @PostMapping("/guardar")
    public String guardarShipper(Shippers shipper) {
        shipperRepository.save(shipper);
        System.out.println(shipper.getShipperId());
        return "redirect:/shipper/listar";
    }

    @GetMapping("/editar")
    public String editar(@RequestParam("id") int id, Model model) {
        Optional<Shippers> shipperOpt = shipperRepository.findById(id);
        if (shipperOpt.isPresent()) {
            Shippers shipper = shipperOpt.get();
            model.addAttribute("transportista", shipper);
            return "shipper/edit";
        } else {
            return "redirect:/shipper/listar";
        }
    }

    @GetMapping("/borrar")
    public String borrar(@RequestParam("id") String idStr) {

        try {
            int id = Integer.parseInt(idStr);
            Optional<Shippers> opt = shipperRepository.findById(id);
            if (opt.isPresent()) {
                shipperRepository.deleteById(opt.get().getShipperId());
            }
            System.out.println("borrado correctamente");
        } catch (NumberFormatException e) {
            System.out.println("error en id");
        }

        return "redirect:/shipper/listar";


    }

}
