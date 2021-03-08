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
                    <h4>Suppression d'un v&eacute;hicule</h4>
                    <div class="box">
                        <!-- form start -->
                        <!-- Le  type de methode http qui sera appel� lors de action submit du formulaire -->
                        <!-- est d�crit an l'attribut "method" de la balise forme -->
                        <!-- action indique � quel "cible" sera envoyr la requ�te, ici notre Servlet qui sera bind sur -->
                        <!-- /vehicles/delete -->
                        <form class="form-horizontal" method="post">
                            <div class="box-body">
                            	Es-tu sur de vouloir supprimer ce v&eacute;hicule ? (cette action est irr&eacute;versible)
                                <table class="table table-striped">
								    <thead>
								        <tr>
								            <th colspan="2">R&eacute;sum&eacute; du v&eacute;hicule &agrave; supprimer</th>
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
                                <input type="hidden" class="form-control" id="confirm" name="confirm" value="true"></input>
                                <input type="hidden" class="form-control" id="id" name="id" value="${vehicle.id}"></input>
                            </div>
                            <!-- /.box-body -->
                            <div class="box-footer">
                                <button type="submit" class="btn btn-info pull-right">Confirmer</button>
                            </div>
                            <!-- /.box-footer -->
                        </form>
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
