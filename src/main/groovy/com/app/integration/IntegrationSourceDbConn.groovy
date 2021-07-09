package com.app.integration

import groovy.sql.Sql

class IntegrationSourceDbConn {

//    def url = "dbc:oracle:thin:@192.168.200.64:1521:hrpos"
    def url = "dbc:oracle:thin:@192.168.100.190:1531:test"
    def user = "APPS"
    def password = "APPS"
    def driver = "oracle.jdbc.OracleDriver"

    def dbClient = "ORACLE" // MYSQL, ORACLE IBMDB2

    def conn
    def sqlStr
    def queryRows
    def queryResult

    Boolean sysDebug = false

    IntegrationSourceDbConn( String url, String user, String password, String driver, String dbClient ){

        if(url != "") this.url = url
        if(user != "") this.user = user
        if(password != "") this.password = password
        if(driver != "") this.driver = driver
        if(dbClient != "") this.dbClient = dbClient

        if(this.sysDebug){
            println("Connection parameters")
            println("url ---" + this.url)
            println("user ---" + this.user)
            println("password ---" + this.password)
            println("driver ---" + this.driver)
            println("dbClient ---" + this.dbClient)
        }


        try {

            this.conn = Sql.newInstance(this.url, this.user, this.password, this.driver)

            try {
                if(this.dbClient == "MYSQL"){
                    this.conn.eachRow('select current_date()'){ row ->
                        println row
                        println("Successfully connected ---> " + this.dbClient)
                    }
                } else if(this.dbClient == "ORACLE"){
                    this.conn.eachRow('select sysdate from dual'){ row ->
                        println row
                        println("Successfully connected ---> " + this.dbClient)
                    }
                }
            } finally {
            }


        } catch (Exception e){
            println(e)
            throw new Exception(e.message)
        }


    }


    def execute(sqlStr){
        this.sqlStr = sqlStr;
        this.conn.execute(this.sqlStr)
    }

    def query(sqlStr){
        this.conn.execute(sqlStr)
    }

    def close(){
        this.conn.close()
    }


    def resultToArray(){
    }

    def resultToJson(){
    }

    def sqlToJson(sqlStr){
        this.sqlStr = sqlStr
    }

    def sqlToRows(sqlStr){
        this.sqlStr = sqlStr
        this.queryRows = this.conn.rows(this.sqlStr);
        return this.queryRows
    }

    def fuzzyLike(){
    }

    def sanitizeSuperStrict(){
    }

    def sqlToArray_1r(){
    }

    def showColumns(table){
    }

    def getFields(table){
    }


}
