const senhaLogin = document.querySelector(".senhaLogin");
const emailLogin = document.querySelector(".emailLogin");
const btnLogin = document.querySelector(".btnLogin");
//////////////////////////////////////////////////
/////////////////////////////
//Login
const login = async () =>{ 
    const urlf = "/indiewise/login"; //url de vcs
    const userDto = {
      "username": emailLogin.value, //Passar os parametros do seu banco
      "password": senhaLogin.value,//pegar o value dos inputs do html de vcs
    }
    try{
      const response = await fetch(urlf, {
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
        console.log("Logado com sucesso", data);
        window.location.href = 'home.html'; //Encaminhar para a home de vcs
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