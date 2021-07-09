package service

import grails.gorm.services.Service

@Service(ProdServicePriceRateList)
interface ProdServicePriceRateListService {

    ProdServicePriceRateList get(Serializable id)

    List<ProdServicePriceRateList> list(Map args)

    Long count()

    void delete(Serializable id)

    ProdServicePriceRateList save(ProdServicePriceRateList prodServicePriceRateList)

}