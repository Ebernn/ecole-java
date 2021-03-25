<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<%@include file="/WEB-INF/views/common/head.jsp"%>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

    <%@ include file="/WEB-INF/views/common/header.jsp" %>
    <!-- Left side column. contains the logo and sidebar -->
    <%@ include file="/WEB-INF/views/common/sidebar.jsp" %>

    <!-- Content Wrapper. Contains page content -->
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
            <h1>
                Utilisateurs
                <a class="btn btn-primary" href="${pageContext.request.contextPath}/users/create">Ajouter</a>
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="row">
                <div class="col-md-12">
                    <div class="box">
                        <div class="box-body no-padding">
                            <table class="table table-striped">
                            	<tr>
                                    <th style="width: 10px">#</th>
                                    <th>Nom</th>
                                    <th>Prenom</th>
                                    <th>Email</th>
                                    <th>Naissance</th>
                                    <th>Action</th>
                                </tr>
                                <tr>
                                
                            	<c:forEach items="${users}" var="user">
                            		
                            		<!-- Modal -->
									<div class="modal fade" id="supprModal${user.id}" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
									  <div class="modal-dialog" role="document">
									    <div class="modal-content">
									      <div class="modal-header">
									        <h5 class="modal-title" id="exampleModalLabel">Confirmation de suppression</h5>
									      </div>
									      <div class="modal-body">
									        Es-tu sur de vouloir supprimer l'utilisateur ${user.id}?
									      </div>
									      <div class="modal-footer">
									        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
									        <a class="btn btn-primary" href="./users/delete?id=${user.id}">Confirmer</a>
									      </div>
									    </div>
									  </div>
									</div>
                            	
                            		<td>${user.id}</td>
                                    <td>${user.nom}</td>
                                    <td>${user.prenom}</td>
                                    <td>${user.email}</td>
                                    <td>${user.naissance}</td>
                                    <td>
                                        <a class="btn btn-primary" href="./users/details?id=${user.id}">
                                        	<i class="fa fa-play"></i>
                                        </a>
                                        <a class="btn btn-success" href="./users/edit?id=${user.id}">
                                            <i class="fa fa-edit"></i>
                                        </a>
                                        <a class="btn btn-danger" data-toggle="modal" data-target="#supprModal${user.id}">
										  	<i class="fa fa-trash"></i>
										</a>
                                    </td>
                            	</tr>
                            	</c:forEach>
                            </table>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
            </div>
        </section>
        <!-- /.content -->
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
</body>
</html>
