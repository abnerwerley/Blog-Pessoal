# Projeto_blog_pessoal

Para que serve?
  Esse é um projeto que será blog em que as pessoas poderão se cadastrar para fazer postagens sobre temas diversos, que vou usar para fazer postagens sobre mim, para acrescentar 
  no meu portifólio, e conferir através da utilização como usuário se está funcionando ou precisa de manutenção, além de poder imprementar idéias novas no programa.

Descrição geral da organização:

* Os Models são responsáveis por:
- Criar as respectivas tabelas
- Condições (usando o validation) que ela deve atender.
 Ex: private @NotBlank @Size(min = 5, max = 100) String titulo;
- Conter como essa tabela vai se relacionar com as demais
- Getters e setters.

* Os Repositórios são responsáveis por fazer as pesquisas por essas tabelas para realizar determinada tarefa no controller ou no services.
  Ex: Para retornar lista com os usuários existentes pesquisando pelo nome:
  public List<Usuario> findByNomeContainingIgnoreCase(String nome);
  
  Para retornar se existe ou não:
  public Optional<Usuario> findByEmail(String email);

* Os controlladores realização a mediação entre os pedidos do usuário às respostas e ações a serem realizadas.
  Ex: se um usuário quer apagar uma postagem feita, será usado a pesquisa pelo id da postagem no banco de dados para o controlador poder apagá-la, e dar a resposta para o usuário

* Os Services são responsáveis pelas regras de negócio, como não cadastrar um usuário novo se o email informado já foi cadastrado anterior, não ter dois 
  usuários com exatamente o mesmo nome, dentre outras regras.
  
