<%@ page language="java" contentType="text/html; charset=iso-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<title>Inscri��es</title>
<c:import url="../../components/links.html" />
<link rel="stylesheet" href="../view/view.css">
</head>

<body>
	<c:import url="../../components/navbar/full.jsp" />
	<c:import url="../../components/modal/inscricaoView.jsp"/>
	<c:import url="../../components/modal/inscricaoDelete.jsp"/>

	<div class="bodyCover">
		<c:if test='${sessao_user == null}'>
			<h1>Voc� n�o est� logado</h1>
			<a href="/horadoevento/inicio/">Voltar ao in�cio</a>
		</c:if>
		<c:if test='${sessao_user != null}'>
			<div class="row">
				<div class="col-12 align-self-center">
					<h3 style="text-align: center;">${sessao_user.nome}, aqui est�o
						suas inscri��es:</h3>
				</div>
				<div class="col-6">
					<div class="table-responsive col-md-12">
						<table class="table table-striped">
							<thead>
								<th>ID</th>
								<th>Evento</th>
								<th class="actions">A��es</th>
							</thead>
							<tbody>
								<c:if test="${not empty inscricoes}">
									<c:forEach var="inscricao" items="${inscricoes}">
										<tr>
											<td>${inscricao.id}</td>
											<td>${inscricao.evento.titulo}</td>
											<td class="actions">
												<button id="${inscricao.id}" type="button"
                                                    class="btn btn-primary btn-xs" data-toggle="modal"
                                                    data-target="#modalInscricaoView" data-inscricao="${inscricao.id}">
                                                    Visualizar</button>
                                                    
												<button id="${inscricao.id}" type="button"
													class="btn btn-danger btn-xs" data-toggle="modal"
													data-target="#delete-modal" data-pais="${inscricao.id}">
													Excluir</button>
											</td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>

					</div>
				</div>
			</div>
		</c:if>
	</div>

	<c:import url="../../components/footer/" />

	<script type="text/javascript"
		src="/horadoevento/components/bootstrap/js/jquery-3.5.1.min.js"></script>
	<script type="text/javascript"
		src="/horadoevento/components/bootstrap/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		$("#modalInscricaoView").on('show.bs.modal', function(event) {
			var button = $(event.relatedTarget); //botao que disparou a modal
			var recipient = button.data('inscricao');
			$("#id").val(recipient);
		});
	</script>

</body>
</html>