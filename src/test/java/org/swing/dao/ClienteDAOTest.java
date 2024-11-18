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
public class ClienteDAOTest {

    private ClienteDAO clienteDAO;
    private UsuarioDAO usuarioDAO;

    @BeforeAll
    public void setup() {
        clienteDAO = new ClienteDAO();
        usuarioDAO = new UsuarioDAO();
    }

    @BeforeEach
    public void prepareDatabase() throws SQLException {
        Connection conn = usuarioDAO.getConnection();

        conn.prepareStatement("SET FOREIGN_KEY_CHECKS = 0").executeUpdate();

        conn.prepareStatement("TRUNCATE TABLE cliente").executeUpdate();
        conn.prepareStatement("TRUNCATE TABLE usuario").executeUpdate();
        conn.prepareStatement("TRUNCATE TABLE endereco").executeUpdate();

        conn.prepareStatement("SET FOREIGN_KEY_CHECKS = 1").executeUpdate();

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

        Cliente cliente = new Cliente(usuario.getId_usuario(), "João Cliente", "12345678900", LocalDate.of(1990, 1, 1),
                "11987654321", endereco, "senha123");

        clienteDAO.save(cliente);

        Assertions.assertNotEquals(0, cliente.getId_usuario());
    }

    @Test
    public void testFindById() throws SQLException {
        int enderecoId = getEnderecoId();
        Endereco endereco = new Endereco(enderecoId, "12345-678", "Rua X", 100, "Bairro Y", "Cidade Z", "SP");

        Usuario usuario = new Usuario(0, "Maria Cliente", "98765432100", LocalDate.of(1985, 5, 15), "11912345678", endereco) {
            @Override
            public boolean login(String senha) {
                return senha.equals(this.getSenha());
            }
        };
        usuario.setTipoUsuario(Usuario.TipoUsuario.CLIENTE);
        usuario.setSenha("senhaSegura");
        usuarioDAO.save(usuario);

        Cliente cliente = new Cliente(usuario.getId_usuario(), "Maria Cliente", "98765432100", LocalDate.of(1985, 5, 15),
                "11912345678", endereco, "senhaSegura");

        clienteDAO.save(cliente);

        Cliente fetchedCliente = clienteDAO.findById(cliente.getId_usuario());
        Assertions.assertNotNull(fetchedCliente);
        Assertions.assertEquals("Maria Cliente", fetchedCliente.getNome());
    }


    @Test
    public void testDeleteCliente() throws SQLException {
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

        Cliente cliente = new Cliente(usuario.getId_usuario(), "João Cliente", "12345678900", LocalDate.of(1990, 1, 1),
                "11987654321", endereco, "senha123");
        clienteDAO.save(cliente);
        clienteDAO.delete(cliente.getId_usuario());
        Cliente fetchedCliente = clienteDAO.findById(cliente.getId_usuario());
        Assertions.assertNull(fetchedCliente);
    }
}
