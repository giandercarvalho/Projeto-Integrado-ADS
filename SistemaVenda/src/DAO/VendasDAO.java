/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import factory.ConnectionFactory;
import model.Vendas;
import model.Produto;
import model.Clientes;
import java.sql.*;
import java.sql.PreparedStatement;


public class VendasDAO {
    
   private Connection connection;
   int id;
   int idCliente;
   int idProduto;
   int qtd;
   double total;
   
   public VendasDAO() {
       this.connection = new ConnectionFactory().getConnection();
   }
   
   public void adicionaVenda(Vendas venda) {
       String sql = "INSERT INTO vendas(idCliente, idProduto, qtd, total) VALUES (?, ?, ?, ?)";
       
       try {
            
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, venda.getIdCliente());
            stmt.setInt(2, venda.getIdProduto());
            stmt.setInt(3,venda.getQtd());
            stmt.setDouble(4, venda.getTotal());
            
            stmt.execute();
            stmt.close();
        }
        catch(SQLException u){
            throw new RuntimeException(u);
        }
   }
}
