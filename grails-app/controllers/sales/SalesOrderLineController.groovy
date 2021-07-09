package sales

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class SalesOrderLineController {

    SalesOrderLineService salesOrderLineService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond salesOrderLineService.list(params), model:[salesOrderLineCount: salesOrderLineService.count()]
    }

    def show(Long id) {
        respond salesOrderLineService.get(id)
    }

    def create() {
        respond new SalesOrderLine(params)
    }

    def save(SalesOrderLine salesOrderLine) {
        if (salesOrderLine == null) {
            notFound()
            return
        }

        try {
            salesOrderLineService.save(salesOrderLine)
        } catch (ValidationException e) {
            respond salesOrderLine.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'salesOrderLine.label', default: 'SalesOrderLine'), salesOrderLine.id])
                redirect salesOrderLine
            }
            '*' { respond salesOrderLine, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond salesOrderLineService.get(id)
    }

    def update(SalesOrderLine salesOrderLine) {
        if (salesOrderLine == null) {
            notFound()
            return
        }

        try {
            salesOrderLineService.save(salesOrderLine)
        } catch (ValidationException e) {
            respond salesOrderLine.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'salesOrderLine.label', default: 'SalesOrderLine'), salesOrderLine.id])
                redirect salesOrderLine
            }
            '*'{ respond salesOrderLine, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        salesOrderLineService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'salesOrderLine.label', default: 'SalesOrderLine'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesOrderLine.label', default: 'SalesOrderLine'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
