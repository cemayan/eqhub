package org.dark.eqhub.postservice.writeapi.application.constants;

import org.springframework.stereotype.Component;

@Component
public class Constants {

    public static final String API_VERSION = "v1";
    public static final String POST_CONTROLLER_PREFIX = "post";

    public static final String POST_CONTROLLER_GETBYID_PREFIX = "/{postId}";
    public static final String POST_CONTROLLER_POST_PREFIX = "/";


    public static final String CACHE_CONTROLLER_PREFIX = "cache";
    public static final String CACHE_CONTROLLER_CACHE_PREFIX = "/";


    public static final String CACHE_POSTS_KEY_NAME = "posts";
    public static final String CACHE_FEEDS_KEY_NAME = "feeds";




}
