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
const postsContainerComunidade = document.querySelector('.posts');
const imgHeaderComunidade = document.getElementById("headerComunidade");
//Post
const buttonPost = document.querySelector(".buttonPost");
const inputPost = document.querySelector(".inputPost"); 
///////////////////////////////////////////
//Uso Geral
const userId = localStorage.getItem('userId');
const userName = localStorage.getItem('userName');
const imageIdPerfil = localStorage.getItem('imageID');
const imageFundoId = localStorage.getItem('imagenFundoId');
const savedCommunityId = localStorage.getItem('communityId');
const savedCommunityName = localStorage.getItem('communityName');
const mudarInformacaoUsuarioCanto = () => {
    const informacaoUsuarioCanto = document.getElementById("informacaoUsuarioCanto");
    informacaoUsuarioCanto.firstElementChild.src = `/indiewise/image/${imageIdPerfil}`;
    informacaoUsuarioCanto.children[1].textContent = userName;
};
async function uploadImageAndGetId(file) {
    const urlImage = "/indiewise/image";
    try {
      const formData = new FormData();
      formData.append("file", file);
  
      const response = await fetch(urlImage, {
        method: "POST",
        body: formData
      });
  
      if (response.ok) {
        const data = await response.text();
        console.log("Imagem enviada com sucesso. ID:", data);
        return data;
      } else {
        console.error("Erro ao enviar a imagem. Status:", response.status);
      }
    } catch (error) {
      console.error("Erro ao enviar a imagem:", error);
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
////////////////////////////////////////
//comunidade
const mostrarPostsComunidade = async (communidadeId) => {
const url = `/indiewise/community/${communidadeId}/post`;
postsContainerComunidade.innerHTML = ''; 
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
      postsContainerComunidade.insertAdjacentHTML('afterbegin', elementoPost);
      console.log("posts carregados");
  });
  }else{
      console.log("Erro ao carregar posts", response.statusText)
      }
  }catch(error){
    console.log("Erro ao carregar posts", error);
}
}
async function carregarComunidade(id){
      urlComunity =`/indiewise/community/${id}`;
      try{
        const response = await fetch(urlComunity, {method: 'Get'});
        if(response.ok){
          const data = await response.json();
          imgHeaderComunidade.src = `/indiewise/image/${data.imageId}`
          mostrarPostsComunidade(id);
        }else{
          console.log("erro ao carregar página", response.status);
        }
      }catch (error) {
        console.error("Erro ao enviar ao carregar página", error);
      }
    }

//PopUp de post
function aparecerPopup() {
    let popup = document.getElementById('popUp-comunidade');
    let html = document.documentElement;
  
    html.classList.add('popup-active');
    popup.style.display = 'flex';
  }
  
  function desaparecerPopup() {
    let popup = document.getElementById('popUp-comunidade');
    let html = document.documentElement;
  
    html.classList.remove('popup-active');
    popup.style.display = 'none';
  }

//Cria posts
buttonPost.addEventListener("click", async() =>{
    const url = "/indiewise/post";
    const fileInput = document.getElementById("myFiles");
    const imageFile = fileInput.files[0];
  
    let imageId = null;
    if (imageFile) {
      imageId = await uploadImageAndGetId(imageFile);
    }
    const postDto = {
      "perfilImageId": imageIdPerfil,
      "userId":userId,
      "userName":userName,
      "texto":inputPost.value,
      "communityId":savedCommunityId,
      "imageId": imageId,
      "communityName":savedCommunityName
    }
    try{
      const response = await fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(postDto)
      });
      if(response.ok){
        const data = await response.json();
        console.log("Post enviado com sucesso", data);
        desaparecerPopup();
        await mostrarPostsComunidade(savedCommunityId);
      }else{
        console.log("Falha ao enviar", response.statusText)
      }
    }catch(error){
      console.log("Falha ao enviar", error)
    }
      inputPost.value = ''
});

//Ativação de funções
carregarComunidade(savedCommunityId);
mudarInformacaoUsuarioCanto();

//sei lá tbm