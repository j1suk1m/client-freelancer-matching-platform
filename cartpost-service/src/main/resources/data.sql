-- commissions 테이블
MERGE INTO "commissions" KEY("id") VALUES
                                       (1, RANDOM_UUID(), 'c-1111-uuid', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 'Commission A', 'Content for Commission A', 'MONTHLY', 100000, CURRENT_TIMESTAMP, DATEADD('DAY', 30, CURRENT_TIMESTAMP), TRUE, 'Alice'),
                                       (2, RANDOM_UUID(), 'c-2222-uuid', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 'Commission B', 'Content for Commission B', 'PER_JOB', 200000, CURRENT_TIMESTAMP, DATEADD('DAY', 15, CURRENT_TIMESTAMP), TRUE, 'Bob'),
                                       (3, RANDOM_UUID(), 'c-3333-uuid', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, 'Commission C', 'Content for Commission C', 'MONTHLY', 150000, CURRENT_TIMESTAMP, DATEADD('DAY', 60, CURRENT_TIMESTAMP), FALSE, 'Charlie');

-- commissions_tags 테이블
MERGE INTO "commissions_tags" KEY("id") VALUES
                                            (1, 'c-1111-uuid', RANDOM_UUID()),
                                            (2, 'c-2222-uuid', RANDOM_UUID()),
                                            (3, 'c-3333-uuid', RANDOM_UUID());

-- carts 테이블
MERGE INTO "carts" KEY("id") VALUES
                                 (1, 'cart-1111-uuid', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, RANDOM_UUID()),
                                 (2, 'cart-2222-uuid', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, RANDOM_UUID()),
                                 (3, 'cart-3333-uuid', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, FALSE, RANDOM_UUID());

-- cart_items 테이블
MERGE INTO "cart_items" KEY("id") VALUES
                                      (1, RANDOM_UUID(), 'cart-1111-uuid', 'ACTIVE', CURRENT_TIMESTAMP, DATEADD('DAY', 30, CURRENT_TIMESTAMP), 'MONTHLY', '50000'),
                                      (2, RANDOM_UUID(), 'cart-2222-uuid', 'PENDING', CURRENT_TIMESTAMP, DATEADD('DAY', 15, CURRENT_TIMESTAMP), 'ONE_TIME', '150000'),
                                      (3, RANDOM_UUID(), 'cart-3333-uuid', 'CANCELLED', CURRENT_TIMESTAMP, DATEADD('DAY', 45, CURRENT_TIMESTAMP), 'MONTHLY', '75000');
