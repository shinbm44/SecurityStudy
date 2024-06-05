package org.example.securitystudy.controller;

import lombok.RequiredArgsConstructor;
import org.example.securitystudy.dto.JoinDTO;
import org.example.securitystudy.service.JoinService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;


    @GetMapping("/join")
    public String join() {

        return "join";
    }

    @PostMapping("/joinProc")
    public String joinProc(JoinDTO joinDTO) {
        System.out.println(joinDTO.toString());

        joinService.JoinProcess(joinDTO);

        return "redirect:/login";
    }


}
