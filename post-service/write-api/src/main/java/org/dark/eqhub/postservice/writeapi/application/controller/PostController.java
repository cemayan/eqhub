package org.dark.eqhub.postservice.writeapi.application.controller;


import org.dark.eqhub.common.model.Response;
import org.dark.eqhub.postservice.writeapi.application.constants.Constants;
import org.dark.eqhub.postservice.writeapi.domain.model.Post;
import org.dark.eqhub.postservice.writeapi.domain.port.input.PostUsecase;
import org.dark.eqhub.postservice.writeapi.domain.service.EventsGrpcServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(PostController.class);
    private final PostUsecase postUsecase;

    @Autowired
    private EventsGrpcServiceImpl eventsGrpcService;

    public PostController(PostUsecase postUsecase) {
        this.postUsecase = postUsecase;
    }

    @GetMapping("/test")
    public void sendEvent() {
        eventsGrpcService.sendFriendListEvent().subscribe();
    }


    @GetMapping("/")
    public Mono<String[]> get() {
        SecurityContext context = SecurityContextHolder.getContext();
        return Mono.just(new String[]{context.getAuthentication().getName()});
    }

    @GetMapping(Constants.POST_CONTROLLER_GETBYID_PREFIX)
    public Mono<ResponseEntity<Response>> getPost(@PathVariable String postId) {
        return postUsecase.getPost(postId).map(x -> ResponseEntity.ok().body(new Response(x)));
    }


    @PostMapping(Constants.POST_CONTROLLER_POST_PREFIX)
    public Mono<ResponseEntity<Response>> createPost(@RequestBody Post post) {

        try {
            Mono<Post> response = postUsecase.createPost(post);
            return response.map(x -> ResponseEntity.ok().body(new Response(x)));

        } catch (Exception e) {
            logger.error("Bad request", e);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

}
