package classes;

import banco.Banco;
import erro.ErroHandle;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @version 1.0
 * @author Igor Martinelli
 */
public class Clientes
{
    private static int codCliente;
    private static String nome;
    private static String endereco;
    private static String bairro;
    private static String cidade;
    private static String uf;
    private static String cep;
    private static String telefone;
    private static String email;
    private static String dataCadCliente;
    private static ResultSet retorno;
    
    /**
     * @return the codCliente
     */
    public int getCodCliente() {
        return codCliente;
    }

    /**
     * @param codCliente the codCliente to set
     */
    public void setCodCliente(int codCliente) {
        Clientes.codCliente = codCliente;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        Clientes.nome = nome;
    }

    /**
     * @return the endereco
     */
    public String getEndereco() {
        return endereco;
    }

    /**
     * @param endereco the endereco to set
     */
    public void setEndereco(String endereco) {
        Clientes.endereco = endereco;
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @param bairro the bairro to set
     */
    public void setBairro(String bairro) {
        Clientes.bairro = bairro;
    }

    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @param cidade the cidade to set
     */
    public void setCidade(String cidade) {
        Clientes.cidade = cidade;
    }

    /**
     * @return the uf
     */
    public String getUf() {
        return uf;
    }

    /**
     * @param uf the uf to set
     */
    public void setUf(String uf) {
        Clientes.uf = uf;
    }

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @param cep the cep to set
     */
    public void setCep(String cep) {
        Clientes.cep = cep;
    }

    /**
     * @return the telefone
     */
    public String getTelefone() {
        return telefone;
    }

    /**
     * @param telefone the telefone to set
     */
    public void setTelefone(String telefone) {
        Clientes.telefone = telefone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        Clientes.email = email;
    }

    /**
     * @return the dataCadCliente
     */
    public String getDataCadCliente() {
        return dataCadCliente;
    }

    /**
     * @param dataCadCliente the dataCadCliente to set
     */
    public void setDataCadCliente(String dataCadCliente) {
        Clientes.dataCadCliente = dataCadCliente;
    }
    
    public static ResultSet pesquisaPeloCod() throws ErroHandle
    {
        retorno = null;
        try
        {
            retorno = Banco.Selecionar("codcliente, nome, endereço, cidade, estado, cep, telefone, e_mail, data_cad_cliente", "Clientes c INNER JOIN Estados e ON c.uf = e.uf", "WHERE codcliente = " + codCliente);
        }
        catch (ErroHandle | ClassNotFoundException e)
        {
            throw new erro.ErroHandle("Falha na pesquisa pelo Código do Cliente");
        }
        return retorno;
    }
    
    public static ResultSet pesquisaPeloNome() throws ErroHandle
    {
        retorno = null;
        try
        {
            retorno = Banco.Selecionar("codcliente, nome, endereço, cidade, estado, cep, telefone, e_mail, data_cad_cliente", "Clientes c INNER JOIN Estados e ON c.uf = e.uf", "WHERE nome LIKE '" + nome + "%' ORDER BY nome ASC");
        }
        catch (ErroHandle | ClassNotFoundException e)
        {
            throw new erro.ErroHandle("Falha na pesquisa pelo Nome do Cliente");
        }
        return retorno;
    }
    
    public static void Inserir() throws ErroHandle
    {
        try
        {
            Banco.Inserir("Clientes", "nome, endereco, bairro, cidade, uf, cep, telefone, e_mail", "'" + nome + "','" + endereco + "','" + bairro + "','" + cidade + "','" + uf + "','" + cep + "','" + telefone + "','" + email + "'");
        }
        catch (ErroHandle | ClassNotFoundException e)
        {
            throw new erro.ErroHandle("Falha ao inserir novo Cliente");
        }
    }
    
    public static void Apagar() throws ErroHandle
    {
        retorno = null;
        try
        {
            retorno = Banco.Selecionar("COUNT(1)", "vendas", "WHERE CodCliente = " + codCliente);
            if (retorno.next())
            {
                throw new erro.ErroHandle("Não pode ser realizar a exclusão do cliente devido ao fato do mesmo já ter realizado compras.");
            }
            
            Banco.Apagar("Clientes", "CodCliente = " + codCliente);
        }
        catch (ErroHandle | ClassNotFoundException | SQLException e)
        {
            throw new erro.ErroHandle("Falha ao apagar novo Cliente");
        }
        finally
        {
            retorno = null;
        }
    }
    
    public static void EstatisticaVendasCliente()
    {
        retorno = null;
        try
        {
            // TO-DO
        }
        catch (Exception e)
        {
            // TO-DO
        }
    }
}
