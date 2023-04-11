/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import factory.ConnectionFactory;
import model.Clientes;
import java.sql.*;
import java.sql.PreparedStatement;
/**
 *
 * @author Giander
 */
public class ClientesDAO {
    
    private Connection connection;
    int id;
    String nome;
    int cpf;
    String endereco;
    String telefone;
    
    public ClientesDAO(){
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void adiciona(Clientes cliente) {
        String sql = "INSERT INTO clientes(nome, cpf, end, telefone) VALUES (?, ?, ?, ?)";
        
        try {
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, cliente.getNome());
            stmt.setInt(2, cliente.getCpf());
            stmt.setString(3,cliente.getEndereco());
            stmt.setString(4, cliente.getTelefone());
            
            stmt.execute();
            stmt.close();
        }
        catch(SQLException u){
            throw new RuntimeException(u);
        }
    }
    
    public Clientes procuraCliente(int cpf) {
        String sql = "SELECT * FROM clientes WHERE cpf = ?";
        
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, cpf);
            ResultSet rs = stmt.executeQuery();
            
            Clientes cl = new Clientes();
            rs.first();
            cl.setCpf(rs.getInt("cpf"));
            cl.setNome(rs.getString("nome"));
            cl.setEndereco(rs.getString("end"));
            cl.setTelefone(rs.getString("telefone"));
            cl.setId(rs.getInt("id"));
            
            return cl;
        }
        catch(SQLException u) {
            throw new RuntimeException(u);
        }
    }
    
}
