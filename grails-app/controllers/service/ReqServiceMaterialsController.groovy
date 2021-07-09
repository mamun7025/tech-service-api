package service

import grails.plugin.springsecurity.annotation.Secured
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*

class ReqServiceMaterialsController {

    ReqServiceMaterialsService reqServiceMaterialsService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    static responseFormats = ['json', 'xml']

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond reqServiceMaterialsService.list(params), model:[reqServiceMaterialsCount: reqServiceMaterialsService.count()]
    }

    def show(Long id) {
        respond reqServiceMaterialsService.get(id)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def create() {
        respond new ReqServiceCost(params)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def save(ReqServiceCost reqServiceMaterials) {
        if (reqServiceMaterials == null) {
            notFound()
            return
        }

        try {
            reqServiceMaterialsService.save(reqServiceMaterials)
        } catch (ValidationException e) {
            respond reqServiceMaterials.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'reqServiceMaterials.label', default: 'ReqServiceCost'), reqServiceMaterials.id])
                redirect reqServiceMaterials
            }
            '*' { respond reqServiceMaterials, [status: CREATED] }
        }
    }
    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def edit(Long id) {
        respond reqServiceMaterialsService.get(id)
    }
    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def update(ReqServiceCost reqServiceMaterials) {
        if (reqServiceMaterials == null) {
            notFound()
            return
        }

        try {
            reqServiceMaterialsService.save(reqServiceMaterials)
        } catch (ValidationException e) {
            respond reqServiceMaterials.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'reqServiceMaterials.label', default: 'ReqServiceCost'), reqServiceMaterials.id])
                redirect reqServiceMaterials
            }
            '*'{ respond reqServiceMaterials, [status: OK] }
        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        reqServiceMaterialsService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'reqServiceMaterials.label', default: 'ReqServiceCost'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'reqServiceMaterials.label', default: 'ReqServiceCost'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
