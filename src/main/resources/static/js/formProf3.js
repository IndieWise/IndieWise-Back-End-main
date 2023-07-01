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
const tituloCadastroProfessor = document.getElementById("tituloCadastroProfessor");
const descricaoCadastroProfessor = document.getElementById("descricaoCadastroProfessor");
const enviarCadastroProfessor = document.getElementById("enviarCadastroProfessor");
//////////////////////////
//Uso geral
const userId = localStorage.getItem('userId');
const userName = localStorage.getItem('userName');
const imageIdPerfil = localStorage.getItem('imageID');
const imageFundoId = localStorage.getItem('imagenFundoId');
////////////////////
//Virar Professor
async function virarProfessor() {
    localStorage.setItem("tituloCadastroProfessor", tituloCadastroProfessor.value);
    localStorage.setItem("descricaoCadastroProfessor", descricaoCadastroProfessor.value);
    const urlVirarProf = `http://localhost:8080/indiewise/usuario/${userId}`;
    const profDto = {
      "conhecimento": localStorage.getItem("seuConhecimentoCadastroProfessor"),
      "comoAdquiriu": localStorage.getItem("adquiriuConhecimentoCadastroProfessor"),
      "precoAula": localStorage.getItem("valorAulaCadastroProfessor"),
      "tituloPerfil": localStorage.getItem("tituloCadastroProfessor"),
      "descricaoPerfil": localStorage.getItem("descricaoCadastroProfessor")
    };
    try {
      const response = await fetch(urlVirarProf, {
        method: 'PUT',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(profDto)
      });
      if (response.ok) {
        const data = await response.json();
        console.log("Olá novo Professor", data);
        const professorIdLocal = localStorage.setItem("professorId", userId);
        window.location.href = 'professor.html';
      } else {
        console.log("Falha ao cadastrar", response.status);
      }
    } catch (error) {
      console.log("Falha ao cadastrar", error);
    }
};