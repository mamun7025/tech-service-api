package service

import auth.User

class ServiceOrders {

    Long id

    // Order
    String orderCode
    Date orderPlaceTime

    // Client
    User clientUser
    String clientUserName
    String clientMobileNo
    String deliveryAddress

    String clientGeoLocation
    Double clientLatitude = 0.0
    Double clientLongitude = 0.0

    // Technician
    User technicianUser
    String technicianUserName
    String technicianGeoLocation
    Double techLatitude = 0.0
    Double techLongitude = 0.0

    // Service request item
    ServiceItems serviceItems
    String serviceItemCode
    String serviceItemName
    String serviceDetailsDesc
    String issueImagePath

    Boolean warrantyProduct = false
    Boolean servicePartsRequired = false
    Boolean servicePartsRequiredAcknlg
    Boolean isAgreed = false
    String refInvoiceNumber
    String invoiceNumber
    Boolean isScheduleOrder = false
    Date scheduleDate
    String brandName
    String productSerial
    Date serviceStartTime
    Date serviceEndTime
    Double serviceDuration = 0.0

    Double learningPeriod
    Boolean isLearningOrder

    Double partsCost
    Double billAmount

    Double tadaCost
    Double othersCost
    Double sdAmount
    Double vatAmount
    Double cashRcvAmount
    Double cardRcvAmount
    Double totalRcvAmount

    Double serviceCost // serviceCost = comChargeAmount+techAmount
    Double comChargePct
    Double comChargeAmount
    Double techAmount

    Date bookTime



    Integer status = 0 // Pending-0, Accept-1, Complete-2 Reject-3

    Double orderPrice
    // Payment
    Double paymentAmount

    Boolean isRated = false
    Boolean isDueAmount = true
    Double rating = 0.0
    String clientPhoneNumber



    // System log fields
    Date creationDateTime
    String creationUser
    Date lastUpdateDateTime
    String lastUpdateUser

    static mapping = {
        table 'service_orders'
        version false
        id generator: 'increment'
        id column: 'ID'

        // Order
        orderCode column: 'ORDER_CODE'
        orderPlaceTime column: 'ORDER_PLACE_TIME'

        // Client
        clientUser column: 'CLIENT_USER_ID'
        clientUserName column: 'CLIENT_USER_NAME'
        clientMobileNo column: 'CLIENT_MOBILE_NO'
        deliveryAddress column: 'DELIVERY_ADDRESS'
        clientGeoLocation column: 'CLIENT_GEO_LOCATION'
        clientLatitude column: 'CLIENT_LATITUDE'
        clientLongitude column: 'CLIENT_LONGITUDE'

        // Technician
        technicianUser column: 'TECHNICIAN_USER_ID'
        technicianUserName column: 'TECHNICIAN_USER_NAME'
        technicianGeoLocation column: 'TECHNICIAN_USER_GEO_LOCATION'
        techLatitude column: 'TECH_LATITUDE'
        techLongitude column: 'TECH_LONGITUDE'

        // Service request item
        serviceItems column: 'SERVICE_ITEMS_ID'
        serviceItemCode column: 'SERVICE_ITEMS_CODE'
        serviceItemName column: 'SERVICE_ITEMS_NAME'
        serviceDetailsDesc column: 'SERVICE_DETAILS_DESC'
        issueImagePath column: 'ISSUE_IMAGE_PATH'

        warrantyProduct column: 'WARRANTY_PRODUCT'
        servicePartsRequired column: 'SERVICE_PARTS_REQUIRED'
        servicePartsRequiredAcknlg column: 'SERVICE_PARTS_REQUIRED_ACKNLG'
        isAgreed column: 'IS_AGREED'
        refInvoiceNumber column: 'REF_INVOICE_NUMBER'
        invoiceNumber column: 'INVOICE_NUMBER'
        isScheduleOrder column: 'IS_SCHEDULE_ORDER'
        scheduleDate column: 'SCHEDULE_DATE'
        brandName column: 'BRAND_NAME'
        productSerial column: 'PRODUCT_SERIAL'
        serviceStartTime column: 'SERVICE_START_TIME'
        serviceEndTime column: 'SERVICE_END_TIME'
        serviceDuration column: 'SERVICE_DURATION'

        learningPeriod column: 'LEARNING_PERIOD'
        isLearningOrder column: 'IS_LEARNING_ORDER'

        partsCost column: 'PARTS_COST'
        billAmount column: 'BILL_AMOUNT'
        serviceCost column: 'SERVICE_COST'
        tadaCost column: 'TADA_COST'
        othersCost column: 'OTHERS_COST'
        sdAmount column: 'SD_AMOUNT'
        vatAmount column: 'VAT_AMOUNT'
        cashRcvAmount column: 'CASH_RCV_AMOUNT'
        cardRcvAmount column: 'CARD_RCV_AMOUNT'
        totalRcvAmount column: 'TOTAL_RCV_AMOUNT'

        bookTime column: 'BOOK_TIME'

        status column: 'STATUS'
        orderPrice column: 'ORDER_PRICE'
        paymentAmount column: 'PAYMENT_AMOUNT'

        isRated column: 'IS_RATED'
        isDueAmount column: 'IS_DUE_AMOUNT'
        rating column: 'RATING'
        clientPhoneNumber column: 'CLIENT_PHONE_NUMBER'

        comChargePct column: 'COM_CHARGE_PCT'
        comChargeAmount column: 'COM_CHARGE_AMOUNT'
        techAmount column: 'TECH_AMOUNT'

        creationDateTime column: 'CREATION_DATETIME'
        creationUser column: 'CREATION_USER'
        lastUpdateDateTime column: 'LAST_UPDATE_DATETIME'
        lastUpdateUser column: 'LAST_UPDATE_USER'
    }

    static constraints = {
        // Order
        orderCode nullable: true
        orderPlaceTime nullable: true

        // Client
        clientUser nullable: true
        clientUserName nullable: true
        clientMobileNo nullable: true
        deliveryAddress nullable: true
        clientGeoLocation nullable: true
        clientLatitude nullable: true
        clientLongitude nullable: true

        // Technician
        technicianUser nullable: true
        technicianUserName nullable: true
        technicianGeoLocation nullable: true
        techLatitude nullable: true
        techLongitude nullable: true

        // Service request item
        serviceItems nullable: true
        serviceItemCode nullable: true
        serviceItemName nullable: true
        serviceDetailsDesc nullable: true
        issueImagePath nullable: true

        warrantyProduct nullable: true
        servicePartsRequired nullable: true
        servicePartsRequiredAcknlg nullable: true
        isAgreed nullable: true
        refInvoiceNumber nullable: true
        invoiceNumber nullable: true
        isScheduleOrder nullable: true
        scheduleDate nullable: true
        brandName nullable: true
        productSerial nullable: true
        serviceStartTime nullable: true
        serviceEndTime nullable: true
        serviceDuration nullable: true

        learningPeriod nullable: true
        isLearningOrder nullable: true

        partsCost nullable: true
        billAmount nullable: true
        serviceCost nullable: true
        tadaCost nullable: true
        othersCost nullable: true
        sdAmount nullable: true
        vatAmount nullable: true
        cashRcvAmount nullable: true
        cardRcvAmount nullable: true
        totalRcvAmount nullable: true

        bookTime nullable: true
        deliveryAddress nullable: true

        status nullable: true
        orderPrice nullable: true
        paymentAmount nullable: true

        isRated nullable: true
        isDueAmount nullable: true
        rating nullable: true
        clientPhoneNumber nullable: true

        comChargePct nullable: true
        comChargeAmount nullable: true
        techAmount nullable: true

        creationDateTime nullable: true
        creationUser nullable: true
        lastUpdateDateTime nullable: true
        lastUpdateUser nullable: true
    }


}
