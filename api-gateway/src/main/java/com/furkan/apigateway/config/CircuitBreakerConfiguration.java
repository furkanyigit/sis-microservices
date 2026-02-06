package com.furkan.apigateway.config;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.core.registry.EntryAddedEvent;
import io.github.resilience4j.core.registry.RegistryEventConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CircuitBreakerConfiguration {

    private static final Logger logger = LoggerFactory.getLogger(CircuitBreakerConfiguration.class);

    @Bean
    public RegistryEventConsumer<CircuitBreaker> myRegistryEventConsumer() {
        return new RegistryEventConsumer<CircuitBreaker>() {

            @Override
            public void onEntryAddedEvent(EntryAddedEvent<CircuitBreaker> entryAddedEvent) {
                // Sigorta oluşturulduğunda bu olay dinleyiciyi ona ekle
                entryAddedEvent.getAddedEntry().getEventPublisher()
                        .onStateTransition(event -> {
                            // DURUM DEĞİŞİKLİKLERİNİ YAKALA

                            // 1. Sigorta ATTI (OPEN)
                            if (event.getStateTransition().getToState() == CircuitBreaker.State.OPEN) {
                                logger.error("🚨🚨🚨 DİKKAT! SİGORTA ATTI (OPEN) 🚨🚨🚨");
                                logger.error("Servis: {}", event.getCircuitBreakerName());
                                logger.error("Sebep: Sistem aşırı yüklendi veya hata oranı arttı.");
                            }

                            // 2. Sigorta DÜZELDİ (CLOSED)
                            else if (event.getStateTransition().getToState() == CircuitBreaker.State.CLOSED) {
                                logger.info("✅✅✅ SİGORTA DÜZELDİ (CLOSED) ✅✅✅");
                                logger.info("Servis: {}", event.getCircuitBreakerName());
                                logger.info("Artık istekler normal şekilde iletiliyor.");
                            }

                            // 3. Sigorta TEST EDİYOR (HALF_OPEN)
                            else if (event.getStateTransition().getToState() == CircuitBreaker.State.HALF_OPEN) {
                                logger.warn("🟡 SİGORTA KONTROL EDİYOR (HALF_OPEN) 🟡");
                                logger.warn("Servis: {}", event.getCircuitBreakerName());
                                logger.warn("Birkaç istek gönderilip düzelip düzelmediğine bakılacak...");
                            }
                        });
            }

            @Override
            public void onEntryRemovedEvent(io.github.resilience4j.core.registry.EntryRemovedEvent<CircuitBreaker> entryRemoveEvent) {
                // Gerekirse burası doldurulabilir
            }

            @Override
            public void onEntryReplacedEvent(io.github.resilience4j.core.registry.EntryReplacedEvent<CircuitBreaker> entryReplacedEvent) {
                // Gerekirse burası doldurulabilir
            }
        };
    }
}