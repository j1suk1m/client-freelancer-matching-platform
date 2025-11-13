package com.example.contractservice.settlement.service.batch.reader;

import static java.time.ZoneOffset.UTC;

import com.example.contractservice.settlement.entity.SettlementEntity;
import jakarta.persistence.EntityManagerFactory;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Map;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@StepScope
public class SettlementDataReader extends JpaCursorItemReader<SettlementEntity> {

    private static final long MONTH_INTERVAL = 1L;

    public SettlementDataReader(EntityManagerFactory emFactory,
            @Value("#{jobParameters['dateStr']}") String dateStr,
            @Value("${batch.settlement.size}") int fetchSize) {

        setEntityManagerFactory(emFactory);

        setQueryString("""
        SELECT
            s
        FROM
            SettlementEntity s
        WHERE
            s.progressingAt >= :startDate AND s.progressingAt < :endDate
        """); // 쿼리 설정

        setHintValues(Map.of("org.hibernate.fetchSize", fetchSize)); // 하이버네이트에서 DB 레코드를 한 번에 가져오는 사이즈

        Instant curTime = Instant.parse(dateStr);
        LocalDate endDate = LocalDate.ofInstant(curTime, UTC); // Instant -> Date(년-월-일)
        LocalDate startDate = endDate.minusMonths(MONTH_INTERVAL);

        Map<String, Object> paramMap = Map.of("startDate", startDate, "endDate", endDate);
        setParameterValues(paramMap);
    }
}
