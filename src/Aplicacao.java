public class Aplicacao {
    public static void main(String[] args) {
        String opcao;

        do {
            System.out.println("--------------------");
            System.out.println("---- Let's Bank ----");
            System.out.println("--------------------");
            System.out.println("Digite <INSERIR> para inserir um cliente");
            System.out.println("Digite <ABRIR> para abrir uma conta");
            System.out.println("Digite <SACAR> para sacar de uma conta");
            System.out.println("Digite <DEPOSITAR> para depositar em uma conta");
            System.out.println("Digite <TRANSFERIR> para transferir de uma conta para outra");
            System.out.println("Digite <CONSULTAR> para consultar saldo de uma conta");
            System.out.println("Digite <SAIR> para sair do sistema");

            System.out.print("Opção: ");
            opcao = BaseDados.getInstancia().getInput().nextLine().toLowerCase();

            switch (opcao) {
                case "inserir":
                    Cliente.criarCliente();
                    break;
                case "abrir":
                    Conta.criarConta();
                    break;
                case "sacar":
                    break;
                case "depositar":
                    break;
                case "transferir":
                    break;
                case "consultar":
                    Conta.consultarSaldo();
                    break;
                default:
                    System.out.println("Sistema fechado.");
            }



        } while (!opcao.equals("sair"));
    }
}