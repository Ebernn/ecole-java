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
            </h1>
        </section>
        
        <!-- Main content -->
        <section class="content">
		<h4>D&eacute;tails de l'utilisateur</h4>
            <div class="row">
                <div class="col-md-3">

                    <!-- Profile Image -->
                    <div class="box box-primary">
                        <div class="box-body box-profile">
                        	
                        	<table class="table table-striped">
							    <thead>
							        <tr>
							            <th colspan="2">R&eacute;sum&eacute; de l'utilisateur</th>
							        </tr>
							    </thead>
							    <tbody>
							        <tr>
							            <td>Identifiant</td>
							            <td>${user.id}</td>
							        </tr>
							      	<tr>
							            <td>Nom complet</td>
							            <td>${user.prenom} ${user.nom}</td>
							        </tr>
							        <tr>
							            <td>Email</td>
							            <td>${user.email}</td>
							        </tr>
							        <tr>
							            <td>Date de naissance</td>
							            <td>${user.naissance}</td>
							        </tr>
								 </tbody>
							</table>
                            <ul class="list-group list-group-unbordered">
                                <li class="list-group-item">
                                    <b>Vehicule(s)</b> <a class="pull-right">${countv}</a>
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
                            <li class="active"><a href="#rents" data-toggle="tab">Reservations</a></li>
                            <li><a href="#cars" data-toggle="tab">Voitures</a></li>
                        </ul>
                        <div class="tab-content">
                            <div class="active tab-pane" id="rents">
                                <div class="box-body no-padding">
                                    <table class="table table-striped">
                                        <tr>
                                            <th style="width: 10px">#</th>
                                            <th>Voiture</th>
                                            <th>Debut</th>
                                            <th>Fin</th>
                                        </tr>
	                                    <tr>
	
		                                <c:forEach items="${reservations}" var="reservation">
		                                    <td>${reservation.id}.</td>
		                                    <td>${reservation.vehicleId}</td>
		                                    <td>${reservation.debut}</td>
		                                    <td>${reservation.fin}</td>
		                                </tr>
		                                </c:forEach>
                                    </table>
                                </div>
                            </div>
                            <!-- /.tab-pane -->
                            <div class="tab-pane" id="cars">
                                <!-- /.box-header -->
                                <div class="box-body no-padding">
                                    <table class="table table-striped">
                                        <tr>
                                            <th style="width: 10px">#</th>
                                            <th>Modele</th>
                                            <th>Constructeur</th>
                                            <th style=>Nombre de places</th>
                                        </tr>
                                        <tr>
                                        
                                        <c:forEach items="${vehicles}" var="vehicle">
		                                    <td>${vehicle.id}.</td>
		                                    <td>${vehicle.modele}</td>
		                                    <td>${vehicle.constructeur}</td>
		                                    <td>${vehicle.nb_places}</td>
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
