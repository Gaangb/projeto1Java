
package agenda.contoller;

import agenda.dao.ContatoDAO;
import agenda.model.Contato;
import java.util.List;


public class ContatoController {
   
    private ContatoDAO dao = new ContatoDAO();
    
    
    public void salvar(Contato contato){
        dao.salvar(contato);
    }
    public void atualizar(Contato contato){
        dao.atualizarContato(contato);
    }
    public void remover(int id){
        dao.remover(id);
    }
    public List<Contato> obterTodosOsContatos(){
        return ContatoDAO.listaContatos;
}
}
