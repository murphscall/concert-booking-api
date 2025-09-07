-- -- H2 데이터베이스용 SQL 문법입니다.
--
--
-- INSERT INTO USERS (email, password, nickname, role, created_at, updated_at)
-- VALUES ('testuser@example.com', '$2a$10$vQ5z7Z.7Y.N0.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a', '테스트유저', 'USER', NOW(),
--         NOW());
--
--
-- -- === 콘서트 데이터 생성 (ID=1) ===
-- INSERT INTO CONCERTS (name, ticket_limit, concert_venue, concert_date, created_at, updated_at)
-- VALUES ('아이유 월드투어 HER in Seoul', 100, '서울 고척스카이돔', '2025-12-24T19:00:00', NOW(), NOW());
--
-- -- === 티켓 데이터 100개 생성 (Concert ID=1) ===
--
-- -- VIP 등급 (20개)
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'VIP-A-1', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'VIP-A-2', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'VIP-A-3', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'VIP-A-4', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'VIP-A-5', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'VIP-A-6', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'VIP-A-7', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'VIP-A-8', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'VIP-A-9', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'VIP-A-10', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'VIP-B-1', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'VIP-B-2', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'VIP-B-3', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'VIP-B-4', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'VIP-B-5', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'VIP-B-6', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'VIP-B-7', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'VIP-B-8', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'VIP-B-9', 'BOOKED', 'VIP', 220000.00, NOW(), NOW()); -- 테스트용 예매 완료 좌석
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'VIP-B-10', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
--
-- -- R 등급 (30개)
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'R-C-1', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'R-C-2', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'R-C-3', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'R-C-4', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'R-C-5', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'R-C-6', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'R-C-7', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'R-C-8', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'R-C-9', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'R-C-10', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'R-D-1', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'R-D-2', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'R-D-3', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'R-D-4', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'R-D-5', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'R-D-6', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'R-D-7', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'R-D-8', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'R-D-9', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'R-D-10', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'R-E-1', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'R-E-2', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'R-E-3', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'R-E-4', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'R-E-5', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'R-E-6', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'R-E-7', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'R-E-8', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'R-E-9', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'R-E-10', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
--
-- -- S 등급 (50개)
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-F-1', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-F-2', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-F-3', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-F-4', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-F-5', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-F-6', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-F-7', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-F-8', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-F-9', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-F-10', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-G-1', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-G-2', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-G-3', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-G-4', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-G-5', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-G-6', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-G-7', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-G-8', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-G-9', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-G-10', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-H-1', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-H-2', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-H-3', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-H-4', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-H-5', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-H-6', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-H-7', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-H-8', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-H-9', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-H-10', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-I-1', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-I-2', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-I-3', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-I-4', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-I-5', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-I-6', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-I-7', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-I-8', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-I-9', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-I-10', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-J-1', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-J-2', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-J-3', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-J-4', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-J-5', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-J-6', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-J-7', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-J-8', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-J-9', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
-- INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
-- VALUES (1, 'S-J-10', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());

-- MySQL 데이터베이스용 SQL 문법입니다.

-- === 사용자 데이터 생성 (ID=1) ===
INSERT INTO USERS (email, password, nickname, role, created_at, updated_at)
VALUES ('testuser@example.com', '$2a$10$vQ5z7Z.7Y.N0.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a.a', '테스트유저', 'USER', NOW(),
        NOW());

-- === 콘서트 데이터 생성 (ID=1) ===
INSERT INTO CONCERTS (name, ticket_limit, concert_venue, concert_date, created_at, updated_at)
VALUES ('아이유 월드투어 HER in Seoul', 100, '서울 고척스카이돔', '2025-12-24 19:00:00', NOW(), NOW());

-- === 티켓 데이터 100개 생성 (Concert ID=1) ===

-- VIP 등급 (20개)
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'VIP-A-1', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'VIP-A-2', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'VIP-A-3', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'VIP-A-4', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'VIP-A-5', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'VIP-A-6', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'VIP-A-7', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'VIP-A-8', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'VIP-A-9', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'VIP-A-10', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'VIP-B-1', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'VIP-B-2', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'VIP-B-3', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'VIP-B-4', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'VIP-B-5', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'VIP-B-6', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'VIP-B-7', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'VIP-B-8', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'VIP-B-9', 'BOOKED', 'VIP', 220000.00, NOW(), NOW()); -- 테스트용 예매 완료 좌석
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'VIP-B-10', 'AVAILABLE', 'VIP', 220000.00, NOW(), NOW());

-- R 등급 (30개)
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'R-C-1', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'R-C-2', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'R-C-3', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'R-C-4', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'R-C-5', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'R-C-6', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'R-C-7', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'R-C-8', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'R-C-9', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'R-C-10', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'R-D-1', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'R-D-2', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'R-D-3', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'R-D-4', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'R-D-5', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'R-D-6', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'R-D-7', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'R-D-8', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'R-D-9', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'R-D-10', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'R-E-1', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'R-E-2', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'R-E-3', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'R-E-4', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'R-E-5', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'R-E-6', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'R-E-7', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'R-E-8', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'R-E-9', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'R-E-10', 'AVAILABLE', 'R', 180000.00, NOW(), NOW());

-- S 등급 (50개)
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-F-1', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-F-2', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-F-3', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-F-4', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-F-5', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-F-6', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-F-7', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-F-8', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-F-9', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-F-10', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-G-1', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-G-2', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-G-3', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-G-4', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-G-5', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-G-6', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-G-7', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-G-8', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-G-9', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-G-10', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-H-1', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-H-2', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-H-3', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-H-4', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-H-5', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-H-6', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-H-7', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-H-8', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-H-9', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-H-10', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-I-1', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-I-2', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-I-3', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-I-4', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-I-5', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-I-6', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-I-7', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-I-8', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-I-9', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-I-10', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-J-1', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-J-2', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-J-3', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-J-4', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-J-5', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-J-6', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-J-7', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-J-8', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-J-9', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
INSERT INTO TICKETS (concert_id, seat_number, status, grade, price, created_at, updated_at)
VALUES (1, 'S-J-10', 'AVAILABLE', 'S', 150000.00, NOW(), NOW());
