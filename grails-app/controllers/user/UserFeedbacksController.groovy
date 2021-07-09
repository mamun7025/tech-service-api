package user

import grails.rest.RestfulController
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class UserFeedbacksController extends RestfulController {

    UserFeedbacksService userFeedbacksService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    static responseFormats = ['json', 'xml']

    UserFeedbacksController(){
        super(UserFeedbacks)
    }



    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond userFeedbacksService.list(params), model:[userFeedbacksCount: userFeedbacksService.count()]
    }

    def show(Long id) {
        respond userFeedbacksService.get(id)
    }

    def create() {
        respond new UserFeedbacks(params)
    }

    def save(UserFeedbacks userFeedbacks) {
        if (userFeedbacks == null) {
            notFound()
            return
        }

        try {
            userFeedbacksService.save(userFeedbacks)
        } catch (ValidationException e) {
            respond userFeedbacks.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userFeedbacks.label', default: 'UserFeedbacks'), userFeedbacks.id])
                redirect userFeedbacks
            }
            '*' { respond userFeedbacks, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond userFeedbacksService.get(id)
    }

    def update(UserFeedbacks userFeedbacks) {
        if (userFeedbacks == null) {
            notFound()
            return
        }

        try {
            userFeedbacksService.save(userFeedbacks)
        } catch (ValidationException e) {
            respond userFeedbacks.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'userFeedbacks.label', default: 'UserFeedbacks'), userFeedbacks.id])
                redirect userFeedbacks
            }
            '*'{ respond userFeedbacks, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        userFeedbacksService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'userFeedbacks.label', default: 'UserFeedbacks'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userFeedbacks.label', default: 'UserFeedbacks'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
