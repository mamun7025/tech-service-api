package apps

import auth.User
import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController
import grails.validation.ValidationException
import groovy.json.JsonBuilder
import java.nio.charset.StandardCharsets
import static org.springframework.http.HttpStatus.*


class NotificationController extends RestfulController {

    NotificationService notificationService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    static responseFormats = ['json', 'xml']

    NotificationController() {
        super(Notification)
    }


    def httpClientPost(){
        // r&d links
        // http://zetcode.com/java/getpostrequest/
        // https://dzone.com/articles/how-to-implement-get-and-post-request-through-simp
        // https://mkyong.com/java/how-to-send-http-request-getpost-in-java/
        // https://stackoverflow.com/questions/3324717/sending-http-post-request-in-java


        // https://pub.dev/packages/firebase_messaging
        String serverToken = "AAAA1XgEL_8:APA91bFTJGvdkO_E4y6uZKOVfkiU0U2r_YJKILrTUcP-gpU67T8kdZ4VP8U_m9nWyKwQUHd8XlTHOUsL00m13LeC-KJzmZTyKM3oR7sLstjWsQKdJFPSk80XldVKZ_Hul6VCNEoEMDPM"

        URL url = new URL("https://fcm.googleapis.com/fcm/send")
        URLConnection con = url.openConnection()
        HttpURLConnection http = (HttpURLConnection)con
        http.setRequestMethod("POST") // PUT is another valid option
        http.setDoOutput(true)
        // json
        http.setRequestProperty("Content-Type", "application/json")
        http.setRequestProperty("Authorization", "key="+serverToken)

        try {

            // prepare post data ---------------------------------------------------------------------------------------
//            def urlParameters = "name=Jack&occupation=programmer";
//            byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8)

//            byte[] out = "{\"username\":\"root\",\"password\":\"password\"}" .getBytes(StandardCharsets.UTF_8);
//            int length = out.length;

            Map jsonObj = [
                    notification: [
                            body: "this is a body...1234567",
                            title: "this is a title...1234567"
                    ],
                    priority: "high",
                    data: [
                            click_action: "FLUTTER_NOTIFICATION_CLICK",
                            id: "1",
                            status: "done",
                    ],
                    to: ""
            ]
            def jsonObjStr = new JsonBuilder( jsonObj ).toPrettyString()

            // write post data -----------------------------------------------------------------------------------------
            byte[] postData = jsonObjStr.getBytes(StandardCharsets.UTF_8)
            int length = postData.length

            http.setFixedLengthStreamingMode(length)
            http.connect()

            def wr = new DataOutputStream(http.getOutputStream())
            wr.write(postData)


            // Read response -------------------------------------------------------------------------------------------
            StringBuilder content
            def br = new BufferedReader(new InputStreamReader(http.getInputStream()))

            String line
            content = new StringBuilder()

            while ((line = br.readLine()) != null) {
                content.append(line)
                content.append(System.lineSeparator())
            }
            println(content.toString())


        } catch (e){
            println(e.toString())
        }


    }


    def prepareMessageParams(){
        Map mgsParams = [:]
        mgsParams['title'] = 'title12345'
        mgsParams['message'] = 'message12345'
        mgsParams['user'] = null
        mgsParams['userType'] = null
        mgsParams['userName'] = 'Al-Mamun'
        mgsParams['status'] = '1'
        mgsParams['isPromotional'] = true
        mgsParams['orderCode'] = 'W1245'
        return mgsParams
    }


    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
            def returnJSON = []

            def notificationInstList = Notification.createCriteria().list {
                if(params.user){
                    eq("user", User.get(params.user as String))
                }
                order('id', 'desc')
            }

        if(notificationInstList){

            notificationInstList.each{it->
                def notifyObj = [:]
                def notificationIns = it as Notification
                notifyObj['id'] = notificationIns?.id
                notifyObj['userId'] = notificationIns?.user?.id
                notifyObj['userType'] = notificationIns?.user?.userType
                notifyObj['status'] = notificationIns?.status
                notifyObj['orderCode'] = notificationIns?.orderCode
                notifyObj['organization'] = notificationIns?.organization?.name
                notifyObj['organizationId'] = notificationIns?.organization?.id
                notifyObj['isPromotional'] = notificationIns?.isPromotional
                notifyObj['message'] = notificationIns?.message
                notifyObj['timestamp'] = notificationIns?.timestamp
                returnJSON.add(notifyObj)

            }
            respond returnJSON

        }else{
            respond returnJSON

        }


    }


    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def show(Long id) {
        respond notificationService.get(id)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def create() {
        respond new Notification(params)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def save(Notification notification) {
        if (notification == null) {
            notFound()
            return
        }

        try {
            notificationService.save(notification)
        } catch (ValidationException e) {
            respond notification.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'notification.label', default: 'Notification'), notification.id])
                redirect notification
            }
            '*' { respond notification, [status: CREATED] }
        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def edit(Long id) {
        respond notificationService.get(id)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def update(Notification notification) {
        if (notification == null) {
            notFound()
            return
        }

        try {
            notificationService.save(notification)
        } catch (ValidationException e) {
            respond notification.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'notification.label', default: 'Notification'), notification.id])
                redirect notification
            }
            '*'{ respond notification, [status: OK] }
        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        notificationService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'notification.label', default: 'Notification'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'notification.label', default: 'Notification'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

}
