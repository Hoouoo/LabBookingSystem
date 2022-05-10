package deu.team.jsp.OneTimeKey;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class OneTimeKeyService {

    private final OneTimeKeyRepository oneTimeKeyRepository;

    /*
    일회용 6자리 암호화 키 생성
     */
//    @Transactional
    public String generateOneTimeKey() {
        String key = String.valueOf(ThreadLocalRandom.current().nextInt(100000, 1000000));
        System.out.println(key);
        OneTimeKey oneTimeKey = OneTimeKey.builder().passKey(key).build();
        Optional<OneTimeKey> keySet = oneTimeKeyRepository.findById(1L);
        if (keySet.isEmpty()) {
            return oneTimeKeyRepository.save(oneTimeKey).getPassKey();
        } else {
            keySet.ifPresent(target -> {
                System.out.println(target.getId());
                target.setPassKey(key);
                oneTimeKeyRepository.save(target);
            });
        }
        return keySet.get().getPassKey();
    }

    public boolean checkOneTimeKey(){
        Optional<OneTimeKey> keySet = oneTimeKeyRepository.findById(1L);
        return keySet.isPresent();
    }

    public String getOneTimeKey(){
        Optional<OneTimeKey> keySet = oneTimeKeyRepository.findById(1L);
        if(keySet.isEmpty())
            return null;
        return keySet.get().getPassKey();
    }
}
