-- ==========================================
-- 1. UUID 변수 사전 생성 (모든 관계 정의)
-- ==========================================
-- Member용 UUID
SET @member_uuid_1 = RANDOM_UUID();
SET @member_uuid_2 = RANDOM_UUID();

-- Payment & Order 연결용 PG ID UUID
SET @pg_uuid_1 = RANDOM_UUID();
SET @pg_uuid_2 = RANDOM_UUID();


-- ==========================================
-- 2. Members 테이블 일괄 INSERT
-- ==========================================
INSERT INTO members (
    code, created_at, updated_at, is_deleted,
    name, email, phone_number, birth_date, gender,
    provider, provider_id, can_work
) VALUES
      (
          @member_uuid_1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE,
          '홍길동', 'hong@test.com', '010-1234-5678', '1990-05-05', 'MALE',
          'EMAIL', 'provider_id_001', TRUE
      ),
      (
          @member_uuid_2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE,
          '김철수', 'kim@test.com', '010-9876-5432', '1995-10-20', 'FEMALE',
          'GOOGLE', 'google_12345', TRUE
      );


-- ==========================================
-- 3. Payments 테이블 일괄 INSERT
-- ==========================================
INSERT INTO payments (
    code, is_deleted, created_at, updated_at,
    order_pg_id, payment_key, amount, payment_status
) VALUES
      (
          RANDOM_UUID(), FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
          @pg_uuid_1, 'toss_pay_key_001', 50000, 'COMPLETED'
      ),
      (
          RANDOM_UUID(), FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
          @pg_uuid_2, NULL, 12000, 'PENDING'
      );


-- ==========================================
-- 4. Orders 테이블 일괄 INSERT
-- ==========================================
-- 미리 생성해둔 Member UUID와 PG UUID를 조합하여 관계를 맺습니다.
INSERT INTO orders (
    code, is_deleted, created_at, updated_at,
    member_code, order_pg_id
) VALUES
      (
          RANDOM_UUID(), FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
          @member_uuid_1, @pg_uuid_1
      ),
      (
          RANDOM_UUID(), FALSE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP,
          @member_uuid_2, @pg_uuid_2
      );