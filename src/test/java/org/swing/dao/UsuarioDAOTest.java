package org.swing.dao;

import org.junit.jupiter.api.*;
import org.swing.model.Cliente;
import org.swing.model.Endereco;
import org.swing.model.Usuario;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UsuarioDAOTest {
    private ClienteDAO clienteDAO;
    private UsuarioDAO usuarioDAO;
    public void setup() {
        BaseDAO.setTestMode(true);
        usuarioDAO = new UsuarioDAO();
        clienteDAO = new ClienteDAO();
    }

    @BeforeEach
    public void prepareDatabase() throws SQLException {
        Connection conn = usuarioDAO.getConnection();

        // Desativa as restrições de chave estrangeira para limpar tabelas
        conn.prepareStatement("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();

        // Limpa as tabelas
        conn.prepareStatement("TRUNCATE TABLE cliente").executeUpdate();
        conn.prepareStatement("TRUNCATE TABLE funcionario").executeUpdate();
        conn.prepareStatement("TRUNCATE TABLE usuario").executeUpdate();
        conn.prepareStatement("TRUNCATE TABLE endereco").executeUpdate();

        // Reativa as restrições de chave estrangeira
        conn.prepareStatement("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();

        // Adiciona um endereço válido
        conn.prepareStatement(
                "INSERT INTO endereco (cep, local, numero_casa, bairro, cidade, estado) " +
                        "VALUES ('12345-678', 'Rua X', 100, 'Bairro Y', 'Cidade Z', 'SP')"
        ).executeUpdate();
    }

    private int getEnderecoId() throws SQLException {
        Connection conn = usuarioDAO.getConnection();
        ResultSet rs = conn.prepareStatement("SELECT id_endereco FROM endereco WHERE cep = '12345-678'").executeQuery();
        rs.next();
        return rs.getInt("id_endereco");
    }

    @Test
    public void testSaveCliente() throws SQLException {
        // Salva o usuário no banco
        int enderecoId = getEnderecoId();
        Endereco endereco = new Endereco(enderecoId, "12345-678", "Rua X", 100, "Bairro Y", "Cidade Z", "SP");

        Usuario usuario = new Usuario(0, "João Cliente", "12345678900", LocalDate.of(1990, 1, 1), "11987654321", endereco) {
            @Override
            public boolean login(String senha) {
                return senha.equals(this.getSenha());
            }
        };
        usuario.setTipoUsuario(Usuario.TipoUsuario.CLIENTE);
        usuario.setSenha("senha123");
        usuarioDAO.save(usuario);

        // Usa o ID do usuário salvo para criar o cliente
        Cliente cliente = new Cliente(usuario.getId_usuario(), "João Cliente", "12345678900", LocalDate.of(1990, 1, 1),
                "11987654321", endereco, "senha123");

        clienteDAO.save(cliente);

        // Valida que o cliente foi salvo
        Assertions.assertNotEquals(0, cliente.getId_usuario());
    }


    @Test
    public void testFindById() throws SQLException {
        int enderecoId = getEnderecoId();

        Endereco endereco = new Endereco(enderecoId, "12345-678", "Rua X", 100, "Bairro Y", "Cidade Z", "SP");
        Usuario usuario = new Usuario(0, "Maria Oliveira", "98765432100", LocalDate.of(1985, 5, 15), "11912345678", endereco) {
            @Override
            public boolean login(String senha) {
                return senha.equals(this.getSenha());
            }
        };

        usuario.setTipoUsuario(Usuario.TipoUsuario.CLIENTE);
        usuario.setSenha("senhaSegura");

        usuarioDAO.save(usuario);

        Usuario fetchedUsuario = usuarioDAO.findById(usuario.getId_usuario());
        Assertions.assertNotNull(fetchedUsuario);
        Assertions.assertEquals("Maria Oliveira", fetchedUsuario.getNome());
    }

    @Test
    public void testDeleteUsuario() throws SQLException {
        int enderecoId = getEnderecoId();

        Endereco endereco = new Endereco(enderecoId, "12345-678", "Rua X", 100, "Bairro Y", "Cidade Z", "SP");
        Usuario usuario = new Usuario(0, "João Silva", "12345678900", LocalDate.of(1990, 1, 1), "11987654321", endereco) {
            @Override
            public boolean login(String senha) {
                return senha.equals(this.getSenha());
            }
        };

        usuario.setTipoUsuario(Usuario.TipoUsuario.CLIENTE);
        usuario.setSenha("senha123");

        usuarioDAO.save(usuario);
        usuarioDAO.delete(usuario.getId_usuario());

        Usuario fetchedUsuario = usuarioDAO.findById(usuario.getId_usuario());
        Assertions.assertNull(fetchedUsuario);
    }
}
