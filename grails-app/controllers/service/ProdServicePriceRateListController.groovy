package service

import auth.User
import geotrack.UserLocation
import grails.rest.RestfulController
import grails.validation.ValidationException
import groovy.json.JsonSlurper

import static org.springframework.http.HttpStatus.*

class ProdServicePriceRateListController extends RestfulController {

    ProdServicePriceRateListService prodServicePriceRateListService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    static responseFormats = ['json', 'xml']

    ProdServicePriceRateListController(){
        super(ProdServicePriceRateList)
    }


    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond prodServicePriceRateListService.list(params), model:[prodServicePriceRateListCount: prodServicePriceRateListService.count()]
    }

    def ratingWiseServicePrice(){

        def serviceIteminst = ServiceItems.findByItemCode(params.itemCode)

//        def technicianList = User.findAllByUserType("Technician")
        def technicianList = User.createCriteria().list(params) {
            eq("userType","Technician")
            eq("isApproved",true)
            ilike("expertiseArea", "%${serviceIteminst.itemName}%")
        }


        def serviceTechnicianList = UserLocation.createCriteria().list(params) {
            inList("user",technicianList)
        }

//        def servicePriceList = ProdServicePriceRateList.createCriteria().list(params) {
//            eq("serviceProduct", serviceIteminst.itemName)
//        }

//        def returnJSON = []
//        serviceTechnicianList.each{tech->
//            servicePriceList.each{price->
//                def lineBean = [:]
//                if(tech.user.rating>=price.startRange && tech.user.rating<=price.endRange){
//                    lineBean['techId'] = tech.user.id
//                    lineBean['techName'] = tech.user.displayName
//                    lineBean['expertiseArea'] = tech.user.expertiseArea
//                    lineBean['techRating'] = tech.user.rating
//                    lineBean['techLatitude'] = tech.latitude
//                    lineBean['techLongitude'] = tech.longitude
//                    lineBean['servicePrice'] = price.servicePrice
//                    returnJSON.add(lineBean)
//                }
//
//            }
//
//        }

        def returnJSON = []
        serviceTechnicianList.each{tech->
                def lineBean = [:]
                    lineBean['techId'] = tech.user.id
                    lineBean['techName'] = tech.user.displayName
                    lineBean['expertiseArea'] = tech.user.expertiseArea
                    lineBean['techRating'] = tech.user.rating
                    lineBean['techLatitude'] = tech.latitude
                    lineBean['techLongitude'] = tech.longitude
                    returnJSON.add(lineBean)


        }

        respond returnJSON
    }

    def show(Long id) {
        respond prodServicePriceRateListService.get(id)
    }

    def create() {
        respond new ProdServicePriceRateList(params)
    }

    def save(ProdServicePriceRateList prodServicePriceRateList) {
        if (prodServicePriceRateList == null) {
            notFound()
            return
        }

        try {
            prodServicePriceRateListService.save(prodServicePriceRateList)
        } catch (ValidationException e) {
            respond prodServicePriceRateList.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'prodServicePriceRateList.label', default: 'ProdServicePriceRateList'), prodServicePriceRateList.id])
                redirect prodServicePriceRateList
            }
            '*' { respond prodServicePriceRateList, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond prodServicePriceRateListService.get(id)
    }

    def update(ProdServicePriceRateList prodServicePriceRateList) {
        if (prodServicePriceRateList == null) {
            notFound()
            return
        }

        try {
            prodServicePriceRateListService.save(prodServicePriceRateList)
        } catch (ValidationException e) {
            respond prodServicePriceRateList.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'prodServicePriceRateList.label', default: 'ProdServicePriceRateList'), prodServicePriceRateList.id])
                redirect prodServicePriceRateList
            }
            '*'{ respond prodServicePriceRateList, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        prodServicePriceRateListService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'prodServicePriceRateList.label', default: 'ProdServicePriceRateList'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'prodServicePriceRateList.label', default: 'ProdServicePriceRateList'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
