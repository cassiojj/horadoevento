<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="pt-br">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no">
        <title>login</title>
        <link rel="stylesheet" href="./login.css">
    </head>
    
    <body>
        <input type="button" value="Voltar" onClick="history.go(-1)"> 

        <div class="cover">
            <div>
                <img src="https://static3.tcdn.com.br/img/img_prod/460977/boneco_bb8_12_cm_star_wars_despertar_da_forca_ep_7_the_force_awekens_hasbro_31877_1_20180406124654.jpg" alt="iconeTeste">

                <form action="Login.do" method="post">
                    <input name="idEmail" placeholder="id ou email" type="text">
                    <input name="senha" placeholder="senha" type="password">
                    <div>
                        <button type="submit">Login</button>
                    </div>
                </form>
            </div>
        </div>
        
    </body>
</html>