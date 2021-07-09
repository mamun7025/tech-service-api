package service

import grails.gorm.services.Service

@Service(FavoriteTechnician)
interface FavoriteTechnicianService {

    FavoriteTechnician get(Serializable id)

    List<FavoriteTechnician> list(Map args)

    Long count()

    void delete(Serializable id)

    FavoriteTechnician save(FavoriteTechnician favoriteTechnician)

}