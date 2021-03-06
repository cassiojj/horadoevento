<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-br">

<head>
    <title>Hora do Evento</title>
    <c:import url="../../components/links.html" />
    <link rel="stylesheet" href="../home.css">
</head>

<body class="bodyCover">
    <c:import url="../../components/navbar/full.jsp" />

    <div>
        <c:if test='${sessao_user == null}'>
            <h1>Você não está logado</h1>
            <a href="/horadoevento/inicio/">Voltar ao início</a>
        </c:if>

        <c:if test='${sessao_user != null}'>
            <c:import url="../../components/marquee/homepage.jsp" />
            <c:import url="../../components/carousel/" />

            <div class="container">
                <h5 class="text-center">Missões que combinam com você</h5>

                <div class="row">
                    <div class="col-6 col-md-3 mt-2">
                        <button class="card-hde primary-card yellow-card">
                            Segurança de TI <br><small>20/06/2020 | 10:00</small>
                        </button>
                    </div>
                    <div class="col-6 col-md-3 mt-2">
                        <button class="card-hde primary-card yellow-card">
                           Campeonato de COD <br><small>05/07/2020 | 15:30</small>
                        </button>
                    </div>
                    <div class="col-6 col-md-3 mt-2">
                        <button class="card-hde primary-card yellow-card">
                            Desenvolvimento Mobile <br><small>14/09/2020 | 12:00</small>
                        </button>
                    </div>
                    <div class="col-6 col-md-3 mt-2">
                        <button class="card-hde primary-card yellow-card">
                            Procure por mais eventos
                        </button>
                    </div>
                </div>

                <h5 class="text-center mt-4">Categorias</h5>

                <div class="row pb-4" style="justify-content: space-evenly;">
					<c:forEach var="tag" items="${listaTagsTotais}">
						<form action="/horadoevento/pesquisa/Pesquisa.do" method="get">
							<input type="hidden" name="idTag" value="${tag.id}" />
							<button type="submit"
								class="card-hde pink-card"
								name="criterio" value="tag">${tag.nome}</button>
						</form>
					</c:forEach>
				</div>
            </div>
        </c:if>
    </div>
    <div style="position: absolute; bottom: 0; width: 100%;">    
        <c:import url="../../components/footer/" />
    </div>

    <script type="text/javascript" src="/horadoevento/components/bootstrap/js/jquery-3.5.1.min.js"></script>
    <script type="text/javascript" src="/horadoevento/components/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>
