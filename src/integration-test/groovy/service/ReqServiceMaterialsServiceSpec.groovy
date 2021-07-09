package service

import grails.test.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ReqServiceMaterialsServiceSpec extends Specification {

    ReqServiceMaterialsService reqServiceMaterialsService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ReqServiceMaterials(...).save(flush: true, failOnError: true)
        //new ReqServiceMaterials(...).save(flush: true, failOnError: true)
        //ReqServiceMaterials reqServiceMaterials = new ReqServiceMaterials(...).save(flush: true, failOnError: true)
        //new ReqServiceMaterials(...).save(flush: true, failOnError: true)
        //new ReqServiceMaterials(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //reqServiceMaterials.id
    }

    void "test get"() {
        setupData()

        expect:
        reqServiceMaterialsService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ReqServiceMaterials> reqServiceMaterialsList = reqServiceMaterialsService.list(max: 2, offset: 2)

        then:
        reqServiceMaterialsList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        reqServiceMaterialsService.count() == 5
    }

    void "test delete"() {
        Long reqServiceMaterialsId = setupData()

        expect:
        reqServiceMaterialsService.count() == 5

        when:
        reqServiceMaterialsService.delete(reqServiceMaterialsId)
        sessionFactory.currentSession.flush()

        then:
        reqServiceMaterialsService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ReqServiceMaterials reqServiceMaterials = new ReqServiceMaterials()
        reqServiceMaterialsService.save(reqServiceMaterials)

        then:
        reqServiceMaterials.id != null
    }
}
