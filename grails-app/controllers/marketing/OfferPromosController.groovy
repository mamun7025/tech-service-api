package marketing

import auth.User
import grails.converters.JSON
import grails.rest.RestfulController
import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class OfferPromosController extends RestfulController {

    OfferPromosService offerPromosService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    static responseFormats = ['json', 'xml']

    OfferPromosController(){
        super(OfferPromos)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
//        respond offerPromosService.list(params), model:[offerPromosCount: offerPromosService.count()]
        def result= OfferPromos.createCriteria().list(params) {
            if(params?.type){
                eq("type", params.type)
            }
            if(params?.promoCode){
                eq("promoCode", params.promoCode)
            }

            if(params?.promoUser){
                eq("promoUser", User.get(params?.promoUser))
            }
        }
        render result as JSON
    }

    def show(Long id) {
        respond offerPromosService.get(id)
    }

    def create() {
        respond new OfferPromos(params)
    }

    def save(OfferPromos offerPromos) {
        if (offerPromos == null) {
            notFound()
            return
        }

        try {
            if(offerPromos.promoUser){
                def promoOffer = OfferPromos.findByPromoCodeAndIsPromoCode(offerPromos.promoCode,true)
                def specificUserPromo = OfferPromos.findByPromoCodeAndPromoUser(offerPromos.promoCode,offerPromos.promoUser)
                if(!specificUserPromo){
                    if(promoOffer){
//                        if(promoOffer.isExpired == false){
                        if(!promoOffer){
                            offerPromos.title = promoOffer.title
                            offerPromos.type = promoOffer.type
                            offerPromos.discountAmount = promoOffer.discountAmount
                            offerPromos.expiryDate = promoOffer.expiryDate
                            offerPromos.conditions = promoOffer.conditions
                            offerPromos.leftPromo = promoOffer.leftPromo
                            offerPromos.isExpired = promoOffer.isExpired
                            if(offerPromosService.save(offerPromos)){
                                def result = [message:"Your Promo Offer has been Successfully Created!"]
                                render result as JSON
                            }
                        }else{
                            def result = [message:"This promo offer has expired!"]
                            render result as JSON
                        }
                    }


                } else{

                    def result = [message:"This user has already taken this promo!"]
                    render result as JSON
                }

            }else{
                offerPromos.setIsPromoCode(true)
                if(offerPromosService.save(offerPromos)){
                    def result = [message:"Offer has been Successfully Created!"]
                    render result as JSON
                }
            }

        } catch (ValidationException e) {
            respond offerPromos.errors, view:'create'
            return
        }

//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.created.message', args: [message(code: 'offerPromos.label', default: 'OfferPromos'), offerPromos.id])
//                redirect offerPromos
//            }
//            '*' { respond offerPromos, [status: CREATED] }
//        }
    }

    def edit(Long id) {
        respond offerPromosService.get(id)
    }

    def update(OfferPromos offerPromos) {
        if (offerPromos == null) {
            notFound()
            return
        }

        try {
            offerPromosService.save(offerPromos)
        } catch (ValidationException e) {
            respond offerPromos.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'offerPromos.label', default: 'OfferPromos'), offerPromos.id])
                redirect offerPromos
            }
            '*'{ respond offerPromos, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        offerPromosService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'offerPromos.label', default: 'OfferPromos'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'offerPromos.label', default: 'OfferPromos'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
