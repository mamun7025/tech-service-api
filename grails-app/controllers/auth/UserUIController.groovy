package auth

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK

class UserUIController {

    UserService userService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    UserUIController() {
    }


    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
//        respond userService.list(params), model:[userCount: userService.count()]
        def result = User.createCriteria().list(params) {}
        def totalCount = result.totalCount
        [entityInstanceList: result, entityInstanceTotal: totalCount, entityInstance:User.get(params.id)]
    }


    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def show(Long id) {
        respond userService.get(id)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def create() {
        respond new User(params)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def save(User user) {
        if (user == null) {
            notFound()
            return
        }

        try {
            userService.save(user)
        } catch (ValidationException e) {
            respond user.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*' { respond user, [status: CREATED] }
        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def edit(Long id) {
        respond userService.get(id)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def update(User user) {
        if (user == null) {
            notFound()
            return
        }

        try {
            userService.save(user)
        } catch (ValidationException e) {
            respond user.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*'{ respond user, [status: OK] }
        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        userService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
