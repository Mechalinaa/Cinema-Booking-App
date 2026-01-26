# Cinema-Booking-App

Z tym pomogę:

Dokumentacja (Swagger/OpenAPI)

Konteneryzacja (Docker) Przygotowanie Dockerfile dla aplikacji oraz docker-compose.yml stawiającego bazę danych i aplikację.

Testy:

Testy Integracyjne (IT) Testy kontrolerów (@SpringBootTest + MockMvc). 
Weryfikacja ścieżki HTTP -> DB (H2/Testcontainers). 

Testy Repozytorium (Integration) Testy integracyjne warstwy danych (@JdbcTest lub @SpringBootTest). 
Weryfikacja, czy zapytania SQL w JdbcTemplate poprawnie pobierają/zapisują dane w bazie H2/Testcontainers. 

E2E z Selenium Test automatyczny Selenium WebDriver. 
Uruchomienie aplikacji i weryfikacja "Happy Path" przez przeglądarkę (np. na Swagger UI). 

Pokrycie Kodu (Coverage) Raport z narzędzia (np. JaCoCo) wykazujący pokrycie kodu testami na poziomie minimum 80%.