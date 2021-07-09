package marketing

import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class OfferPromosServiceSpec extends Specification {

    OfferPromosService offerPromosService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new OfferPromos(...).save(flush: true, failOnError: true)
        //new OfferPromos(...).save(flush: true, failOnError: true)
        //OfferPromos offerPromos = new OfferPromos(...).save(flush: true, failOnError: true)
        //new OfferPromos(...).save(flush: true, failOnError: true)
        //new OfferPromos(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //offerPromos.id
    }

    void "test get"() {
        setupData()

        expect:
        offerPromosService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<OfferPromos> offerPromosList = offerPromosService.list(max: 2, offset: 2)

        then:
        offerPromosList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        offerPromosService.count() == 5
    }

    void "test delete"() {
        Long offerPromosId = setupData()

        expect:
        offerPromosService.count() == 5

        when:
        offerPromosService.delete(offerPromosId)
        sessionFactory.currentSession.flush()

        then:
        offerPromosService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        OfferPromos offerPromos = new OfferPromos()
        offerPromosService.save(offerPromos)

        then:
        offerPromos.id != null
    }
}
