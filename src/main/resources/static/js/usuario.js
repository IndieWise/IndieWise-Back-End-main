////////////////////////////////////////////////
//Modo vizualização
const folhaDeEstilo = document.querySelector(".folhaDeEstilo");

if(localStorage.getItem("modoVisualizacao") == 1 || localStorage.getItem("modoVisualizacao") == null){
  folhaDeEstilo.href = "css/style.css"
}else if(localStorage.getItem("modoVisualizacao") == 2){
  ffolhaDeEstilo.href = "css/Dark.css"
}else if(localStorage.getItem("modoVisualizacao") == 3){
  folhaDeEstilo.href = "css/Protanopia.css"
}else if(localStorage.getItem("modoVisualizacao") == 4){
  folhaDeEstilo.href = "css/Deuteranopia.css"
}else if(localStorage.getItem("modoVisualizacao") == 5){
  folhaDeEstilo.href = "css/Tritanopia.css"
}
const postsContainerUsuario = document.querySelector(".postsUser")
const imgCapa = document.querySelector(".minhaCapa");
const imgPerfil = document.querySelector(".minhaFoto");
const meuNome = document.querySelector(".meuNome");
const informacaoUsuarioCanto = document.getElementById("informacaoUsuarioCanto");
//////////////////////
//Uso Geral
const userId = localStorage.getItem('userId');
const userName = localStorage.getItem('userName');
const imageIdPerfil = localStorage.getItem('imageID');
const imageFundoId = localStorage.getItem('imagenFundoId');
//usuario
const carregarPostsUsuario = async (id) => {
    const url = `/indiewise/user/post/${userId}`;
    postsContainerUsuario.innerHTML = ''; 
    try{
      const response = await fetch(url, {method: 'Get'});
      if(response.ok){
        posts = await response.json();
        posts.forEach(post => {
          let elementoPost = `<div class="post" id="${post.id}">
                     <div class="usuario">
                         <img src="/indiewise/image/${post.perfilImageId}" alt="">
                         <h3 class="nome" onclick="pegarIdUsuarioAlheio(event)" id="${post.userId}">${post.userName}</h3>
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
          postsContainerUsuario.insertAdjacentHTML('afterbegin', elementoPost);
          console.log("posts carregados");
      });
      }else{
          console.log("Erro ao carregar posts", response.statusText)
          }
      }catch(error){
        console.log("Erro ao carregar posts", error);
    }
};
const carregarMinhaPagina = async(id) =>{
    urlGetUser =`/indiewise/usuario/${id}`;
    try{
      const response = await fetch(urlGetUser, {method: 'Get'});
      if(response.ok){
        const data = await response.json();
        imgCapa.src = `/indiewise/image/${data.imagenFundoId}`;
        imgPerfil.src = `/indiewise/image/${data.imageId}`;
        meuNome.innerHTML = data.username;
        carregarPostsUsuario(id);
      }else{
        console.log("erro ao carregar página", response.status);
      }
    }catch (error) {
      console.error("Erro ao enviar ao carregar página", error);
    }
};
/////////////////////////////////
//Ativação de funções
carregarMinhaPagina(userId);