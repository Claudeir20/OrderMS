# OrderMS — Microsserviço de Pedidos

Microsserviço responsável pelo gerenciamento de pedidos, desenvolvido com Java e Spring Boot seguindo os princípios da Clean Architecture e DDD.

## Tecnologias
- Java 25  
- Spring Boot 4  
- Spring Data JPA  
- PostgreSQL  
- Flyway  
- RabbitMQ  
- Bean Validation  
- JUnit 5 + Mockito  

## Arquitetura
O projeto segue **Clean Architecture**, separando responsabilidades em duas camadas principais:

- `core/` → regras de negócio, sem dependência externa  
- `infra/` → banco de dados, controllers, JPA, handlers  

Fluxo principal de requisição:  
`Controller → Use Case → Gateway → Banco`

## Estrutura de Pastas
```
src/main/java/com/example/OrderMS/
├── core/
│   ├── entities/
│   │   └── Order.java
│   ├── entities/enums/
│   │   └── StatusEnum.java
│   ├── events/
│   │   ├── DomainEvent.java
│   │   ├── EventPublisher.java
│   │   ├── OrderCreated.java
│   │   ├── OrderConfirmed.java
│   │   └── OrderCancelled.java
│   ├── exceptions/
│   │   ├── DomainException.java
│   │   ├── OrderNotFoundException.java
│   │   ├── OrderValidationException.java
│   │   └── OrderInvalidStateException.java
│   ├── gateways/
│   │   └── OrderGateway.java
│   └── usecases/
│       ├── create/
│       │   ├── CreateOrderUseCase.java
│       │   ├── CreateOrderInput.java
│       │   ├── CreateOrderOutput.java
│       │   └── CreateOrderUseCaseImpl.java
│       ├── cancel/
│       │   ├── CancelOrderUseCase.java
│       │   └── CancelOrderUseCaseImpl.java
│       ├── confirm/
│       │   ├── ConfirmOrderUseCase.java
│       │   └── ConfirmOrderUseCaseImpl.java
│       └── getorder/
│           ├── GetOrderByIdUseCase.java
│           └── GetOrderByIdUseCaseImpl.java
│
└── infra/
    ├── controller/
    │   └── OrderController.java
    ├── config/
    │   └── UseCaseConfig.java
    ├── model/
    │   └── OrderEntity.java
    ├── repository/
    │   └── OrderRepository.java
    ├── gateway/
    │   └── OrderRepositoryGateway.java
    ├── mapper/
    │   └── OrderMapper.java
    └── events/
        ├── SpringEventPublisher.java
        ├── OrderConfirmedHandler.java
        ├── OrderConfirmedEmailHandler.java
        ├── OrderConfirmedStockHandler.java
        └── OrderCreatedHandler.java
```

## Casos de Uso
| Use Case | Descrição |
|---------|----------|
| CreateOrder | Cria um novo pedido |
| CancelOrder | Cancela um pedido pendente |
| ConfirmOrder | Confirma um pedido pendente |
| GetOrderById | Busca um pedido pelo ID |

## Regras de Negócio
- Quantidade deve ser maior que 0  
- Preço deve ser maior que 0  
- Usuário e produto são obrigatórios  
- Pedido começa com status `PENDING`  
- Pedido `CONFIRMED` não pode ser cancelado  
- Pedido `CANCELLED` não pode ser confirmado  
- Pedido `CANCELLED` ou `CONFIRMED` não pode ter quantidade alterada  

## Status do Pedido
- `PENDING → CONFIRMED`  
- `PENDING → CANCELLED`  

## Domain Events
Eventos de domínio publicados após operações principais:
- `OrderCreated`
- `OrderConfirmed`
- `OrderCancelled`

Handlers na infra reagem aos eventos (logs e placeholders para integrações).

## Testes
Testes unitários cobrem entidade e casos de uso, sem banco ou Spring Context.

```
.\mvnw.cmd test
```

| Classe | Cobertura |
|--------|----------|
| OrderTest | Regras de negócio da entidade |
| CreateOrderUseCaseImplTest | Criação de pedido |
| CancelOrderUseCaseImplTest | Cancelamento de pedido |
| ConfirmOrderUseCaseImplTest | Confirmação de pedido |

## Comunicação entre Microsserviços
A comunicação com outros microsserviços (Produtos e Usuários) é feita via RabbitMQ. *(em desenvolvimento)*

## Como Rodar

```bash
# Clonar o repositório
git clone https://github.com/seu-usuario/OrderMS.git

# Entrar na pasta
cd OrderMS

# Rodar os testes
.\mvnw.cmd test

# Subir a aplicação
.\mvnw.cmd spring-boot:run
```
