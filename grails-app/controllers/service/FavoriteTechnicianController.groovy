package service

import auth.User
import grails.converters.JSON
import grails.rest.RestfulController
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class FavoriteTechnicianController extends RestfulController {

    FavoriteTechnicianService favoriteTechnicianService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    static responseFormats = ['json', 'xml']
    FavoriteTechnicianController(){
        super(FavoriteTechnician)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
//        respond favoriteTechnicianService.list(params), model:[favoriteTechnicianCount: favoriteTechnicianService.count()]
        def result= FavoriteTechnician.createCriteria().list(params) {
            if(params?.clientUserId){
                eq("clientUserId", User.get(params.clientUserId))
            }
            if(params?.techUserId){
                eq("techUserId", User.get(params.techUserId))
            }

            eq("favorite", true)

        }

        def returnJSON = []

        result.each{thisLine->
            thisLine = thisLine as FavoriteTechnician
            def lineBean = [:]
            lineBean['id'] = thisLine.id
            lineBean['techId'] = thisLine.techUserId.id
            lineBean['name'] = thisLine.techUserId.displayName
            lineBean['favorite'] = thisLine.favorite
            lineBean['expertiseArea'] = thisLine.techUserId.expertiseArea
            returnJSON.add(lineBean)
        }
        respond returnJSON
    }

    def getFavouriteTechnician(Integer max){
        def result= FavoriteTechnician.createCriteria().list(params) {
            if(params?.clientUserId){
                eq("clientUserId", User.get(params.clientUserId))
            }
            if(params?.techUserId){
                eq("techUserId", User.get(params.techUserId))
            }

            eq("favorite", true)

        }

        def returnJSON = []

        result.each{thisLine->
            thisLine = thisLine as FavoriteTechnician
            def lineBean = [:]
            lineBean['id'] = thisLine.id
            lineBean['name'] = thisLine.techUserId.displayName
            lineBean['favorite'] = thisLine.favorite
            lineBean['expertiseArea'] = thisLine.techUserId.expertiseArea
            returnJSON.add(lineBean)
        }
        respond returnJSON

    }

    def show(Long id) {
        respond favoriteTechnicianService.get(id)
    }

    def create() {
        respond new FavoriteTechnician(params)
    }

    def save(FavoriteTechnician favoriteTechnician) {
        if (favoriteTechnician == null) {
            notFound()
            return
        }

        def techInst = User.findById(favoriteTechnician.techUserIdId)
        def favTechInst = FavoriteTechnician.findByClientUserIdAndTechUserId(favoriteTechnician.clientUserId,techInst)

        try {
            if(favTechInst){
                def result = [message:"This Technician already Tag with this user!"]
                render result as JSON

            }else{
                if( favoriteTechnicianService.save(favoriteTechnician)){
                    def result = [message:"Your favorite Technician has been taged Successfully"]
                    render result as JSON

                }
            }

        } catch (ValidationException e) {
            respond favoriteTechnician.errors, view:'create'
            return
        }

//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.created.message', args: [message(code: 'favoriteTechnician.label', default: 'FavoriteTechnician'), favoriteTechnician.id])
//                redirect favoriteTechnician
//            }
//            '*' { respond favoriteTechnician, [status: CREATED] }
//        }
    }

    def edit(Long id) {
        respond favoriteTechnicianService.get(id)
    }

    def update(FavoriteTechnician favoriteTechnician) {
        if (favoriteTechnician == null) {
            notFound()
            return
        }

        try {
            favoriteTechnicianService.save(favoriteTechnician)
        } catch (ValidationException e) {
            respond favoriteTechnician.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'favoriteTechnician.label', default: 'FavoriteTechnician'), favoriteTechnician.id])
                redirect favoriteTechnician
            }
            '*'{ respond favoriteTechnician, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        favoriteTechnicianService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'favoriteTechnician.label', default: 'FavoriteTechnician'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'favoriteTechnician.label', default: 'FavoriteTechnician'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
