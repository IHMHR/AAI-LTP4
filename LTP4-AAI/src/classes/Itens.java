package classes;

import banco.Banco;
import erro.ErrorHandle;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @version 1.0
 * @author Igor Martineli
 */
public class Itens
{
    private static int codItem;
    private static int codVenda;
    private static int codProduto;
    private static int quantidade;
    private static double valor;
    private static ResultSet retorno;
    private static final String TABLE_NAME = "itens";

    /**
     * @return the codItem
     */
    public int getCodItem() {
        return codItem;
    }

    /**
     * @param aCodItem the codItem to set
     */
    public void setCodItem(int aCodItem) {
        codItem = aCodItem;
    }

    /**
     * @return the codVenda
     */
    public int getCodVenda() {
        return codVenda;
    }

    /**
     * @param aCodVenda the codVenda to set
     */
    public void setCodVenda(int aCodVenda) {
        codVenda = aCodVenda;
    }

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
     * @return the quantidade
     */
    public int getQuantidade() {
        return quantidade;
    }

    /**
     * @param aQuantidade the quantidade to set
     */
    public void setQuantidade(int aQuantidade) {
        quantidade = aQuantidade;
    }

    /**
     * @return the valor
     */
    public double getValor() {
        return valor;
    }

    /**
     * @param aValor the valor to set
     */
    public void setValor(double aValor) {
        valor = aValor;
    }
    
    public void AdicioarItemVenda() throws ErrorHandle, IOException
    {
        try
        {
            Banco.Inserir(TABLE_NAME, "codVenda, codProduto, quantidade, valor", codVenda + "," + codProduto + "," + quantidade + "," + valor);
        }
        catch (ErrorHandle | ClassNotFoundException e)
        {
            FileWriter arq = new FileWriter("erroLog.txt", true);
            arq.write(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()) + " - " + e + " (AdicioarItemVenda)(Itens)\n");
            arq.flush();
            arq.close();
            throw new ErrorHandle("Falha na inserção de novo Item");
        }
    }
}
