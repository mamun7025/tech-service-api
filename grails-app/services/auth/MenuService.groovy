package auth

import grails.gorm.transactions.Transactional
import grails.web.mapping.LinkGenerator
import org.springframework.web.context.request.RequestContextHolder

@Transactional
class MenuService {

    def grailsApplication
    def springSecurityService
    def webInvocationPrivilegeEvaluator
    def menuSession = 'appMenu'
    LinkGenerator grailsLinkGenerator

    def getMenu(String menuType) {
        def session = RequestContextHolder.currentRequestAttributes().getSession()
        def menu
        if (session.getAttribute(menuSession)) {
            menu = session.getAttribute(menuSession)
        } else {
            def menuString = new StringBuilder()
            menu = getChildMenu(null, menuType, 0, menuString)
            session.setAttribute(menuSession, menu)
        }
        return menu
    }

    def getChildMenu(Menu parentMenu, String menuType, Integer level, StringBuilder menuString) {
        def menuInstanceList = Menu.createCriteria().list {
            if (parentMenu)
                eq("parentMenu", parentMenu)
            else
                isNull("parentMenu")
            eq("menuType", menuType)
            eq("isActive", true)
            order('sortOrder', 'asc')
            order('title', 'asc')
        }

        if (menuInstanceList) {
            def ul = level == 0 ? '<ul class="sidebar-menu" data-widget="tree">' : '<ul class="treeview-menu">'
            menuString.append(ul)
            for (Menu menuInstance in menuInstanceList) {
                def linkProperties = menuInstance.isOpenNewTab ? 'target="_blank"' : 'target="_self"'
                def menuLink = menuInstance.isExternal ? menuInstance?.urlPath : grailsLinkGenerator.link(uri: menuInstance?.urlPath, absolute: false)
                def menuClass = menuInstance?.menuClass ? menuInstance?.menuClass : 'fa fa-circle-o'
                def menuTitle = level == 0 ? "<span>" + menuInstance?.title + "</span>" : menuInstance?.title
                def countChild = countChildMenu(menuInstance)
                if (countChild > 0) {
                    menuTitle = menuTitle + "<i class=\"fa fa-angle-left pull-right\"></i>"
                }
                if (grailsApplication.config.grails.plugin.springsecurity.active == true && webInvocationPrivilegeEvaluator.isAllowed(menuInstance?.urlPath, springSecurityService.getAuthentication())) {
                    if (countChild > 0) {
                        menuString.append('<li class="treeview"><a href="' + menuLink + '" ' + linkProperties + '><i class="' + menuClass + '"></i>' + menuTitle + '</a>')
                    } else {
                        menuString.append('<li><a href="' + menuLink + '" ' + linkProperties + '><i class="' + menuClass + '"></i>' + menuTitle + '</a>')
                    }
                    getChildMenu(menuInstance, menuType, level + 1, menuString)
                    menuString.append('</li>')
                }
            }
            menuString.append('</ul>')
        }

        return menuString
    }

    def countChildMenu(Menu menuInstance) {
        return Menu.countByParentMenuAndIsActive(menuInstance, true)
    }

    def getHierarchicalMenuList() {
        def menuInstanceList = _getHierarchicalMenuList(new ArrayList(), null, null)
        return menuInstanceList
    }

    def _getHierarchicalMenuList(List menuList, Menu parentMenu, String menuPrefix) {
        def menuInstanceList = Menu.createCriteria().list {
            if (parentMenu)
                eq("parentMenu", parentMenu)
            else
                isNull("parentMenu")
            eq("isActive", true)
            order('sortOrder', 'asc')
            order('title', 'asc')
        }
        if (menuInstanceList) {
            for (Menu menuInstance in menuInstanceList) {
                def title = menuPrefix ? menuPrefix + " > " + menuInstance.title : menuInstance.title
                menuList.add([id: menuInstance.id, title: title])
                _getHierarchicalMenuList(menuList, menuInstance, title)
            }
        }
        return menuList
    }

    def clearSession() {
        def session = RequestContextHolder.currentRequestAttributes().getSession()
        return session.removeAttribute(menuSession)
    }
}
