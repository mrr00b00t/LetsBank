public class Cliente {
    private long id;
    private String nome;
    private String documento;
    private TipoCliente tipo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public TipoCliente getTipo() {
        return tipo;
    }

    public void setTipo(TipoCliente tipo) {
        this.tipo = tipo;
    }

    public void criarCliente() {
        String novoNome;
        System.out.println("Digite o nome do cliente:");
        novoNome = BaseDados.getInstancia().getInput().nextLine();

        String novoDocumento;
        System.out.println("Digite o documento do cliente:");
        novoDocumento = BaseDados.getInstancia().getInput().nextLine();

        int novoTipo;
        System.out.println("Digite o tipo do cliente (0 - PF, 1 - PJ:");
        novoTipo = Integer.parseInt(BaseDados.getInstancia().getInput().nextLine());

        if (novoTipo < 0 || novoTipo > 1) {
            System.out.println("Cliente não adicionado: Tipo precisar ser '0' ou '1'!");
            return;
        }

        Cliente novoCliente = new Cliente();
        novoCliente.setNome(novoNome);
        novoCliente.setDocumento(novoDocumento);
        novoCliente.setTipo(novoTipo == 0 ? TipoCliente.PF : TipoCliente.PJ);

        if (BaseDados.getInstancia().adicionarCliente(novoCliente)) {
            System.out.println("Cliente criado com sucesso!");
        } else {
            System.out.println("Cliente já existe na base de dados!");
        }
    }
}
