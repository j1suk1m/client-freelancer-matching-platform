CREATE TABLE IF NOT EXISTS `payments` (
      `id` BIGINT NOT NULL,
      `member_code` CHAR(36) NOT NULL,
      `code` CHAR(36) NOT NULL,
      `created_at` TIMESTAMP NOT NULL,
      `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
      `is_deleted` BOOLEAN NOT NULL,
      `total_amount` BIGINT NOT NULL,
      `payment_key` VARCHAR(255) NOT NULL,
      `pg_order_id` VARCHAR(255) NOT NULL,
      `status` VARCHAR(20) NOT NULL,
      PRIMARY KEY (`id`)
);