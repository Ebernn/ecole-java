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
            <div class="row">
                <div class="col-md-12">
                    <!-- Horizontal Form -->
                    <span>
	                    <h4>R&eacute;servations associ&eacute;s au v&eacute;hicule</h4>
	                    <c:forEach items="${reservations}" var="reservation">
	                    	<tr>
	                            <td>${reservation.id}</td>
	                            <td>${reservation.clientId}</td>
	                            <td>${reservation.vehicleId}</td>
	                            <td>${reservation.debut}</td>
	                            <td>${reservation.fin}</td>
	                        </tr>
	                    </c:forEach>
                    </span>
                    <h4>D&eacute;tails d'un v&eacute;hicule</h4>
                    <div class="box">
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
