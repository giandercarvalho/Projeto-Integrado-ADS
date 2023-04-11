/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import factory.ConnectionFactory;
import model.Produto;
import java.sql.*;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import model.Clientes;


public class ProdutoDAO {
    
    private Connection connection;
    int id;
    String nome;
    double preco;
    
    public ProdutoDAO(){
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void adiciona(Produto produto) {
        String sql = "INSERT INTO produtos(nome, preco) VALUES (?, ?)";
        
        try {
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            
            stmt.execute();
            stmt.close();
        }
        catch(SQLException u){
            throw new RuntimeException(u);
        }
    }
    
    public List<Produto> listarProduto(){
        
       try {
           List<Produto> lista = new ArrayList<>();
           
           String sql = "SELECT nome FROM produtos";
           PreparedStatement stmt = connection.prepareStatement(sql);
           ResultSet rs = stmt.executeQuery();
           while (rs.next()) {
               Produto obj = new Produto();
               obj.setNome(rs.getString("nome"));
               obj.setPreco(rs.getDouble("preco"));
               
               lista.add(obj);
               
               return lista;
           }
           
       } catch(SQLException u) {
          throw new RuntimeException(u);
       }
        return null;
    }
    
}
