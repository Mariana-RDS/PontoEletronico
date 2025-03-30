import FuncionarioService from './FuncionarioService.js';

const funcionarioService = new FuncionarioService();

function exibirFuncionarios() {
    const container = document.getElementById('container-cards');
    container.innerHTML = '';

    funcionarioService.getAll()
        .then(funcionarios => {
            funcionarios.forEach(func => {
                const card = document.createElement('div');
                card.className = 'card';
                card.innerHTML = `
                    <h3>${func.nome}</h3>
                    <p>Email: ${func.email}</p>
                    <p>Setor: ${func.setor}</p>
                    <div class="buttons">
                        <button class="button button-entrada">Entrada</button>
                        <button class="button button-saida">Saída</button>
                    </div>
                `;
                container.appendChild(card);
            });
        })
        .catch(error => {
            console.error("Erro:", error);
            container.innerHTML = '<p>Erro ao carregar funcionários</p>';
        });
}

window.addEventListener('DOMContentLoaded', exibirFuncionarios);