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
//////////////////////////////////
//Uso Geral
const postsContainerUsuarioAlheio = document.querySelector(".postsUserAlheio");
const imgCapaAlheia = document.querySelector(".capaAlheia");
const imgPerfilAlheia = document.querySelector(".fotoAlheia");
const nomeAlheio = document.querySelector(".nomeAlheio");
///////////////////////////////////////
//Usuario alheio
const carregarPostsUsuarioAlheio = async (id) => {
    const url = `http://localhost:8080/indiewise/user/post/${id}`;
    postsContainerUsuarioAlheio.innerHTML = ''; 
    try{
      const response = await fetch(url, {method: 'Get'});
      if(response.ok){
        posts = await response.json();
        posts.forEach(post => {
          let elementoPost = `<div class="post" id="${post.id}">
                     <div class="usuario">
                         <img src="http://localhost:8080/indiewise/image/${post.perfilImageId}" alt="">
                         <h3 class="nome" onclick="pegarIdUsuarioAlheio(event)" id="${post.userId}">${post.userName}</h3>
                         <p>${post.communityName}</p>
                         </div>
                         <div class="conteudo">
                         <p>${post.texto}</p>
                         <img src="http://localhost:8080/indiewise/image/${post.imageId}" alt="">
                         </div>
                         <div class="botoes">
                         <button type="button"><i class="fa-solid fa-brain"></i>Curtir</button>
                         <button type="button"><i class="fa-solid fa-comment"></i>Comentar</button>
                         </div>
                         </div>`;
          postsContainerUsuarioAlheio.insertAdjacentHTML('afterbegin', elementoPost);
          console.log("posts carregados");
      });
      }else{
          console.log("Erro ao carregar posts", response.statusText)
          }
      }catch(error){
        console.log("Erro ao carregar posts", error);
    }
};
const carregarPaginaAlheia = async(id) =>{
    urlGetUser =`http://localhost:8080/indiewise/usuario/${id}`;
    try{
      const response = await fetch(urlGetUser, {method: 'Get'});
      if(response.ok){
        const data = await response.json();
        imgCapaAlheia.src = `http://localhost:8080/indiewise/image/${data.imagenFundoId}`;
        imgPerfilAlheia.src = `http://localhost:8080/indiewise/image/${data.imageId}`;
        nomeAlheio.innerHTML = data.username;
        carregarPostsUsuarioAlheio(id);
      }else{
        console.log("erro ao carregar página", response.status);
      }
    }catch (error) {
      console.error("Erro ao enviar ao carregar página", error);
    }
};
carregarPaginaAlheia(localStorage.getItem('userIdAlheio'));