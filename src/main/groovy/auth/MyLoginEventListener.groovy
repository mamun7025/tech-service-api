package auth

import org.springframework.context.ApplicationEvent
import org.springframework.context.ApplicationListener
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.logout.LogoutHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse


// links
// https://www.tothenew.com/blog/how-to-perform-event-on-successful-login-via-spring-security-in-grails/


class MyLoginEventListener  implements ApplicationListener, LogoutHandler {


    // working
    void onApplicationEvent(ApplicationEvent event) {


        if (event instanceof InteractiveAuthenticationSuccessEvent) {
            // your code here....
            println(" @InteractiveAuthenticationSuccessEvent......Fire ")
            event.authentication.with {

                String username = principal.hasProperty('username')?.getProperty(principal) ?: principal
                String xxx = "event=${event.class.simpleName} username=${username} " +
                        "remoteAddress=${details.remoteAddress} sessionId=${details.sessionId}"
                println(xxx)

                // set active online user
                User.withTransaction {
                    def user = User.findByUsername(username)
                    user.setActiveOnline(true)
                    user.save(flush: true, failOnError: true)
                }

            }


        }


    }




    void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        println("I am login out now......")
        authentication.with {
        }
    }




}
