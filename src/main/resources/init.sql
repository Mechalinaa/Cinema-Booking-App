-- Movies (dodane: director, age_restriction)
INSERT INTO movie (id, title, genre, description, duration_in_minutes, director, age_restriction)
VALUES ('11111111-1111-1111-1111-111111111111', 'Interstellar', 'Sci-Fi',
        'Ziemia w niedalekiej przyszłości. Planeta jest sukcesywnie niszczona przez klęski suszy, a ludziom grozi wyginięcie. Nieoczekiwanie naukowcy odkrywają możliwość podróżowania poza granice Układu Słonecznego. Grupa astronautów wyrusza w najważniejszą w dziejach ludzkości podróż – muszą znaleźć miejsce, gdzie nasz gatunek będzie mógł przetrwać. Na czele załogi staje Cooper, były pilot NASA, który obecnie zajmuje się uprawą roli.',
        169, 'Christopher Nolan', 13),
       ('22222222-2222-2222-2222-222222222222', 'Inception', 'Sci-Fi/Thriller',
        'Dom Cobb jest niezwykle sprawnym złodziejem, mistrzem w wydobywaniu wartościowych sekretów ukrytych głęboko w świadomości podczas fazy snu, kiedy umysł jest najbardziej wrażliwy. Wyjątkowe umiejętności Cobba uczyniły z niego ważnego gracza w świecie szpiegostwa przemysłowego, ale i najbardziej poszukiwanego zbiega, a za swoją pozycję zapłacił utratą wszystkiego, co kocha. Teraz Cobb otrzymuje szansę na odkupienie. Za sprawą jednego, ostatniego zadania może odzyskać stracone życie. Musi tylko wraz ze swym zespołem dokonać rzeczy niemożliwej: zamiast skraść myśl, zaszczepić ją w śpiącym umyśle. Jeśli im się to uda, dokonają zbrodni doskonałej. Jednak nawet najbardziej precyzyjne planowanie nie jest w stanie przygotować ich na spotkanie z niezwykłym przeciwnikiem, który potrafi przewidzieć każdy ich ruch. Wróg, którego tylko Cobb mógł się spodziewać.',
        148, 'Christopher Nolan', 16),
       ('33333333-3333-3333-3333-333333333333', 'The Dark Knight', 'Action/Crime',
        'Kontynuacja filmu "Batman: Początek", "Mroczny Rycerz", to kolejne wspólne dzieło reżysera Christophera Nolana i gwiazdora Christiana Bale''a, który ponownie wciela się w postać Batmana / Bruce''a Wayne''a toczącego zażartą wojnę ze światem przestępczym. Batmanowi udaje się na dobre rozbić kryminalne podziemie Gotham z pomocą porucznika Jima Gordona (Gary Oldman) i Prokuratora Okręgowego Harveya Denta (Aaron Eckhart). Ich współpraca okazuje się niezwykle skuteczna, ale szybko trzej mężczyźni stają się celem nowego geniusza zbrodni, Jokera (Heath Ledger), którego celem jest wprowadzenie w Gotham anarchii i zmuszenie Batmana, by ten przeszedł na stronę zła, przestał być bohaterem, a stał się złoczyńcą.',
        152, 'Christopher Nolan', 16),
       ('44444444-4444-4444-4444-444444444444', 'Dune', 'Sci-Fi',
        'Diuna opowiada historię niezwykłej, pełnej przygód i emocji podróży Paula Atreidesa (Timothée Chalamet). Temu genialnemu i utalentowanemu młodemu człowiekowi pisane jest wspaniałe przeznaczenie, którego on sam nie jest w stanie pojąć. Najpierw jednak Paul musi się udać na najbardziej niebezpieczną planetę we wszechświecie, żeby ratować rodzinę i rodaków. Na planecie wybucha zażarty konflikt o dostęp do niewystępującej nigdzie indziej, najcenniejszej ze znanych substancji. Wyzwala ona w ludziach ich największy potencjał. Ale wojnę przetrwają tylko ci, którzy pokonają swój strach.', 155, 'Denis Villeneuve', 13),
       ('55555555-5555-5555-5555-555555555555', 'Fight Club', 'Drama',
        'Co ty możesz o sobie wiedzieć, jeśli nigdy nie walczyłeś? W tym niezwykłym, pełnym niespodziewanych zwrotów akcji i nie pozbawionym swoistego humoru filmie w reżyserii Davida Finchera ("Siedem") oryginalne i dynamiczne kreacje stworzyli Brad Pitt ("Siedem") i Edward Norton ("Lęk pierwotny"). Jack (Norton) cierpi na chroniczną bezsenność i jest całkowicie znudzony swym dotychczasowym życiem. Do czasu, gdy spotyka charyzmatycznego Tylera Durdena (Pitt) - sprzedawcę mydła o dość pokrętnej filozofii życia... Uważa on bowiem, że samo-doskonalenie jest dla słabeuszy, a to co rzeczywiście sprawia, że warto żyć to samo-destrukcja.', 139, 'David Fincher', 18),
       ('66666666-6666-6666-6666-666666666666', 'The Matrix', 'Sci-Fi/Action',
        'Thomas Anderson wiedzie podwójne życie - na co dzień pracuje w koncernie Meta Cortechs jako programista, prywatnie jest genialnym hackerem, działającym pod nickiem Neo. Pewnego dnia nawiązuje z nim kontakt tajemniczy Morfeusz - człowiek, który obiecuje mu odkryć prawdę o rzeczywistości, w jakiej żyją. Prawdę o Matriksie. Prawdę o dwóch światach: prawdziwym i wygenerowanym, który ma tylko "udawać" rzeczywistość. Neo początkowo wbrew sobie przystaje do grupy kierowanej przez Morfeusza, stopniowo jednak sam zaczyna dostrzegać, że ze światem, w którym żył dotychczas, jest coś nie tak. Że jego życiem cały czas ktoś kierował. Kolejne stopnie wtajemniczenia stawiają przed Neo nowe pytania. Który ze światów jest prawdziwy? Czym jest Matrix i komu służy? I jaką rolę w planie Morfeusza ma do spełnienia sam Neo?', 136, 'Wachowscy', 16),
       ('77777777-7777-7777-7777-777777777777', 'Gladiator', 'Action/Drama',
        'Generał Maximus zakończył kolejną udaną wyprawę wojenną. Zmuszony do ucieczki przez zazdrosnego o jego wpływy u Cesarza dziedzica tronu Commodusa, Maximus trafia do niewoli i zostaje gladiatorem. Powraca do Rzymu z zamiarem pomszczenia śmierci syna i żony. Wie, że jedyną siłą potężniejszą od samego Cesarza jest wola ludu, może dokonać swej zemsty, zostając największym bohaterem całego Imperium. "Gladiator" to epicka opowieść o odwadze i zemście osadzona w realiach Cesarstwa Rzymskiego roku 180 n.e.', 155, 'Ridley Scott', 16);

-- Actors (ElementCollection: movie_actors)
INSERT INTO movie_actors (movie_id, actors)
VALUES ('11111111-1111-1111-1111-111111111111', 'Matthew McConaughey'),
       ('11111111-1111-1111-1111-111111111111', 'Anne Hathaway'),
       ('11111111-1111-1111-1111-111111111111', 'Jessica Chastain'),

       ('22222222-2222-2222-2222-222222222222', 'Leonardo DiCaprio'),
       ('22222222-2222-2222-2222-222222222222', 'Joseph Gordon-Levitt'),
       ('22222222-2222-2222-2222-222222222222', 'Elliot Page'),

       ('33333333-3333-3333-3333-333333333333', 'Christian Bale'),
       ('33333333-3333-3333-3333-333333333333', 'Heath Ledger'),
       ('33333333-3333-3333-3333-333333333333', 'Aaron Eckhart'),

       ('44444444-4444-4444-4444-444444444444', 'Timothée Chalamet'),
       ('44444444-4444-4444-4444-444444444444', 'Zendaya'),
       ('44444444-4444-4444-4444-444444444444', 'Rebecca Ferguson'),

       ('55555555-5555-5555-5555-555555555555', 'Brad Pitt'),
       ('55555555-5555-5555-5555-555555555555', 'Edward Norton'),
       ('55555555-5555-5555-5555-555555555555', 'Helena Bonham Carter'),

       ('66666666-6666-6666-6666-666666666666', 'Keanu Reeves'),
       ('66666666-6666-6666-6666-666666666666', 'Carrie-Anne Moss'),
       ('66666666-6666-6666-6666-666666666666', 'Laurence Fishburne'),

       ('77777777-7777-7777-7777-777777777777', 'Russell Crowe'),
       ('77777777-7777-7777-7777-777777777777', 'Joaquin Phoenix'),
       ('77777777-7777-7777-7777-777777777777', 'Connie Nielsen');

-- Trailers (ElementCollection: movie_trailers)
INSERT INTO movie_trailers (movie_id, trailers)
VALUES ('11111111-1111-1111-1111-111111111111', 'https://www.youtube.com/watch?v=zSWdZVtXT7E'),
       ('22222222-2222-2222-2222-222222222222', 'https://www.youtube.com/watch?v=YoHD9XEInc0'),
       ('33333333-3333-3333-3333-333333333333', 'https://www.youtube.com/watch?v=EXeTwQWrcwY'),
       ('44444444-4444-4444-4444-444444444444', 'https://www.youtube.com/watch?v=n9xhJrPXop4'),
       ('55555555-5555-5555-5555-555555555555', 'https://www.youtube.com/watch?v=SUXWAEX2jlg'),
       ('66666666-6666-6666-6666-666666666666', 'https://www.youtube.com/watch?v=vKQi3bBA1y8'),
       ('77777777-7777-7777-7777-777777777777', 'https://www.youtube.com/watch?v=owK1qxDselE');

-- Gallery (ElementCollection: movie_gallery)
INSERT INTO movie_gallery (movie_id, gallery)
VALUES ('11111111-1111-1111-1111-111111111111', 'https://image.tmdb.org/t/p/w500/8ZTVqvKDQ8emSGUEMjsS4yHAwrp.jpg'),
       ('11111111-1111-1111-1111-111111111111', 'https://image.tmdb.org/t/p/w500/rAiYTfKGqDCRIIqo664sY9XZIvQ.jpg'),
       ('11111111-1111-1111-1111-111111111111', 'https://image.tmdb.org/t/p/original/gg12Nnz7YETfC2Nwb6jGM5sif6X.jpg'),

       ('22222222-2222-2222-2222-222222222222', 'https://image.tmdb.org/t/p/original/lvHNQSGdjxyW2n0rYCqb40NuCh4.jpg'),
       ('22222222-2222-2222-2222-222222222222', 'https://image.tmdb.org/t/p/original/iDqeLorp5WBynVaRFqMbMgD7buu.jpg'),
       ('22222222-2222-2222-2222-222222222222', 'https://image.tmdb.org/t/p/original/zPZ4virixj8Rq8WqhxcarroDutk.jpg'),

       ('33333333-3333-3333-3333-333333333333', 'https://image.tmdb.org/t/p/original/nMKdUUepR0i5zn0y1T4CsSB5chy.jpg'),
       ('33333333-3333-3333-3333-333333333333', 'https://image.tmdb.org/t/p/original/plDp52MirFHc2PMJRMNWoG0kfr3.jpg'),
       ('33333333-3333-3333-3333-333333333333', 'https://image.tmdb.org/t/p/original/qejZvo4zzL6KQ74h2IVnfGDbeQj.jpg'),

       ('44444444-4444-4444-4444-444444444444', 'https://image.tmdb.org/t/p/original/h3HsfV8Kn9Sz2QWUYYdP5ya23hx.jpg'),
       ('44444444-4444-4444-4444-444444444444', 'https://image.tmdb.org/t/p/original/jKhsC3yguywiKF1XA6OlslFhlwT.jpg'),
       ('44444444-4444-4444-4444-444444444444', 'https://image.tmdb.org/t/p/original/qVgZu5BTx6pu4owCvVOm4zjTfOi.jpg'),

       ('55555555-5555-5555-5555-555555555555', 'https://image.tmdb.org/t/p/original/hZkgoQYus5vegHoetLkCJzb17zJ.jpg'),
       ('55555555-5555-5555-5555-555555555555', 'https://image.tmdb.org/t/p/original/yrN6gon5NG6t7Lgh05byChFSZem.jpg'),
       ('55555555-5555-5555-5555-555555555555', 'https://image.tmdb.org/t/p/original/8iVyhmjzUbvAGppkdCZPiyEHSoF.jpg'),

       ('66666666-6666-6666-6666-666666666666', 'https://image.tmdb.org/t/p/original/waCRuAW5ocONRehP556vPexVXA9.jpg'),
       ('66666666-6666-6666-6666-666666666666', 'https://image.tmdb.org/t/p/original/bFrAd2tAgmuS81EOSrNobqZ0eks.jpg'),
       ('66666666-6666-6666-6666-666666666666', 'https://image.tmdb.org/t/p/original/lrtSb1skJayPydZk0OSMAKjBOVe.jpg'),

       ('77777777-7777-7777-7777-777777777777', 'https://image.tmdb.org/t/p/original/hND7xAaxxBgaIspp9iMsaEXOSTz.jpg'),
       ('77777777-7777-7777-7777-777777777777', 'https://image.tmdb.org/t/p/original/jhk6D8pim3yaByu1801kMoxXFaX.jpg'),
       ('77777777-7777-7777-7777-777777777777', 'https://image.tmdb.org/t/p/original/aZtwH3RQ0L8jbInxr7OSc9tlGMJ.jpg');

-- Rooms
INSERT INTO room (id, name)
VALUES ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Sala A'),
       ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'Sala B');

-- Seats (wszystkie fotele dla sal A i B)
INSERT INTO seat (id, row_num, seat_num, reserved)
VALUES
-- Sala A (a000...)
('a0000000-0000-0000-0000-000000000001', 1, 1, false),
('a0000000-0000-0000-0000-000000000002', 1, 2, false),
('a0000000-0000-0000-0000-000000000003', 1, 3, false),
('a0000000-0000-0000-0000-000000000004', 1, 4, false),
('a0000000-0000-0000-0000-000000000005', 1, 5, false),
('a0000000-0000-0000-0000-000000000006', 1, 6, false),
('a0000000-0000-0000-0000-000000000007', 2, 1, false),
('a0000000-0000-0000-0000-000000000008', 2, 2, false),
('a0000000-0000-0000-0000-000000000009', 2, 3, false),
('a0000000-0000-0000-0000-000000000010', 2, 4, false),
('a0000000-0000-0000-0000-000000000011', 2, 5, false),
('a0000000-0000-0000-0000-000000000012', 2, 6, false),
('a0000000-0000-0000-0000-000000000013', 3, 1, false),
('a0000000-0000-0000-0000-000000000014', 3, 2, true),
('a0000000-0000-0000-0000-000000000015', 3, 3, true),
('a0000000-0000-0000-0000-000000000016', 3, 4, true),
('a0000000-0000-0000-0000-000000000017', 3, 5, false),
('a0000000-0000-0000-0000-000000000018', 3, 6, false),

-- Sala B (b000...)
('b0000000-0000-0000-0000-000000000001', 1, 1, false),
('b0000000-0000-0000-0000-000000000002', 1, 2, false),
('b0000000-0000-0000-0000-000000000003', 1, 3, false),
('b0000000-0000-0000-0000-000000000004', 1, 4, false),
('b0000000-0000-0000-0000-000000000005', 1, 5, false),
('b0000000-0000-0000-0000-000000000006', 1, 6, false),
('b0000000-0000-0000-0000-000000000007', 2, 1, false),
('b0000000-0000-0000-0000-000000000008', 2, 2, true),
('b0000000-0000-0000-0000-000000000009', 2, 3, true),
('b0000000-0000-0000-0000-000000000010', 2, 4, true),
('b0000000-0000-0000-0000-000000000011', 2, 5, false),
('b0000000-0000-0000-0000-000000000012', 2, 6, false),
('b0000000-0000-0000-0000-000000000013', 3, 1, false),
('b0000000-0000-0000-0000-000000000014', 3, 2, false),
('b0000000-0000-0000-0000-000000000015', 3, 3, false),
('b0000000-0000-0000-0000-000000000016', 3, 4, false),
('b0000000-0000-0000-0000-000000000017', 3, 5, false),
('b0000000-0000-0000-0000-000000000018', 3, 6, false);

-- Room_Seats (łączymy sale z fotelami, bez duplikatów)
-- Sala A
INSERT INTO room_seats (room_id, seats_id)
VALUES ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'a0000000-0000-0000-0000-000000000001'),
       ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'a0000000-0000-0000-0000-000000000002'),
       ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'a0000000-0000-0000-0000-000000000003'),
       ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'a0000000-0000-0000-0000-000000000004'),
       ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'a0000000-0000-0000-0000-000000000005'),
       ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'a0000000-0000-0000-0000-000000000006'),
       ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'a0000000-0000-0000-0000-000000000007'),
       ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'a0000000-0000-0000-0000-000000000008'),
       ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'a0000000-0000-0000-0000-000000000009'),
       ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'a0000000-0000-0000-0000-000000000010'),
       ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'a0000000-0000-0000-0000-000000000011'),
       ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'a0000000-0000-0000-0000-000000000012'),
       ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'a0000000-0000-0000-0000-000000000013'),
       ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'a0000000-0000-0000-0000-000000000014'),
       ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'a0000000-0000-0000-0000-000000000015'),
       ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'a0000000-0000-0000-0000-000000000016'),
       ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'a0000000-0000-0000-0000-000000000017'),
       ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'a0000000-0000-0000-0000-000000000018');

-- Sala B
INSERT INTO room_seats (room_id, seats_id)
VALUES ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'b0000000-0000-0000-0000-000000000001'),
       ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'b0000000-0000-0000-0000-000000000002'),
       ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'b0000000-0000-0000-0000-000000000003'),
       ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'b0000000-0000-0000-0000-000000000004'),
       ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'b0000000-0000-0000-0000-000000000005'),
       ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'b0000000-0000-0000-0000-000000000006'),
       ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'b0000000-0000-0000-0000-000000000007'),
       ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'b0000000-0000-0000-0000-000000000008'),
       ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'b0000000-0000-0000-0000-000000000009'),
       ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'b0000000-0000-0000-0000-000000000010'),
       ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'b0000000-0000-0000-0000-000000000011'),
       ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'b0000000-0000-0000-0000-000000000012'),
       ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'b0000000-0000-0000-0000-000000000013'),
       ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'b0000000-0000-0000-0000-000000000014'),
       ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'b0000000-0000-0000-0000-000000000015'),
       ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'b0000000-0000-0000-0000-000000000016'),
       ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'b0000000-0000-0000-0000-000000000017'),
       ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'b0000000-0000-0000-0000-000000000018');

-- Showtimes
INSERT INTO showtime (id, movie_id, room_id, start_time)
VALUES ('99999999-0000-0000-0000-000000000001', '11111111-1111-1111-1111-111111111111',
        'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '2026-01-27 18:00:00'),
       ('99999999-0000-0000-0000-000000000002', '33333333-3333-3333-3333-333333333333',
        'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '2026-01-26 20:30:00'),
       ('99999999-0000-0000-0000-000000000003', '22222222-2222-2222-2222-222222222222',
        'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', '2026-01-27 19:15:00'),
       ('99999999-0000-0000-0000-000000000004', '44444444-4444-4444-4444-444444444444',
        'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', '2026-01-28 18:00:00'),
       ('99999999-0000-0000-0000-000000000005', '55555555-5555-5555-5555-555555555555',
        'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '2026-01-28 21:00:00'),
       ('99999999-0000-0000-0000-000000000006', '66666666-6666-6666-6666-666666666666',
        'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', '2026-01-29 19:30:00'),
       ('99999999-0000-0000-0000-000000000007', '77777777-7777-7777-7777-777777777777',
        'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', '2026-01-30 20:00:00');

-- Reservations
INSERT INTO reservation (id, movie_name, start_time, total_price)
VALUES ('44444444-4444-4444-4444-444444444444', 'Interstellar', TIMESTAMP '2026-01-17 18:00:00', 40.00),
       ('55555555-5555-5555-5555-555555555555', 'Inception', TIMESTAMP '2026-01-18 19:15:00', 20.00);

INSERT INTO reservation_seats (reservation_id, seats)
VALUES ('44444444-4444-4444-4444-444444444444', 'a0000000-0000-0000-0000-000000000006'),
       ('44444444-4444-4444-4444-444444444444', 'a0000000-0000-0000-0000-000000000007'),
       ('55555555-5555-5555-5555-555555555555', 'b0000000-0000-0000-0000-000000000001');

-- Users
INSERT INTO users (id, username, password, roles)
VALUES ('eeeeeeee-eeee-eeee-eeee-eeeeeeeeeeee', 'admin', '$2a$12$VxQLnFp9t3ZBN.Lw5PuMVervJa2Mju9cFu3fhEtak88s6cEtBEsHi',
        'ROLE_ADMIN');
