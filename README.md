# Wallet Service

REST API для управления балансом кошелька.

## Как запустить
1.  Установить PostgreSQL
2.  Запустить службу PostgreSQL
3.  В файле src/main/resources/application.properties указать пароль от БД
4.  Запустить Application в IDE

## Примеры запросов

### Пополнить кошелёк
```bash
curl -X POST http://localhost:8080/api/v1/wallet \\
-H "Content-Type: application/json" \\
-d '{
  "walletId": "550e8400-e29b-41d4-a716-446655440000",
  "operationType": "DEPOSIT", 
  "amount": 1000
}'