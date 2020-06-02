<%@ page language="java" contentType="text/html; charset=iso-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
    	<title>Perfil ${empresa.getNome()} </title>
        <c:import url="../../components/links.html"/>
        <link rel="stylesheet" href="../view/view.css"/>
    </head>
    
    <body>
    	<c:import url="../../components/navbar/full.jsp"/>
    	<div class="bodyContent">
<<<<<<< HEAD
	    	<div class="container">
	    		<div class="row">
	    			<div class="col-12 col-md-6">
	    				<h3>${empresa.getNome()}</h3>
	     				<small> ${empresa.getUserName()} </small>
		     			<a href="${empresa.getLinkedin()}" target="_blank">LINKEDIN</a><%!//TODO icon linkedin /!\%>
	    			</div>
	    		</div>
	    	</div>
			
	    	<div class="row">
	    		<div class="col-12">
	    			<h5>Pr�ximos eventos</h5>
	    			<%! //TODO exibi��o arraylist bot�es eventos %>
	    			<%! //TODO pesquisa join com eventos %>
	    		</div>
	    	</div>
	    	
	    	<div class="row">
	    		<div class="col-12">
	    			<h5>�ltimos eventos</h5>
	    			<%! //TODO exibi��o bot�es link evento %>
	    			<%! //TODO %>
	    		</div>
	    	</div>
=======
    		<c:if test='${sessao_user == null}'>
				<h1>Voc� n�o est� logado</h1>
				<a href="/horadoevento/inicio/">Voltar ao in�cio</a>
			</c:if>
			<c:if test='${sessao_user != null}'>
		    	<div class="container">
		    		<div class="row">
		    			<div class="col-12 col-md-6">
		    				<%!//TODO foto /!\%>
		    			</div>
		    			<div class="col-12 col-md-6">
		    				<h3>${empresa.getNome()}</h3>
		     				<small> ${empresa.getUserName()} </small>
			     			<a href="${empresa.getLinkedin()}">LINKEDIN</a><%!//TODO icon linkedin /!\%>
		    			</div>
		    		</div>
		    	</div>
				
		    	<div class="row">
		    		<div class="col-12">
		    			<h5>Pr�ximos eventos</h5>
		    			<%! //TODO exibi��o arraylist bot�es eventos %>
		    			<%! //TODO pesquisa join com eventos %>
		    		</div>
		    	</div>
		    	
		    	<div class="row">
		    		<div class="col-12">
		    			<h5>�ltimos eventos</h5>
		    			<%! //TODO exibi��o bot�es link evento %>
		    			<%! //TODO %>
		    		</div>
		    	</div>
	    	</c:if>
>>>>>>> refs/remotes/origin/master
	    </div>

     	<c:import url="../../components/footer/"/>
     	
		<script type="text/javascript" src="/horadoevento/components/bootstrap/js/jquery-3.5.1.min.js"></script>
		<script type="text/javascript" src="/horadoevento/components/bootstrap/js/bootstrap.min.js"></script>
     	
 	</body>
</html>