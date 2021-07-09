package wtsapi

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")
        "500"(view:'/error')
        "404"(view:'/notFound')

        "/api/products"(resources:"product")
        "/api/user"(resources:"user") {
            collection {
                '/search'(controller: 'user', action: 'search')
            }
        }
//        "/api/userLocation"(resources:"userLocation") {
//            collection {
//                '/search'(controller: 'userLocation', action: 'search')
//            }
//        }
//        "/api/userLocation"(resources:"userLocation") {
//            collection {
//                '/searchNearby'(controller: 'userLocation', action: 'searchNearby')
//            }
//        }
        "/api/serviceCenter"(resources:"serviceCenter")
        "/api/serviceItems"(resources:"serviceItems")
        "/api/serviceOrders"(resources:"serviceOrders")
        "/api/notification"(resources:"notification")
        "/api/reqServiceMaterials"(resources:"reqServiceMaterials")
        "/api/favoriteTechnician"(resources:"favoriteTechnician")
        "/api/salesOrder"(resources:"salesOrder")
        "/api/userFeedbacks"(resources:"userFeedbacks")
        "/api/offerPromos"(resources:"offerPromos")
        get "/api/prodServicePriceRateList"(controller:"prodServicePriceRateList", action:"ratingWiseServicePrice")
        post "/api/serviceOrders/addOrderCost"(controller:"serviceOrders", action:"addOrderCost")
        post "/api/user/updateUserProfile"(controller:"user", action:"updateUserProfile")
        get "/api/user/getTechnicianRating/$techId?"(controller:"user", action:"getTechnicianRating")
        get "/api/serviceOrders/getDueAmount/$techId?"(controller:"serviceOrders", action:"getDueAmount")
        post "/api/userLocation/userLocationUpdate"(controller:"userLocation", action:"userLocationUpdate")
//        get "/api/userLocation"(controller:"userLocation", action:"searchNearTechByItem")
        get "/api/getNearestTechnicians"(controller:"userLocation", action:"searchNearTechByItem")
        post "/api/setOfflineUser"(controller:"setUnsetUserActivity", action:"unsetUserActiveOnline")
        post "/api/orderCost"(controller:"reqServiceCost", action:"totalServiceCost")
    }
}
