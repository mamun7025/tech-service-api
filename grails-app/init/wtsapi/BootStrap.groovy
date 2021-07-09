package wtsapi

import auth.Requestmap
import auth.Role
import auth.User
import auth.UserRole

class BootStrap {

    def springSecurityService

    def init = { servletContext ->

        def roleAdmin = new Role(authority: 'ROLE_ADMIN').save()
        def roleUser = new Role(authority: 'ROLE_USER').save()
        def roleGuest = new Role(authority: 'ROLE_GUEST').save()
        def roleTechnician = new Role(authority: 'ROLE_TECHNICIAN').save()
        def roleCustomer = new Role(authority: 'ROLE_CUSTOMER').save()

        def adminUser = new User(username: 'system', password: 'system', phoneNumber: '01799999999', firstName: 'Sys', lastName: 'tem', displayName: 'System', userType: 'Admin').save()
        def adminUser2 = new User(username: 'arju', password: 'system', phoneNumber: '01779282133', firstName: 'Arju', lastName: 'Vai', displayName: 'Arju', userType: 'Admin').save()
        def adminUser3 = new User(username: 'chayan', password: 'system', phoneNumber: '01779282134', firstName: 'Chaya', lastName: 'Vai', displayName: 'Chayan', userType: 'Admin').save()
        def adminUser4 = new User(username: 'shihan', password: 'system', phoneNumber: '01779282135', firstName: 'Shihan', lastName: 'Vai', displayName: 'Shihan', userType: 'Admin').save()
        def adminUser5 = new User(username: 'galib', password: 'system', phoneNumber: '01779282136', firstName: 'Galib', lastName: 'Ibn-Kibria', displayName: 'Galib', userType: 'Admin').save()
        def adminUser6 = new User(username: 'guest', password: 'system', phoneNumber: '01779282137', firstName: 'Guest', lastName: 'User', displayName: 'Guest User', userType: 'Admin').save()

        def chkUsrRole = UserRole.findByUser(adminUser)
        if(!chkUsrRole){
            UserRole.create adminUser, roleAdmin
            UserRole.withSession {
                it.flush()
                it.clear()
            }
        }

        /*UserRole.create adminUser2, roleUser
        UserRole.withSession {
            it.flush()
            it.clear()
        }

        UserRole.create adminUser3, roleUser
        UserRole.withSession {
            it.flush()
            it.clear()
        }

        UserRole.create adminUser4, roleUser
        UserRole.withSession {
            it.flush()
            it.clear()
        }

        UserRole.create adminUser5, roleUser
        UserRole.withSession {
            it.flush()
            it.clear()
        }

        UserRole.create adminUser6, roleGuest
        UserRole.withSession {
            it.flush()
            it.clear()
        }*/


        createDefaultRequestMap()

    }



    def destroy = {
    }





    def createDefaultRequestMap(){

        for (String url in [
                '/', '/error', '/index', '/index.gsp', '/**/favicon.ico', '/shutdown',
                '/**/js/**', '/**/css/**', '/**/images/**',
                '/login', '/login.*', '/login/*',
                '/logout', '/logout.*', '/logout/*']) {
            new Requestmap(url: url, configAttribute: 'permitAll').save()
        }


        new Requestmap(url: '/profile/**',    configAttribute: 'ROLE_USER').save()
        new Requestmap(url: '/admin/**',      configAttribute: 'ROLE_ADMIN').save()
        new Requestmap(url: '/admin/role/**', configAttribute: 'ROLE_SUPERVISOR').save()
        new Requestmap(url: '/admin/user/**', configAttribute: 'ROLE_ADMIN,ROLE_SUPERVISOR').save()

        new Requestmap(url: '/api/login/**', configAttribute: 'permitAll').save()
        new Requestmap(url: '/api/logout/**', configAttribute: 'ROLE_USER').save()

        new Requestmap(url: '/**/img/**', configAttribute: 'permitAll').save()
        new Requestmap(url: '/multimedia/*', configAttribute: 'permitAll').save()


        new Requestmap(url: '/user/**', configAttribute: 'ROLE_USER, ROLE_ADMIN').save()
        new Requestmap(url: '/api/user/**', configAttribute: 'ROLE_USER, ROLE_ADMIN').save()
        new Requestmap(url: '/adminPanel/**', configAttribute: 'ROLE_ADMIN').save()


        new Requestmap(url: '/serviceItems/**', configAttribute: 'ROLE_USER, ROLE_ADMIN').save()
        new Requestmap(url: '/api/serviceItems/**', configAttribute: 'ROLE_USER, ROLE_ADMIN').save()

        new Requestmap(url: '/serviceOrders/**', configAttribute: 'ROLE_USER, ROLE_ADMIN').save()
        new Requestmap(url: '/api/serviceOrders/**', configAttribute: 'ROLE_USER, ROLE_ADMIN').save()

        new Requestmap(url: '/requestmap/**', configAttribute: 'ROLE_USER, ROLE_ADMIN').save()
        new Requestmap(url: '/api/requestmap/**', configAttribute: 'ROLE_USER, ROLE_ADMIN').save()


        new Requestmap(url: '/userUI/**', configAttribute: 'ROLE_USER, ROLE_ADMIN').save()
        new Requestmap(url: '/role/**', configAttribute: 'ROLE_USER, ROLE_ADMIN').save()
        new Requestmap(url: '/userRole/**', configAttribute: 'ROLE_USER, ROLE_ADMIN').save()

        new Requestmap(url: '/WDODataPullApi/**', configAttribute: 'ROLE_USER, ROLE_ADMIN').save()


        // new
        new Requestmap(url: '/api/prodServicePriceRateList/**', configAttribute: 'ROLE_USER, ROLE_ADMIN').save()
        new Requestmap(url: '/api/notification/**', configAttribute: 'ROLE_USER, ROLE_ADMIN').save()
        new Requestmap(url: '/api/favoriteTechnician/**', configAttribute: 'ROLE_USER, ROLE_ADMIN').save()
        new Requestmap(url: '/api/userFeedbacks/**', configAttribute: 'ROLE_USER, ROLE_ADMIN').save()
        new Requestmap(url: '/api/offerPromos/**', configAttribute: 'ROLE_USER, ROLE_ADMIN').save()
        new Requestmap(url: '/api/reqServiceMaterials/**', configAttribute: 'ROLE_USER, ROLE_ADMIN').save()
        new Requestmap(url: '/api/userLocation/**', configAttribute: 'ROLE_USER, ROLE_ADMIN').save()
        new Requestmap(url: '/api/serviceCenter/**', configAttribute: 'ROLE_USER, ROLE_ADMIN').save()


        springSecurityService.clearCachedRequestmaps()

    }


}
