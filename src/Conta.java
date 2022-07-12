public class Conta {
    private long id;
    private long saldo; // valor em centavos
    private Cliente cliente;
    private TipoConta tipo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getSaldo() {
        return saldo;
    }

    public void setSaldo(long saldo) {
        this.saldo = saldo;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public TipoConta getTipo() {
        return tipo;
    }

    public void setTipo(TipoConta tipo) {
        this.tipo = tipo;
    }

    public void depositar(long valor) {
        if (valorNegativo(valor, "DEPÓSITO")) return;

        saldo = saldo + valor;

        System.out.println("Valor solicitado: " + valor);
        System.out.println("Valor depositado: " + valor);
        System.out.println("Valor depositado com sucesso!");
    }

    public void sacar(long valor) {
        if (valorNegativo(valor, "SAQUE")) return;
        if (saldoInsuficiente(valor, "SAQUE")) return;

        saldo = saldo - valor;
        double valorTaxado = valor - taxaSaque(valor);

        System.out.println("Valor solicitado: " + valor);
        System.out.println("Valor sacado: " + valorTaxado);
        System.out.println("Valor sacado com sucesso!");
    }

    public void transferir(long valor, Conta destino) {
        if (valorNegativo(valor, "TRANSFERÊNCIA")) return;
        if (saldoInsuficiente(valor, "TRANSFERÊNCIA")) return;

        saldo = saldo - valor;
        long valorTaxado = valor - taxaTransferencia(valor);
        destino.setSaldo(destino.getSaldo() + valorTaxado);

        System.out.println("Valor solicitado: " + valor);
        System.out.println("Valor transferido: " + valorTaxado);
        System.out.println("Valor transferido com sucesso!");
    }

    public void investir(long valor) {
        if (valorNegativo(valor, "INVESTIMENTO")) return;
        if (saldoInsuficiente(valor, "INVESTIMENTO")) return;

        System.out.println("Valor solicitado: " + valor);
        System.out.println("Valor investido: " + valor);
        System.out.println("Valor investido com sucesso!");
    }

    public void render() {
        double taxa = .00;
        boolean flag;

        // poupança de pessoa física
        flag = cliente.getTipo() == TipoCliente.PF && tipo == TipoConta.POUPANCA;
        taxa = flag ? BaseDados.REND_POUP : taxa;

        // investimento de pessoa física
        flag = cliente.getTipo() == TipoCliente.PF && tipo == TipoConta.INVESTIMENTO;
        taxa = flag ? BaseDados.REND_INVE_PF : taxa;

        // investimento de pessoa jurídica
        flag = cliente.getTipo() == TipoCliente.PJ && tipo == TipoConta.INVESTIMENTO;
        taxa = flag ? BaseDados.REND_INVE_PJ : taxa;

        saldo = saldo + Math.round( ((double) saldo) * taxa);
    }

    public void consultarSaldo() {
        System.out.println("Saldo da conta é: " + saldo);
    }

    public boolean valorNegativo(long valor, String contexto) {
        if (valor <= 0) {
            System.out.println("Somente valores positivos em " + contexto);
            return true;
        }

        return false;
    }

    public boolean saldoInsuficiente(long valor, String contexto) {
        if (valor > saldo) {
            System.out.println("Saldo insuficiente em " + contexto);
            return true;
        }

        return false;
    }

    public long taxaSaque(long valor) {
        double taxa = .00;

        if (cliente.getTipo() == TipoCliente.PJ) {
            taxa = BaseDados.TAXA_SAQU_TRAN_PJ;
        }

        return Math.round( ((double) valor) * taxa );
    }

    public long taxaTransferencia(long valor) {
        double taxa = .00;

        if (cliente.getTipo() == TipoCliente.PJ) {
            taxa = BaseDados.TAXA_SAQU_TRAN_PJ;
        }

        return Math.round( ((double) valor) * taxa );
    }

    public void criarConta() {

    }
}
