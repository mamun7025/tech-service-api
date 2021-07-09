package auth

import org.springframework.dao.DataIntegrityViolationException

class MenuController {

    static defaultAction = "list"
    def springSecurityService
    def menuService

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 1000)
        def result = Menu.createCriteria().list(params) {
            if (params.parentMenuId?.isNumber()) {
                eq("parentMenu", Menu.get(params.parentMenuId))
            }
            if (params.title) {
                ilike("title", "%${params.title}%")
            }
            if (params.urlPath) {
                ilike("urlPath", "%${params.urlPath}%")
            }
        }
        def totalCount = result.totalCount
        [menuInstanceList: result, menuInstanceTotal: totalCount, menuInstanceListDD: menuService.getHierarchicalMenuList()]
    }

    def show(Menu menuInstance) {
        if (!menuInstance) {
            flash.waring = 'Oops! No such record found!'
            redirect(action: "list")
            return
        }
        [menuInstance: menuInstance]
    }


    def add() {
        def menuInstance = new Menu(params)
        if (request.method == 'POST') {
            def currentUserUsername = springSecurityService.currentUser ? springSecurityService.currentUser.username : 'SYSTEM'
            def currentDate = new Date()
            menuInstance.setCreatedDate(currentDate)
            menuInstance.setCreatedBy(currentUserUsername)
            if (!menuInstance.save(flush: true)) {
                render(view: "add", model: [menuInstance: menuInstance])
                return
            }
            menuService.clearSession()
            flash.success = "Data have been saved successfully!"
            redirect(action: "show", id: menuInstance.id)
        }
        [menuInstance: menuInstance, menuInstanceListDD: menuService.getHierarchicalMenuList()]
    }


    def edit(Menu menuInstance) {
        if (!menuInstance) {
            flash.waring = 'Oops! No such record found!'
            redirect(action: "list")
            return
        }
        if (request.method == 'PUT') {
            def currentUserUsername = springSecurityService.currentUser ? springSecurityService.currentUser.username : 'SYSTEM'
            def currentDate = new Date()
            menuInstance.setModifiedDate(currentDate)
            menuInstance.setModifiedBy(currentUserUsername)
            menuInstance.save(flush: true)
            menuService.clearSession()
            flash.success = "Data have been saved successfully!"
            redirect(action: "show", id: menuInstance.id)
            return
        }
        [menuInstance: menuInstance, menuInstanceListDD: menuService.getHierarchicalMenuList()]
    }


    def delete(Menu menuInstance) {

        if (!menuInstance) {
            flash.error = message(code: 'default.not.found.message', args: [message(code: 'menu.label', default: 'Menu'), id])
            redirect(action: "list")
            return
        }

        try {
            menuInstance.delete(flush: true)
            flash.success = "Data have been deleted successfully!"
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.error = e.message
            redirect(action: "show", id: menuInstance.id)
        }
    }
}
