package org.dark.eqhub.postservice.application.controller;


import org.dark.eqhub.postservice.application.constants.Constants;
import org.dark.eqhub.postservice.domain.model.Post;
import org.dark.eqhub.postservice.domain.port.input.RedisUsecase;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.API_VERSION + "/" + Constants.CACHE_CONTROLLER_PREFIX)
public class CacheController {
    private final RedisUsecase redisUsecase;

    public CacheController(RedisUsecase redisUsecase) {
        this.redisUsecase = redisUsecase;
    }


    @PostMapping(Constants.CACHE_CONTROLLER_CACHE_PREFIX)
    public void putCache() {
         redisUsecase.Put("12312321312321312",new Post("test"));
    }



}
