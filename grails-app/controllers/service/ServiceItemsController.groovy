package service

import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController
import grails.validation.ValidationException

import static org.springframework.http.HttpStatus.*

class ServiceItemsController extends RestfulController {

    ServiceItemsService serviceItemsService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    static responseFormats = ['json', 'xml']

    ServiceItemsController(){
        super(ServiceItems)
    }


    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def index(Integer max) {
//        params.max = Math.min(max ?: 10, 100)
//        respond serviceItemsService.list(params), model:[serviceItemsCount: serviceItemsService.count()]

        // customize ----------------------------------------------------
        params.max = Math.min(max ?: 10, 100)
        params.order = params.order ?: "desc"
        // others filter params
        // Integer status = params.status as Integer
        // String clientUserId = params.clientUser
        def entityInstList = ServiceItems.createCriteria().list(params) {
            eq("active", true)
            order('sequence', 'asc')
            maxResults(50)
        }

        // prepare return JSON
        def returnJSON = []

        if(entityInstList){

            entityInstList.each { it->
                it = it as ServiceItems
                def props = it.properties

                def lineBean = [:]
                lineBean['id'] = it.id
                props.each { k, v ->
                    lineBean[k] = v
                }
                returnJSON.add(lineBean)
            }
            respond returnJSON

        } else {
            respond returnJSON
        }

    }


    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def show(Long id) {
        respond serviceItemsService.get(id)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def create() {
        respond new ServiceItems(params)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def save(ServiceItems serviceItems) {
        if (serviceItems == null) {
            notFound()
            return
        }

        try {
            serviceItemsService.save(serviceItems)
        } catch (ValidationException e) {
            respond serviceItems.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'serviceItems.label', default: 'ServiceItems'), serviceItems.id])
                redirect serviceItems
            }
            '*' { respond serviceItems, [status: CREATED] }
        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def edit(Long id) {
        respond serviceItemsService.get(id)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def update(ServiceItems serviceItems) {
        if (serviceItems == null) {
            notFound()
            return
        }

        try {
            serviceItemsService.save(serviceItems)
        } catch (ValidationException e) {
            respond serviceItems.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'serviceItems.label', default: 'ServiceItems'), serviceItems.id])
                redirect serviceItems
            }
            '*'{ respond serviceItems, [status: OK] }
        }
    }

    @Secured(['ROLE_ADMIN'])
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        serviceItemsService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'serviceItems.label', default: 'ServiceItems'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'serviceItems.label', default: 'ServiceItems'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
