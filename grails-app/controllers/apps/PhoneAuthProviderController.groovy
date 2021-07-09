package apps

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class PhoneAuthProviderController {

    PhoneAuthProviderService phoneAuthProviderService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond phoneAuthProviderService.list(params), model:[phoneAuthProviderCount: phoneAuthProviderService.count()]
    }

    def show(Long id) {
        respond phoneAuthProviderService.get(id)
    }

    def create() {
        respond new PhoneAuthProvider(params)
    }

    def save(PhoneAuthProvider phoneAuthProvider) {
        if (phoneAuthProvider == null) {
            notFound()
            return
        }

        try {
            phoneAuthProviderService.save(phoneAuthProvider)
        } catch (ValidationException e) {
            respond phoneAuthProvider.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'phoneAuthProvider.label', default: 'PhoneAuthProvider'), phoneAuthProvider.id])
                redirect phoneAuthProvider
            }
            '*' { respond phoneAuthProvider, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond phoneAuthProviderService.get(id)
    }

    def update(PhoneAuthProvider phoneAuthProvider) {
        if (phoneAuthProvider == null) {
            notFound()
            return
        }

        try {
            phoneAuthProviderService.save(phoneAuthProvider)
        } catch (ValidationException e) {
            respond phoneAuthProvider.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'phoneAuthProvider.label', default: 'PhoneAuthProvider'), phoneAuthProvider.id])
                redirect phoneAuthProvider
            }
            '*'{ respond phoneAuthProvider, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        phoneAuthProviderService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'phoneAuthProvider.label', default: 'PhoneAuthProvider'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'phoneAuthProvider.label', default: 'PhoneAuthProvider'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
