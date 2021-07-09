package service

import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ProdServicePriceRateListServiceSpec extends Specification {

    ProdServicePriceRateListService prodServicePriceRateListService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ProdServicePriceRateList(...).save(flush: true, failOnError: true)
        //new ProdServicePriceRateList(...).save(flush: true, failOnError: true)
        //ProdServicePriceRateList prodServicePriceRateList = new ProdServicePriceRateList(...).save(flush: true, failOnError: true)
        //new ProdServicePriceRateList(...).save(flush: true, failOnError: true)
        //new ProdServicePriceRateList(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //prodServicePriceRateList.id
    }

    void "test get"() {
        setupData()

        expect:
        prodServicePriceRateListService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ProdServicePriceRateList> prodServicePriceRateListList = prodServicePriceRateListService.list(max: 2, offset: 2)

        then:
        prodServicePriceRateListList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        prodServicePriceRateListService.count() == 5
    }

    void "test delete"() {
        Long prodServicePriceRateListId = setupData()

        expect:
        prodServicePriceRateListService.count() == 5

        when:
        prodServicePriceRateListService.delete(prodServicePriceRateListId)
        sessionFactory.currentSession.flush()

        then:
        prodServicePriceRateListService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ProdServicePriceRateList prodServicePriceRateList = new ProdServicePriceRateList()
        prodServicePriceRateListService.save(prodServicePriceRateList)

        then:
        prodServicePriceRateList.id != null
    }
}
