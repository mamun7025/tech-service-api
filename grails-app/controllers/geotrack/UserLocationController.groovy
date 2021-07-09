package geotrack

import auth.User
import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController
import groovy.json.JsonSlurper
import service.ServiceItems

class UserLocationController extends RestfulController {

    LocationTrackService locationTrackService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    static responseFormats = ['json', 'xml']

    UserLocationController(){
        super(UserLocation)
    }



    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def search() {

        def returnJSON = []
        String userId = params.user
        if(userId != ""){
            def user = User.get(userId)
            if(user){
                def userLocationInstList = UserLocation.createCriteria().list {
                    eq ("user", user)
                    maxResults(1)
                }
                if(userLocationInstList){
                    respond userLocationInstList.get(0)
                } else {
                    respond returnJSON
                }
            } else {
                respond returnJSON
            }

        } else {
            respond returnJSON
        }

    }


    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def searchNearby() {

        def returnJSON = []
        String userLatitude = params.latitude
        String userLongitude = params.longitude

        if(userLatitude != null && userLongitude != null){
            def userLocationInstList = UserLocation.createCriteria().list {
                maxResults(20)
            }
            if(userLocationInstList){

                userLocationInstList.each { it->
                    it = it as UserLocation
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
        } else {
            respond returnJSON
        }

    }


    def searchNearTechByItem(){
        def returnJSON = []

        // api parameters
        String itemCode = params.itemCode
        String custLatitude = params.custLatitude
        String custLongitude = params.custLongitude

        // parameter validation
        if(itemCode == null || itemCode == ""){
            returnJSON["ErrorMsg"] = "Parameter itemcode is mandatory"
            respond returnJSON
            return
        }
        if(custLatitude == null || custLatitude == ""){
            returnJSON["ErrorMsg"] = "Parameter custLatitude is mandatory"
            respond returnJSON
            return
        }
        if(custLongitude == null || custLongitude == ""){
            returnJSON["ErrorMsg"] = "Parameter custLongitude is mandatory"
            respond returnJSON
            return
        }


        // data proceed
        def serviceItemInst = ServiceItems.findByItemCode(itemCode)
        Double clientLat = Double.valueOf(custLatitude)
        Double clientLon = Double.valueOf(custLongitude)

        def technicianList = User.createCriteria().list(params) {
            eq("userType","Technician")
            eq("isApproved",true)
            ilike("expertiseArea", "%${serviceItemInst?.itemName}%")
        }

        if(technicianList){
            def userLocationList = UserLocation.createCriteria().list(params) {
                inList("user",technicianList)
            }
            if(userLocationList){
//                LinkedHashMap<User, Double> nPeoples = this.getNearestPeoples(userLocationList as ArrayList,clientLat,clientLon,50)
                LinkedHashMap<User, Double> nPeoples = locationTrackService.getNearestPeoples(userLocationList, clientLat,clientLon,50)

                for( Map.Entry<User, Double> entry : nPeoples.entrySet() ) {
                    User tech = entry.getKey()
                    def lineBean = [:]
                    lineBean['techId'] = tech.id
                    lineBean['techName'] = tech.displayName
                    lineBean['expertiseArea'] = tech.expertiseArea
                    lineBean['techRating'] = tech.rating
                    lineBean['techLatitude'] = UserLocation.findByUser(User.findById(Long.valueOf(tech.id)))?.latitude
                    lineBean['techLongitude'] = UserLocation.findByUser(User.findById(Long.valueOf(tech.id)))?.longitude
                    returnJSON.add(lineBean)


                }

                respond returnJSON

            }else{
                def lineLocBean = [:]
                lineLocBean['message'] = 'There is no Location for this Technician'
                returnJSON.add(lineLocBean)
                respond returnJSON

            }

        }else{

            def lineNullBean = [:]
            lineNullBean['message'] = 'There is no approved Technician'
            returnJSON.add(lineNullBean)
            respond returnJSON

        }

    }



    def userLocationUpdate(){
        String jsonObject = request.getJSON()
        def jsonObj = new JsonSlurper().parseText(jsonObject)
        def userInst = User.findById(Long.valueOf(jsonObj.userId.toString()))
        def userLocationInst = UserLocation.findByUser(userInst)
        if(userLocationInst){
            userLocationInst.latitude = Double.valueOf(jsonObj.latitude.toString())
            userLocationInst.longitude = Double.valueOf(jsonObj.longitude.toString())
            userLocationInst.lastUpdateDateTime = new Date()
            userLocationInst.lastUpdateUser = "System"
            userLocationInst.timestamp = new Date().getTime()
            if(userLocationInst.save(flush: true)){
                userInst.setActiveOnline(true)
                userInst.save(flush: true)
                def result = [message:"Your location has been updated successfully"]
                render result as JSON
            }

        }else{
            def newUserLocationInst = new UserLocation()
            newUserLocationInst.user = userInst
            newUserLocationInst.latitude = Double.valueOf(jsonObj.latitude.toString())
            newUserLocationInst.longitude = Double.valueOf(jsonObj.longitude.toString())
            newUserLocationInst.creationDateTime = new Date()
            newUserLocationInst.timestamp = new Date().getTime()
            newUserLocationInst.creationUser = "System"
            newUserLocationInst.lastUpdateDateTime = new Date()
            newUserLocationInst.lastUpdateUser = "System"
            if(newUserLocationInst.save(flush: true)){
                userInst.setActiveOnline(true)
                userInst.save(flush: true)
                def result = [message:"Your location has been updated successfully"]
                render result as JSON
            }
        }


    }


}
