package deu.team.jsp.admin.warning;

import deu.team.jsp.account.AccountRepository;
import deu.team.jsp.account.domain.Account;
import deu.team.jsp.account.domain.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j

public class WarningService {

    private final WarningRepository warningRepository;
    private final AccountRepository accountRepository;

    public List<Account> getAllStudentList(){
        return accountRepository.findAllByRole(Role.STUDENT);
    }

    public void giveWarning(String studentId){

        Optional<Warning> target = warningRepository.findByStudentId(studentId);
        if (target.isPresent()) {
            int totalWarningCnt = target.get().getWarningCnt() + 1;
            if (totalWarningCnt < 3) {
                target.get().changeWarningCnt(totalWarningCnt);
                warningRepository.save(target.get());
            }else if (totalWarningCnt == 4){
                log.info("totalWarningCnt : " + totalWarningCnt);
                // TODO 예약 하지 못하도록 수정해야함
                accountRepository.findByWarning_Id(target.get().getId()).ifPresent(
                        targetWarningObj -> {
                            targetWarningObj.setBookStatus(2);
                            accountRepository.save(targetWarningObj);
                        }
                );

            }

        }else{
            accountRepository.getAccountByStudentId(studentId).ifPresent(
                    targetAccount ->{
                        Warning targetWarning = Warning.builder().studentId(studentId).build();
                        warningRepository.save(targetWarning);
                        targetAccount.setWarning(targetWarning);
                        accountRepository.save(targetAccount);
                    }
            );
        }
    }

    public void resetWarning(String studentId){
        Optional<Warning> targetWarning = warningRepository.findByStudentId(studentId);
        targetWarning.ifPresent(target->{
            target.changeWarningCnt(0);
            accountRepository.findByWarning_Id(target.getId()).ifPresent(
                    targetAccountObj ->{
                        targetAccountObj.setBookStatus(0);
                        accountRepository.save(targetAccountObj);
                    }
            );
            warningRepository.save(target);
        });
    }
}


