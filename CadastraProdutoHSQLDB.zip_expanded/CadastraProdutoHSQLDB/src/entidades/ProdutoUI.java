package entidades;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import bancoDeDados.ProdutoDAO;

/**
 * A classe ProdutoUI cria a interface gráfica para operações CRUD de produtos.
 * Aplica o SRP por se concentrar na apresentação da tela e botões para operações
 * CRUD e não se envolve em implementar conexão com o banco por exemplo.
 */

public class ProdutoUI {

	@SuppressWarnings("serial")
	public class CadastraProduto extends JFrame implements ActionListener {

		private JPanel fundo;
		private JPanel campos;
		private JPanel botoes;

		private JButton bBusca;
		private JButton bLimpa;
		private JButton bInsere;
		private JButton bAltera;
		private JButton bRemove;

		private JLabel lCodigo;
		private JLabel lNome;
		private JLabel lDescricao;

		private JTextField tCodigo;
		private JTextField tNome;
		private JTextField tDescricao;

		private Connection conn;
		
		

		public CadastraProduto() {

			super("Cadastro de Produtos");

			this.fundo = new JPanel(new BorderLayout());
			this.campos = new JPanel(new GridLayout(3, 2));
			this.botoes = new JPanel(new FlowLayout());

			this.lCodigo = new JLabel("Código:");
			this.lNome = new JLabel("Nome:");
			this.lDescricao = new JLabel("Descrição:");

			this.tCodigo = new JTextField(10);
			this.tNome = new JTextField(30);
			this.tDescricao = new JTextField(60);

			this.campos.add(lCodigo);
			this.campos.add(tCodigo);
			this.campos.add(lNome);
			this.campos.add(tNome);
			this.campos.add(lDescricao);
			this.campos.add(tDescricao);

			this.fundo.add(this.campos, BorderLayout.CENTER);

			this.bBusca = new JButton("Buscar");
			this.bBusca.addActionListener(this);
			this.bLimpa = new JButton("Limpar");
			this.bLimpa.addActionListener(this);
			this.bInsere = new JButton("Incluir");
			this.bInsere.addActionListener(this);
			this.bAltera = new JButton("Alterar");
			this.bAltera.addActionListener(this);
			this.bRemove = new JButton("Remover");
			this.bRemove.addActionListener(this);

			this.botoes.add(bBusca);
			this.botoes.add(bLimpa);
			this.botoes.add(bInsere);
			this.botoes.add(bAltera);
			this.botoes.add(bRemove);

			this.fundo.add(this.botoes, BorderLayout.SOUTH);

			this.getContentPane().add(this.fundo);

			this.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					try {
						if (conn != null)
							conn.close();
					} catch (SQLException se) {
						System.out.println("Não foi possível conectar ao Banco de Dados");
					}
					System.exit(0);
				}
			});

			this.setSize(500, 150);
		}

/**
 * Atravé dos botões ativam as funções CRUD implementadas nas outras classes.
 * Aplica o Open/Closed Principle (OCP) pois permite a implementação de novos
 * botões por exemplo sem modificar esta estrutura existente.
 */
		
		@Override
	    public void actionPerformed(ActionEvent e) {
	        try {
	            ProdutoDAO dao = new ProdutoDAO();
	            if (e.getSource() == bBusca) {
	                int codigo = Integer.parseInt(tCodigo.getText());
	                Produto produto = dao.buscar(codigo);
	                if (produto != null) {
	                    tNome.setText(produto.getNome());
	                    tDescricao.setText(produto.getDescricao());
	                } else {
	                    JOptionPane.showMessageDialog(this, "Produto não encontrado.");
	                }
	            } else if (e.getSource() == bLimpa) {
	                acaoLimpar();
	            } else if (e.getSource() == bInsere) {
	                Produto produto = new Produto(
	                        Integer.parseInt(tCodigo.getText()),
	                        tNome.getText(),
	                        tDescricao.getText());
	                dao.inserir(produto);
	                JOptionPane.showMessageDialog(this, "Produto inserido com sucesso.");
	                acaoLimpar();
	            } else if (e.getSource() == bAltera) {
	                Produto produto = new Produto(
	                        Integer.parseInt(tCodigo.getText()),
	                        tNome.getText(),
	                        tDescricao.getText());
	                dao.atualizar(produto);
	                JOptionPane.showMessageDialog(this, "Produto atualizado com sucesso.");
	            } else if (e.getSource() == bRemove) {
	                int codigo = Integer.parseInt(tCodigo.getText());
	                dao.remover(codigo);
	                JOptionPane.showMessageDialog(this, "Produto removido com sucesso.");
	                acaoLimpar();
	            }
	        } catch (ClassNotFoundException cnfe) {
	            JOptionPane.showMessageDialog(this, "Erro ao carregar o driver do banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
	        } catch (SQLException sqle) {
	            JOptionPane.showMessageDialog(this, "Erro ao realizar a operação no banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
	        } catch (NumberFormatException nfe) {
	            JOptionPane.showMessageDialog(this, "Por favor, insira um código válido.", "Erro", JOptionPane.ERROR_MESSAGE);
	        } catch (Exception ex) {
	            JOptionPane.showMessageDialog(this, "Erro: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
	        }
	    }
		private void acaoLimpar() {
			this.tCodigo.setText("");
			this.tNome.setText("");
			this.tDescricao.setText("");
		}
		
	}

	public void exibir() {
		CadastraProduto cadastraProduto = new CadastraProduto();
		cadastraProduto.setVisible(true);
		
	}
}