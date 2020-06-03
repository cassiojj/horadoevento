<%@ page language="java" contentType="text/html; charset=iso-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
    	<title>Evento: ${evento.titulo} </title>
        <c:import url="../../components/links.html"/>
        <link rel="stylesheet" href="../view/view.css"/>
    </head>
    
    <body>
    	<c:import url="../../components/modal/inscricao.jsp"/>
    	<c:import url="../../components/navbar/full.jsp"/>
    	
    	<div class="bodyCover">
    		<c:if test='${sessao_user == null}'>
				<h1>Voc� n�o est� logado</h1>
				<a href="/horadoevento/inicio/">Voltar ao in�cio</a>
			</c:if>
			<c:if test='${sessao_user != null}'>
		    	<div class="bodyContent">
		    		<div class="container">
			    		<div class="row">
			    			<div class="col-12 col-md-6 evento-container">
			    			<c:out value="${evento.id}"/>
			    				<h3>${evento.titulo}</h3>
			    				<small>${evento.palestrante}</small>
			    				<p>${evento.descricao}</p>
			    				<!-- continuar exibi��o -->
			    			</div>
			    		</div>
			    	</div>
			    	
			    	<div class="row">
			    		<div class="col-12">
			    			<h5>Tags relacionadas</h5>
			    			<c:forEach var="tags" items=""></c:forEach><%! //TODO tem que ver ! %>
			    		</div>
			    	</div>
			    	
			    	<c:if test='${tipo_entidade == "usuario"}'>
			    	    <button type="button" data-toggle="modal" data-target="#modalInscricao">Inscrever-se</button>
			    	</c:if>
			    	
			    	<c:if test='${tipo_entidade == "empresa"}'>
                        <button type="button" data-toggle="modal" data-target="#modalDeletar">Deletar evento</button>
                        <form action="/Evento.do" method="post">
                            <input type="hidden" name="id" value="${evento.id}"/>
                            <button type="submit" name="acao" value="editar">Editar</button>
                        </form>
                    </c:if>
			    	
			    </div>
			</c:if>
		</div>
    	
		<c:import url="../../components/footer/"/>

		<script type="text/javascript" src="/horadoevento/components/bootstrap/js/jquery-3.5.1.min.js"></script>
		<script type="text/javascript" src="/horadoevento/components/bootstrap/js/bootstrap.min.js"></script>
 	</body>
</html>