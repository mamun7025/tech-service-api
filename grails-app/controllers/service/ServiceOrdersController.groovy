package service

import apps.NotificationMgrService
import apps.SystemCounter
import auth.User
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController
import grails.validation.ValidationException
import groovy.json.JsonSlurper

import static org.springframework.http.HttpStatus.*

class ServiceOrdersController extends RestfulController {

    ServiceOrdersService serviceOrdersService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    static responseFormats = ['json', 'xml']

    ServiceOrdersController(){
        super(ServiceOrders)
    }


    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def indexOld(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond serviceOrdersService.list(params), model:[serviceOrdersCount: serviceOrdersService.count()]
    }

//    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
//    def index(Integer max) {
//
//        params.max = Math.min(max ?: 10, 100)
//        params.order = params.order ?: "desc"
//        // others filter params
//        Integer status = params.status as Integer
//        String clientUserId = params.clientUser
//        String technicianUserId = params.technicianUser
//
//        def entityInstList = ServiceOrders.createCriteria().list(params) {
//            if (params.status) {
//                eq("status", status)
//            }
//            if (params.clientUser) {
//                eq("clientUser", User.get(clientUserId))
//            }
//            if (params.technicianUser) {
//                eq("technicianUser", User.get(technicianUserId))
//            }
//            maxResults(20)
//        }
//
//
//        def returnJSON = []
//
//        if(entityInstList){
//
//            entityInstList.each { it->
//                it = it as ServiceOrders
//                def props = it.properties
//
//                def lineBean = [:]
//                lineBean['id'] = it.id
//                props.each { k, v ->
//                    lineBean[k] = v
//                }
//                returnJSON.add(lineBean)
//            }
//            respond returnJSON
//
//        } else {
//            respond returnJSON
//        }
//
//    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def index(Integer max) {

        params.max = Math.min(max ?: 10, 100)
        params.order = params.order ?: "desc"
        // others filter params
        Integer status = params.status as Integer
        String clientUserId = params.clientUser
        String technicianUserId = params.technicianUser

        def entityInstList = ServiceOrders.createCriteria().list(params) {
            if (params.status) {
                eq("status", status)
            }
            if (params.clientUser) {
                eq("clientUser", User.get(clientUserId))
            }
            if (params.technicianUser) {
                eq("technicianUser", User.get(technicianUserId))
            }
            order('id', 'desc')
//            maxResults(20)
        }

        def returnJSON = []

        if(entityInstList){

            entityInstList.each { it->
                def totalObj=[:]
                def serviceOrderInst = it as ServiceOrders
                totalObj['id'] = serviceOrderInst?.id
                totalObj['status'] = serviceOrderInst?.status
                totalObj['serviceItemName'] = serviceOrderInst?.serviceItemName
                totalObj['serviceItemCode'] = serviceOrderInst?.serviceItemCode
                totalObj['orderCode'] = serviceOrderInst?.orderCode
                totalObj['paymentAmount'] = serviceOrderInst?.paymentAmount
                totalObj['serviceDuration'] = serviceOrderInst?.serviceDuration
                totalObj['isAgreed'] = serviceOrderInst?.isAgreed
                totalObj['servicePartsRequired'] = serviceOrderInst?.servicePartsRequired
                totalObj['isRated'] = serviceOrderInst?.isRated
                totalObj['rating'] = serviceOrderInst?.rating
                totalObj['serviceStartTime'] = serviceOrderInst?.serviceStartTime
                totalObj['serviceEndTime'] = serviceOrderInst?.serviceEndTime
                totalObj['brandName'] = serviceOrderInst?.brandName
                totalObj['orderPlaceTime'] = serviceOrderInst?.orderPlaceTime
                totalObj['warrantyProduct'] = serviceOrderInst?.warrantyProduct
                totalObj['invoiceNumber'] = serviceOrderInst?.invoiceNumber
                totalObj['productSerial'] = serviceOrderInst?.productSerial
                totalObj['serviceDetailsDesc'] = serviceOrderInst?.serviceDetailsDesc
//                totalObj['orderPrice'] = serviceOrderInst.orderPrice
//                totalObj['sdAmount'] = serviceOrderInst.sdAmount
//                totalObj['bookTime'] = serviceOrderInst.bookTime
                totalObj['clientUserId'] = serviceOrderInst?.clientUser?.id
                totalObj['clientUserName'] = serviceOrderInst?.clientUserName
                totalObj['clientMobileNo'] = serviceOrderInst?.clientMobileNo
                totalObj['deliveryAddress'] = serviceOrderInst?.deliveryAddress
                totalObj['clientPhoneNumber'] = serviceOrderInst?.clientMobileNo
                totalObj['clientLatitude'] = serviceOrderInst?.clientLatitude
                totalObj['clientLongitude'] = serviceOrderInst?.clientLongitude
                totalObj['clientGeoLocation'] = serviceOrderInst?.clientGeoLocation
//                totalObj['cashRcvAmount'] = serviceOrderInst.cashRcvAmount
//                totalObj['serviceItems'] = serviceOrderInst.serviceItems
//                totalObj['lastUpdateUser'] = serviceOrderInst.lastUpdateUser
//                totalObj['issueImagePath'] = serviceOrderInst.issueImagePath
//                totalObj['totalRcvAmount'] = serviceOrderInst.totalRcvAmount
                totalObj['technicianUserId'] = serviceOrderInst?.technicianUser?.id
                totalObj['technicianUserName'] = serviceOrderInst?.technicianUserName
                totalObj['technicianPhoneNumber'] = serviceOrderInst?.technicianUser?.phoneNumber
                totalObj['techLatitude'] = serviceOrderInst?.techLatitude
                totalObj['techLongitude'] = serviceOrderInst?.techLongitude
                totalObj['technicianGeoLocation'] = serviceOrderInst?.technicianGeoLocation
                totalObj['techExpertiseArea'] = serviceOrderInst?.technicianUser?.expertiseArea
//                totalObj['servicePartsRequiredAcknlg'] = serviceOrderInst.servicePartsRequiredAcknlg
//                totalObj['cardRcvAmount'] = serviceOrderInst.cardRcvAmount
//                totalObj['creationDateTime'] = serviceOrderInst.creationDateTime
//                totalObj['vatAmount'] = serviceOrderInst.vatAmount
//                totalObj['creationUser'] = serviceOrderInst.creationUser
//                totalObj['billAmount'] = serviceOrderInst.billAmount
//                totalObj['lastUpdateDateTime'] = serviceOrderInst.lastUpdateDateTime
                def reqServiceCostList = ReqServiceCost.findAllByServiceOrders(serviceOrderInst)
                def costReqObj = []
                reqServiceCostList.each{ costInst->
                    def costObj = [:]
                    def reqServiceCostInst = costInst as ReqServiceCost
                    costObj['orderId'] = serviceOrderInst?.id
                    costObj['costCategory'] = reqServiceCostInst?.costCategory
                    costObj['costPrice'] = reqServiceCostInst?.costPrice
                    costObj['status'] = reqServiceCostInst?.status
                    costReqObj.add(costObj)

                }
                totalObj['cost'] = costReqObj
                returnJSON.add(totalObj)

            }
            respond returnJSON

        } else {
            respond returnJSON
        }

    }



    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def showOld(Long id) {
        respond serviceOrdersService.get(id)
    }
//    def show(Long id) {
//        respond serviceOrdersService.get(id)
//    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def show(Long id) {
        def entityInst = ServiceOrders.get(id)
        def props = entityInst.properties

        def objBean = [:]
        objBean['id'] = entityInst.id
        props.each { k, v ->
            objBean[k] = v
        }
        respond objBean
    }



    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def create() {
        respond new ServiceOrders(params)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def save(ServiceOrders serviceOrders) {
        if (serviceOrders == null) {
            notFound()
            return
        }

        try {

            // handle order counter
            def scInst = SystemCounter.findByCode("SR")
            if(!scInst){
                scInst = new SystemCounter()
                scInst.setCode("SR")
                scInst.setNextNumber(1000)
                scInst.save(flush: true)
                scInst = SystemCounter.findByCode("SR")
            }
            if(scInst){
                String orderCode = "W-"+scInst.nextNumber.toString()
                scInst.setNextNumber(Integer.valueOf(scInst.nextNumber)+1)
                scInst.save(flush: true)
                serviceOrders.setOrderCode(orderCode)
                serviceOrders.setIsAgreed(false)
                serviceOrders.setServicePartsRequired(false)
            }

            // save
            serviceOrdersService.save(serviceOrders)

            // send notification
             println("......1")
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    // Insert some method call here.
                    def nInst = new NotificationMgrService()

                    def mgsParamsClnt = nInst.prepareMgsParams(
                            [
                                    title: "Order Placed",
                                    message: "Your order "+ serviceOrders.orderCode +" has been placed successfully",
                                    orderCode: serviceOrders.orderCode,
                                    user: serviceOrders.clientUser.id,
                                    userName: serviceOrders.clientUser.displayName,
                                    status: serviceOrders.status,
                                    isPromotional: false,
                                    timestamp: new Date()
                            ]
                    )
                    // client
                    nInst.sendNotification( mgsParamsClnt, serviceOrders.clientUser.deviceToken.toString() )

                    if(serviceOrders.technicianUser){
                        def mgsParamsTech = nInst.prepareMgsParams(
                                [
                                        title: "Order Placed",
                                        message: "New order "+ serviceOrders.orderCode +" has been assigned to You",
                                        orderCode: serviceOrders.orderCode,
                                        user: serviceOrders.technicianUser.id,
                                        userName: serviceOrders.technicianUser.displayName,
                                        status: serviceOrders.status,
                                        isPromotional: false,
                                        timestamp: new Date()
                                ]
                        )
                        // tech
                        nInst.sendNotification( mgsParamsTech, serviceOrders.technicianUser.deviceToken.toString() )
                        println("......3")
                    }


                }
            })
            t.start()
             println("......2")

        } catch (ValidationException e) {
            respond serviceOrders.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'serviceOrders.label', default: 'ServiceOrders'), serviceOrders.id])
                redirect serviceOrders
            }
            '*' { respond serviceOrders, [status: CREATED] }
        }
    }

    def addOrderCost(){
        String jsonObject = request.getJSON()
        def jsonObj = new JsonSlurper().parseText(jsonObject)
        def serviceOrderInst = ServiceOrders.findById(Long.valueOf(jsonObj.id))
        serviceOrderInst.partsCost = jsonObj?.partsCost?:0
        serviceOrderInst.serviceCost = jsonObj?.serviceCost?:0
        serviceOrderInst.tadaCost = jsonObj?.tadaCost?:0
        serviceOrderInst.othersCost = jsonObj?.othersCost?:0
        serviceOrderInst.isAgreed = false
        serviceOrderInst.servicePartsRequired = true
        if(serviceOrderInst.save(flush:true)){
            Thread t = new Thread(new Runnable() {
                @Override
                void run() {
                    def nInst = new NotificationMgrService()
                    nInst.sendNotification(
                            nInst.prepareMgsParams(
                                    [
                                            title:  "Parts Cost Added",
                                            message: "Parts Cost has been added for your order ${serviceOrderInst.orderCode}",
                                            orderCode: serviceOrderInst.orderCode,
                                            user: serviceOrderInst.clientUser.id,
                                            userName: serviceOrderInst.clientUser.displayName,
                                            status: serviceOrderInst.status,
                                            isPromotional: false,
                                            timestamp: new Date()
                                    ]
                            ),
                            serviceOrderInst.clientUser.deviceToken.toString())
                }
            })
            t.start()
            println( serviceOrderInst.clientUser.deviceToken.toString())
            def result = [message:"Cost added successfully"]
            render result as JSON

        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def getDueAmount(Long techId){
        def returnJSON = []
        def grandReturnJSON = [:]
        def userInst = User.findById(techId)

        def techServiceList = ServiceOrders.createCriteria().list() {
            eq("technicianUser", userInst)
            eq("status", 2)
            eq("isDueAmount", true)
            order('id', 'desc')
//            maxResults(20)
        }

        def serviceItemList = []
        techServiceList.each{serviceItem->
            def serviceName = serviceItem as ServiceOrders
            if(!serviceItemList.contains(serviceName.serviceItemName)){
                serviceItemList.add(serviceName.serviceItemName)
            }

        }

        def grandTotal = 0
        def grandCostObj = [:]
        serviceItemList.each{ expert ->
            def costObj = [:]
            def totalAmt = 0
            def totalService = 0
            techServiceList.each{ it ->
                String expertName = expert
                def serviceInst = it as ServiceOrders
                if(serviceInst.serviceItemName.trim() == expertName.trim()){
                    def serviceAmt = serviceInst?.serviceCost?:0
                    def rateAmt = serviceAmt*30/100
                    totalAmt += rateAmt
                    grandTotal += rateAmt
                    totalService = totalService+1
                }
            }
            costObj['serviceName'] = expert
            costObj['totalAmount'] = totalAmt
            costObj['totalService'] = totalService
            returnJSON.add(costObj)

        }
//        grandCostObj['totalDue'] = grandTotal
        grandReturnJSON.put('totalDue',grandTotal)
        grandReturnJSON.put('services',returnJSON)

        respond grandReturnJSON
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def edit(Long id) {
        respond serviceOrdersService.get(id)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def update(ServiceOrders serviceOrders) {
        if (serviceOrders == null) {
            notFound()
            return
        }

        try {
            serviceOrdersService.save(serviceOrders)

            if(serviceOrders.isRated == true && serviceOrders.status == 2){
                def rating = serviceOrders?.rating
                def techInstance = serviceOrders?.technicianUser
                def noOfratingCount = techInstance?.numOfRatingUser+1
                def totalRating = (techInstance?.rating*techInstance?.numOfRatingUser+rating)/noOfratingCount
                techInstance.rating = totalRating
                techInstance.numOfRatingUser = noOfratingCount
                techInstance.save(flush: true)

                def seviceCost = serviceOrders?.serviceCost
                def serviceItemInst = ServiceItems.findByItemCode(serviceOrders?.serviceItemCode)
                def comCostPct = serviceItemInst?.comChargePct
                def comGettingAmt = seviceCost*comCostPct/100
                serviceOrders.setComChargePct(comCostPct)
                serviceOrders.setComChargeAmount(comGettingAmt)
                serviceOrders.setTechAmount(seviceCost-comGettingAmt)
                serviceOrders.save(flush: true)


            }else if(serviceOrders.isRated == false && serviceOrders.status == 2){
                def seviceCost = serviceOrders?.serviceCost
                def serviceItemInst = ServiceItems.findByItemCode(serviceOrders?.serviceItemCode)
                def comCostPct = serviceItemInst?.comChargePct
                def comGettingAmt = seviceCost*comCostPct/100
                serviceOrders.setComChargePct(comCostPct)
                serviceOrders.setComChargeAmount(comGettingAmt)
                serviceOrders.setTechAmount(seviceCost-comGettingAmt)
                serviceOrders.save(flush: true)

            }


            // Send message notification by thread Start ---------------------------------------------------------------
            def nMgsTittle = [
                    0: "Order Placed",
                    1: "Order Accepted",
                    2: "Order Completed",
                    3: "Order Rejected"
            ]
            def nMgsBodyClnt = [
                    0: "placed successfully",
                    1: "accepted",
                    2: "completed",
                    3: "rejected"
            ]
            def nMgsBodyTech = [
                    0: "placed successfully",
                    1: "successfully assigned",
                    2: "successfully completed",
                    3: "rejected"
            ]
            Thread t = new Thread(new Runnable() {
                @Override
                void run() {
                    def nInst = new NotificationMgrService()
//                    nInst.sendNotification(
//                            nInst.prepareMgsParams(
//                                    [
//                                            title:  nMgsTittle[serviceOrders.status],
//                                            message: "Your order "+ serviceOrders.orderCode +" has been " + nMgsBody[serviceOrders.status],
//                                            orderCode: serviceOrders.orderCode,
//                                            user: serviceOrders.technicianUser.id,
//                                            userName: serviceOrders.technicianUser.displayName,
//                                            status: serviceOrders.status,
//                                            isPromotional: false,
//                                            timestamp: new Date()
//                                    ]
//                            ),
//                            serviceOrders.clientUser.deviceToken.toString() )

                    def mgsParamsClnt = nInst.prepareMgsParams(
                            [
                                    title: "Order Placed",
                                    message: "Your order "+ serviceOrders.orderCode +" has been " + nMgsBodyClnt[serviceOrders.status],
                                    orderCode: serviceOrders.orderCode,
                                    user: serviceOrders.clientUser.id,
                                    userName: serviceOrders.clientUser.displayName,
                                    status: serviceOrders.status,
                                    isPromotional: false,
                                    timestamp: new Date()
                            ]
                    )

                    def mgsParamsTech = nInst.prepareMgsParams(
                            [
                                    title:  nMgsTittle[serviceOrders.status],
                                    message: "Order : "+ serviceOrders.orderCode +" has been " + nMgsBodyTech[serviceOrders.status],
                                    orderCode: serviceOrders.orderCode,
                                    user: serviceOrders.technicianUser.id,
                                    userName: serviceOrders.technicianUser.displayName,
                                    status: serviceOrders.status,
                                    isPromotional: false,
                                    timestamp: new Date()
                            ]
                    )


                    // client
                    nInst.sendNotification( mgsParamsClnt, serviceOrders.clientUser.deviceToken.toString() )
                    // tech
                    nInst.sendNotification( mgsParamsTech, serviceOrders.technicianUser.deviceToken.toString() )
                    println("......3")
                }
            })
            t.start()
            // Send message notification by thread End -----------------------------------------------------------------


        } catch (ValidationException e) {
            respond serviceOrders.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'serviceOrders.label', default: 'ServiceOrders'), serviceOrders.id])
                redirect serviceOrders
            }
            '*'{ respond serviceOrders, [status: OK] }
        }
    }

    @Secured(['ROLE_ADMIN'])
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        serviceOrdersService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'serviceOrders.label', default: 'ServiceOrders'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'serviceOrders.label', default: 'ServiceOrders'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }



}
