## Usando o Docker

Para que haja facilidade no desenvolvimento deste projeto, ele está usando Docker. Instale o Docker, ou claro, use Postgres, Java e Gradle nativo.

Docker é uma plataforma de software que permite que você crie, teste e implante aplicativos rapidamente.
Ele funciona em um conceito de contêineres - unidades isoladas de software que contêm tudo que um aplicativo
precisa para ser executado. Cada contêiner é criado a partir de uma imagem, que é um pacote de software
completo que contém todo o código, bibliotecas e dependências necessárias para executar um aplicativo.                                                                              

O Docker Compose é uma ferramenta que permite que você defina e execute aplicativos Docker de vários contêineres
Ele usa um arquivo YAML para definir as configurações de cada contêiner e como eles se comunicam uns com os
outros.

neste projeto, existem dois serviços: um para o aplicativo e outro para a base de dados.

### Como Rodar Tudo

Basta rodar o seguinte:

```bash
docker compose up -d --build
```

Isso vai subir o Banco e a Api, garantindo que ela está atualizada com o `--build`

### Redes

Os containeres estão conectados em uma rede chamada "api-db". É necessária esta rede para que haja conexão do 
Spring com o Banco de Dados.

### Portas

Cada Container Expôe uma porta. Para saber qual porta deve ser exposta, devemos conhecer o projeto. por exemplo,
sabemos que o Postgres roda na 5432 e o Spring Boot, por padrão, na 8080.

### Env

Para configurar certas imagens, rodamos elas passando Variáveis de Ambiente para dentro delas. Mais uma vez,
devemos procurar na página oficial do Docker Hub da Imagem específica (Se a imagem não fornece isso, não é
recomendado usar ela em primeiro lugar). No caso do Postgres, a única obrigatória é `POSTGRES_PASSWORD`, que
define a senha para se conectar no Banco. O usuário é sempre `postgres`. Nesta App, em específico, precisamos
que ao menos um banco existe, o `loja`, por isso ele também é definido na variável `POSTGRES_DB`

### Exemplo Final do docker-compose.yml

```yaml
services:
  app:
    build: .
    ports:
      - 8080:8080
    depends_on:
      - database
    networks:
      - api-db
  database:
    image: postgres
    environment:
      - POSTGRES_DB=loja
      - POSTGRES_PASSWORD=Teste01@
    ports:
      - 5432:5432
    networks:
      - api-db
    volumes:
      - db-data:/var/lib/postgresql/data                                                                                                                                                                                                                  
volumes:                                                                                                                                                                                                                                                  
  db-data:
networks:
  api-db:
```

### Comandos docker, caso não quiser fazer por Compose

- Construir imagem local, com o nome `api` (Fazer na raiz do projeto)!

```bash
docker build -t api .
```
- Rodar imagem `api` (Rodar após pelo menos buildar uma vez)

```bash
# Precisamos de "-d" para não prender nosso terminal
docker run -d -p 8080:8080 --name api-spring --network api-db api
```

- Rodar banco de dados

```bash
docker run -d -p 5432:5432 --network api-db --name database --volume db-data postgres
```

- Testar banco de dados:

```bash
# Após rodar, digite a senha certa
docker exec -it database psql -U postgres -h localhost -W
```