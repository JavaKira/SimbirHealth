# Основное задание:
1. Account URL: http://localhost:8081/api/swagger-ui/index.html
2. Hospital URL: http://localhost:8082/api/swagger-ui/index.html
3. Timetable URL: http://localhost:8083/api/swagger-ui/index.html
4. Document URL: http://localhost:8084/api/swagger-ui/index.html
# Дополнительная информация которую я захотите указать
1. В AccountController добавлен PATCH /api/Accounts/UpdatePassword для обновления пароля вместо PATCH /api/Accounts/Update
2. В некоторых контролерах эндпойнты, которые используются только админами переместились на */Admin, чтобы было удобнее матчить их в Spring Security