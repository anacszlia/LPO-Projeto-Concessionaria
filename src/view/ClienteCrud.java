
package view;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import model.Cliente;
import model.ClienteDao;

public class ClienteCrud extends JFrame {

    private JPanel painelFundo;
    private JPanel painelBotoes;
    private JTable tabela;
    private JScrollPane barraRolagem;
    private JButton btInserir;
    private JButton btExcluir;
    private JButton btEditar;
    private DefaultTableModel modelo = new DefaultTableModel();
    private ClienteDao dao = new ClienteDao();

    public ClienteCrud() {
        super("Clientes");
        criaJTable();
        criaJanela();
    }

    public void criaJanela() {
        btInserir = new JButton("Inserir");
        btExcluir = new JButton("Excluir");
        btEditar = new JButton("Editar");
        painelBotoes = new JPanel();
        barraRolagem = new JScrollPane(tabela);
        painelFundo = new JPanel();
        painelFundo.setLayout(new BorderLayout());
        painelFundo.add(BorderLayout.CENTER, barraRolagem);
        painelBotoes.add(btInserir);
        painelBotoes.add(btEditar);
        painelBotoes.add(btExcluir);
        painelFundo.add(BorderLayout.SOUTH, painelBotoes);

        getContentPane().add(painelFundo);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 350);
        setVisible(true);

        btInserir.addActionListener(new BtInserirListener());
        btEditar.addActionListener(new BtEditarListener());
        btExcluir.addActionListener(new BtExcluirListener());
    }

    private void criaJTable() {
        tabela = new JTable(modelo);
        modelo.addColumn("Nome");
        modelo.addColumn("CPF");
        modelo.addColumn("Data de Nascimento");
        modelo.addColumn("Telefone");

        tabela.getColumnModel().getColumn(0).setPreferredWidth(150);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(120);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(100);

        pesquisar();
    }

    public void pesquisar() {
        modelo.setNumRows(0);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        List<Cliente> lista = dao.getClientes();
        for (Cliente c : lista) {
            modelo.addRow(new Object[]{
                c.getNome(),
                c.getCpf(),
                c.getDataNascimento() != null ? c.getDataNascimento().format(fmt) : "",
                c.getTelefone()
            });
        }
    }

    private class BtInserirListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            CadastroJFBuilder cadastro = new CadastroJFBuilder();
            cadastro.setVisible(true);
            pesquisar();
        }
    }

    private class BtEditarListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int linhaSelecionada = tabela.getSelectedRow();
            if (linhaSelecionada >= 0) {
                String nome = (String) tabela.getValueAt(linhaSelecionada, 0);
                String cpf = (String) tabela.getValueAt(linhaSelecionada, 1);
                String telefone = (String) tabela.getValueAt(linhaSelecionada, 3);
                
                String novoNome = JOptionPane.showInputDialog("Nome:", nome);
                String novoTelefone = JOptionPane.showInputDialog("Telefone:", telefone);
                
                if (novoNome != null && novoTelefone != null) {
                    Cliente clienteAtualizado = new Cliente();
                    clienteAtualizado.setNome(novoNome);
                    clienteAtualizado.setCpf(cpf);
                    clienteAtualizado.setTelefone(novoTelefone);
                    
                    dao.atualizar(cpf, clienteAtualizado);
                    pesquisar();
                    JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "É necessário selecionar uma linha.");
            }
        }
    }

    private class BtExcluirListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int linhaSelecionada = tabela.getSelectedRow();
            if (linhaSelecionada >= 0) {
                int confirmacao = JOptionPane.showConfirmDialog(null, 
                    "Tem certeza que deseja excluir este cliente?", 
                    "Confirmar Exclusão", 
                    JOptionPane.YES_NO_OPTION);
                    
                if (confirmacao == JOptionPane.YES_OPTION) {
                    String cpf = (String) tabela.getValueAt(linhaSelecionada, 1);
                    dao.remover(cpf);
                    pesquisar();
                    JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "É necessário selecionar uma linha.");
            }
        }
    }

    public static void main(String[] args) {
        new ClienteCrud().setVisible(true);
    }
}
