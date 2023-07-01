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
const imgProfessor = document.getElementById("imgProfessor");
const nomeProfessor = document.getElementById("nomeProfessor");
const conhecimentoProfessor = document.getElementById("conhecimentoProfessor");
const descricaoProfessor = document.getElementById("descricaoTextoProfessor");
const tituloTextoProfessor = document.getElementById("tituloTextoProfessor");
const precoAulaProfessor = document.getElementById("precoAulaProfessor");
//////////////////
//Uso geral
///////////////////////////////
const userId = localStorage.getItem('userId');
const userName = localStorage.getItem('userName');
const imageIdPerfil = localStorage.getItem('imageID');
const imageFundoId = localStorage.getItem('imagenFundoId');
const professorIdLocal = localStorage.getItem("professorId");
const mudarInformacaoUsuarioCanto = () => {
    const informacaoUsuarioCanto = document.getElementById("informacaoUsuarioCanto");
    informacaoUsuarioCanto.firstElementChild.src = `/indiewise/image/${imageIdPerfil}`;
    informacaoUsuarioCanto.children[1].textContent = userName;
};
//////////////////////////////////////////
//Professor
const carregarPaginaProfessor = async(professorIdLocal) =>{
    urlGetUser =`/indiewise/usuario/${professorIdLocal}`;
    try{
      const response = await fetch(urlGetUser, {method: 'Get'});
      if(response.ok){
        const data = await response.json();
        imgProfessor.src = `/indiewise/image/${data.imageId}`;
        conhecimentoProfessor.innerHTML = `Ensina ${data.conhecimento}`;
        nomeProfessor.innerHTML = data.username;
        descricaoProfessor.innerHTML = data.descricaoPerfil;
        tituloTextoProfessor.innerHTML = data.tituloPerfil;
        precoAulaProfessor.innerHTML = data.precoAula;
      }else{
        console.log("erro ao carregar página", response.status);
      }
    }catch (error) {
      console.error("Erro ao enviar ao carregar página", error);
    }
};
//Ativar funções
carregarPaginaProfessor(professorIdLocal);
mudarInformacaoUsuarioCanto();