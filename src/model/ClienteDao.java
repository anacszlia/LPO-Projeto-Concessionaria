/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author anacs
 */
import java.util.ArrayList;
import java.util.List;
import model.Cliente;

public class ClienteDao {

    private static List<Cliente> clientes = new ArrayList<>();

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void inserir(Cliente c) {
        clientes.add(c);
    }

    public void remover(String cpf) {
        clientes.removeIf(c -> c.getCpf().equals(cpf));
    }

    public void atualizar(String cpf, Cliente atualizado) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getCpf().equals(cpf)) {
                clientes.set(i, atualizado);
                break;
            }
        }
    }

    public void salvar(Cliente cliente) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
