package dev.Fade.FadeApp;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import dev.Fade.FadeApp.entities.Fade;
@RestController()
@RequestMapping("/fades")
public class FadeController {
    private final FadeService fadeService; 
    @GetMapping("/getFades") 
    public Iterable<Fade> getFades() {
       return fadeService.getDisocveryFades(); 
    }
    @PutMapping("/createFade") 
    public void createFade(@RequestParam String content) {
            fadeService.createNewFade(content);
    }

    public FadeController(FadeService service) {
        this.fadeService = service;
    } 
}
