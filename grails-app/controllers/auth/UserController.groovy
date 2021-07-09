package auth

import grails.converters.JSON
import grails.plugin.springsecurity.annotation.Secured
import grails.rest.RestfulController
import grails.validation.ValidationException
import service.ServiceOrders

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

import static org.springframework.http.HttpStatus.*

class UserController extends RestfulController{

    UserService userService
    def utilityService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    static responseFormats = ['json', 'xml']

    UserController() {
        super(User)
    }


    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond userService.list(params), model:[userCount: userService.count()]
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def search(String property, String value, Integer qf) {
        if(qf == 1){
            respond User.createCriteria().list {
                eq property, "$value"
            }
        } else {
            respond User.createCriteria().list {
                like property, "%$value%"
            }
        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def show(Long id) {
        respond userService.get(id)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def create() {
        respond new User(params)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def save(User user) {
        if (user == null) {
            notFound()
            return
        }

        try {
            if(user.userType == 'Technician'){
                user.setRegistrationDate(new Date())
            }
            user.setCreationDateTime(new Date())

            userService.save(user)
        } catch (ValidationException e) {
            respond user.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*' { respond user, [status: CREATED] }
        }
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def edit(Long id) {
        respond userService.get(id)
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def update(User user) {
        if (user == null) {
            notFound()
            return
        }

        try {
            userService.save(user)
        } catch (ValidationException e) {
            respond user.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*'{ respond user, [status: OK] }
        }
    }
    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def updateUserProfile(){
        def userInstance = User.get(params.id)
        def fileEmpPic = request.getFile('imageName')
        String picFileTitle
        String savePath
        if (fileEmpPic && (fileEmpPic.getSize() > 0)) {
            picFileTitle = utilityService.getUniqueFileName(fileEmpPic.getOriginalFilename())
//            String pathFl = "/soft/WMCS_IMAGE/" + picFileTitle
//            String pathFl = "images" + File.separator + "repository" + File.separator + "USER" + File.separator + "PIC"
            String pathFl = "D:/thikthak_image/"+ picFileTitle
//            String picPath = grailsAttributes.getApplicationContext().getResource(pathFl).getFile().toString() + File.separator + picFileTitle
//            savePath = "images/repository/USER/PIC/" + picFileTitle
            savePath = "D:/thikthak_image/" + picFileTitle
//            savePath = "/soft/WMCS_IMAGE/" + picFileTitle
//            File tmpFile = new File(grailsAttributes.getApplicationContext().getResource(pathFl).getFile().toString())
//            tmpFile.delete()
//            if (!tmpFile.exists()) {
//                tmpFile.mkdir()
//            }
            fileEmpPic.transferTo(new File(pathFl))

        }

        try {
            userInstance.setImageName(picFileTitle)
            userInstance.setProfilePicPath(savePath)

            if (userInstance.save(flush: true)){
                render userInstance as JSON
            }
        } catch (ValidationException e) {
            respond userInstance.errors, view:'create'
            return
        }


    }

    def showFile(String id) {

//        println("imageName "+params.imageName)

        String filePath="D:/thikthak_image/"
        println("filePath :"+filePath)

        String profileImagePath = filePath

//        String image = id // image name

//        String image = params.imageName // or whatever name you saved in your db
//        String[] listpp = image.split(".")
//        String ddd = listpp[1]

        File imageFile = new File(profileImagePath + params.imageName);

        BufferedImage originalImage = ImageIO.read(imageFile);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        ImageIO.write(originalImage, "jpg", baos);

        byte[] imageInByte = baos.toByteArray();

        response.setHeader('Content-length', imageInByte.length.toString())

        response.contentType = 'image/jpg' // or the appropriate image content type

        response.outputStream << imageInByte
        response.outputStream.flush()

    }


    def getTechnicianRating(Long techId){
        def techUserInstance = User.findById(techId)
        def techServiceList = ServiceOrders.createCriteria().list() {
            eq("technicianUser", techUserInstance)
            eq("status", 2)
            eq("isDueAmount", true)
            order('id', 'desc')
//            maxResults(20)
        }
        if(techServiceList){
            def result = [rating:techUserInstance?.rating,isDueAmount:true]
            render result as JSON
        }else{
            def result = [rating:techUserInstance?.rating,isDueAmount:false]
            render result as JSON
        }

    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        userService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
