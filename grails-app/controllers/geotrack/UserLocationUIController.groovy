package geotrack

import auth.User
import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NOT_FOUND
import static org.springframework.http.HttpStatus.NO_CONTENT
import static org.springframework.http.HttpStatus.OK

class UserLocationUIController {


    UserLocationService userLocationService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    UserLocationUIController(){
    }


    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
//        respond userLocationService.list(params), model:[userLocationCount: userLocationService.count()]
        def result = UserLocation.createCriteria().list(params) {}
        def totalCount = result.totalCount
        [entityInstanceList: result, entityInstanceTotal: totalCount, entityInstance:UserLocation.get(params.id)]
    }


    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def show(Long id) {
        respond userLocationService.get(id)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def create() {
        respond new UserLocation(params)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def save(UserLocation userLocation) {
        if (userLocation == null) {
            notFound()
            return
        }

        try {
            userLocationService.save(userLocation)
        } catch (ValidationException e) {
            respond userLocation.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'userLocation.label', default: 'UserLocation'), userLocation.id])
                redirect userLocation
            }
            '*' { respond userLocation, [status: CREATED] }
        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def edit(Long id) {
        respond userLocationService.get(id)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def update(UserLocation userLocation) {
        if (userLocation == null) {
            notFound()
            return
        }

        try {
            userLocationService.save(userLocation)
        } catch (ValidationException e) {
            respond userLocation.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'userLocation.label', default: 'UserLocation'), userLocation.id])
                redirect userLocation
            }
            '*'{ respond userLocation, [status: OK] }
        }
    }

    @Secured(['ROLE_ADMIN'])
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        userLocationService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'userLocation.label', default: 'UserLocation'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'userLocation.label', default: 'UserLocation'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

}
