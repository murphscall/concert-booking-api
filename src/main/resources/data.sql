-- src/main/resources/data.sql

-- 참고: created_at, updated_at은 JPA Auditing 기능으로 자동 생성되므로 쿼리에선 제외했습니다.
-- id 또한 auto-increment이므로 제외합니다.
INSERT INTO concerts (name, concert_venue, concert_date, ticket_limit, created_at, updated_at)
VALUES ('아이유 ''H.E.R.'' 월드 투어', '서울월드컵경기장', '2025-10-18 19:00:00', 50000, NOW(), NOW());

INSERT INTO concerts (name, concert_venue, concert_date, ticket_limit, created_at, updated_at)
VALUES ('싸이 흠뻑쇼 2025', '잠실종합운동장 주경기장', '2025-08-15 18:30:00', 60000, NOW(), NOW());

INSERT INTO concerts (name, concert_venue, concert_date, ticket_limit, created_at, updated_at)
VALUES ('성시경 ''축가'' 콘서트', '올림픽공원 KSPO DOME', '2025-05-25 18:00:00', 15000, NOW(), NOW());