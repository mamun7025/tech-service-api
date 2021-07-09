<!DOCTYPE html>
<html lang="en">
<head>
    <g:applyLayout name="_head" />
</head>
<body class="hold-transition sidebar-mini layout-fixed layout-navbar-fixed layout-footer-fixed">
<div class="wrapper">
    <!-- Navbar -->
    <g:applyLayout name="_header" />

    <!-- Main Sidebar Container -->
    %{--<g:render template="/layouts/mainSidebarx"/>--}%
    <g:applyLayout name="_mainSidebarx" />

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <div class="content-header">
            <div class="container-fluid">
                <div class="row mb-2">
                    <div class="col-sm-6">
                        <h1 class="m-0 text-dark">Admin Panel</h1>
                    </div><!-- /.col -->
                    <div class="col-sm-6">
                        <ol class="breadcrumb float-sm-right">
                            <li class="breadcrumb-item"><a href="#">Home</a></li>
                            <li class="breadcrumb-item active">Admin Panel</li>
                        </ol>
                    </div><!-- /.col -->
                </div><!-- /.row -->
            </div><!-- /.container-fluid -->
        </div>
        <!-- /.content-header -->

        <!-- Main content -->
        <section class="content">
            <div class="container-fluid">

                <g:applyLayout name="_templateBody" />

            </div><!--/. container-fluid -->
        </section>
        <!-- /.content -->
    </div>
    <!-- /.content-wrapper -->

    <!-- Control Sidebar -->
    <aside class="control-sidebar control-sidebar-dark">
        <!-- Control sidebar content goes here -->
    </aside>
    <!-- /.control-sidebar -->

    <!-- Main Footer -->
    <g:render template="/layouts/footer"/>

</div>
<!-- ./wrapper -->


<g:applyLayout name="_footerScripts" />

<!-- PAGE SCRIPTS -->
<asset:javascript src="dist/js/pages/dashboard2.js"/>
</body>
</html>
