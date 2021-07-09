<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'serviceItems.label', default: 'ServiceItems')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-serviceItems" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-serviceItems" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            %{--<f:table collection="${serviceItemsList}" />--}%

        %{--Customize List Start--}%
            <div class="box-body table-responsive no-padding">
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <g:sortableColumn property="id" title="id" params="${params}"/>
                        <g:sortableColumn property="itemCode" title="itemCode" params="${params}"/>
                        <g:sortableColumn property="itemDescription" title="itemDescription" params="${params}"/>
                        <g:sortableColumn property="itemName" title="itemName" params="${params}"/>
                        <g:sortableColumn property="regularPrice" title="regularPrice" params="${params}"/>
                        <th style="color: #337ab7">Actions</th>
                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${entityInstanceList}" status="i" var="entityInstance">
                        <tr>
                            <td>${fieldValue(bean: entityInstance, field: "id")}</td>
                            <td>${fieldValue(bean: entityInstance, field: "itemCode")}</td>
                            <td>${fieldValue(bean: entityInstance, field: "itemDescription")}</td>
                            <td>${fieldValue(bean: entityInstance, field: "itemName")}</td>
                            <td>${fieldValue(bean: entityInstance, field: "regularPrice")}</td>

                            <td>
                                <div class="list-action">
                                    <sec:access url="/serviceOrdersUI/index/${entityInstance?.id}">
                                        <g:link action="edit" id="${entityInstance?.id}"><i class="fa fa-fw fa-edit"></i>Edit</g:link>
                                    </sec:access>
                                    <sec:access url="/serviceOrdersUI/edit/${entityInstance.id}">
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
                <g:paginate total="${serviceItemsCount ?: 0}" />
            </div>
        </div>
    </body>
</html>