<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
 <!-- Left side column. contains the logo and sidebar -->
  <aside class="main-sidebar">

    <!-- sidebar: style can be found in sidebar.less -->
    <section class="sidebar">

      <!-- Sidebar user panel (optional) -->
      <div class="user-panel">
        <div class="pull-left image">
          <img src="<c:url value="/resources/dist/img/user2-160x160.jpg" />" class="img-circle" alt="User Image">
        </div>
        <div class="pull-left info">
          <p>Alexander Pierce</p>
          <!-- Status -->
          <a href="#"><i class="fa fa-circle text-success"></i> Online</a>
        </div>
      </div>

      <!-- Sidebar Menu -->
      <ul class="sidebar-menu" data-widget="tree">
        <li class="header">Action</li>
        <!-- Optionally, you can add icons to the links -->
        <li class="active"><a href="<c:url value="/admin/" />"><i class="fa fa-columns"></i> <span>Dashboard</span></a></li>
        <li><a href="<c:url value="/admin/administrators" />"><i class="fa fa-cog"></i> <span>Administrators</span></a></li>
        <li><a href="<c:url value="/admin/employers" />"><i class="fa fa-building"></i> <span>Companies</span></a></li>
        <li><a href="<c:url value="/admin/users" />"><i class="fa fa-user"></i> <span>Users</span></a></li>
        <li><a href="<c:url value="/admin/skillsets" />"><i class="fa fa-wrench"></i> <span>Skills</span></a></li>
      </ul>
      <!-- /.sidebar-menu -->
    </section>
    <!-- /.sidebar -->
  </aside>