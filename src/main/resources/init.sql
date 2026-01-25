-- Movies (dodane: director, age_restriction)
INSERT INTO movie (id, title, genre, description, duration_in_minutes, director, age_restriction) VALUES
                                                                                                      ('11111111-1111-1111-1111-111111111111', 'Interstellar',    'Sci-Fi',         'Ziemia w niedalekiej przyszłości. Planeta jest sukcesywnie niszczona przez klęski suszy, a ludziom grozi wyginięcie. Nieoczekiwanie naukowcy odkrywają możliwość podróżowania poza granice Układu Słonecznego. Grupa astronautów wyrusza w najważniejszą w dziejach ludzkości podróż – muszą znaleźć miejsce, gdzie nasz gatunek będzie mógł przetrwać. Na czele załogi staje Cooper, były pilot NASA, który obecnie zajmuje się uprawą roli.', 169, 'Christopher Nolan', 13),
                                                                                                      ('22222222-2222-2222-2222-222222222222', 'Inception',       'Sci-Fi/Thriller', 'Dom Cobb jest niezwykle sprawnym złodziejem, mistrzem w wydobywaniu wartościowych sekretów ukrytych głęboko w świadomości podczas fazy snu, kiedy umysł jest najbardziej wrażliwy. Wyjątkowe umiejętności Cobba uczyniły z niego ważnego gracza w świecie szpiegostwa przemysłowego, ale i najbardziej poszukiwanego zbiega, a za swoją pozycję zapłacił utratą wszystkiego, co kocha. Teraz Cobb otrzymuje szansę na odkupienie. Za sprawą jednego, ostatniego zadania może odzyskać stracone życie. Musi tylko wraz ze swym zespołem dokonać rzeczy niemożliwej: zamiast skraść myśl, zaszczepić ją w śpiącym umyśle. Jeśli im się to uda, dokonają zbrodni doskonałej. Jednak nawet najbardziej precyzyjne planowanie nie jest w stanie przygotować ich na spotkanie z niezwykłym przeciwnikiem, który potrafi przewidzieć każdy ich ruch. Wróg, którego tylko Cobb mógł się spodziewać.',              148, 'Christopher Nolan', 16),
                                                                                                      ('33333333-3333-3333-3333-333333333333', 'The Dark Knight', 'Action/Crime',    'Kontynuacja filmu "Batman: Początek", "Mroczny Rycerz", to kolejne wspólne dzieło reżysera Christophera Nolana i gwiazdora Christiana Bale''a, który ponownie wciela się w postać Batmana / Bruce''a Wayne''a toczącego zażartą wojnę ze światem przestępczym. Batmanowi udaje się na dobre rozbić kryminalne podziemie Gotham z pomocą porucznika Jima Gordona (Gary Oldman) i Prokuratora Okręgowego Harveya Denta (Aaron Eckhart). Ich współpraca okazuje się niezwykle skuteczna, ale szybko trzej mężczyźni stają się celem nowego geniusza zbrodni, Jokera (Heath Ledger), którego celem jest wprowadzenie w Gotham anarchii i zmuszenie Batmana, by ten przeszedł na stronę zła, przestał być bohaterem, a stał się złoczyńcą.',               152, 'Christopher Nolan', 16);

-- Actors (ElementCollection: movie_actors)
INSERT INTO movie_actors (movie_id, actors) VALUES
                                                ('11111111-1111-1111-1111-111111111111', 'Matthew McConaughey'),
                                                ('11111111-1111-1111-1111-111111111111', 'Anne Hathaway'),
                                                ('11111111-1111-1111-1111-111111111111', 'Jessica Chastain'),

                                                ('22222222-2222-2222-2222-222222222222', 'Leonardo DiCaprio'),
                                                ('22222222-2222-2222-2222-222222222222', 'Joseph Gordon-Levitt'),
                                                ('22222222-2222-2222-2222-222222222222', 'Elliot Page'),

                                                ('33333333-3333-3333-3333-333333333333', 'Christian Bale'),
                                                ('33333333-3333-3333-3333-333333333333', 'Heath Ledger'),
                                                ('33333333-3333-3333-3333-333333333333', 'Aaron Eckhart');

-- Trailers (ElementCollection: movie_trailers)
INSERT INTO movie_trailers (movie_id, trailers) VALUES
                                                    ('11111111-1111-1111-1111-111111111111', 'https://www.youtube.com/watch?v=zSWdZVtXT7E'),
                                                    ('22222222-2222-2222-2222-222222222222', 'https://www.youtube.com/watch?v=YoHD9XEInc0'),
                                                    ('33333333-3333-3333-3333-333333333333', 'https://www.youtube.com/watch?v=EXeTwQWrcwY');

-- Gallery (ElementCollection: movie_gallery)
INSERT INTO movie_gallery (movie_id, gallery) VALUES
                                                  ('11111111-1111-1111-1111-111111111111', 'https://image.tmdb.org/t/p/w500/8ZTVqvKDQ8emSGUEMjsS4yHAwrp.jpg'),
                                                  ('11111111-1111-1111-1111-111111111111', 'https://image.tmdb.org/t/p/w500/rAiYTfKGqDCRIIqo664sY9XZIvQ.jpg'),
                                                  ('11111111-1111-1111-1111-111111111111', 'https://image.tmdb.org/t/p/original/gg12Nnz7YETfC2Nwb6jGM5sif6X.jpg'),

                                                  ('22222222-2222-2222-2222-222222222222', 'https://image.tmdb.org/t/p/original/lvHNQSGdjxyW2n0rYCqb40NuCh4.jpg'),
                                                  ('22222222-2222-2222-2222-222222222222', 'https://image.tmdb.org/t/p/original/iDqeLorp5WBynVaRFqMbMgD7buu.jpg'),
                                                  ('22222222-2222-2222-2222-222222222222', 'https://image.tmdb.org/t/p/original/zPZ4virixj8Rq8WqhxcarroDutk.jpg'),

                                                  ('33333333-3333-3333-3333-333333333333', 'https://image.tmdb.org/t/p/original/nMKdUUepR0i5zn0y1T4CsSB5chy.jpg'),
                                                  ('33333333-3333-3333-3333-333333333333', 'https://image.tmdb.org/t/p/original/plDp52MirFHc2PMJRMNWoG0kfr3.jpg'),
                                                  ('33333333-3333-3333-3333-333333333333', 'https://image.tmdb.org/t/p/original/qejZvo4zzL6KQ74h2IVnfGDbeQj.jpg');

-- Rooms
INSERT INTO room (id, name) VALUES
                                ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Sala A'),
                                ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'Sala B');

-- Seats
INSERT INTO seat (id, row_num, seat_num, reserved) VALUES
                                                       -- Sala A
                                                       ('a0000000-0000-0000-0000-000000000001', 1, 1, FALSE),
                                                       ('a0000000-0000-0000-0000-000000000002', 1, 2, FALSE),
                                                       ('a0000000-0000-0000-0000-000000000003', 1, 3, FALSE),
                                                       ('a0000000-0000-0000-0000-000000000004', 1, 4, FALSE),
                                                       ('a0000000-0000-0000-0000-000000000005', 1, 5, FALSE),
                                                       ('a0000000-0000-0000-0000-000000000006', 2, 1, FALSE),
                                                       ('a0000000-0000-0000-0000-000000000007', 2, 2, TRUE),
                                                       ('a0000000-0000-0000-0000-000000000008', 2, 3, TRUE),
                                                       ('a0000000-0000-0000-0000-000000000009', 2, 4, FALSE),
                                                       ('a0000000-0000-0000-0000-00000000000a', 2, 5, FALSE),

                                                       -- Sala B
                                                       ('b0000000-0000-0000-0000-000000000001', 1, 1, TRUE),
                                                       ('b0000000-0000-0000-0000-000000000002', 1, 2, TRUE),
                                                       ('b0000000-0000-0000-0000-000000000003', 1, 3, TRUE),
                                                       ('b0000000-0000-0000-0000-000000000004', 1, 4, FALSE),
                                                       ('b0000000-0000-0000-0000-000000000005', 1, 5, FALSE),
                                                       ('b0000000-0000-0000-0000-000000000006', 2, 1, FALSE),
                                                       ('b0000000-0000-0000-0000-000000000007', 2, 2, FALSE),
                                                       ('b0000000-0000-0000-0000-000000000008', 2, 3, FALSE),
                                                       ('b0000000-0000-0000-0000-000000000009', 2, 4, FALSE),
                                                       ('b0000000-0000-0000-0000-00000000000a', 2, 5, FALSE);

-- Join table Room <-> Seats (unidirectional OneToMany)
INSERT INTO room_seats (room_id, seats_id) VALUES
                                               -- Sala A
                                               ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'a0000000-0000-0000-0000-000000000001'),
                                               ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'a0000000-0000-0000-0000-000000000002'),
                                               ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'a0000000-0000-0000-0000-000000000003'),
                                               ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'a0000000-0000-0000-0000-000000000004'),
                                               ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'a0000000-0000-0000-0000-000000000005'),
                                               ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'a0000000-0000-0000-0000-000000000006'),
                                               ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'a0000000-0000-0000-0000-000000000007'),
                                               ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'a0000000-0000-0000-0000-000000000008'),
                                               ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'a0000000-0000-0000-0000-000000000009'),
                                               ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'a0000000-0000-0000-0000-00000000000a'),

                                               -- Sala B
                                               ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'b0000000-0000-0000-0000-000000000001'),
                                               ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'b0000000-0000-0000-0000-000000000002'),
                                               ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'b0000000-0000-0000-0000-000000000003'),
                                               ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'b0000000-0000-0000-0000-000000000004'),
                                               ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'b0000000-0000-0000-0000-000000000005'),
                                               ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'b0000000-0000-0000-0000-000000000006'),
                                               ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'b0000000-0000-0000-0000-000000000007'),
                                               ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'b0000000-0000-0000-0000-000000000008'),
                                               ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'b0000000-0000-0000-0000-000000000009'),
                                               ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'b0000000-0000-0000-0000-00000000000a');

-- Showtimes
INSERT INTO showtime (id, movie_id, room_id, start_time) VALUES
                                                             ('99999999-0000-0000-0000-000000000001', '11111111-1111-1111-1111-111111111111', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '2026-01-27 18:00:00'),
                                                             ('99999999-0000-0000-0000-000000000002', '33333333-3333-3333-3333-333333333333', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '2026-01-26 20:30:00'),
                                                             ('99999999-0000-0000-0000-000000000003', '22222222-2222-2222-2222-222222222222', 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', '2026-01-27 19:15:00');

-- Reservations
INSERT INTO reservation (id, movie_name, start_time, total_price) VALUES
                                                                      ('44444444-4444-4444-4444-444444444444', 'Interstellar', TIMESTAMP '2026-01-17 18:00:00', 40.00),
                                                                      ('55555555-5555-5555-5555-555555555555', 'Inception',    TIMESTAMP '2026-01-18 19:15:00', 20.00);

INSERT INTO reservation_seats (reservation_id, seats) VALUES
                                                          ('44444444-4444-4444-4444-444444444444', 'a0000000-0000-0000-0000-000000000006'),
                                                          ('44444444-4444-4444-4444-444444444444', 'a0000000-0000-0000-0000-000000000007'),
                                                          ('55555555-5555-5555-5555-555555555555', 'b0000000-0000-0000-0000-000000000001');
-- Users
INSERT INTO users (id, username, password, roles) VALUES
    (
        'eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee',
        'admin',
        '$2a$12$VxQLnFp9t3ZBN.Lw5PuMVervJa2Mju9cFu3fhEtak88s6cEtBEsHi',
        'ROLE_ADMIN'
    );