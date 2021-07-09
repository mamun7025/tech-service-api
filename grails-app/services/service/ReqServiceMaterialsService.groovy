package service

import grails.gorm.services.Service

@Service(ReqServiceCost)
interface ReqServiceMaterialsService {

    ReqServiceCost get(Serializable id)

    List<ReqServiceCost> list(Map args)

    Long count()

    void delete(Serializable id)

    ReqServiceCost save(ReqServiceCost reqServiceMaterials)

}