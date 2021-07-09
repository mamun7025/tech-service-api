package marketing

import grails.gorm.services.Service

@Service(OfferPromos)
interface OfferPromosService {

    OfferPromos get(Serializable id)

    List<OfferPromos> list(Map args)

    Long count()

    void delete(Serializable id)

    OfferPromos save(OfferPromos offerPromos)

}