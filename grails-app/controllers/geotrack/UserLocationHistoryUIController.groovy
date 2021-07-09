package geotrack

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK

class UserLocationHistoryUIController {

    UserLocationHistoryService userLocationHistoryService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
//        respond userLocationHistoryService.list(params), model:[userLocationHistoryCount: userLocationHistoryService.count()]
        def result = UserLocationHistory.createCriteria().list(params) {}
        def totalCount = result.totalCount
        [entityInstanceList: result, entityInstanceTotal: totalCount, entityInstance:UserLocationHistory.get(params.id)]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def show(Long id) {
        respond userLocationHistoryService.get(id)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def create() {
        respond new UserLocationHistory(params)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def save(UserLocationHistory userLocationHistory) {
        if (userLocationHistory == null) {
            notFound()
            return
        }

        try {
            userLocationHistoryService.save(userLocationHistory)
        } catch (ValidationException e) {
            respond userLocationHistory.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userLocationHistory.label', default: 'UserLocationHistory'), userLocationHistory.id])
                redirect userLocationHistory
            }
            '*' { respond userLocationHistory, [status: CREATED] }
        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def edit(Long id) {
        respond userLocationHistoryService.get(id)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def update(UserLocationHistory userLocationHistory) {
        if (userLocationHistory == null) {
            notFound()
            return
        }

        try {
            userLocationHistoryService.save(userLocationHistory)
        } catch (ValidationException e) {
            respond userLocationHistory.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'userLocationHistory.label', default: 'UserLocationHistory'), userLocationHistory.id])
                redirect userLocationHistory
            }
            '*'{ respond userLocationHistory, [status: OK] }
        }
    }

    @Secured(['ROLE_ADMIN'])
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        userLocationHistoryService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'userLocationHistory.label', default: 'UserLocationHistory'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userLocationHistory.label', default: 'UserLocationHistory'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

}
