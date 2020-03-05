/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package IService;


import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author House
 */
public interface IServiceEnfant<T> {
    void ajouter(T t) throws SQLException;
    boolean delete(T t) throws SQLException;
    void update(T t) throws SQLException;
    List<T> readAll() throws SQLException;
}

