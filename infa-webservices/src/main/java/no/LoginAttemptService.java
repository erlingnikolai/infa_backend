package no;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * @author Erling Nikolai Ukkelberg
 * @created 28/07/2021
 */
@Service
public class LoginAttemptService {
    private static final int MAX_ATTEMPT = 7;
    private final LoadingCache<String, Integer> attemptsCache;

    public LoginAttemptService() {
        attemptsCache = CacheBuilder.newBuilder().
                expireAfterWrite(1L, TimeUnit.DAYS).build(getCacheLoader());
    }

    private CacheLoader<String, Integer> getCacheLoader() {
        return new CacheLoader<>() {
            public Integer load(String key) {
                return 0;
            }
        };
    }

    void loginSucceeded(String key) {
        attemptsCache.invalidate(key);
    }

    void loginFailed(String key) {
        int attempts;
        try {
            attempts = attemptsCache.get(key);
        } catch (ExecutionException e) {
            attempts = 0;
        }
        attempts++;
        attemptsCache.put(key, attempts);
    }

    boolean isBlocked(String key) {
        return isAttemptsHigherThanAllowed(key);
    }

    private boolean isAttemptsHigherThanAllowed(String key) {
        try {
            return attemptsCache.get(key) >= MAX_ATTEMPT;
        } catch (ExecutionException ignored) {
            return false;
        }
    }
}