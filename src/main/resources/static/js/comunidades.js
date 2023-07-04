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
const comunidadeContainer = document.querySelector(".comunidadesRecentes");
///////////////////////////////////
//Uso geral
const userId = localStorage.getItem('userId');
const userName = localStorage.getItem('userName');
const imageIdPerfil = localStorage.getItem('imageID');
const imageFundoId = localStorage.getItem('imagenFundoId');

const mudarInformacaoUsuarioCanto = () => {
    const informacaoUsuarioCanto = document.getElementById("informacaoUsuarioCanto");
    informacaoUsuarioCanto.firstElementChild.src = `/indiewise/image/${imageIdPerfil}`;
    informacaoUsuarioCanto.children[1].textContent = userName;
};
async function  salvarComunidadeLocal(event){
    event.stopPropagation();
    const communityId = String(event.currentTarget.id);
    const communityName = String(event.currentTarget.title);
    try {

      localStorage.setItem('communityId',communityId);
      localStorage.setItem('communityName', communityName);
      console.log("ID da comunidade salvo:",communityId);
      console.log("Nome da comunidade salvo:",communityName);
      window.location.href = 'comunidade.html';
    } catch (error) {
      console.log("Erro ao salvar o ID da comunidade:", error);
    }
};
///////////////////////////////////////////////////////////////
//Comunidades
const mostrarComunidades = async () => {
    comunidadeContainer.innerHTML = '';
    const url = "/indiewise/community";
    try {
      const response = await fetch(url, { method: 'GET' });
      if (response.ok) {
        const comunidades = await response.json();
        comunidades.forEach(comunidade => {
          let elementoComunidade = `<a class="cRecentes" onclick="salvarComunidadeLocal(event)" id ="${comunidade.id}" title="${comunidade.nome}">
          <img src="/indiewise/image/${comunidade.imageId}" alt="">
          <h4>${comunidade.nome}</h4>
          <p><i class="fa-solid fa-user"></i> 99 membros</p>
          <p><i class="fa-solid fa-chalkboard-user"></i>10 mentores</p>  
          </a>`;
      comunidadeContainer.insertAdjacentHTML('afterbegin', elementoComunidade);
          console.log("Comunidades carregadas", comunidades);
        });
      } else {
        console.log("Erro ao carregar comunidades", response.statusText);
      }
    } catch (error) {
      console.log("Erro ao carregar comunidades", error);
    }
};
////////////////////////////////////////////
//Chamando funções
mudarInformacaoUsuarioCanto();
mostrarComunidades();
