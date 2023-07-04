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
const imageCadastroProfessor = document.getElementById("imageCadastroProfessor");
const adquiriuConhecimentoCadastroProfessor = document.getElementById("adquiriuConhecimentoCadastroProfessor");
const seuConhecimentoCadastroProfessor = document.getElementById("seuConhecimentoCadastroProfessor");
const valorAulaCadastroProfessor = document.getElementById("valor");

///////////////////////////////////
//Uso geral
const userId = localStorage.getItem('userId');
const userName = localStorage.getItem('userName');
const imageIdPerfil = localStorage.getItem('imageID');
const imageFundoId = localStorage.getItem('imagenFundoId');
/////////////////////////////////
//Form Prof 2
async function formProf2() {
  localStorage.setItem("adquiriuConhecimentoCadastroProfessor", adquiriuConhecimentoCadastroProfessor.value);
  localStorage.setItem("seuConhecimentoCadastroProfessor", seuConhecimentoCadastroProfessor.value);
  localStorage.setItem("valorAulaCadastroProfessor", valorAulaCadastroProfessor.value);
  window.location.href = 'formProf3.html';
}