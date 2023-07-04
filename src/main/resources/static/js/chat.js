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

let menu = document.querySelector('#abrirConversa');
let navbar = document.querySelector('.mensagens');
let caixaChat = document.querySelector('.caixaChat');
let contato = document.querySelector('.contato');

menu.onclick = () => {
    caixaChat.classList.toggle('active');
};

contato.onclick = () => {
    caixaChat.classList.remove('active');
};

