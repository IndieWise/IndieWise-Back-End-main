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

const postsContainerHome = document.querySelector('.feed');
const comunidadeContainerHome = document.querySelector('.comunidades');
///////////////////////////////////
//Uso geral
const userId = localStorage.getItem('userId');
const userName = localStorage.getItem('userName');
const imageIdPerfil = localStorage.getItem('imageID');
const imageFundoId = localStorage.getItem('imagenFundoId');

// const folhaDeEstilo = document.querySelector('.folhaDeEstilo');
// folhaDeEstilo.href

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
const pegarIdUsuarioAlheio = async (event) =>{
    const userIdAlheio= String(event.currentTarget.id);
    try {
      localStorage.setItem('userIdAlheio',userIdAlheio);
      window.location.href = 'usuarioAlheio.html';
    } catch (error) {
      console.log("Erro ao salvar id do usuario:", error);
    }
};
///////////////////////////////////////////////////////////////
//Home
const mostrarComunidadesHome = async () => {
    comunidadeContainerHome.innerHTML = '';
    const url = "/indiewise/community";
    try {
      const response = await fetch(url, { method: 'GET' });
      if (response.ok) {
        const comunidades = await response.json();
        comunidades.forEach(comunidade => {
          let elementoComunidade = `<img src="/indiewise/image/${comunidade.imageId}"  onclick="salvarComunidadeLocal(event)" id="${comunidade.id}" title="${comunidade.nome}" class="destaque">`;
          comunidadeContainerHome.insertAdjacentHTML('afterbegin', elementoComunidade);
          console.log("Comunidades carregadas", comunidades);
        });
      } else {
        console.log("Erro ao carregar comunidades", response.statusText);
      }
    }catch (error) {
      console.log("Erro ao carregar comunidades", error);
    }
};
const mostrarPosts = async () => {
    postsContainerHome.innerHTML = ''; 
    const url = "/indiewise/post";
    try{
      const response = await fetch(url, {method: 'Get'});
      if(response.ok){
        posts = await response.json();
         posts.forEach(post => {
        let elementoPost = `<div class="post" id="${post.id}">
                      <div class="usuario">
                          <img src="/indiewise/image/${post.perfilImageId}" alt="">
                          <h3 class="nome" onclick="pegarIdUsuarioAlheio(event)"id="${post.userId}">${post.userName}</h3>
                          <p>${post.communityName}</p>
                          </div>
                          <div class="conteudo">
                          <p>${post.texto}</p>
                          <img src="/indiewise/image/${post.imageId}" alt="">
                          </div>
                          <div class="botoes">
                          <button type="button"><i class="fa-solid fa-brain"></i>Curtir</button>
                          <button type="button"><i class="fa-solid fa-comment"></i>Comentar</button>
                         </div>
                          </div>`;
        postsContainerHome.insertAdjacentHTML('afterbegin', elementoPost);
        console.log("Sucesso ao carregar");
        });
      }else{
          console.log("Erro ao carregar posts", response.statusText)
          }
      }catch(error){
        console.log("Erro ao carregar posts", error);
    }
};
////////////////////////////////////////////
//Chamando funções
mudarInformacaoUsuarioCanto();
mostrarPosts();
mostrarComunidadesHome();