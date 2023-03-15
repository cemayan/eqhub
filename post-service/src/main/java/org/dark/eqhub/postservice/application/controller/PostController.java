package org.dark.eqhub.postservice.application.controller;



import org.dark.eqhub.common.model.Response;
import org.dark.eqhub.postservice.application.constants.Constants;
import org.dark.eqhub.postservice.domain.model.Post;
import org.dark.eqhub.postservice.domain.port.input.PostUsecase;
import org.dark.eqhub.postservice.domain.service.EventsGrpcServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(Constants.API_VERSION + "/" + Constants.POST_CONTROLLER_PREFIX)
public class PostController {
    private final PostUsecase postUsecase;

    @Autowired
    private EventsGrpcServiceImpl eventsGrpcService;

    public PostController(PostUsecase postUsecase) {
        this.postUsecase = postUsecase;
    }



    @GetMapping("/test")
    public void sendEvent() {
         eventsGrpcService.sendEvent();
    }


    @GetMapping("/")
    public Mono<String[]> get() {
        SecurityContext context = SecurityContextHolder.getContext();



        return Mono.just(new String[]{context.getAuthentication().getName()});
    }

    @PostMapping(Constants.POST_CONTROLLER_POST_PREFIX)
    public Mono<ResponseEntity<Response>> CreatePost(@RequestBody Post post) {

        try {
            Mono<Post> response = postUsecase.CreatePost(post);
            return response.map(x -> ResponseEntity.ok().body(new Response(x)));

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
