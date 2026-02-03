# 🤖 MoveRobot API

Este projeto foi desenvolvido como parte de um **desafio técnico**, com o objetivo de estudar e praticar **Spring Boot**, **boas práticas de API REST**, **validação**, **tratamento de exceções** e **testes automatizados**.

A aplicação simula a movimentação de um robô dentro de uma área limitada (5x5), recebendo comandos via API REST.

---

## 🧠 Desafio

O robô:

* Inicia sempre na posição **(0,0)**
* Começa voltado para o **Norte (N)**
* Move-se dentro de um grid **5x5**
* Não pode sair dos limites do grid

### Comandos aceitos

| Comando | Descrição                  |
| ------- | -------------------------- |
| `L`     | Gira 90° para a esquerda   |
| `R`     | Gira 90° para a direita    |
| `M`     | Move 1 posição para frente |

Os comandos são enviados como uma **string**, por exemplo:

```
MMRMM
```

---

## 🚀 Tecnologias utilizadas

* Java 17
* Spring Boot
* Spring Web MVC
* Bean Validation (Jakarta Validation)
* JUnit 5
* Mockito
* Maven
* JaCoCo (cobertura de testes)

---

## 📂 Estrutura do projeto

```
src
├── main
│   └── java
│       └── com.erycferreira.moverobot
│           ├── controller
│           ├── service
│           ├── dto
│           ├── exception
│           └── MoveRobotApplication.java
└── test
    └── java
        └── com.erycferreira.moverobot
            ├── controller
            ├── service
            ├── exception
            └── MoveRobotApplicationTests.java
```

A estrutura segue **separação clara de responsabilidades** e espelha `main` → `test`, conforme boas práticas do Spring.

---

## 📡 Endpoint disponível

### GET `/rest/mars/{position}`

**Exemplo de requisição:**

```
GET /rest/mars/MMRMM
```

### Resposta (200 OK)

```json
{
  "position": "(2,2,E)",
  "x": 2,
  "y": 2,
  "d": "E"
}
```

---

## ❌ Validações

A API valida:

* Campo obrigatório
* Tamanho mínimo e máximo da string
* Comandos permitidos (`L`, `R`, `M`)
* Movimento dentro dos limites do grid

### Exemplo de erro (400 Bad Request)

```json
{
  "error": "validation_error",
  "message": "to position only L,R or M"
}
```

---

## ⚠️ Tratamento de exceções

* Validações de entrada → `MethodArgumentNotValidException`
* Erros de domínio (robô fora do grid) → exceção customizada
* Tratamento centralizado via `@RestControllerAdvice`

Isso garante **respostas consistentes** e desacoplamento da lógica de negócio.

---

## 🧪 Testes automatizados

O projeto conta com:

* Testes unitários de Service
* Testes de Controller com `@WebMvcTest`
* Testes de Exception Handler
* Teste de carregamento de contexto

### Executar testes

```bash
mvn test
```

### Executar testes + cobertura

```bash
mvn clean verify
```

### Relatório de cobertura

Após o comando acima, abra no navegador:

```
target/site/jacoco/index.html
```

---

## 📈 Boas práticas aplicadas

✔ Stateless services
✔ DTOs imutáveis (records)
✔ Validação no boundary da aplicação
✔ Exceções de domínio
✔ Controller enxuto
✔ Testes rápidos e isolados
✔ Arquitetura limpa e didática

---

## 🎯 Objetivo do projeto

Este projeto **não tem foco em produção**, mas sim em:

* Aprendizado de Spring Boot
* Consolidação de conceitos de API REST
* Escrita de código limpo e testável
* Boas práticas arquiteturais

---

Projeto criado como parte de um desafio técnico e estudo contínuo em backend com Java e Spring.
