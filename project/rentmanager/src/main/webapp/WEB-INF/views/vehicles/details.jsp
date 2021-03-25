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
                Voitures
            </h1>
        </section>
        
        <!-- Main content -->
        <section class="content">
		<h4>D&eacute;tails du v&eacute;hicule</h4>
            <div class="row">
                <div class="col-md-3">

                    <!-- Profile Image -->
                    <div class="box box-primary">
                        <div class="box-body box-profile">
                        	<table class="table table-striped">
							    <thead>
							        <tr>
							            <th colspan="2">R&eacute;sum&eacute; du v&eacute;hicule</th>
							        </tr>
							    </thead>
							    <tbody>
							        <tr>
							            <td>Identifiant</td>
							            <td>${vehicle.id}</td>
							        </tr>
							      	<tr>
							            <td>Constructeur</td>
							            <td>${vehicle.constructeur}</td>
							        </tr>
							        <tr>
							            <td>Mod&egrave;le</td>
							            <td>${vehicle.modele}</td>
							        </tr>
							        <tr>
							            <td>Nombre de places</td>
							        	<td>${vehicle.nb_places}</td>
								     </tr>
								 </tbody>
							</table>
                            <ul class="list-group list-group-unbordered">
                                <li class="list-group-item">
                                    <b>Utilisateur(s)</b> <a class="pull-right">${countu}</a>
                                </li>
                                <li class="list-group-item">
                                    <b>Reservation(s)</b> <a class="pull-right">${countr}</a>
                                </li>
                            </ul>
                        </div>
                        <!-- /.box-body -->
                    </div>
                    <!-- /.box -->
                </div>
                <!-- /.col -->
                <div class="col-md-9">
                    <div class="nav-tabs-custom">
                        <ul class="nav nav-tabs">
                            <li class="active"><a href="#users" data-toggle="tab">Utilisateur(s)</a></li>
                            <li><a href="#rents" data-toggle="tab">Reservation(s)</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="active tab-pane" id="users">
                                <div class="box-body no-padding">
                                    <table class="table table-striped">
                                    	<tr>
                                            <th style="width: 10px">#</th>
                                            <th>Nom</th>
                                            <th>Prenom</th>
                                            <th>Email</th>
                                            <th>Naissance</th>
                                        </tr>
	                                    <tr>
	
		                                <c:forEach items="${users}" var="user">
		                                    <td>${user.id}.</td>
		                                    <td>${user.nom}</td>
		                                    <td>${user.prenom}</td>
		                                    <td>${user.email}</td>
		                                    <td>${user.naissance}</td>
		                                </tr>
		                                </c:forEach>
                                    </table>
                                </div>
                            </div>
                            <!-- /.tab-pane -->
                            <div class="tab-pane" id="rents">
                                <!-- /.box-header -->
                                <div class="box-body no-padding">
                                    <table class="table table-striped">
                                       	<tr>
                                            <th style="width: 10px">#</th>
                                            <th>Identifiant client</th>
                                            <th>Debut</th>
                                            <th>Fin</th>
                                        </tr>
	                                    <tr>
	
		                                <c:forEach items="${reservations}" var="reservation">
		                                    <td>${reservation.id}.</td>
		                                    <td>${reservation.clientId}</td>
		                                    <td>${reservation.debut}</td>
		                                    <td>${reservation.fin}</td>
		                                </tr>
		                                </c:forEach>
                                    </table>
                                </div>
                            </div>
                            <!-- /.tab-pane -->
                        </div>
                        <!-- /.tab-content -->
                    </div>
                    <!-- /.nav-tabs-custom -->
                </div>
                <!-- /.col -->
            </div>
            <!-- /.row -->

        </section>
        <!-- /.content -->
    </div>

    <%@ include file="/WEB-INF/views/common/footer.jsp" %>
</div>
<!-- ./wrapper -->

<%@ include file="/WEB-INF/views/common/js_imports.jsp" %>
</body>
</html>
