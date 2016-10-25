package banco;

import erro.ErroHandle;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @version 1.0
 * @author Igor Martinelli
 */
public abstract class Banco
{
    private static final String DRIVER = "jdbc:firebirdsql:server1b/3050:";
    private static final String PATH = "D:/PROGRAM FILES/FIREBIRD/LTP4/BDVENDAS.GDB";
    private static final String UID = "SYSDBA";
    private static final String PWD = "masterkey";
    private static Connection conexao;
    private static PreparedStatement comando;
    private static ResultSet retorno;
    
    /**
     * Metodo para abrir e fechar conexao com o SGBDR
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private static void AbrirFecharConexao() throws SQLException, ErroHandle, ClassNotFoundException
    {
        try
        {
            if(conexao.isClosed())
            {
                Class.forName(DRIVER);
                conexao = DriverManager.getConnection(PATH, UID, PWD);
            }
            else
            {
                conexao.close();
            }
        }
        catch (SQLException erro)
        {
            throw new erro.ErroHandle("Falha na realizar a abertura ou fechamento da conexão");
        }
    }
    
    /**
     * Metodo para inserir registro
     * @param table
     * @param values
     * @throws erro.ErroHandle
     * @throws java.lang.ClassNotFoundException
     */
    public static void Inserir(String table, String values) throws ErroHandle, ClassNotFoundException
    {
        if(table.isEmpty() || values.isEmpty())
        {
            throw new erro.ErroHandle("Falta de informações para realizar inserção dado.");
        }

        try 
        {
            comando = conexao.prepareStatement("INSERT INTO " + table + " VALUES (" + values + ")");
            AbrirFecharConexao();
            comando.execute();
            AbrirFecharConexao();
        }
        catch (SQLException erro)
        {
            throw new erro.ErroHandle("Falha ao realizar a inserção de dados na tabela " + table);
        }
    }
    
    /**
     * Metodo para inserir registro
     * @param table
     * @param fields
     * @param values
     * @throws erro.ErroHandle
     * @throws java.lang.ClassNotFoundException
     */
    public static void Inserir(String table, String fields, String values) throws ErroHandle, ClassNotFoundException
    {
        if(table.isEmpty() || values.isEmpty())
        {
            throw new erro.ErroHandle("Falta de informações para realizar inserção dado.");
        }
        
        try 
        {
            comando = conexao.prepareStatement("INSERT INTO " + table + " (" + fields + ") VALUES (" + values + ")");
            AbrirFecharConexao();
            comando.execute();
            AbrirFecharConexao();
        }
        catch (SQLException erro)
        {
            throw new erro.ErroHandle("Falha ao realizar a inserção de dados na tabela " + table);
        }
    }
    
    /**
     * Metodo para inserir registro
     * @param table
     * @param values
     * @return ResultSet retorno
     * @throws erro.ErroHandle
     * @throws java.lang.ClassNotFoundException
     */
    public static ResultSet InserirComRetorno(String table, String values) throws ErroHandle, ClassNotFoundException
    {
        if(table.isEmpty() || values.isEmpty())
        {
            throw new erro.ErroHandle("Falta de informações para realizar inserção dado.");
        }
        
        retorno = null;
        
        try 
        {
            comando = conexao.prepareStatement("INSERT INTO " + table + " VALUES (" + values + ")");
            AbrirFecharConexao();
            retorno = comando.executeQuery();
            AbrirFecharConexao();
        }
        catch (SQLException erro)
        {
            throw new erro.ErroHandle("Falha ao realizar a inserção de dados na tabela " + table);
        }
        return retorno;
    }
    
    /**
     * Metodo para inserir registro com retorno
     * @param table
     * @param fields
     * @param values
     * @return ResultSet retorno
     * @throws erro.ErroHandle
     * @throws java.lang.ClassNotFoundException
     */
    public ResultSet InserirComRetorno(String table, String fields, String values) throws ErroHandle, ClassNotFoundException
    {
        if(table.isEmpty() || values.isEmpty())
        {
            throw new erro.ErroHandle("Falta de informações para realizar inserção dado.");
        }
        
        retorno = null;
        
        try 
        {
            comando = conexao.prepareStatement("INSERT INTO " + table + " (" + fields + ") VALUES (" + values + ")");
            AbrirFecharConexao();
            retorno = comando.executeQuery();
            AbrirFecharConexao();
        }
        catch (SQLException erro)
        {
            throw new erro.ErroHandle("Falha ao realizar a inserção de dados na tabela " + table);
        }
        return retorno;
    }
    
    /**
     * Metodo para apagar registros
     * @param table
     * @param condition
     * @throws erro.ErroHandle
     * @throws java.lang.ClassNotFoundException
     */
    public static void Apagar(String table, String condition) throws ErroHandle, ClassNotFoundException
    {
        if(table.isEmpty() || condition.isEmpty())
        {
            throw new erro.ErroHandle("Falta de informações para apagar dado.");
        }
        
        retorno = null;
        
        try 
        {
            comando = conexao.prepareStatement("DELETE FROM " + table + " WHERE " + condition);
            AbrirFecharConexao();
            comando.execute();
            AbrirFecharConexao();
        }
        catch (SQLException erro)
        {
            throw new erro.ErroHandle("Falha ao realizar a deleção de dados na tabela " + table);
        }
    }
    
    /**
     * Metodo para selecionar registros
     * @param columns
     * @param table
     * @return ResultSet retorno
     * @throws erro.ErroHandle
     * @throws java.lang.ClassNotFoundException
     */
    public static ResultSet Selecionar(String columns, String table) throws ErroHandle, ClassNotFoundException
    {
        if(table.isEmpty() || columns.isEmpty())
        {
            throw new erro.ErroHandle("Falta de informações para visualizar informações.");
        }
        
        retorno = null;
        
        try 
        {
            comando = conexao.prepareStatement("SELECT " + columns + " FROM " + table);
            AbrirFecharConexao();
            retorno = comando.executeQuery();
            AbrirFecharConexao();
        }
        catch (SQLException erro)
        {
            throw new erro.ErroHandle("Falha ao realizar a seleção de dados na tabela " + table);
        }
        return retorno;
    }
    
    /**
     * Metodo para selecionar registros
     * @param columns
     * @param table
     * @param condition
     * @return ResultSet retorno
     * @throws erro.ErroHandle
     * @throws java.lang.ClassNotFoundException
     */
    public static ResultSet Selecionar(String columns, String table, String condition) throws ErroHandle, ClassNotFoundException
    {
        if(table.isEmpty() || columns.isEmpty() || condition.isEmpty())
        {
            throw new erro.ErroHandle("Falta de informações para visualizar informações.");
        }
        
        retorno = null;
        
        try 
        {
            comando = conexao.prepareStatement("SELECT " + columns + " FROM " + table + " " + condition);
            AbrirFecharConexao();
            retorno = comando.executeQuery();
            AbrirFecharConexao();
        }
        catch (SQLException erro)
        {
            throw new erro.ErroHandle("Falha ao realizar a seleção de dados na tabela " + table);
        }
        return retorno;
    }
    
    /**
     * Metodo para alterar registros
     * @param table
     * @param fieldsNvalues
     * @param condition
     * @throws erro.ErroHandle
     * @throws java.lang.ClassNotFoundException
     */
    public static void Alterar(String table, String fieldsNvalues, String condition) throws ErroHandle, ClassNotFoundException            
    {
        if(table.isEmpty() || fieldsNvalues.isEmpty() || condition.isEmpty())
        {
            throw new erro.ErroHandle("Falta de informações para alterar dado.");
        }
        
        try 
        {
            comando = conexao.prepareStatement("UPDATE " + table + " SET " + fieldsNvalues + " WHERE " + condition);
            AbrirFecharConexao();
            comando.execute();
            AbrirFecharConexao();
        }
        catch (SQLException erro)
        {
            throw new erro.ErroHandle("Falha ao realizar a alteração de dados na tabela " + table);
        }
    }
}
