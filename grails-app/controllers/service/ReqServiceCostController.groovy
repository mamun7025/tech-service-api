package service

import grails.rest.RestfulController
import grails.validation.ValidationException
import groovy.json.JsonSlurper

import java.text.SimpleDateFormat

import static org.springframework.http.HttpStatus.*

class ReqServiceCostController extends RestfulController {

    ReqServiceCostService reqServiceCostService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    static responseFormats = ['json', 'xml']

    ReqServiceCostController(){
        super(ReqServiceCost)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond reqServiceCostService.list(params), model:[reqServiceCostCount: reqServiceCostService.count()]
    }

    def show(Long id) {
        respond reqServiceCostService.get(id)
    }

    def create() {
        respond new ReqServiceCost(params)
    }

  def totalServiceCost(){
      List msg = []
      List orderId = []
      String jsonObject = request.getJSON()
      def jsonObj = new JsonSlurper().parseText(jsonObject)
      Double totalCost = 0.0
      Double partsCost = 0.0
      for(int i =0;i<jsonObj.size;i++){
          def costObj = [:]
          def reqServiceCostInst = new ReqServiceCost()
          Map thisLineMap = jsonObj[i]
          def ServiceOrdersinst = ServiceOrders.findById(Long.valueOf(thisLineMap['orderId']))
          if(!orderId.contains(ServiceOrdersinst?.id)){
              orderId.add(ServiceOrdersinst?.id)
          }
          reqServiceCostInst.serviceOrders = ServiceOrdersinst
          reqServiceCostInst.costCategory = thisLineMap['costCategory']
          Double costPrice = thisLineMap['costPrice'] ? thisLineMap['costPrice'] as Double : 0.00
          if(thisLineMap['costCategory'] == 'Parts Cost'){
              partsCost += costPrice
          }
          totalCost += costPrice
          reqServiceCostInst.costPrice = costPrice
          reqServiceCostInst.status = thisLineMap['status'] ? thisLineMap['status'] as Integer : 0
          if(reqServiceCostInst.save(flush: true)){
              costObj['id'] = reqServiceCostInst?.id
              costObj['orderId'] = reqServiceCostInst?.serviceOrders?.id
              costObj['costCategory'] = reqServiceCostInst?.costCategory
              costObj['costPrice'] = reqServiceCostInst?.costPrice
              costObj['status'] = reqServiceCostInst?.status
              msg.add(costObj)
          }
      }
      orderId.each {id->

         def  orderInst = ServiceOrders.findById(id)
          def serviceCostInst = ReqServiceCost.findByServiceOrdersAndCostCategory(orderInst,'Service Cost')
          if(serviceCostInst){
              orderInst.serviceCost = serviceCostInst?.costPrice
          }
          def tadaCostInst = ReqServiceCost.findByServiceOrdersAndCostCategory(orderInst,'Ta/Da Cost')
          if(tadaCostInst){
              orderInst.tadaCost = tadaCostInst?.costPrice
          }
          def othersCostInst = ReqServiceCost.findByServiceOrdersAndCostCategory(orderInst,'Others Cost')
          if(othersCostInst){
              orderInst.othersCost = othersCostInst?.costPrice
          }
//          def reqServiceinst = ReqServiceCost.findByServiceOrdersAndCostCategory(orderInst,'Parts Cost')
          orderInst.paymentAmount = totalCost
          orderInst.partsCost = partsCost
          orderInst.save(flush: true)
      }


      respond msg

  }

    def save(ReqServiceCost reqServiceCost) {
        if (reqServiceCost == null) {
            notFound()
            return
        }

        try {
            reqServiceCostService.save(reqServiceCost)
        } catch (ValidationException e) {
            respond reqServiceCost.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'reqServiceCost.label', default: 'ReqServiceCost'), reqServiceCost.id])
                redirect reqServiceCost
            }
            '*' { respond reqServiceCost, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond reqServiceCostService.get(id)
    }

    def update(ReqServiceCost reqServiceCost) {
        if (reqServiceCost == null) {
            notFound()
            return
        }

        try {
            reqServiceCostService.save(reqServiceCost)
        } catch (ValidationException e) {
            respond reqServiceCost.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'reqServiceCost.label', default: 'ReqServiceCost'), reqServiceCost.id])
                redirect reqServiceCost
            }
            '*'{ respond reqServiceCost, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        reqServiceCostService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'reqServiceCost.label', default: 'ReqServiceCost'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'reqServiceCost.label', default: 'ReqServiceCost'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
