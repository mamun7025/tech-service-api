package auth

import groovy.util.logging.Slf4j;

import javax.servlet.http.HttpServletRequest;

import grails.plugin.springsecurity.rest.credentials.AbstractJsonPayloadCredentialsExtractor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
/**
 * Extracts credentials from a JSON request like: <code>{"username": "foo", "password": "bar"}</code>
 */
@Slf4j
class DefaultJsonPayloadCredentialsExtractor extends AbstractJsonPayloadCredentialsExtractor {

    String usernamePropertyName = "username"
    String passwordPropertyName = "password"

    UsernamePasswordAuthenticationToken extractCredentials(HttpServletRequest httpServletRequest) {
        def jsonBody = getJsonBody(httpServletRequest)

        if (jsonBody) {
            String username = jsonBody."${usernamePropertyName}"
            String password = jsonBody."${passwordPropertyName}"

            log.debug "Extracted credentials from JSON payload. Username: ${username}, password: ${password?.size()?'[PROTECTED]':'[MISSING]'}"

            new UsernamePasswordAuthenticationToken(username, password)
        } else {
            log.debug "No JSON body sent in the request"
            return null
        }
    }

}