<!-- <aside class="main-sidebar sidebar-dark-primary elevation-4"> -->
<aside class="main-sidebar elevation-4 sidebar-light-indigo">
    <!-- Brand Logo -->
    <!-- <a href="index3.html" class="brand-link"> -->
    <a href="index3.html" class="brand-link navbar-primary">
        <!-- <img src="images/AdminLTELogo.png" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8"> -->
        <img src="${createLinkTo(dir:'images', file:'adminlte/AdminLTELogo.png')}" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8">
        <span class="brand-text font-weight-light">ThikThak</span>
    </a>

    <!-- Sidebar -->
    <div class="sidebar">
        <!-- Sidebar user panel (optional) -->
        <div class="user-panel mt-3 pb-3 mb-3 d-flex">
            <div class="image">
                <img src="${createLinkTo(dir:'images', file:'adminlte/user2-160x160.jpg')}" class="img-circle elevation-2" alt="User Image">
            </div>
            <div class="info">
                <a href="#" class="d-block">Alexander Pierce</a>
            </div>
        </div>

        <!-- Sidebar Menu -->
        <nav class="mt-2">
            <ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
                <!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
                <li class="nav-item has-treeview menu-open">
                    <a href="#" class="nav-link active">
                        <i class="nav-icon fas fa-tachometer-alt"></i>
                        <p>
                            Dashboard
                            %{--<i class="right fas fa-angle-left"></i>--}%
                        </p>
                    </a>

                </li>


                <li class="nav-header">System</li>
                <li class="nav-item">
                    <a href="${request.contextPath}/userUI/" class="nav-link">
                        <i class="far fa-dot-circle nav-icon"></i>
                        <p>
                            User
                        </p>
                    </a>
                </li>

                <li class="nav-item">
                    <a href="${request.contextPath}/role/" class="nav-link">
                        <i class="far fa-dot-circle nav-icon"></i>
                        <p>
                            Role
                        </p>
                    </a>
                </li>

                <li class="nav-item">
                    <a href="${request.contextPath}/userRole/" class="nav-link">
                        <i class="far fa-dot-circle nav-icon"></i>
                        <p>
                            User Role
                        </p>
                    </a>
                </li>

                <li class="nav-item">
                    <a href="${request.contextPath}/requestmap/" class="nav-link">
                        <i class="far fa-dot-circle nav-icon"></i>
                        <p>
                            Request Map
                        </p>
                    </a>
                </li>



                <li class="nav-header">Service</li>
                <li class="nav-item">
                    <a href="https://adminlte.io/docs/3.0" class="nav-link">
                        <i class="nav-icon fas fa-file"></i>
                        <p>Documentation</p>
                    </a>
                </li>
                <li class="nav-header">Sales</li>
                <li class="nav-item">
                    <a href="#" class="nav-link">
                        <i class="fas fa-circle nav-icon"></i>
                        <p>Level 1</p>
                    </a>
                </li>
                <li class="nav-item">
                    <a href="#" class="nav-link">
                        <i class="fas fa-circle nav-icon"></i>
                        <p>Level 1</p>
                    </a>
                </li>

            </ul>
        </nav>
        <!-- /.sidebar-menu -->
    </div>
    <!-- /.sidebar -->
</aside>