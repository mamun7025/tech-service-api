package auth;

import grails.plugin.springsecurity.rest.RestTokenCreationEvent;
import org.springframework.context.ApplicationListener;

class MyLoginTokenEventListener implements ApplicationListener<RestTokenCreationEvent>{


    // working
    void onApplicationEvent(RestTokenCreationEvent event) {
        // The access token is a delegate of the event, so you have access to an instance of `grails.plugin.springsecurity.rest.token.AccessToken`

        String username = event.name
        String accessToken = event.accessToken
        println(username)
        println(accessToken)
        println(" @RestTokenCreationEvent .....")
        println(" I am creating token .........")

        // set active online user
        User.withTransaction {
            def user = User.findByUsername(username)
            user.setActiveOnline(true)
            user.save(flush: true)
        }

    }




}
