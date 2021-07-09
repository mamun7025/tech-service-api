package apps

import auth.User
import grails.converters.JSON
import grails.rest.RestfulController

class SetUnsetUserActivityController extends RestfulController {

    SetUnsetUserActivityController(){
        super(User)
    }
    def index() { }


    // its not needed now, its done by customize build in method
    // api path : /thikthak/api/setUserActiveOnline/201
    def setUserActiveOnline(){

    }

    // api path : /thikthak/api/unsetUserActiveOnline/201
    def unsetUserActiveOnline(){
        def userIntance = User.findById(Long.valueOf(params.userId.toString()))
        if(userIntance){
            userIntance.setActiveOnline(false)
            if(userIntance.save(flush: true)){
                def result = [message:"You are offline now"]
                render result as JSON

            }
        }else{
            def result = [message:"You are not validate User"]
            render result as JSON
        }

    }



}
