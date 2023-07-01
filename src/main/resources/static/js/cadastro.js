const nomeForm = document.querySelector(".nomeForm");
const emailForm = document.querySelector(".emailForm");
const senhaForm = document.querySelector(".senhaForm");
const confirmarSenhaForm = document.querySelector(".confirmarSenhaForm");
const fotoPerfilForm = document.querySelector(".fotoPerfilForm");
const fotoCapaForm = document.querySelector(".fotoCapaForm");
const btnCadastro = document.querySelector(".btnCadastro");

//Upload de imagens
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
  }
///////////////////////////////////
//Cadastro
const cadastrarUsuario = async () =>{
    const url = "/indiewise/cadastro";
        const fotoCapa = fotoCapaForm.files[0];
        const fotoPerfil = fotoPerfilForm.files[0]; 
        let imageIdPerfilCadastro = null;
        let imageIdCapaCadastro = null;
        if (fotoCapa) {
          imageIdCapaCadastro = await uploadImageAndGetId(fotoCapa);
        }
        if (fotoPerfil) {
          imageIdPerfilCadastro = await uploadImageAndGetId(fotoPerfil);
        }
    const userDto = {
      "imageId": imageIdPerfilCadastro,
      "imagenFundoId":imageIdCapaCadastro,
      "username": nomeForm.value,
      "email": emailForm.value,
      "password": senhaForm.value,
      "confirmPassword":confirmarSenhaForm.value
    }
    try{
      const response = await fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(userDto)
      });
      if(response.ok){
        const data = await response.json();
        localStorage.setItem('userId',data.id);
        localStorage.setItem('userName', data.username);
        localStorage.setItem('imageID', data.imageId);
        localStorage.setItem('imagenFundoId', data.imagenFundoId);
        console.log("Cadastrado com sucesso");
        window.location.href = 'home.html';
      }else{
        console.log("Falha ao enviar", response.statusText)
      }
    }catch(error){
      console.log("Falha ao enviar", error)
    }
  
};
const userId = localStorage.getItem('userId');
const userName = localStorage.getItem('userName');
const imageIdPerfil = localStorage.getItem('imageID');
const imageFundoId = localStorage.getItem('imagenFundoId');
  