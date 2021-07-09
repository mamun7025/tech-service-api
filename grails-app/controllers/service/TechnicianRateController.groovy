package service

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class TechnicianRateController {

    TechnicianRateService technicianRateService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond technicianRateService.list(params), model:[technicianRateCount: technicianRateService.count()]
    }

    def show(Long id) {
        respond technicianRateService.get(id)
    }

    def create() {
        respond new TechnicianRate(params)
    }

    def save(TechnicianRate technicianRate) {
        if (technicianRate == null) {
            notFound()
            return
        }

        try {
            technicianRateService.save(technicianRate)
        } catch (ValidationException e) {
            respond technicianRate.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'technicianRate.label', default: 'TechnicianRate'), technicianRate.id])
                redirect technicianRate
            }
            '*' { respond technicianRate, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond technicianRateService.get(id)
    }

    def update(TechnicianRate technicianRate) {
        if (technicianRate == null) {
            notFound()
            return
        }

        try {
            technicianRateService.save(technicianRate)
        } catch (ValidationException e) {
            respond technicianRate.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'technicianRate.label', default: 'TechnicianRate'), technicianRate.id])
                redirect technicianRate
            }
            '*'{ respond technicianRate, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        technicianRateService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'technicianRate.label', default: 'TechnicianRate'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'technicianRate.label', default: 'TechnicianRate'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
