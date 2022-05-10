package deu.team.jsp.OneTimeKey;

import deu.team.jsp.account.domain.Role;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author 박성호
 * 일회용 토큰관련 서비스 정의
 */

@Service
@RequiredArgsConstructor
public class OneTimeKeyService {

    private final OneTimeKeyRepository oneTimeKeyRepository;

    /*
    일회용 6자리 암호화 키 생성
     */
//    @Transactional
    public void generateOneTimeKey(Role role) {
        String key = String.valueOf(ThreadLocalRandom.current().nextInt(100000, 1000000));
        System.out.println(key);
        OneTimeKey oneTimeKey = OneTimeKey.builder().passKey(key).role(role).build();
        Optional<OneTimeKey> keySet;
        if (oneTimeKeyRepository.findByRole(role).isEmpty()) {
            oneTimeKeyRepository.save(oneTimeKey);
        }
        else {
            keySet = oneTimeKeyRepository.findByRole(role);
            keySet.ifPresent(target -> {
                System.out.println(target.getId());
                target.setPassKey(key);
                oneTimeKeyRepository.save(target);
            });
        }
    }

    public boolean checkOneTimeKey(Role role){
        Optional<OneTimeKey> keySet = oneTimeKeyRepository.findByRole(role);
        return keySet.isPresent();
    }

    public String getOneTimeKey(Role role){
        Optional<OneTimeKey> keySet = oneTimeKeyRepository.findByRole(role);
        if(keySet.isEmpty())
            return null;
        return keySet.get().getPassKey();
    }
}
