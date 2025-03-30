import axios from "axios";

export default class FuncionarioService {
    async getAll() {
        return axios.get('http://localhost:3000/api/funcionario')
            .then((res) => res.data)
            .catch((error) => {
                console.error("Erro ao buscar dados dos funcionários:", error);
                throw error;
            });
    }
}