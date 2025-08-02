package dev.ihorshulha.externalapiintegration.service;

import dev.ihorshulha.externalapiintegration.api.CatsApi;
import dev.ihorshulha.externalapiintegration.dto.CatDto;
import dev.ihorshulha.externalapiintegration.exceptionhandler.ExternalApiUnauthorizedException;
import dev.ihorshulha.externalapiintegration.mapper.CatMapper;
import feign.FeignException;
import io.github.resilience4j.ratelimiter.RateLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class CatService {

    private final CatMapper catMapper;
    private final CatsApi catsApi;
    private final RateLimiter rateLimiter;

    public List<CatDto> getAll() {
        Supplier<List<CatDto>> catsSupplier = () -> {
            try {
                var response = catsApi.getCats();
                var externalCats = response.getBody();
                return catMapper.toDtos(externalCats);
            } catch (FeignException.Unauthorized e) {
                throw new ExternalApiUnauthorizedException("Missing or incorrect api key");
            }
        };
        return RateLimiter
                .decorateSupplier(rateLimiter, catsSupplier)
                .get();
    }

    public CatDto getById(final Long id) {
        Supplier<CatDto> catSupplier = () -> {
            try {
                var response = catsApi.getCatById(id);
                var externalCat = response.getBody();
                return catMapper.toDto(externalCat);
            } catch (FeignException.Unauthorized e) {
                throw new ExternalApiUnauthorizedException("Missing or incorrect api key");
            }
        };
        return RateLimiter
                .decorateSupplier(rateLimiter, catSupplier)
                .get();
    }
}
