package comn

import grails.gorm.services.Service

@Service(Payment)
interface PaymentsService {

    Payment get(Serializable id)

    List<Payment> list(Map args)

    Long count()

    void delete(Serializable id)

    Payment save(Payment payments)

}