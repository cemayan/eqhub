package org.dark.eqhub.authservice.application.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.clientRegistrationId;
import static org.springframework.security.oauth2.client.web.reactive.function.client.ServletOAuth2AuthorizedClientExchangeFilterFunction.oauth2AuthorizedClient;

/**
 * @author Joe Grandja
 * @since 0.0.1
 */
@Controller
public class AuthorizationController {
    private final WebClient webClient;
    private final String postServiceUrl;

    public AuthorizationController(WebClient webClient,
                                   @Value("${post-service.base-uri}") String messagesBaseUri) {
        this.webClient = webClient;
        this.postServiceUrl = messagesBaseUri;
    }

    @GetMapping(value = "/authorize", params = "grant_type=authorization_code")
    public String authorizationCodeGrant(Model model,
                                         @RegisteredOAuth2AuthorizedClient("login-client-authorization-code")
                                         OAuth2AuthorizedClient authorizedClient) {

        String[] messages = this.webClient
                .get()
                .uri(this.postServiceUrl)
                .attributes(oauth2AuthorizedClient(authorizedClient))
                .retrieve()
                .bodyToMono(String[].class)
                .block();
        model.addAttribute("posts", messages);

        return "index";
    }

    // '/authorized' is the registered 'redirect_uri' for authorization_code
    @GetMapping(value = "/authorized", params = OAuth2ParameterNames.ERROR)
    public String authorizationFailed(Model model, HttpServletRequest request) {
        String errorCode = request.getParameter(OAuth2ParameterNames.ERROR);
        if (StringUtils.hasText(errorCode)) {
            model.addAttribute("error",
                    new OAuth2Error(
                            errorCode,
                            request.getParameter(OAuth2ParameterNames.ERROR_DESCRIPTION),
                            request.getParameter(OAuth2ParameterNames.ERROR_URI))
            );
        }

        return "index";
    }

    @GetMapping(value = "/authorize", params = "grant_type=client_credentials")
    public String clientCredentialsGrant(Model model) {

        String[] messages = this.webClient
                .get()
                .uri(this.postServiceUrl)
                .attributes(clientRegistrationId("login-client-client-credentials"))
                .retrieve()
                .bodyToMono(String[].class)
                .block();
        model.addAttribute("posts", messages);

        return "index";
    }
}