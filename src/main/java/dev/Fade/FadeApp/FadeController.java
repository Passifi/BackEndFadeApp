package dev.Fade.FadeApp;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.Fade.FadeApp.entities.Fade;
@RestController()
@RequestMapping("/fades")
public class FadeController {
    private final FadeRepository repo; 
    @GetMapping() 
    public Iterable<Fade> getFades() {
        Pageable pageable = PageRequest.of(0,20);
        return repo.findAll(pageable).getContent();
    }
    @GetMapping("/getLatestFade")
    public Iterable<Fade> getMostRecentFade() {
        return repo.findTop20ByOrderByLastUpvoteDesc();
    }
    public FadeController(FadeRepository repo) {
        this.repo = repo;
    } 
}
