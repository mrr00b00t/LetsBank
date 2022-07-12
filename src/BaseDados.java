import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class BaseDados {
    private static BaseDados instancia;

    private ArrayList<Conta> contas;
    private ArrayList<Cliente> clientes;
    private Scanner input;

    private long dia;

    public static double REND_POUP = .005;
    public static double REND_INVE_PF = 0.01;
    public static double REND_INVE_PJ = REND_INVE_PF + 0.02;

    public static double TAXA_SAQU_TRAN_PJ = 0.005;

    private BaseDados() {
        contas = new ArrayList<>();
        clientes = new ArrayList<>();
        input = new Scanner(System.in);
        dia = 0;
    }

    public static synchronized BaseDados getInstancia() {
        if (instancia == null) {
            instancia = new BaseDados();
        }

        return instancia;
    }

    public Scanner getInput() {
        return input;
    }

    public boolean adicionarCliente(Cliente novoCliente) {
        boolean podeAdicionar = !clientes
                .stream()
                .anyMatch(c -> c.getDocumento().equals(novoCliente.getDocumento()));

        if (podeAdicionar) {
            novoCliente.setId(clientes.size());

            return clientes.add(novoCliente);
        }

        return false;
    }

    public boolean adicionarConta(Conta novaConta) {
        boolean podeAdicionar = !contas
                .stream()
                .filter(c -> c.getTipo() == novaConta.getTipo())
                .anyMatch(c -> c.getCliente().getDocumento().equals(novaConta.getCliente().getDocumento()));

        if (podeAdicionar) {
            novaConta.setId(contas.size());

            return contas.add(novaConta);
        }

        return false;
    }

    public Optional<Cliente> buscarCliente(String documento) {
        return clientes
                .stream()
                .filter(c -> c.getDocumento().equals(documento))
                .findFirst();
    }

    public Optional<Conta> buscarConta(TipoConta tipo, String documento) {
        return contas
                .stream()
                .filter(c -> c.getTipo() == tipo)
                .filter(c -> c.getCliente().getDocumento().equals(documento))
                .findFirst();
    }

    public void proximoDia() {
        dia = dia + 1;

        for (Conta conta : contas) {
            conta.render();
        }
    }
}
