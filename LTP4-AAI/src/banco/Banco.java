package banco;

import erro.ErrorHandle;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import org.firebirdsql.jdbc.FBDriver;

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
    private static final String connectionString = "jdbc:firebirdsql:server1b/3050:D:/PROGRAM FILES/FIREBIRD/LTP4/BDVendas.GDB";
    private static Connection conexao;
    private static PreparedStatement comando;
    private static ResultSet retorno;
    
    /**
     * Metodo para abrir a conexao com o SGBDR
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private static void AbrirConexao() throws SQLException, ErrorHandle, ClassNotFoundException, IOException
    {
        try
        {
            DriverManager.registerDriver(new FBDriver());
            conexao = DriverManager.getConnection(DRIVER + PATH, UID, PWD);

            /*Class.forName(DRIVER);
            //conexao = DriverManager.getConnection(PATH, UID, PWD);
            conexao = DriverManager.getConnection(connectionString);*/
        }
        catch (SQLException erro)
        {
            FileWriter arq = new FileWriter("erroLog.txt", true);
            arq.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()) + " - " + erro + " (AbrirConexao)\n");
            arq.flush();
            arq.close();
            throw new erro.ErrorHandle("Falha na realizar a abertura da conexão");
        }
    }
    
    /**
     * Metodo para fechar a conexao com o SGBDR
     * @throws SQLException
     * @throws ClassNotFoundException
     */
    private static void FecharConexao() throws SQLException, ErrorHandle, ClassNotFoundException, IOException
    {
        try
        {
            if (!conexao.isClosed())
            {
                conexao.close();
            }
        }
        catch (SQLException erro)
        {
            FileWriter arq = new FileWriter("erroLog.txt", true);
            arq.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()) + " - " + erro + " (FecharConexao)\n");
            arq.flush();
            arq.close();
            throw new erro.ErrorHandle("Falha na realizar a abertura ou fechamento da conexão");
        }
    }
    
    /**
     * Metodo para inserir registro
     * @param table
     * @param values
     * @throws erro.ErrorHandle
     * @throws java.lang.ClassNotFoundException
     * @throws java.io.IOException
     */
    public static void Inserir(String table, String values) throws ErrorHandle, ClassNotFoundException, IOException
    {
        if(table.isEmpty() || values.isEmpty())
        {
            throw new erro.ErrorHandle("Falta de informações para realizar inserção dado.");
        }

        try 
        {
            AbrirConexao();
            comando = conexao.prepareStatement("INSERT INTO " + table + " VALUES (" + values + ")");
            comando.execute();
        }
        catch (SQLException erro)
        {
            FileWriter arq = new FileWriter("erroLog.txt", true);
            arq.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()) + " - " + erro + " (Inserir(String table, String values))\n");
            arq.flush();
            arq.close();
            throw new erro.ErrorHandle("Falha ao realizar a inserção de dados na tabela " + table);
        }
    }
    
    /**
     * Metodo para inserir registro
     * @param table
     * @param fields
     * @param values
     * @throws erro.ErrorHandle
     * @throws java.lang.ClassNotFoundException
     * @throws java.io.IOException
     */
    public static void Inserir(String table, String fields, String values) throws ErrorHandle, ClassNotFoundException, IOException
    {
        if(table.isEmpty() || values.isEmpty())
        {
            throw new erro.ErrorHandle("Falta de informações para realizar inserção dado.");
        }
        
        try 
        {
            AbrirConexao();
            System.out.println("INSERT INTO " + table + " (" + fields + ") VALUES (" + values + ")");
            comando = conexao.prepareStatement("INSERT INTO " + table + " (" + fields + ") VALUES (" + values + ")");
            comando.execute();
        }
        catch (SQLException erro)
        {
            FileWriter arq = new FileWriter("erroLog.txt", true);
            arq.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()) + " - " + erro + " (Inserir(String table, String fields, String values))\n");
            arq.flush();
            arq.close();
            throw new erro.ErrorHandle("Falha ao realizar a inserção de dados na tabela " + table);
        }
    }
    
    /**
     * Metodo para inserir registro
     * @param table
     * @param values
     * @return ResultSet retorno
     * @throws erro.ErrorHandle
     * @throws java.lang.ClassNotFoundException
     * @throws java.io.IOException
     */
    public static ResultSet InserirComRetorno(String table, String values) throws ErrorHandle, ClassNotFoundException, IOException
    {
        if(table.isEmpty() || values.isEmpty())
        {
            throw new erro.ErrorHandle("Falta de informações para realizar inserção dado.");
        }
        
        retorno = null;
        
        try 
        {
            AbrirConexao();
            comando = conexao.prepareStatement("INSERT INTO " + table + " VALUES (" + values + ")");
            retorno = comando.executeQuery();
        }
        catch (SQLException erro)
        {
            FileWriter arq = new FileWriter("erroLog.txt", true);
            arq.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()) + " - " + erro + " (InserirComRetorno(String table, String values))\n");
            arq.flush();
            arq.close();
            throw new erro.ErrorHandle("Falha ao realizar a inserção de dados na tabela " + table);
        }
        return retorno;
    }
    
    /**
     * Metodo para inserir registro com retorno
     * @param table
     * @param fields
     * @param values
     * @return ResultSet retorno
     * @throws erro.ErrorHandle
     * @throws java.lang.ClassNotFoundException
     * @throws java.io.IOException
     */
    public static ResultSet InserirComRetorno(String table, String fields, String values) throws ErrorHandle, ClassNotFoundException, IOException
    {
        if(table.isEmpty() || values.isEmpty())
        {
            throw new erro.ErrorHandle("Falta de informações para realizar inserção dado.");
        }
        
        retorno = null;
        
        try 
        {
            AbrirConexao();
            System.out.println("INSERT INTO " + table + " (" + fields + ") VALUES (" + values + ")");
            comando = conexao.prepareStatement("INSERT INTO " + table + " (" + fields + ") VALUES (" + values + ")");
            retorno = comando.executeQuery();
        }
        catch (SQLException erro)
        {
            FileWriter arq = new FileWriter("erroLog.txt", true);
            arq.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()) + " - " + erro + " (InserirComRetorno(String table, String fields, String values))\n");
            arq.flush();
            arq.close();
            throw new erro.ErrorHandle("Falha ao realizar a inserção de dados na tabela " + table);
        }
        return retorno;
    }
    
    /**
     * Metodo para apagar registros
     * @param table
     * @param condition
     * @throws erro.ErrorHandle
     * @throws java.lang.ClassNotFoundException
     * @throws java.io.IOException
     */
    public static void Apagar(String table, String condition) throws ErrorHandle, ClassNotFoundException, IOException
    {
        if(table.isEmpty() || condition.isEmpty())
        {
            throw new erro.ErrorHandle("Falta de informações para apagar dado.");
        }
        
        retorno = null;
        
        try 
        {
            AbrirConexao();
            comando = conexao.prepareStatement("DELETE FROM " + table + " WHERE " + condition);
            comando.execute();
        }
        catch (SQLException erro)
        {
            FileWriter arq = new FileWriter("erroLog.txt", true);
            arq.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()) + " - " + erro + " (Apagar(String table, String condition))\n");
            arq.flush();
            arq.close();
            throw new erro.ErrorHandle("Falha ao realizar a deleção de dados na tabela " + table);
        }
    }
    
    /**
     * Metodo para selecionar registros
     * @param columns
     * @param table
     * @return ResultSet retorno
     * @throws erro.ErrorHandle
     * @throws java.lang.ClassNotFoundException
     * @throws java.io.IOException
     */
    public static ResultSet Selecionar(String columns, String table) throws ErrorHandle, ClassNotFoundException, IOException
    {
        if(table.isEmpty() || columns.isEmpty())
        {
            throw new erro.ErrorHandle("Falta de informações para visualizar informações.");
        }
        
        retorno = null;
        
        try 
        {
            AbrirConexao();
            comando = conexao.prepareStatement("SELECT " + columns + " FROM " + table);
            retorno = comando.executeQuery();
        }
        catch (SQLException erro)
        {
            FileWriter arq = new FileWriter("erroLog.txt", true);
            arq.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()) + " - " + erro + " (Selecionar(String columns, String table))\n");
            arq.flush();
            arq.close();
            throw new erro.ErrorHandle("Falha ao realizar a seleção de dados na tabela " + table + "\n" + erro);
        }
        return retorno;
    }
    
    /**
     * Metodo para selecionar registros
     * @param columns
     * @param table
     * @param condition
     * @return ResultSet retorno
     * @throws erro.ErrorHandle
     * @throws java.lang.ClassNotFoundException
     * @throws java.io.IOException
     */
    public static ResultSet Selecionar(String columns, String table, String condition) throws ErrorHandle, ClassNotFoundException, IOException, IOException, IOException
    {
        if(table.isEmpty() || columns.isEmpty() || condition.isEmpty())
        {
            throw new erro.ErrorHandle("Falta de informações para visualizar informações.");
        }
        
        retorno = null;
        
        try
        {
            AbrirConexao();
            comando = conexao.prepareStatement("SELECT " + columns + " FROM " + table + " " + condition);
            retorno = comando.executeQuery();
        }
        catch (SQLException erro)
        {
            FileWriter arq = new FileWriter("erroLog.txt", true);
            arq.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()) + " - " + erro + " (Selecionar(String columns, String table, String condition))\n");
            arq.flush();
            arq.close();
            throw new erro.ErrorHandle("Falha ao realizar a seleção de dados na tabela " + table);
        }
        return retorno;
    }
    
    /**
     * Metodo para alterar registros
     * @param table
     * @param fieldsNvalues
     * @param condition
     * @throws erro.ErrorHandle
     * @throws java.lang.ClassNotFoundException
     * @throws java.io.IOException
     */
    public static void Alterar(String table, String fieldsNvalues, String condition) throws ErrorHandle, ClassNotFoundException, IOException            
    {
        if(table.isEmpty() || fieldsNvalues.isEmpty() || condition.isEmpty())
        {
            throw new erro.ErrorHandle("Falta de informações para alterar dado.");
        }
        
        try 
        {
            AbrirConexao();
            System.out.println("UPDATE " + table + " SET " + fieldsNvalues + " WHERE " + condition);
            comando = conexao.prepareStatement("UPDATE " + table + " SET " + fieldsNvalues + " WHERE " + condition);
            comando.execute();
        }
        catch (SQLException erro)
        {
            FileWriter arq = new FileWriter("erroLog.txt", true);
            arq.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()) + " - " + erro + " (Alterar(String table, String fieldsNvalues, String condition))\n");
            arq.flush();
            arq.close();
            throw new erro.ErrorHandle("Falha ao realizar a alteração de dados na tabela " + table);
        }
    }
}
