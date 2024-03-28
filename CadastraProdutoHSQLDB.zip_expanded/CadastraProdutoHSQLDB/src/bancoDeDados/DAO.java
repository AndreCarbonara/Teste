package bancoDeDados;

import java.sql.SQLException;
import entidades.Produto;

/**
 * Interface DAO para operações CRUD em objetos Produto.
 * 
 * Aplica ISP por ser específica para operações CRUD.
 *  
 * Aplica o DIP pois as classes que usam o CRUD não precisam
 * saber como foi implementado internamente, apenas precisam
 *  executar as funções e ter o resultado esperado 
 */

public interface DAO {
    void inserir(Produto produto) throws SQLException;
    Produto buscar(int codigo) throws SQLException;
    void atualizar(Produto produto) throws SQLException;
    void remover(int codigo) throws SQLException;
}