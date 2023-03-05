package org.dark.eqhub.postservice.application.controller;



import model.Response;
import org.dark.eqhub.postservice.application.constants.Constants;
import org.dark.eqhub.postservice.domain.model.Post;
import org.dark.eqhub.postservice.domain.port.input.PostUsecase;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(Constants.API_VERSION + "/" + Constants.POST_CONTROLLER_PREFIX)
public class PostController {
    private final PostUsecase postUsecase;

    public PostController(PostUsecase postUsecase) {
        this.postUsecase = postUsecase;
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
