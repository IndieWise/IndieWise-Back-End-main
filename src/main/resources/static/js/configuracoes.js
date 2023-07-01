////////////////////////////////////////////////
//Modo vizualização
const folhaDeEstilo = document.querySelector(".folhaDeEstilo");

if(localStorage.getItem("modoVisualizacao") == 1 || localStorage.getItem("modoVisualizacao") == null){
  folhaDeEstilo.href = "css/style.css"
}else if(localStorage.getItem("modoVisualizacao") == 2){
  folhaDeEstilo.href = "css/Dark.css"
}else if(localStorage.getItem("modoVisualizacao") == 3){
  folhaDeEstilo.href = "css/Protanopia.css"
}else if(localStorage.getItem("modoVisualizacao") == 4){
  folhaDeEstilo.href = "css/Deuteranopia.css"
}else if(localStorage.getItem("modoVisualizacao") == 5){
  folhaDeEstilo.href = "css/Tritanopia.css"
}
const mudarModoVisualizacao = document.querySelector(".mudarModoVisualizacao");
//////////////////////////////////////////////////////////////
//Configurações
const logout = async () =>{
    localStorage.setItem('userId', "");
    localStorage.setItem('userName', "");
    localStorage.setItem('imageID', "");
    localStorage.setItem('imagenFundoId', "");
    window.location.href = 'login.html';
};
mudarModoVisualizacao.addEventListener("change",() =>{
    if(mudarModoVisualizacao.value == 1 || mudarModoVisualizacao.value == null){
        folhaDeEstilo.href = "css/style.css"
    }else if(mudarModoVisualizacao.value == 2){
      folhaDeEstilo.href = "css/Dark.css"
    }else if(mudarModoVisualizacao.value == 3){
        folhaDeEstilo.href = "css/Protanopia.css"
    }else if(mudarModoVisualizacao.value == 4){
      folhaDeEstilo.href = "css/Deuteranopia.css"
    }else if(mudarModoVisualizacao.value == 5){
      folhaDeEstilo.href = "css/Tritanopia.css"
    }
    localStorage.setItem("modoVisualizacao", mudarModoVisualizacao.value)
    console.log(mudarModoVisualizacao.value)
});


