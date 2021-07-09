package apps

import auth.User
import grails.gorm.transactions.Transactional
import groovy.json.JsonBuilder

import java.nio.charset.StandardCharsets

@Transactional
class NotificationMgrService {

    // r&d links
    // http://zetcode.com/java/getpostrequest/
    // https://dzone.com/articles/how-to-implement-get-and-post-request-through-simp
    // https://mkyong.com/java/how-to-send-http-request-getpost-in-java/
    // https://stackoverflow.com/questions/3324717/sending-http-post-request-in-java

    String apiUrl = "https://fcm.googleapis.com/fcm/send"
    String serverToken = "AAAA1XgEL_8:APA91bFTJGvdkO_E4y6uZKOVfkiU0U2r_YJKILrTUcP-gpU67T8kdZ4VP8U_m9nWyKwQUHd8XlTHOUsL00m13LeC-KJzmZTyKM3oR7sLstjWsQKdJFPSk80XldVKZ_Hul6VCNEoEMDPM"

    def prepareMessageParams(){
        Map mgsParams = [:]
        mgsParams['title'] = 'title12345'
        mgsParams['message'] = 'message12345'
        mgsParams['user'] = null
        mgsParams['userType'] = null
        mgsParams['userName'] = 'Al-Mamun'
        mgsParams['status'] = 1
        mgsParams['isPromotional'] = true
        mgsParams['orderCode'] = 'W1245'
        return mgsParams
    }

    def prepareMgsParams(Map params){
        Map mgsParams = [:]
        mgsParams['title'] = params.title ?: 'title12345'
        mgsParams['message'] = params.message ?: 'message12345'
        mgsParams['user'] = params.user ?: null
        mgsParams['userType'] = params.userType ?: ''
        mgsParams['userName'] = params.userName ?: ''
        mgsParams['status'] = params.status ?: 0
        mgsParams['isPromotional'] = params.isPromotional ?: false
        mgsParams['orderCode'] = params.orderCode ?: 'W1245'
        mgsParams['timestamp'] = params.timestamp ?: new Date()
        return mgsParams
    }


    def keepNotificationLog(Map params){

        String title = params.title
        String message = params.message
        User user = (params.user) ? User.get(params.user as String) : null
        String userType = params.userType
        String userName = params.userName
        Integer status = params.status
        Boolean isPromotional = params.isPromotional
        String orderCode = params.orderCode

        // validation
//        if(title == "" || title == null) return
//        if(message == "" || message == null) return

        def newNInst = new Notification()
        newNInst.setTitle(title)
        newNInst.setMessage(message)
        newNInst.setUser(user)
        newNInst.setUserType(userType)
        newNInst.setUserName(userName)
        newNInst.setStatus(status)
        newNInst.setIsPromotional(isPromotional)
        newNInst.setOrderCode(orderCode)
        newNInst.setTimestamp(new Date())

        newNInst.save(flush: true)
        return newNInst

    }


    def sendNotification(Map params, String toToken) {
        println(params)
        println(toToken)
        Map msgData = params

        URL url = new URL(this.apiUrl)
        URLConnection con = url.openConnection()
        HttpURLConnection http = (HttpURLConnection)con
        http.setRequestMethod("POST") // PUT is another valid option
        http.setDoOutput(true)
        // json
        http.setRequestProperty("Content-Type", "application/json")
        http.setRequestProperty("Authorization", "key="+this.serverToken)

        try {

            // prepare post data ---------------------------------------------------------------------------------------
//            def urlParameters = "name=Jack&occupation=programmer";
//            byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8)

            Map jsonObj = [
                    notification: [
                            title: params.title,
                            body: params.message
                    ],
                    priority: "high",
                    data: msgData,
                    to: toToken
            ]
            def jsonObjStr = new JsonBuilder( jsonObj ).toPrettyString()
            // println(jsonObjStr)

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


        this.keepNotificationLog(params)

    }





}
