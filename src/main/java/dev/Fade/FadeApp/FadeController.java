package dev.Fade.FadeApp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import dev.Fade.FadeApp.entities.Fade;
@RestController()
@RequestMapping("/fades")
public class FadeController {
    private static final Logger logger = 
        LoggerFactory.getLogger(FadeService.class);
    private final FadeService fadeService; 
    @GetMapping("/getFades") 
    public Iterable<Fade> getFades() {
        try {
       return fadeService.getDisocveryFades(); 
        }
        catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/createFade") 
    public void createFade(@RequestParam String content) {
        try {
            fadeService.createNewFade(content);
        } catch(Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/upvote") 
    public void vote(@RequestParam long fadeID) {
        try {
            fadeService.vote(fadeID); }
            catch(Exception e) {


            throw new ResponseStatusException(HttpStatus.NOT_MODIFIED);
            }
    }

    public FadeController(FadeService service) {
        this.fadeService = service;
 
    } 
}
