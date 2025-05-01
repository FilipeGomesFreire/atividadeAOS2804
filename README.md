# atividadeAOS2804
atividade da disciplina Aplicações Orientadas a Serviço com vencimento até o dia 28/04/25


Liana: criou o projeto, fez todos os models e repositorys

Filipe: fez todos os services e controllers, ajustou Readme mostrando os endpoints, o projeto ja ta funcionando pelo codespace, tive q adicionar algumas cooisa pra ele funcionar pq ta com algum problema no banco de dados. ele ta funcionando com H2 ou seja,

 tem q deletar do pom.xml isso de baixo e ajustar o aplication.propries pra ele ficar online

<dependency>
    <groupId>com.h2database</groupId>
    <artifactId>h2</artifactId>
    <scope>runtime</scope>
</dependency>


nada ta online ainda, então isso precisa ser ajustado
o aplication.propries ja ta com os dados do banco de dados


# para o postman
## 📝 Endpoints 

| Método  | Rota                 | Descrição                        |
|---------|----------------------|----------------------------------|
| GET     | `/atividades`        | Lista todas as atividades        |
| GET     | `/atividades/{id}`   | Busca uma atividade pelo ID      |
| POST    | `/atividades`        | Cria uma nova atividade          |
| PUT     | `/atividades/{id}`   | Atualiza uma atividade existente |
| DELETE  | `/atividades/{id}`   | Deleta uma atividade pelo ID     |
| |
| GET     | `/criancas`          | Lista todas as crianças          |
| GET     | `/criancas/{id}`     | Busca uma criança pelo ID        |
| POST    | `/criancas`          | Cria uma nova criança            |
| PUT     | `/criancas/{id}`     | Atualiza uma criança existente   |
| DELETE  | `/criancas/{id}`     | Deleta uma criança pelo ID       |
| |
| GET     | `/diagnosticos`      | Lista todos os diagnósticos      |
| GET     | `/diagnosticos/{id}` | Busca um diagnóstico pelo ID     |
| POST    | `/diagnosticos`      | Cria um novo diagnóstico         |
| PUT     | `/diagnosticos/{id}` | Atualiza um diagnóstico existente|
| DELETE  | `/diagnosticos/{id}` | Deleta um diagnóstico pelo ID    |
| |
| GET     | `/responsaveis`      | Lista todos os responsáveis      |
| GET     | `/responsaveis/{id}` | Busca um responsável pelo ID     |
| POST    | `/responsaveis`      | Cria um novo responsável         |
| PUT     | `/responsaveis/{id}` | Atualiza um responsável          |
| DELETE  | `/responsaveis/{id}` | Deleta um responsável pelo ID    |
| |