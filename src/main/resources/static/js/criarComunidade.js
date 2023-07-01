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
const inputFileCriarComunidade = document.getElementById('input-file');
const descricaoCriarComunidade = document.getElementById('descricaoCriarComunidade');
const nomeCriarComunidade = document.getElementById("nomeCriarComunidade");
const buttonComunidade = document.getElementById("botaoComunidade");
////////////////////////////////////////////////
//Uso geral
const userId = localStorage.getItem('userId');
const userName = localStorage.getItem('userName');
const imageIdPerfil = localStorage.getItem('imageID');
const imageFundoId = localStorage.getItem('imagenFundoId');
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
////////////////////////////////////////////////
//criar Comunidade
buttonComunidade.addEventListener('click', async () => {
    const urlCriarComunidades = "/indiewise/community"
    const imageFile = inputFileCriarComunidade.files[0];
    let imageId = null;
    if (imageFile) {
      imageId = await uploadImageAndGetId(imageFile);
    }
    const communityDto = {
      "userId":userId,
      "userName":userName,
      "nome": nomeCriarComunidade.value,
      "descricao": descricaoCriarComunidade.value,
      "imageId": imageId
    }
    try {
      const response = await fetch(urlCriarComunidades, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(communityDto)
      });
      if (response.ok) {
        const data = await response.json();
        localStorage.setItem('communityId',data.id);
        localStorage.setItem('communityName', data.nome);
        window.location.href = 'comunidade.html';
        console.log("Comunidade criada", data);
      } else {
        console.log("Falha ao criar comunidade", response.status);
      }
    } catch (error) {
      console.log("Falha ao criar comunidade", error);
    }
});
