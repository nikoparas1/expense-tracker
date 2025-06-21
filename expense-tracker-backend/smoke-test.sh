# 1) Register & login to get a JWT
curl -s -X POST http://localhost:8080/auth/register \
  -H "Content-Type: application/json" \
  -d '{"username":"testuser","email":"t@t.com","password":"pass"}'

TOKEN=$(curl -s -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"usernameOrEmail":"testuser","password":"pass"}' |
  jq -r .token)

# 2) Create an expense
curl -s -X POST http://localhost:8080/api/expense \
  -H "Authorization: Bearer $TOKEN" \
  -H "Content-Type: application/json" \
  -d '{"expenseName":"Coffee","expenseCategory":"Food","expenseAmount":3.50}'

# 3) List all expenses
curl -s -X GET http://localhost:8080/api/expense \
  -H "Authorization: Bearer $TOKEN"

# 4) Fetch, update, delete by ID (replace {id} with actual)
curl -s -X GET http://localhost:8080/api/expense/1 -H "Authorization: Bearer $TOKEN"
curl -s -X PUT http://localhost:8080/api/expense -H "Authorization: Bearer $TOKEN" -H "Content-Type: application/json" \
  -d '{"id":1,"expenseName":"Tea","expenseCategory":"Drinks","expenseAmount":2.75}'
curl -s -X DELETE http://localhost:8080/api/expense/1 -H "Authorization: Bearer $TOKEN"
