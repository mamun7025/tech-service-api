<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'userLocationHistory.label', default: 'UserLocationHistory')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-userLocationHistory" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-userLocationHistory" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <!-- <f:table collection="${userLocationHistoryList}" /> -->

            %{--Customize List Start--}%
            <div class="box-body table-responsive no-padding">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <g:sortableColumn property="user" title="user" params="${params}"/>
                        <g:sortableColumn property="latitude" title="latitude" params="${params}"/>
                        <g:sortableColumn property="longitude" title="longitude" params="${params}"/>
                        <g:sortableColumn property="timestamp" title="timestamp" params="${params}"/>
                        <g:sortableColumn property="creationDateTime" title="creationDateTime" params="${params}"/>
                        <th style="color: #337ab7">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                        <g:each in="${entityInstanceList}" status="i" var="entityInstance">
                            <tr>
                                <td>${fieldValue(bean: entityInstance, field: "user")}</td>
                                <td>${fieldValue(bean: entityInstance, field: "latitude")}</td>
                                <td>${fieldValue(bean: entityInstance, field: "longitude")}</td>
                                <td>${fieldValue(bean: entityInstance, field: "timestamp")}</td>
                                <td>${fieldValue(bean: entityInstance, field: "creationDateTime")}</td>

                                <td>
                                    <div class="list-action">
                                        <sec:access url="/userUI/index/${entityInstance?.id}">
                                            <g:link action="edit" id="${entityInstance?.id}"><i class="fa fa-fw fa-edit"></i>Edit</g:link>
                                        </sec:access>
                                        <sec:access url="/userUI/edit/${entityInstance.id}">
                                            <g:link action="show" id="${entityInstance.id}" title="View" alt="View"><i class="fa fa-file-text-o"></i>View</g:link>
                                        </sec:access>
                                    </div>
                                </td>
                            </tr>
                        </g:each>
                    </tbody>
                </table>
            </div><!-- /.box-body -->
            %{--Customize List End--}%

            <div class="pagination">
                <g:paginate total="${userLocationHistoryCount ?: 0}" />
            </div>
        </div>
    </body>
</html>