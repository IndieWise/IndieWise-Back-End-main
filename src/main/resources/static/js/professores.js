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
const verticalProf = document.querySelector(".vertical");
////////////////////////////////////////////////
//Uso Geral
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
async function  salvarprofessorLocal(event){
    event.stopPropagation();
    const professorId = String(event.currentTarget.id);
    try {
      localStorage.setItem('professorId',professorId);
      window.location.href = 'professor.html';
    } catch (error) {
      console.log("Erro ao salvar o ID do professor:", error);
    }
};
////////////////////////////////////////
//Professores
async function carregarProfessores() {
    const urlProf = `/indiewise/professores`;
    verticalProf.innerHTML = '';
    try {
      const response = await fetch(urlProf, { method: 'Get' });
      if (response.ok) {
        professores = await response.json();
        professores.forEach(professor => {
          let elementoProfessor = ` <div class="professor" onclick="salvarprofessorLocal(event)"id="${professor.id}">
            <img src="/indiewise/image/${professor.imageId}" alt="">
            <div class="infoProf">
                <h3>${professor.username}</h3>
                <p>Ensina ${professor.conhecimento}</p>
                <div class="estrelas">
                    <p>R$${professor.precoAula} p/hora</p>
                    <i class="fa-solid fa-star"></i>
                    <i class="fa-solid fa-star"></i>
                    <i class="fa-solid fa-star"></i>
                    <i class="fa-solid fa-star"></i>
                    <i class="fa-solid fa-star-half-stroke"></i>
                </div>
            </div>
        </div>`;
          verticalProf.insertAdjacentHTML('afterbegin', elementoProfessor);
        });
        console.log("Professores carregados");
      } else {
        console.log("Erro ao carregar professores", response.statusText);
      }
    } catch (error) {
      console.log("Erro ao carregar professores", error);
    }
};
///////////////////////////////////////////////
//Ativação de funções
carregarProfessores();
mudarInformacaoUsuarioCanto();