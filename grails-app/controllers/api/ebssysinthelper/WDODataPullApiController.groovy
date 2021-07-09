package api.ebssysinthelper

import com.app.integration.IntegrationSourceDbConn
import grails.plugin.springsecurity.annotation.Secured
import groovy.json.JsonBuilder
import groovy.sql.Sql
import sysintegration.SysIntegrationDataSrc

class WDODataPullApiController {

    // connection properties
    def url = ""
    def user = ""
    def password = ""
    def driver = "oracle.jdbc.OracleDriver"
    def dbClient = "ORACLE" // MYSQL, ORACLE IBMDB2

    Boolean sysDebug = false

    WDODataPullApiController(){
        this.setSQConnectionProperties()
    }

    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def index() {
        println("OK")
        render "OK---1"
    }



    def getHeaderData(String doNumber, refSysDbConn){

        String hSql = """
            SELECT 
                WD.ORG_ID,
                D.DELIVERY_ID DO_NO,
                D.CONFIRM_DATE DO_DATE,
                SUM(WD.SHIPPED_QUANTITY) DO_QTY,
                CUS.PARTY_ID AS CUSTOMER_ID,
                P.PARTY_NAME AS CUSTOMER_NAME
            FROM
                APPS.HZ_PARTIES P,
                APPS.HZ_CUST_ACCOUNTS CUS,
                APPS.WSH_DELIVERY_ASSIGNMENTS A,
                APPS.WSH_DELIVERY_DETAILS WD,
                APPS.WSH_NEW_DELIVERIES D
            WHERE
                1 = 1 AND CUS.PARTY_ID = P.PARTY_ID
                    AND CUS.CUST_ACCOUNT_ID = D.CUSTOMER_ID
                    AND WD.INV_INTERFACED_FLAG = 'Y'
                    AND WD.DELIVERY_DETAIL_ID = A.DELIVERY_DETAIL_ID
                    AND A.DELIVERY_ID = D.DELIVERY_ID
                    AND D.DELIVERY_ID = '"""+doNumber+"""'
            GROUP BY WD.ORG_ID , D.CONFIRM_DATE , D.DELIVERY_ID , CUS.PARTY_ID , P.PARTY_NAME
            """
        def queryResultList = refSysDbConn.rows(hSql)
        if(queryResultList.size() > 0) return queryResultList.get(0)
        return [:]

    }


    def getLinesData(String doNumber, refSysDbConn){
        String lSql = """
            SELECT 
                WD.ORG_ID,
                D.DELIVERY_ID DO_NO,
                D.CONFIRM_DATE DO_DATE,
                WD.SHIPPED_QUANTITY DO_QTY,
                WD.SOURCE_HEADER_NUMBER CO_NO,
                WD.DELIVERY_DETAIL_ID,
                WD.INVENTORY_ITEM_ID,
                WD.ITEM_DESCRIPTION,
                ORL.UNIT_SELLING_PRICE COST_PRICE,
                ORL.UNIT_LIST_PRICE MRP_PRICE
            FROM
                APPS.OE_ORDER_LINES_ALL ORL,
                APPS.WSH_DELIVERY_DETAILS WD,
                APPS.WSH_DELIVERY_ASSIGNMENTS A,
                APPS.WSH_NEW_DELIVERIES D
            WHERE
                ORL.LINE_ID = WD.SOURCE_LINE_ID
                    AND ORL.HEADER_ID = WD.SOURCE_HEADER_ID
                    AND WD.INV_INTERFACED_FLAG = 'Y'
                    AND WD.DELIVERY_DETAIL_ID = A.DELIVERY_DETAIL_ID
                    AND A.DELIVERY_ID = D.DELIVERY_ID
                    AND D.DELIVERY_ID = '"""+doNumber+"""'
            """

        def queryResultList = refSysDbConn.rows(lSql)
        if(queryResultList.size() > 0) return queryResultList
        return []
    }


    def getSerialsData(String doNumber, refSysDbConn){
        String sSql = """
            SELECT 
                MMT.SHIPMENT_NUMBER,
                MMT.TRANSACTION_ID,
                MTL.SERIAL_TRANSACTION_ID,
                MMT.INVENTORY_ITEM_ID,
                MTS.SERIAL_NUMBER BAR_CODE
            FROM
                APPS.MTL_UNIT_TRANSACTIONS MTS,
                APPS.MTL_TRANSACTION_LOT_NUMBERS MTL,
                APPS.MTL_MATERIAL_TRANSACTIONS MMT
            WHERE
                MTS.TRANSACTION_ID = MTL.SERIAL_TRANSACTION_ID
                    AND MTL.TRANSACTION_ID = MMT.TRANSACTION_ID
                    AND MMT.SHIPMENT_NUMBER = '"""+doNumber+"""'
            """
        def queryResultList = refSysDbConn.rows(sSql)
        if(queryResultList.size() > 0) return queryResultList
        return []
    }


    def getItemsDetailsData(returnJSON, refSysDbConn){

        Map itemDetailsInfo = [:]
        def lines = returnJSON['lines'] as ArrayList

        lines.each { thisLine ->
            String INVENTORY_ITEM_ID = thisLine['INVENTORY_ITEM_ID']

            String iSql = """
            SELECT DISTINCT
                MSI.ITEM_TYPE AS ITEM_TYPE,
                MCB.SEGMENT1 AS ITEM_TYPE_DESC,
                MSI.INVENTORY_ITEM_ID ITEM_ID,
                MSI.SEGMENT1 ITEM_CODE,
                MSI.DESCRIPTION AS ITEM_DESCRIPTION,
                MSI.PRIMARY_UOM_CODE UOM,
                MCB.SEGMENT2 AS ITEM_GROUP,
                MCB.SEGMENT3 AS ITEM_CATEGORY,
                MCB.SEGMENT4 AS BRAND_NAME,
                MCB.SEGMENT5 AS MODEL_NAME,
                MCB.SEGMENT3 AS PACKAGE_NAME,
                '' AS PROJECT_OR_TRADE_NAME,
                DECODE(MSI.SERIAL_NUMBER_CONTROL_CODE, 5, 'SL', 'LOT') AS CONTROLLED_LABEL,
                DECODE(MSI.SERIAL_NUMBER_CONTROL_CODE, 1, 1, 0) AS LOT_CONTROLLED,
                DECODE(MSI.SERIAL_NUMBER_CONTROL_CODE, 5, 1, 0) AS SERIAL_CONTROLLED,
                MSI.SEGMENT1 AS EXTERNAL_REFERENCE,
                MSI.INVENTORY_ITEM_ID AS EXTERNAL_REFERENCE_ID,
                MSI.CREATION_DATE,
                MSI.LAST_UPDATE_DATE
            FROM
                APPS.MTL_SYSTEM_ITEMS MSI,
                APPS.MTL_ITEM_CATEGORIES MIC,
                APPS.MTL_CATEGORIES_B MCB
             WHERE     1 = 1
                   AND MIC.CATEGORY_SET_ID = 1100000041                              -- OM
                   AND MSI.ORGANIZATION_ID IN (102, 225, 302)
                   AND MSI.INVENTORY_ITEM_ID = MIC.INVENTORY_ITEM_ID
                   AND MSI.ORGANIZATION_ID = MIC.ORGANIZATION_ID
                   AND MIC.CATEGORY_ID = MCB.CATEGORY_ID
                   AND MSI.INVENTORY_ITEM_STATUS_CODE = 'Active'
                   --AND MCB.SEGMENT2 <> 'N/A' -- its comment now, for gift item, itemType = N/A
                   AND MSI.INVENTORY_ITEM_ID = '"""+INVENTORY_ITEM_ID+"""'
            """
            println('@iSql '+ iSql)

            def queryResultList = refSysDbConn.rows(iSql)
            if(queryResultList.size() > 0){
                itemDetailsInfo[INVENTORY_ITEM_ID] = queryResultList.get(0) as Map
            } else {
                itemDetailsInfo[INVENTORY_ITEM_ID] = ""
            }
        }

        return itemDetailsInfo

    }

    /*
    WDoJson {
        header: {

        },
        lines: [

        ],
        serials: {

        },
        itemsDetails:{
            itemId: {

            },
            itemId: {

            },
            itemId: {

            }
        }
      }
    * */
    @Secured(['ROLE_ADMIN', 'ROLE_USER'])
    def getJSONData(){

        def refSysDbConn = new IntegrationSourceDbConn(this.url, this.user, this.password, this.driver, this.dbClient).conn as Sql
        if(this.sysDebug) println(refSysDbConn)

        String doNumber = params.q

        Map returnJSON = [:]
        returnJSON['header'] = this.getHeaderData(doNumber, refSysDbConn)
        returnJSON['lines'] = this.getLinesData(doNumber, refSysDbConn)
        returnJSON['serials'] = this.getSerialsData(doNumber, refSysDbConn)
        returnJSON['itemsDetails'] = this.getItemsDetailsData(returnJSON, refSysDbConn)
        render new JsonBuilder( returnJSON ).toPrettyString()
//        render new JsonBuilder( [key: "Tomal Test....."] ).toPrettyString()

    }



























    def setSQConnectionProperties(){
        // default
        this.url = "dbc:oracle:thin:@192.168.150.46:1521:PROD"
        this.user = "appswg"
        this.password = "appswg#"
        this.driver = "oracle.jdbc.OracleDriver"
        // customize
        def integrationInst = SysIntegrationDataSrc.findByIntegrationName('EBS_DO_PULL_TO_POS_SOFT')
        if(integrationInst){
            this.url = integrationInst.hostUrl
            this.user = integrationInst.dbUser // user is reserved for oracle
            this.password = integrationInst.dbPassword
            this.driver = integrationInst.driver
            this.dbClient = integrationInst.dbClient
        }
    }


}
