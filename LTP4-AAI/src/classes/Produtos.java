package classes;

import banco.Banco;
import erro.ErroHandle;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;

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
    public static int getCodProduto() {
        return codProduto;
    }

    /**
     * @param aCodProduto the codProduto to set
     */
    public static void setCodProduto(int aCodProduto) {
        codProduto = aCodProduto;
    }

    /**
     * @return the produto
     */
    public static String getProduto() {
        return produto;
    }

    /**
     * @param aProduto the produto to set
     */
    public static void setProduto(String aProduto) {
        produto = aProduto;
    }

    /**
     * @return the codUnidade
     */
    public static int getCodUnidade() {
        return codUnidade;
    }

    /**
     * @param aCodUnidade the codUnidade to set
     */
    public static void setCodUnidade(int aCodUnidade) {
        codUnidade = aCodUnidade;
    }

    /**
     * @return the precoUnidade
     */
    public static double getPrecoUnidade() {
        return precoUnidade;
    }

    /**
     * @param aPrecoUnidade the precoUnidade to set
     */
    public static void setPrecoUnidade(double aPrecoUnidade) {
        precoUnidade = aPrecoUnidade;
    }

    /**
     * @return the dataPreco
     */
    public static Date getDataPreco() {
        return dataPreco;
    }

    /**
     * @param aDataPreco the dataPreco to set
     */
    public static void setDataPreco(Date aDataPreco) {
        dataPreco = aDataPreco;
    }
    
    
    public static ResultSet PesquisaPeloCod() throws ErroHandle
    {
        retorno = null;
        try
        {
            retorno = Banco.Selecionar("codProduto, produto, cod_unidade, preco_unidade, dataPreco", TABLE_NAME, "WHERE codProduto = " + codProduto);
        }
        catch (ErroHandle | ClassNotFoundException e)
        {
            throw new erro.ErroHandle("Falha ao realizar a pesquisa pelo Código");
        }
        return retorno;
    }
    
    public static ResultSet PesquisaPeloNome() throws ErroHandle
    {
        retorno = null;
        try
        {
            retorno = Banco.Selecionar("codProduto, produto, cod_unidade, preco_unidade, dataPreco", TABLE_NAME, "WHERE produto LIKE '" + produto + "%' ORDER BY produto ASC");
        }
        catch (ErroHandle | ClassNotFoundException e)
        {
            throw new erro.ErroHandle("Falha ao realizar a pesquisa pelo Nome");
        }
        return retorno;
    }
    
    public static void Inserir() throws ErroHandle
    {
        try
        {
            Banco.Inserir(TABLE_NAME, "produto, cod_unidade, preco_unidade, dataPreco", "'" + produto + "'," + codUnidade + "," + precoUnidade + ", '" + dataPreco + "'");
        }
        catch (ErroHandle | ClassNotFoundException e)
        {
            throw new erro.ErroHandle("Falha ao inserir novo Produto");
        }
    }
    
    public static void Alterar() throws ErroHandle
    {
        try
        {
            Banco.Alterar(TABLE_NAME, String.format("produto = '%1$s', cod_unidade = %2$s, preco_unidade = %3$s, dataPreco = '%4$s'", produto, codUnidade, precoUnidade, dataPreco), "codProduto = " + codProduto);
        }
        catch (ErroHandle | ClassNotFoundException e)
        {
            throw new erro.ErroHandle("Falha ao alterar Produto");
        }
    }
    
    public static void Apagar() throws ErroHandle
    {
        retorno = null;
        try
        {
            retorno = Banco.Selecionar("1", "itens", "WHERE codProduto = " + codProduto);
            if (retorno.next())
            {
                throw new erro.ErroHandle("Não pode ser realizar a exclusão do produto devido ao fato do mesmo já ter sido vendido.");
            }
            
            Banco.Apagar(TABLE_NAME, "codProduto = " + codProduto);
        }
        catch (ErroHandle | ClassNotFoundException | SQLException e)
        {
            throw new erro.ErroHandle("Falha ao excluir Produto");
        }
    }
    
    public static ResultSet RelacaoProdutos() throws ErroHandle
    {
        retorno = null;
        try
        {
            retorno = Banco.Selecionar("produto AS 'Nome do Produto', codproduto AS 'Código', preco_unidade AS 'Preço Unitário', dataPreco AS 'Data de cadastramento'", TABLE_NAME, "ORDER BY produto ASC");
        }
        catch (ErroHandle | ClassNotFoundException e)
        {
            throw new erro.ErroHandle("Falha ao gerar relação de Produtos");
        }
        return retorno;
    }
}
