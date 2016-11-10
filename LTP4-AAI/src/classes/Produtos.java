package classes;

import banco.Banco;
import erro.ErrorHandle;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @version 1.0
 * @author Igor Martinelli
 */
public class Produtos
{
    private static int codProduto;
    private static String produto;
    private static int codUnidade;
    private static double precoUnidade;
    private static Date dataPreco;
    private static ResultSet retorno;
    private static final String TABLE_NAME = "tabProdutos";

    /**
     * @return the codProduto
     */
    public int getCodProduto() {
        return codProduto;
    }

    /**
     * @param aCodProduto the codProduto to set
     */
    public void setCodProduto(int aCodProduto) {
        codProduto = aCodProduto;
    }

    /**
     * @return the produto
     */
    public String getProduto() {
        return produto;
    }

    /**
     * @param aProduto the produto to set
     */
    public void setProduto(String aProduto) {
        produto = aProduto;
    }

    /**
     * @return the codUnidade
     */
    public int getCodUnidade() {
        return codUnidade;
    }

    /**
     * @param aCodUnidade the codUnidade to set
     */
    public void setCodUnidade(int aCodUnidade) {
        codUnidade = aCodUnidade;
    }

    /**
     * @return the precoUnidade
     */
    public double getPrecoUnidade() {
        return precoUnidade;
    }

    /**
     * @param aPrecoUnidade the precoUnidade to set
     */
    public void setPrecoUnidade(double aPrecoUnidade) {
        precoUnidade = aPrecoUnidade;
    }

    /**
     * @return the dataPreco
     */
    public Date getDataPreco() {
        return dataPreco;
    }

    /**
     * @param aDataPreco the dataPreco to set
     */
    public void setDataPreco(Date aDataPreco) {
        dataPreco = aDataPreco;
    }
    
    
    public ResultSet PesquisaPeloCod() throws ErrorHandle, IOException
    {
        retorno = null;
        try
        {
            retorno = Banco.Selecionar("codProduto, produto, codunidade, preco, dataPreco", TABLE_NAME, "WHERE codProduto = " + codProduto);
        }
        catch (ErrorHandle | ClassNotFoundException e)
        {
            FileWriter arq = new FileWriter("erroLog.txt", true);
            arq.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()) + " - " + e + " (PesquisaPeloCod)(Produtos)\n");
            arq.flush();
            arq.close();
            throw new ErrorHandle("Falha ao realizar a pesquisa pelo Código");
        }
        return retorno;
    }
    
    public ResultSet PesquisaPeloNome() throws ErrorHandle, IOException
    {
        retorno = null;
        try
        {
            retorno = Banco.Selecionar("codProduto, produto, codunidade, preco, dataPreco", TABLE_NAME, "WHERE produto LIKE '" + produto + "%' ORDER BY produto ASC");
        }
        catch (ErrorHandle | ClassNotFoundException e)
        {
            FileWriter arq = new FileWriter("erroLog.txt", true);
            arq.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()) + " - " + e + " (PesquisaPeloNome)(Produtos)\n");
            arq.flush();
            arq.close();
            throw new ErrorHandle("Falha ao realizar a pesquisa pelo Nome");
        }
        return retorno;
    }
    
    public void Inserir() throws ErrorHandle, IOException
    {
        try
        {
            dataPreco = new Date(Calendar.getInstance().getTime().getTime());
            Banco.Inserir(TABLE_NAME, "produto, codunidade, preco, dataPreco", "'" + produto + "'," + codUnidade + "," + precoUnidade + ",'" + dataPreco + "'");
        }
        catch (ErrorHandle | ClassNotFoundException e)
        {
            FileWriter arq = new FileWriter("erroLog.txt", true);
            arq.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()) + " - " + e + " (Inserir)(Produtos)\n");
            arq.flush();
            arq.close();
            throw new ErrorHandle("Falha ao inserir novo Produto");
        }
    }
    
    public void Alterar() throws ErrorHandle, IOException
    {
        try
        {
            Banco.Alterar(TABLE_NAME, String.format("produto = '%1$s', codunidade = %2$s, preco = %3$s, dataPreco = '%4$s'", produto, codUnidade, precoUnidade, dataPreco), "codProduto = " + codProduto);
        }
        catch (ErrorHandle | ClassNotFoundException e)
        {
            FileWriter arq = new FileWriter("erroLog.txt", true);
            arq.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()) + " - " + e + " (Alterar)(Produtos)\n");
            arq.flush();
            arq.close();
            throw new ErrorHandle("Falha ao alterar Produto");
        }
    }
    
    public void Apagar() throws ErrorHandle, IOException
    {
        retorno = null;
        try
        {
            retorno = Banco.Selecionar("1", "itens", "WHERE codProduto = " + codProduto);
            if (retorno.next())
            {
                throw new ErrorHandle("Não pode ser realizar a exclusão do produto devido ao fato do mesmo já ter sido vendido.");
            }
            
            Banco.Apagar(TABLE_NAME, "codProduto = " + codProduto);
        }
        catch (ErrorHandle | ClassNotFoundException | SQLException e)
        {
            FileWriter arq = new FileWriter("erroLog.txt", true);
            arq.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()) + " - " + e + " (Apagar)(Produtos)\n");
            arq.flush();
            arq.close();
            
            if(e.getMessage().startsWith("Não pode ser realizar a exclusão"))
            {
                throw new ErrorHandle(e.getMessage());
            }
            else
            {
                throw new ErrorHandle("Falha ao excluir Produto");
            }
        }
    }
    
    public ResultSet RelacaoProdutos() throws ErrorHandle, IOException
    {
        retorno = null;
        try
        {
            retorno = Banco.Selecionar("produto AS 'Nome do Produto', codproduto AS 'Código', preco AS 'Preço Unitário', dataPreco AS 'Data de cadastramento'", TABLE_NAME, "ORDER BY produto ASC");
        }
        catch (ErrorHandle | ClassNotFoundException e)
        {
            FileWriter arq = new FileWriter("erroLog.txt", true);
            arq.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()) + " - " + e + " (RelacaoProdutos)(Produtos)\n");
            arq.flush();
            arq.close();
            throw new ErrorHandle("Falha ao gerar relação de Produtos");
        }
        return retorno;
    }
    
    public static ResultSet listaProdutos() throws ErrorHandle, IOException
    {
        retorno = null;
        try
        {
            retorno = Banco.Selecionar("codProduto, produto, codunidade, preco, dataPreco", TABLE_NAME, "ORDER BY produto ASC");
        }
        catch (ErrorHandle | ClassNotFoundException e)
        {
            FileWriter arq = new FileWriter("erroLog.txt", true);
            arq.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()) + " - " + e + " (listaProdutos)(Produtos)\n");
            arq.flush();
            arq.close();
            throw new ErrorHandle("Falha ao listar Produtos" + e);
        }
        return retorno;
    }
}
