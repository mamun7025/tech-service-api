package sales

import auth.User
import grails.converters.JSON
import grails.rest.RestfulController
import grails.validation.ValidationException
import groovy.json.JsonSlurper

import static org.springframework.http.HttpStatus.*

class SalesOrderController extends RestfulController {

    SalesOrderService salesOrderService
    SalesOrderLineService salesOrderLineService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    static responseFormats = ['json', 'xml']

    SalesOrderController(){
        super(SalesOrder)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond salesOrderService.list(params), model:[salesOrderCount: salesOrderService.count()]
    }

    def show(Long id) {
        respond salesOrderService.get(id)
    }

    def create() {
        respond new SalesOrder(params)
    }

    def save() {
        String jsonObject = request.getJSON()
        def jsonObj = new JsonSlurper().parseText(jsonObject)
        def orderUserInst = User.findById(jsonObj.orderUser)
        def salesOderInst

        try {
            salesOderInst = new SalesOrder()
            salesOderInst.setOrderUser(orderUserInst)
            if(salesOrderService.save(salesOderInst)){
                def salesOderLineInst = new SalesOrderLine()
                salesOderLineInst.setSalesOrder(salesOderInst)
                if(salesOrderLineService.save(salesOderLineInst)){
                    def result = [message:"This Sales Order has been save Successfully"]
                    render result as JSON
                }
            }
        } catch (ValidationException e) {
            respond salesOderInst.errors, view:'create'
            return
        }

//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.created.message', args: [message(code: 'salesOrder.label', default: 'SalesOrder'), salesOrder.id])
//                redirect salesOrder
//            }
//            '*' { respond salesOrder, [status: CREATED] }
//        }
    }

    def edit(Long id) {
        respond salesOrderService.get(id)
    }

    def update(SalesOrder salesOrder) {
        if (salesOrder == null) {
            notFound()
            return
        }

        try {
            salesOrderService.save(salesOrder)
        } catch (ValidationException e) {
            respond salesOrder.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'salesOrder.label', default: 'SalesOrder'), salesOrder.id])
                redirect salesOrder
            }
            '*'{ respond salesOrder, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        salesOrderService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'salesOrder.label', default: 'SalesOrder'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'salesOrder.label', default: 'SalesOrder'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
